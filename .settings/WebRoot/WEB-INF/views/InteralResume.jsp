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
<link rel="stylesheet" href="layui/css/layui.css" media="all">
</head>
<body>
	<div style="padding:15px;" class="layui-row"
		id="GeneralManagerUpdateStatus">
		<div class="layui-col-md10">
			<form class="layui-form" method="post" id="submitInteralResumeForm">
				<div class="layui-form-item">
					<label class="layui-form-label">所报部门：</label>
					<div class="layui-inline">
						<input type="text" id="resume.toDepartment.departmentName"
							name="resume.toDepartment.departmentName" class="layui-input"
							readonly />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所报职位：</label>
					<div class="layui-inline">
						<input type="text" id="resume.toOccupation.occupationName"
							name="resume.toOccupation.occupationName" class="layui-input"
							readonly />
					</div>
				</div>

				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">工作经历</label>
					<div class="layui-input-block">
						<textarea name="resume.workExperience" class="layui-textarea"
							lay-verify="required" placeholder="请输入内容" lay-verify="required"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<div align="center">
							<input type="button" lay-submit id="submitAccount" value="提交"
								class="layui-btn layui-btn-normal" /> <input type="reset"
								value="重置" class="layui-btn layui-btn-normal" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="layui/layui.all.js"></script>
	<script src="layui/layui.all.js" charset="utf-8"></script>
	<script>
		layui.use([ 'form', 'jquery', 'layer' ], function() {
			var $ = layui.$;
			var layer = layui.layer;
			var form = layui.form;
			var index = parent.layer.getFrameIndex(window.name);
			$(document).on('click', '#submitAccount', function() {
				$.ajax({
					url : 'InteralResumeController/insertInteralResume',
					data : $("#submitInteralResumeForm").serialize(),
					async : true,//是否为异步请求
					cache : false,//是否缓存结果
					type : 'post',//请求方式为POST
					dataType : 'json',//改为text
					success : function(result) {
						if (result.status) {
							layer.msg(result.message, {
								icon : 1
							}, function() {
								//window.location.reload();//刷新父页面
								parent.layer.close();
							});
						} else {
							layer.msg(result.message, {
								icon : 2
							}, function() {
								//window.location.reload();//刷新父页面
								parent.layer.close();
							});
						}
					}
				});
			});
		});
	</script>
</body>
</html>


