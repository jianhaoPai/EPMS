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

<div class="layui-row" style="margin-top: 20px;">
    <div class="layui-col-md12">
        <button id="addBtn" class="layui-btn layui-btn-normal">
            <i class="layui-icon">&#xe654;</i>添加权限
        </button>
    </div>
</div>

<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<div style="display:none;">
<input type="text" id="aaa" />
</div>

<script type="text/html" id="barDemo">
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
			var id=parent.layui.$("#aaa").val();
			
            table.render({
            elem: '#table'  //绑定table id
            ,url:'showGroupsRights?id='+id  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'menu_name',title:'名称',align:'center',width:'250'}
                  ,{field:'mark',title:'备注',align:'center',width:'500'}
                  ,{field:'pare_name', title:'所属管理',align: 'center',width:'250'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo',width:'100'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'
            ,text:{
				none:'暂时没有权限'
			}
          });

		  $("#addBtn").click(function(){
		  $("#aaa").val(id);
            layer.open({
              type:2,
              title:"添加权限",
              skin:"myclass",
              area:["90%","90%"],
              content:["requestPage?page=addGroupsRights","yes"],
	          end:function(){
	          	window.location.reload();
	          }
            });
         
          });
          
          table.on('tool(testForm)',function(obj){
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			if(layEvent==='delete'){
			layer.confirm('确定要删除吗？',{
			  btn:['确定','取消'],
			  title:"系统提示"
			  },function(index){
				$.ajax({
					url:'deleteGroupsRights',
					data:{
						menuId:data.menuId,
						id:id
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