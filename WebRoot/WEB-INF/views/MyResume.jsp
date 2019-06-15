<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>企业人事管理</title>
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
${reuestScope.insertExternalResumeMess}
	<form class="layui-form layui-form-pane" action="ExternalResumeController/insertExternalResume" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
				<input name="name" class="layui-input" type="text"
					value="${requestScope.externalResume.name}" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">年龄</label>
			<div class="layui-input-inline">
				<input name="age" class="layui-input" type="text"
					value="${requestScope.externalResume.age}" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学历</label>
			<div class="layui-input-inline">
				<input name="education" class="layui-input" type="text"
					value="${requestScope.externalResume.education}" >
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">所报部门</label>
			<div class="layui-input-inline">
				<input name="department.departmentId" class="layui-input" type="text"
					value="${requestScope.externalResume.resume.toDepartment.departmentName}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所报职位</label>
			<div class="layui-input-inline">
				<input name="occupation.occupationId" class="layui-input" type="text"
					value="${requestScope.externalResume.resume.toOccupation.occupationName}" >
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">联系电话</label>
			<div class="layui-input-inline">
				<input name="phone" class="layui-input" type="text"
					value="${requestScope.externalResume.phone}" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电子邮箱</label>
			<div class="layui-input-inline">
				<input name="email" class="layui-input" type="text"
					value="${requestScope.externalResume.email}" >
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">家庭住址</label>
			<div class="layui-input-inline">
				<input name="address" class="layui-input" type="text"
					value="${requestScope.externalResume.address}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline">
				<input name="sex" class="layui-input" type="text"
					value="${requestScope.externalResume.sex}">
			</div>
		</div>
		
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">工作经历</label>
			<div class="layui-input-block">
				<input name="workExperience" class="layui-input" type="text"
					value="${requestScope.externalResume.resume.workExperience}"></input>
			</div>
		</div>
	</form>

 <script src="layui/layui.all.js"></script> 
</body>
</html>
