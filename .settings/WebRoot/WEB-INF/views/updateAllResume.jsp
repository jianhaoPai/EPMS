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
<br>
	<form class="layui-form" method="post" id="submitFeedBackForm">
		<div class="layui-form-item">
			<label class="layui-form-label">事项id：</label>
			<div class="layui-inline">
				<input type="text" id="id" name="id" class="layui-input" readonly />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">面试人员：</label>
			<div class="layui-inline">
				<input type="text" id="interviewName" name="interviewName"
					class="layui-input" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">面试时间:</label>
			<div class="layui-input-block">
				<input name="interviewDate" class="layui-input" id="date"
					type="text" placeholder="yyyy-MM-dd" autocomplete="off">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">状态：</label>
			<div class="layui-input-block">
				<select name="status" id="status" lay-filter="leaveType">
					<option value="通过">通过</option>
					<option value="未通过">未通过</option>
				</select>
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
	<script src="layui/layui.all.js" charset="utf-8"></script>
	<script>
		layui.use([ 'form', 'layedit', 'laydate' ], function() {
			var form = layui.form, layer = layui.layer;
			var $ = layui.$;
			//将值放在本页的input中
			var email=parent.layui.$("#aaa").val();

			$(document).on('click', '#submitAccount', function() {
			var id = $('#id').val();
		      var interviewName = $('#interviewName').val();
		      var interviewDate=$('#date').val();
		       var status=$('#status').val();
		       console.log(id);
		       console.log(interviewName);
		       console.log(interviewDate);
		       console.log(status);
		       console.log(email);
				$.ajax({
					url : 'ResumeController/updateAllResume',
					data : {
						email:email,
						id:id,
						interviewName:interviewName,
						interviewDate:interviewDate,
						status:status
						},
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
