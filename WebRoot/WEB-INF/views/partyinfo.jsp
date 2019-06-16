<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>个人党信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="layui/css/layui.css"
	media="all">
<style>
.layui-form-item .layui-form-label {
	width: 100px;
}

.layui-form-item .layui-input-block {
	margin-left: 130px;
}

.layui-inline .layui-form-label {
	width: 100px;
}

.layui-inline .layui-input-inline {
	margin-left: 0px;
}
</style>
</head>

<body>
	<blockquote class="layui-elem-quote layui-text">
		<h3>- 个人党信息 -</h3>
	</blockquote>

	<div class="layui-row" style="margin-top: 20px;">
		<div class="layui-col-md12">
			<button id="submitFeedBackBtn" class="layui-btn layui-btn-normal">
				<i class="layui-icon">&#xe654;</i>填写党信息信息反馈表
			</button>
		</div>
	</div>

	<form class="layui-form" action="" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">工号:</label>
			<div class="layui-input-inline">
				<input type="text" name="jobId"
					value="${requestScope.partyinfo.jobId}"
					lay-verify="required" autocomplete="off" class="layui-input"
					readonly>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">党内职务:</label>
			<div class="layui-input-inline">
				<input type="text" name="name"
					value="${requestScope.partyinfo.partyOccupation}"
					lay-verify="required" autocomplete="off" class="layui-input"
					readonly>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">党内身份:</label>
			<div class="layui-input-inline">
				<input type="text" name="sex"
					value="${requestScope.partyinfo.partyIdentity}"
					lay-verify="required" autocomplete="off" class="layui-input"
					readonly>
			</div>
		</div>
	</form>

	<table class="layui-table" id="table" border="5px"
		lay-filter="testForm"></table>

	<!-- 需要弹出的添加账号界面 -->
	<div class="layui-row" id="submitFeedBack" style="display:none;">
		<div class="layui-col-md10">
			<form class="layui-form" id="submitFeedBackForm" method="post">
				<label class="layui-form-label">反馈类型:</label>
				<div class="layui-inline">
					<select name="feedbackType" lay-filter="leaveType">
						<option value="">请选择</option>
						<option value="党信息:党信息有误">党信息有误</option>
						<option value="党信息:缺少党信息">缺少党信息</option>
					</select>
				</div>

				<div class="layui-row" style="margin-top: 20px;">
					<div class="layui-col-md12">
						<label class="layui-form-label">具体原因:</label>
						<div class="layui-inline">
							<textarea name="reason" placeholder="请输入反馈具体原因"
								class="layui-textarea" lay-verify="required"></textarea>
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div align="center">
						<input type="button" lay-submit id="submitAccount" value="提交"
							class="layui-btn layui-btn-normal" /> <input type="reset"
							value="重置" class="layui-btn layui-btn-normal" />
					</div>
				</div>
			</form>
		</div>

		<script src="layui/layui.all.js" charset="utf-8"></script>
		<script>
			//JavaScript代码区域

			layui.use([ 'jquery', 'table', 'layer', 'form' ], function() {
				var table = layui.table;
				var layer = layui.layer;
				var form = layui.form;
				var $ = layui.$;

				$("#submitFeedBackBtn").click(function() {
					layer.open({
						type : 1,
						title : "填写党信息反馈表",
						skin : "myclass",
						area : [ "40%", "50%" ],
						content : $("#submitFeedBack")
					});
					/*渲染表单*/
					form.render();
				});

				$(document).on('click','#submitAccount',function(){
				$.ajax({
					url:'FeedBackController/insertFeedbackInfo',
					data:$("#submitFeedBackForm").serialize(),
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success : function(result) {
					if (result.status) {
						layer.msg(result.message, {icon : 1}, function() 
						{
						   parent.layer.close();
						});
					} else {
						layer.msg(result.message, {icon : 2}, function() 
						{
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


