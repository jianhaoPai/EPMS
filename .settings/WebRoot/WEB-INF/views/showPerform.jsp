<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showPerform.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="layui/css/layui.css"  media="all">
  </head>
  
  <body>
  <blockquote class="layui-elem-quote layui-text">
      <h3>- 查询个人绩效 -</h3>
    </blockquote>
    <!-- 员工的绩效页面 -->
    <table class="layui-table" id="table" lay-filter="testForm" border="5px"></table>
 
<script src="layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['jquery','table','layer','form'],function(){
	var $=layui.$;
	var table=layui.table;
	var layer=layui.layer;
	var form=layui.form;
	
	table.render({
		elem:'#table'
		,url:'showAllPerform'
		,cellMinWidth:80
		,cols:[[
			{type:'numbers',title:'序号',fixed:'left',align:'center'}
			,{field:'score',title:'成绩',align:'center'}
			,{field:'type',title:'类型',align:'center'}
			,{field:'assessDate',title:'绩效周期',align:'center',sort:'true'}
			,{field:'name',title:'审核人',align:'center'}
			,{field:'apprivalDate',title:'审核时间',align:'center'}
			,{field:'state',title:'状态',align:'center'}
		]]
		,page:true
		,limit:10
		,limits:[10,20,30,50]
		,text:{
			none:'暂无绩效评价记录'
		}
	});
});
</script>
  </body>
</html>
