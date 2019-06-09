<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人通讯信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="layui/css/layui.css"  media="all">
	<style>
	  .layui-form-item .layui-form-label{ width:100px;}
	  .layui-form-item .layui-input-block{ margin-left:130px;}
	  .layui-inline .layui-form-label{ width:100px;}
	  .layui-inline .layui-input-inline{ margin-left:0px;}
	</style>
  </head>
  
  <body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 个人通讯信息 -</h3>
  </blockquote>

<form class="layui-form" id="submitFeedBackForm" method="post">

		<div class="layui-form-item">
			<label class="layui-form-label">工号:</label>
			<div class="layui-input-inline">
				<input type="text" name="jobId"
					value="${requestScope.communication.jobId}" readonly
					autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">办公邮箱:</label>
			<div class="layui-input-inline">
				<input name="email" class="layui-input"
					value="${requestScope.communication.email}" type="text"
					autocomplete="off" lay-verify="email" placeholder="请输入办公邮箱">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">办公电话:</label>
			<div class="layui-input-inline">
				<input name="officePhone" class="layui-input" type="text"
					value="${requestScope.communication.officePhone}" placeholder="请输入办公电话" 
					autocomplete="off" lay-verify="officePhone|number|required">
			</div>
			<!-- <div class="layui-form-mid layui-word-aux">请填写长度为7位的座机号码</div> -->
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">办公地址:</label>
			<div class="layui-input-inline">
				<input type="text" name="officeAddress"
					value="${requestScope.communication.officeAddress}" placeholder="请输入办公地址"
					autocomplete="off" class="layui-input" lay-verify="required">
			</div>
		</div>

		<div class="layui-form-item">
			<div align="center">
				<input type="button" lay-submit id="submitAccount" value="提交"
					   class="layui-btn layui-btn-normal" /> 
				<input type="reset"
					   value="重置" class="layui-btn layui-btn-normal" />
			</div>
		</div>

	</form>
    <script src="layui/layui.all.js"></script>
    
   
<script>
layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
  
   //自定义验证规则
  form.verify({officePhone: [
      /^[\S]{7,7}$/
      ,'座机号码长度必须7位'
    ]
  });  
  
  $(document).on('click', '#submitAccount', function() {
			$.ajax({
				url : 'CommunicationController/updateCommunication',
				data : $("#submitFeedBackForm").serialize(),
				async : true,//是否为异步请求
				cache : false,//是否缓存结果
				type : 'post',//请求方式为POST
				dataType : 'json',
				success : function(result) {
					if (result.status) {
						layer.msg(result.message, {icon : 1}, function() {
							window.location.reload();//刷新父页面
						});
					} else {
						layer.msg(result.message, {icon : 2}, function() {
							window.location.reload();//刷新父页面
						});
					}
				}
			});
		});
  
});
</script>
  </body>
</html>
