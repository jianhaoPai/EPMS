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
	<div style="padding:15px;" class="layui-row" id="editAccount"">
		<div class="layui-col-md10">
			<form class="layui-form" method="post" id="submitFeedBackForm">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">生日:</label>
						<div class="layui-inline">
							<input type="text" id="birthday" name="birthday"
								class="layui-input" readonly />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">电话:</label>
						<div class="layui-inline">
							<input type="text" id="phone" name="phone" class="layui-input"
								readonly />
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">邮箱:</label>
						<div class="layui-inline">
							<input type="text" id="email" name="email" class="layui-input"
								readonly />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">地址:</label>
						<div class="layui-inline">
							<input type="text" id="address" name="address"
								class="layui-input" readonly />
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">婚姻状况:</label>
						<div class="layui-inline">
							<input type="text" id="marital" name="marital"
								class="layui-input" readonly />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">籍贯:</label>
						<div class="layui-inline">
							<input type="text" id="census" name="census" class="layui-input"
								readonly />
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">入职时间:</label>
						<div class="layui-inline">
							<input type="text" id="entry_date" name="entry_date"
								class="layui-input" readonly />
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">毕业学校:</label>
						<div class="layui-inline">
							<input type="text" id="school_name" name="school_name"
								class="layui-input" readonly />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>