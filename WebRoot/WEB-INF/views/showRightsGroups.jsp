<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试的用户展示页</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">

</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 查看权限组 -</h3>
  </blockquote>
  <div class="layui-row" style="margin-top: 20px;">
      <div class="layui-col-md12">
          <button id="addBtn" class="layui-btn layui-btn-normal">
              <i class="layui-icon">&#xe654;</i>添加权限组
          </button>
      </div>
  </div>
  
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>




<!-- 需要弹出的添加权限组界面 -->
<div class="layui-row" >
	<div class="layui-col-md10" id="addRightsGroups" style="display:none;">
		<form  class="layui-form" id="addForm" method="post">
			<div class="layui-form-item"><br>
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="required" id="name" name="name" class="layui-input" placeholder="输入权限组的名称"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">所属人员：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="required" id="content" name="content" class="layui-input" placeholder="输入对该权限组的成员描述"/>
				</div>
			</div>
			<div class="layui-form-item">
				<div align="center">
					<input type="button" lay-submit id="submitBtn" value="提交"  class="layui-btn layui-btn-normal"/>
					<input type="reset" value="重置" class="layui-btn layui-btn-normal"/>
				</div>
			</div>
		</form>
	</div>
</div>

<div style="display:none;">
	<input type="text" id="aaa" />
</div>

<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
  <button class="layui-btn layui-btn-sm" lay-event="look">查看人员</button>
  <button class="layui-btn layui-btn-sm" lay-event="lookRights">查看权限</button>
</script>

<script type="text/html" id="peoplebarDemo">
  <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
</script>

<script src="layui/layui.js" charset="utf-8"></script>
<script>
//JavaScript代码区域

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $ = layui.$
            table.render({
            elem: '#table'  //绑定table id
            ,url:'showRightsGroups'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'name',title:'名称',align:'center'}
                  ,{field:'content',title:'人员',align:'center'}
                  ,{field:'createName', title:'创建人',align: 'center'}
                  ,{field:'createDate', title:'创建时间',align: 'center'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
          });

		  $("#addBtn").click(function(){
            layer.open({
              type:1,
              title:"添加权限组",
              skin:"myclass",
              area:["30%","35%"],
              content:$("#addRightsGroups")
            });
            /*渲染表单*/
            form.render();            
          });
          
          $(document).on('click','#submitBtn',function(){
	        var name=document.getElementById("name").value;
	        var content=document.getElementById("content").value;
				$.ajax({
					url:'addRightsGroups',
					data:{
						name:name,
						content:content
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result){
						if(result){
							layer.alert("添加成功",function(){
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
							});	
						}else{
							layer.alert("权限组已存在",function(){
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
							});	
						}
					}
					
				});
			});
			
			table.on('tool(testForm)',function(obj){
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var tr = obj.tr; //获得当前行 tr 的DOM对象
				editList=[];
				$.each(data,function(name,value){
				    editList.push(value);
				});
			    if(layEvent==='update'){
			    	layer.open({
		            type:2,
		            title:"修改权限组",
		            skin:"myclass",
		            area:["38%","45%"],
		            content:["requestPage?page=editRightsGroups","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        $(inputList[0]).val(data.name); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(inputList[1]).val(data.content);
                        $(inputList[3]).val(data.name);
		            },
		        });
		        /*渲染表单*/
		        form.render();
			    }
			    
			    if(layEvent==='look'){
			    //将值放在本页的input中
			     $("#aaa").val(data.id);
			    	layer.open({
		            type:2,
		            title:"查询权限组用户",
		            skin:"myclass",
		            area:["90%","90%"],
		            content:["requestPage?page=showRightsGroupsPeople","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
						

		            }
		        });

			    }
			    
			    if(layEvent==='lookRights'){
			    //将值放在本页的input中
			     $("#aaa").val(data.id);
			    	layer.open({
		            type:2,
		            title:"查看该权限组权限",
		            skin:"myclass",
		            area:["90%","90%"],
		            content:["requestPage?page=showGroupsRights","yes"],
		            
		        });

			    }
			});
			
			
			
			
          
});




  



</script>
</body>
</html>