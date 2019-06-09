<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'evaluate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" type="text/css" href="layui/css/layui.css"  media="all">
  </head>
  
  <body>
    <blockquote class="layui-elem-quote layui-text">
      <h3>- 查询报销费用记录 -</h3>
    </blockquote>
    
    <!-- 需要查询报销费用记录 -->
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
		,url:'showReimbursementByJobId'
		,cellMinWidth: 40
		,cols:[[
			{type:'numbers',title:'序号',fixed:'left',align:'center'}
			,{field:'type',title:'报销类型',align:'center'}
			,{field:'content',title:'报销内容',align:'center'}
			,{field:'time',title:'时间',align:'center'}
			,{field:'amount',title:'金额',align:'center'}
			,{field:'state',title:'状态',align:'center'}

		]]
		,page:true
		,limit:10
		,limits:[10,20,30,50]
		,text:{
			none:'暂时报销记录'
		}
	});
	
});   
</script>    
  </body>
</html>
