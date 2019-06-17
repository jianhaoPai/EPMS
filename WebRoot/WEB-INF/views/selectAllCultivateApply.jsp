<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>审核部门经理申请的培训 </title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 审核部门经理申请的培训 -</h3>
  </blockquote>

<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>


<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="update">查看详情</button>
</script>

<script src="layui/layui.all.js" charset="utf-8"></script>
<script>
//JavaScript代码区域

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
            table.render({
            elem: '#table'  //绑定table id
            ,url:'CultivateApplyController/selectAllCultivateApply'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'write_id', title:'填写人工号',align: 'center',sort:'true'}
                  ,{field:'name', title:'填写人姓名',align: 'center',sort:'true'}
                  ,{field:'train_name', title:'培训名称',align: 'center',sort:'true'}
                  ,{field:'face_people', title:'面对人员',align: 'center',sort:'true'}
                  /* ,{field:'department_name', title:'培训部门',align: 'center',sort:'true'}  */         
                  ,{field:'sum',title:'人数',align:'center'} 
                  ,{field:'status', title:'状态',align: 'center'}
                  ,{fixed:'right',title:'查看',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
           ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
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
						if(result){
							layer.alert("添加成功",function(){
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
							});	
						}else{
							layer.alert("账号已存在",function(){
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
		            title:"培训审核",
		            skin:"myclass",
		            area:["35%","45%"],
		            content:["requestPage?page=detailSelectAllCultivateApply","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        var textList = body.find("textarea");
                        $(inputList[0]).val(data.id);
                        $(inputList[1]).val(data.start_date);
                        $(inputList[2]).val(data.finish_date);
                        $(textList[0]).val(data.introduce); //遍历子窗口的input标签，将之前数组中的值一次放入显示
		            },
		            end:function(result){
		              window.location.reload();//刷新父页面
		            }
		        });
		        /*渲染表单*/
		        form.render(); 
			    }
			});
});		
</script>
</body>
</html>
