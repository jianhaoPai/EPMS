<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<div style="padding:15px;" class="layui-row" id="testEdit"">
		<div class="layui-col-md10">
			<form class="layui-form" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱：</label>
					<div class="layui-inline">
						<input type="text" id="email" name="email"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址：</label>
					<div class="layui-inline">
						<input type="text" id="address" name="address"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">面试人：</label>
					<div class="layui-inline">
						<input type="text" id="interview_id" name="interview_id"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">面试时间：</label>
					<div class="layui-inline">
						<input type="text" id="interview_date" name="interview_date"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">审核时间：</label>
					<div class="layui-inline">
						<input type="text" id="approval_date" name="approval_date"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">工作经历：</label>
					<div class="layui-inline">
						<textarea type="text" id="work_experience" name="work_experience"
							class="layui-input" readonly></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
