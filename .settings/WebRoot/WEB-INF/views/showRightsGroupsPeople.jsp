<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限组用户</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>

<div class="layui-row" style="margin-top: 20px;">
    <div class="layui-col-md12">
        <button id="addBtn" class="layui-btn layui-btn-normal">
            <i class="layui-icon">&#xe654;</i>添加用户
        </button>
    </div>
</div>
  
<table class="layui-table" id="table" border="5px" lay-filter="peopleForm"></table>


<div style="display:none;">
	<input type="text" id="aaa" />
</div>

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
			var $ = layui.$; 
			var id=parent.layui.$("#aaa").val();
			
            table.render({
            elem: '#table'  //绑定table id
            ,url:'showRightsGroupsPeople?id='+id  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
            	   ,{field:'rName',title:'权限组名称',align:'center'}
                  ,{field:'jobId', title:'工号',align: 'center',sort:'true'}
                  ,{field:'name',title:'姓名',align:'center'}
                  ,{field:'dename',title:'部门',align:'center',sort:'true'}
                  ,{field:'ocname',title:'职位',align:'center',sort:'true'}
                  ,{fixed:'right',title:'操作',toolbar:'#peoplebarDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
          });

			$("#addBtn").click(function(){
			$("#aaa").val(id);
            layer.open({
	            type:2,
	            title:"",
	            skin:"myclass",
	            area:["95%","95%"],
	            content:["requestPage?page=addRightsPeople","yes"],
	            end:function(){
	            	window.location.reload();
	            }
		        });
            /*渲染表单*/
            
            form.render();
          });
			
			table.on('tool(peopleForm)',function(obj){
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			if(layEvent==='delete'){
			layer.confirm('确定要删除吗？',{
			  btn:['确定','取消'],
			  title:"系统提示"
			  },function(index){
				$.ajax({
					url:'deleteRightsGroupsPeople',
					data:{
						jobId:data.jobId,
						rName:data.rName
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result){
						if(result){
							location.reload()
						}else{
								layer.msg("删除失败")
						}
					}
					
				});
				}
				);
				}

			});
          
});




  



</script>
</body>
</html>