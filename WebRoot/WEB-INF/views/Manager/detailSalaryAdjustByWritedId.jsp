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
				<label class="layui-form-label">调动类型:</label>
				<div class="layui-inline">
					<input type="text" id="salaryadjust_type"  name="salaryadjust_type" class="layui-input" readonly/>
				</div>
			</div>
		
			<div class="layui-form-item">
				<label class="layui-form-label">调动金额:</label>
				<div class="layui-inline">
					<input type="text" id="salaryadjust_money"  name="salaryadjust_money" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">具体原因:</label>
				<div class="layui-inline">
				<textarea id="reason" name="reason" class="layui-textarea"></textarea>
				</div>
			</div>
		</form>
	</div>
</div>

  <script src="layui/layui.all.js" charset="utf-8"></script>
  <script>
  </script>
  </body>
</html>