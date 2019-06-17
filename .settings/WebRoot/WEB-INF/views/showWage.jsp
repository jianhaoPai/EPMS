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
      <h3>- 查询工资 -</h3>
    </blockquote>
    
    <!-- 需要查询工资页面 -->
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
		,url:'showWageByJobId'
		,cellMinWidth: 40
		,cols:[[
			{type:'numbers',title:'序号',fixed:'left',align:'center'}
			,{field:'wage_time',title:'时间',align:'center'}
			,{field:'basic_wage',title:'基本工资',align:'center'}
			,{field:'overtime_pay',title:'加班费',align:'center'}
			,{field:'live_allowance',title:'生活津贴',align:'center'}
			,{field:'holiday_allowance',title:'节日津贴',align:'center'}
			,{field:'perform_allowance',title:'绩效津贴',align:'center'}
			,{field:'absence',title:'迟到',align:'center'}
			,{field:'social_security',title:'社保',align:'center'}
			,{field:'housing_fund',title:'住房公积金',align:'center'}
			,{field:'borrow',title:'借支',align:'center'}
			,{field:'other',title:'其他',align:'center'}
			,{field:'tax_amount',title:'扣税',align:'center'}
			,{field:'sum',title:'总共工资',align:'center'}
		]]
		,page:true
		,limit:10
		,limits:[10,20,30,50]
		,text:{
			none:'暂时没有工资记录'
		}
	});
	
});   
</script>    
  </body>
</html>
