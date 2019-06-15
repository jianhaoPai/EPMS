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
			<form class="layui-form" method="post" id="submitExteralResumeForm">
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
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-inline">
						<input name="name" class="layui-input" type="text"
							placeholder="请输入" autocomplete="off" lay-verify="required">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年龄</label>
					<div class="layui-input-inline">
						<input name="age" class="layui-input" type="text"
							placeholder="请输入" autocomplete="off" lay-verify="required|number|age">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">学历</label>
					<div class="layui-input-block">
						<select name="education" lay-filter="aihao">
							<option value=""></option>
							<option value="大专">大专</option>
							<option value="本科">本科</option>
							<option value="研究生">研究生</option>
							<option value="博士">博士</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">联系电话</label>
					<div class="layui-input-inline">
						<input name="phone" class="layui-input" type="text"
							placeholder="请输入" autocomplete="off" lay-verify="required|phone">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">电子邮箱</label>
					<div class="layui-input-inline">
						<input name="email" class="layui-input" type="text"
							placeholder="请输入" autocomplete="off" lay-verify="required|email">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">家庭住址</label>
					<div class="layui-input-inline">
						<input name="address" class="layui-input" type="text"
							placeholder="请输入" autocomplete="off" lay-verify="required">
					</div>
				</div>
				<div class="layui-form-item" pane="">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-block">
						<input name="sex" title="男" type="radio" checked="" value="男">
						<input name="sex" title="女" type="radio" value="女">
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">工作经历</label>
					<div class="layui-input-block">
						<textarea name="resume.workExperience" class="layui-textarea"
							placeholder="请输入内容" lay-verify="required"></textarea>
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
					url : 'ExternalResumeController/insertExternalResume',
					data : $("#submitExteralResumeForm").serialize(),
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
			form.verify({
				age: function(value){
                 if(value<=0||value>120){
                 return '年龄不能小于0或大于120';}
              }
			});
		});
	</script>
</body>
</html>
