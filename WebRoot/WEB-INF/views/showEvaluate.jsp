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
      <h3>- 绩效评价 -</h3>
    </blockquote>
    <!-- 需要评价的绩效页面 -->
    <table class="layui-table" id="table" lay-filter="testForm" border="5px"></table>
    
    
<script type="text/html" id="barDemo">
	<button class="layui-btn layui-btn sm" lay-event="start">开始评价</button>
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
		,url:'showAllEvaluate'
		,cellMinWidth:80
		,cols:[[
			{type:'numbers',title:'序号',fixed:'left',align:'center'}
			,{field:'type',title:'评价类型',align:'center'}
			,{field:'assessDate',title:'评价周期',align:'center'}
			,{field:'evaluateId',title:'工号',align:'center'}
			,{field:'name',title:'评价对象',align:'center'}
			,{fixed:'right',title:'操作',toolbar:'#barDemo',align:'center'}
		]]
		,page:true
		,limit:10
		,limits:[10,20,30,50]
		,text:{
			none:'暂时不需要评价'
		}
	});
	
	table.on('tool(testForm)',function(obj){
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		editList=[];
		$.each(data,function(name,value){
		    editList.push(value);
		});
		if(layEvent='start'){
			layer.open({
				type:2,
				title:"",
				skin:"myclass",
				area:["100%","100%"],
				content:"requestPage?page=evaluatePage",
				success:function(layero,index){
					var body = layer.getChildFrame('body', index);  
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                    var inputList = body.find("input");//获取到子窗口的所有的input标签
                    $(inputList[20]).val(data.evaluateId); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                    $(inputList[21]).val(data.assessDate);
                    $(inputList[22]).val(data.type);
				},
				end:function(){
					window.location.reload();
				}
			});
		}
	});
	
});   
</script>    
  </body>
</html>
