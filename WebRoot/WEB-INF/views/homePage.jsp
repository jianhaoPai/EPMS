<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'homePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
  </head>
  
  <body onload="showtime()">
  <blockquote class="layui-elem-quote layui-text">
    <h3>${username}&nbsp;&nbsp;<i id="time"/></h3>
  </blockquote>
    
  <iframe allowtransparency="true" style="float:right;" frameborder="0" width="140" height="428" scrolling="no" src="//tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=1&d=5&bd=0&k=000000&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=71256&w=140&h=428&align=center"></iframe>
<script src="jquery/jquery-3.4.0.min.js" charset="utf-8"></script>
<script>
	function showtime () {
		var now = new Date();
		var year = now.getYear()+1900;
		var month=now.getMonth()+1;
		var hours=now.getHours();
		var date=now.getDate();
		var day=now.getDay();
		var weekday=new Array(7)
		weekday[0]="星期天"
		weekday[1]="星期一"
		weekday[2]="星期二"
		weekday[3]="星期三"
		weekday[4]="星期四"
		weekday[5]="星期五"
		weekday[6]="星期六"
		var timeValue = "" +((hours >= 12) ? "下午好，今天是 " : "上午好，今天是 " );
		timeValue += year+"年 ";
		timeValue +=month+"月 ";
		timeValue +=date+"日 ";
		timeValue +=weekday[day];
		$("#time").text(timeValue);
	} 

</script>
</body>
</html>
