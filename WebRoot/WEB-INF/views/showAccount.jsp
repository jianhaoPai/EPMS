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
    <h3>- 系统用户 -</h3>
  </blockquote>
  <div class="layui-row" style="margin-top: 20px;">
      <div class="layui-col-md12">
          <button id="addAccountBtn" class="layui-btn layui-btn-normal">
              <i class="layui-icon">&#xe654;</i>添加用户
          </button>
      </div>
  </div>
  
  <br>
  <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
	  搜索用户：
	  <div class="layui-inline">
	    <input class="layui-input" name="jobId" id="jobIdReload" autocomplete="off" placeholder="输入工号查询" />
	  </div>
	  <div class="layui-inline">
	    <input class="layui-input" name="name" id="nameReload" autocomplete="off" placeholder="输入姓名查询" />
	  </div>
      <div class="layui-inline">
        <select name="departmentName" id="departmentNameReload">
          <option value="">请选择部门</option>
          <option value="开发部">开发部</option>
          <option value="产品部">产品部</option>
          <option value="管理层">管理层</option>
        </select>
      </div>

	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>
  
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<!-- 需要弹出的添加账号界面 -->
<div class="layui-row" id="addAccount" style="display:none;">
	<div class="layui-col-md10">
		<form  class="layui-form" id="addAccountForm" method="post">
			<div class="layui-form-item"><br>
				<label class="layui-form-label">工号：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="jobId" id="jobId" name="jobId" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="required" id="name" name="name" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
		        <label class="layui-form-label">部门：</label>
		        <div class="layui-input-block">
		          <select id="denameId" lay-verify="required" lay-filter="denameType">
		            <option value="1">开发部</option>
		            <option value="2">产品部</option>
		          </select>
		        </div>
		    </div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="password" id="password" name="password" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<div align="center">
					<input type="button" lay-submit id="submitAccount" value="提交"  class="layui-btn layui-btn-normal"/>
					<input type="reset" value="重置" class="layui-btn layui-btn-normal"/>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
</script>

<script src="layui/layui.js" charset="utf-8"></script>
<script>
//JavaScript代码区域

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			
            table.render({
            elem: '#table'  //绑定table id
            ,url:'showAllUser'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'jobId', title:'工号',align: 'center',sort:'true'}
                  ,{field:'name',title:'姓名',align:'center'}
                  ,{field:'dename',title:'部门',align:'center',sort:'true'}
                  ,{field:'ocname',title:'职位',align:'center',sort:'true'}

                  ,{field:'state', title:'就职情况',align: 'center'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
          });
          
          var $ = layui.$, active = {
		    reload: function(){
		      var jobIdReload = $('#jobIdReload').val();
		      var nameReload = $('#nameReload').val();
		      var departmentNameReload=$('#departmentNameReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            jobId: jobIdReload,
		            name:nameReload,
		            departmentName:departmentNameReload
		        }
		      });
		    }
		  };
          
          $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
          
          $("#addAccountBtn").click(function(){
            layer.open({
              type:1,
              title:"添加账号",
              skin:"myclass",
              area:["30%"],
              content:$("#addAccount")
            });
            /*渲染表单*/
            form.render();            
          });
          
          form.verify({
          	jobId:[
          		/^[\S]{3}$/
          		,'工号必须为3位'
          	]
          	,password:[
          		/^[\S]{6,12}$/
          		,'密码必须为6到12位，且不能出现空格'
          	]
          });
          
          $(document).on('click','#submitAccount',function(){
			    var jobId=document.getElementById("jobId").value;
			    var name=document.getElementById("name").value;
			    var denameId=document.getElementById("denameId").value;
				var password=document.getElementById("password").value;
				
				$.ajax({
					url:'addAccount',
					data:{
						jobId:jobId,
						name:name,
						denameId:denameId,
						password:password
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result){
						if(result.status){
							layer.alert(result.msg,function(){
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
							});	
						}else{
							layer.alert(result.msg,function(){
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
		            title:"修改账号",
		            skin:"myclass",
		            area:["38%","55%"],
		            content:["requestPage?page=editAccount","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        $(inputList[0]).val(data.jobId); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(inputList[1]).val(data.password);
		            },
		        });
		        /*渲染表单*/
		        form.render(); 
			    }
			});
			
			
          
});




  



</script>
</body>
</html>