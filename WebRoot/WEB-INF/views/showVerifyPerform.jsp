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
      <h3>- 绩效评价审核 -</h3>
    </blockquote>
    <!-- 需要绩效评价审核的页面 -->
    <table class="layui-table" id="table" lay-filter="testForm" border="5px"></table>
    
    
<script type="text/html" id="barDemo">
	<button class="layui-btn layui-btn sm" lay-event="pass">审核通过</button>
	<button class="layui-btn layui-btn sm" lay-event="nopass">审核不通过</button>
</script> 
<script src="layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['jquery','table','layer','form'],function(){
	var $=layui.$;
	var table=layui.table;
	var layer=layui.layer;
	var form=layui.form;
	
	table.render({
		elem:'#table'
		,url:'showVerifyPerform'
		,cellMinWidth:80
		,cols:[[
			{type:'numbers',title:'序号',fixed:'left',align:'center'}
			,{field:'jobId',title:'工号',align:'center',width:'150'}
			,{field:'name',title:'姓名',align:'center',width:'150'}
			,{field:'score',title:'成绩',align:'center',width:'100'}
			,{field:'type',title:'评价类型',align:'center',width:'150'}
			,{field:'assessDate',title:'评价周期',align:'center',width:'170'}
			,{field:'state',title:'状态',align:'center',width:'150'}
			,{fixed:'right',title:'操作',toolbar:'#barDemo',align:'center',width:'300'}
		]]
		,page:true
		,limit:10
		,limits:[10,20,30,50]
		,text:{
			none:'暂时不需要绩效评价审核'
		}
	});
	
	table.on('tool(testForm)',function(obj){
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent==='pass'){
			$.ajax({
				url:'updateVerifyPerform',
				data:{
					jobId:data.jobId,
					assessDate:data.assessDate
				},
				async:true,//是否为异步请求
				cache:false,//是否缓存结果
				type:'post',//请求方式为POST
				dataType:'json',
				success:function(result){
					if(result){
						layer.msg("操作成功",{icon:1,time:1000},function(){
							window.location.reload();//刷新父页面
						});	
					}else{
						layer.msg("操作失败",{icon:2,time:1000},function(){
							window.location.reload();//刷新父页面
						});
					}
				}
				
			});
		}
		if(layEvent==='nopass'){
			$.ajax({
					url:'updateVerifyPerformNo',
					data:{
						jobId:data.jobId,
						assessDate:data.assessDate
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result){
						if(result){
							layer.msg("操作成功",{icon:1,time:1000},function(){
								window.location.reload();//刷新父页面
							});	
						}else{
							layer.msg("操作失败",{icon:2,time:1000},function(){
								window.location.reload();//刷新父页面
							});
						}
					}	
				});
		}
	});
	
});   
</script>    
  </body>
</html>
