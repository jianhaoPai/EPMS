<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
  </head>
  
  <body>
   <div style="padding:15px;" class="layui-row" id="editAccount"">
	<div class="layui-col-md10">
		<form  class="layui-form" method="post" id="submitFeedBackForm">
		   <div class="layui-form-item">
				<label class="layui-form-label">请假天数:</label>
				<div class="layui-inline">
					<input type="text" id="daySum"  name="daySum" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">扣工资天数:</label>
				<div class="layui-inline">
					<input type="text" id="discountDay"  name="discountDay" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">审批时间:</label>
				<div class="layui-inline">
					<input type="text" id="approvalDate"  name="cancelDate" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">具体原因：</label>
				<div class="layui-inline">
				<textarea id="reason" name="reason" class="layui-textarea"></textarea>
				</div>
			</div>
		</form>
	</div>
</div>
