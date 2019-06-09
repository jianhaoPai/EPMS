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
      <h3>- 查询审核报销记录 -</h3>
    </blockquote>
    <!-- 审核报销记录 -->
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
		,url:'showVerifyReimburseRecord'
		,cellMinWidth:80
		,cols:[[
			{type:'numbers',title:'序号',fixed:'left'}
			,{field:'jobId',title:'工号',align:'center'}
			,{field:'name',title:'姓名',align:'center'}
			,{field:'type',title:'类型',align:'center'}
			,{field:'content',title:'内容',align:'center'}
			,{field:'time',title:'时间',align:'center'}
			,{field:'verifyDate',title:'审核时间',align:'center'}
			,{field:'state',title:'状态',align:'center'}
		]]
		,page:true
		,limit:10
		,limits:[10,20,30,50]
		,text:{
			none:'暂无审核报销记录'
		}
	});
});
</script>
  </body>
</html>
