<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>企业人事管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
  </head>
  
  <body>
  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
</fieldset> 
<ul class="layui-nav layui-bg-cyan">
<center>
  <div class="layui-nav-item">招聘管理</div>

  <li class="layui-nav-item layui-this">
    <a href="requestPageNoLogin?page=introduce" target="down">公司介绍</a>
  </li>
   
   <li class="layui-nav-item">
    <a href="requestPageNoLogin?page=selectAllRecruitToExteral" target="down">招聘信息</a>
    <dl class="layui-nav-child">
      <dd><a href="">招聘部门</a></dd>
      <dd><a href="">岗位职能介绍</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item">
    <a href="requestPageNoLogin?page=selectAllRecruitToExteral" target="down">招聘申请</a>
  </li>
  <li class="layui-nav-item">
  <a href="requestPageNoLogin?page=MyResume" target="down">我的简历</a></li>
</center>
</ul>
<br>
<br>
    <iframe name="down" id="myrame" src="requestPageNoLogin?page=introduce" frameborder="0" align="left" width="100%" height="100%" scrolling="no">
  <script src="layui/layui.js"></script>
  </body>
</html>
