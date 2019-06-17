<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查询全部下属所报名的培训记录</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
	<blockquote class="layui-elem-quote layui-text">
		<h3>- 查询全部下属所报名的培训记录-</h3>
	</blockquote>

	<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<script type="text/html" id="barDemo">
	<button class="layui-btn layui-btn sm" lay-event="yes">通过</button>
	<button class="layui-btn layui-btn sm" lay-event="no">不通过</button>
</script>

	<script src="layui/layui.all.js" charset="utf-8"></script>
	<script>
		//JavaScript代码区域

		layui
				.use(
						[ 'jquery', 'table', 'layer', 'form' ],
						function() {
							var table = layui.table;
							var layer = layui.layer;
							var form = layui.form;
							var $ = layui.$;

							table
									.render({
										elem : '#table' //绑定table id
										,
										url : 'CultivateRecordController/selectAllCultivateRecord' //数据请求路径
										,
										cellMinWidth : 80,
										cols : [ [ {
											type : 'numbers',
											title : '序号',
											fixed : 'left',
											align : 'center'
										}, {
											field : 'name',
											title : '姓名',
											align : 'center',
											sort : 'true'
										}, {
											field : 'cultivate_name',
											title : '培训类型',
											align : 'center'
										}, {
											field : 'train_name',
											title : '培训名称',
											align : 'center'
										}, {
											field : 'start_date',
											title : '开始时间',
											align : 'center',
											sort : 'true'
										}, {
											field : 'finish_date',
											title : '结束时间',
											align : 'center'
										}, {
											field : 'teacher_name',
											title : '老师姓名',
											align : 'center',
											sort : 'true'
										},  {
											field : 'recordStatus',
											title : '状态',
											align : 'center'
										}, {
											fixed : 'right',
											title : '查看',
											toolbar : '#barDemo'
										}
										//一个工具栏  具体请查看layui官网
										] ]

										,
										page : true //开启分页
										,
										limit : 10 //默认十条数据一页
										,
										limits : [ 10, 20, 30, 50 ] //数据分页条
										,
										id : 'test'
										
									});

table.on('tool(testForm)',function(obj){
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		editList=[];
		$.each(data,function(name,value){
		    editList.push(value);
		});
		if(layEvent==='yes'){
			$.ajax({
					url:'CultivateRecordController/updateCultivateRecordStatusYes',
					data:{
						recordId:data.recordId,
						cultivateId:data.cultivateId,
						recordStatus:data.recordStatus
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result){
						if(result.status){
							layer.msg(result.message,{icon:1,time:1000},function(){
								window.location.reload();//刷新父页面
							});	
						}else{
							layer.msg(result.message,{icon:2,time:1000},function(){
								window.location.reload();//刷新父页面
							});
						}
					}	
				});
		}
		if(layEvent==='no'){
			$.ajax({
					url:'CultivateRecordController/updateCultivateRecordStatusNo',
					data:{
						recordId:data.recordId,
						cultivateId:data.cultivateId,
						recordStatus:data.recordStatus
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result){
						if(result.status){
							layer.msg(result.message,{icon:1,time:1000},function(){
								window.location.reload();//刷新父页面
							});	
						}else{
							layer.msg(result.message,{icon:2,time:1000},function(){
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