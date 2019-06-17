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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>

<body>
	<div style="padding:15px;" class="layui-row" id="testEdit">
		<div class="layui-col-md10">
			<form class="layui-form" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">老师姓名:</label>
					<div class="layui-inline">
						<input type="text" id="teacher_name" name="teacher_name"
							class="layui-input" readonly />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">老师学历:</label>
					<div class="layui-inline">
						<input type="text" id="education" name="education"
							class="layui-input" readonly />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">老师经历:</label>
					<div class="layui-inline">
						<textarea id="experience" name="experience" class="layui-input"
							readonly></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">培训地点:</label>
					<div class="layui-inline">
						<input type="text" id="site" name="site" class="layui-input"
							readonly />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">已报名人数:</label>
					<div class="layui-inline">
						<input type="text" id="already_person" name="already_person" class="layui-input"
							readonly />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">培训介绍:</label>
					<div class="layui-inline">
						<textarea id="introduce" name="introduce" class="layui-input"
							readonly></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
