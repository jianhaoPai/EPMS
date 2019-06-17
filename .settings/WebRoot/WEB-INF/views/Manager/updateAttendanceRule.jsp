<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改上下班时间</title>
    
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
	<h3>- 规定上下班时间-</h3>
</blockquote>
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

	<form class="layui-form" id="submitFeedBackForm" method="post">
		<div class="layui-form-item">
			<br> <label class="layui-form-label">上班时间:</label>
			<div class="layui-inline">
				<input type="text" name="setStart" class="layui-input" 
				       lay-verify="required|number|setStart" autocomplete="off"
				       value="${requestScope.attendanceRule.setStart}"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">下班时间:</label>
			<div class="layui-inline">
				<input type="text" name="setFinish" class="layui-input" autocomplete="off"
					   value="${requestScope.attendanceRule.setFinish}"
					   lay-verify="required|number|setFinish" />
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

<script src="layui/layui.js" charset="utf-8"></script>
<script>

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
          
	   form.verify({
			setStart : function(value) {
				if (value<=0 ||value>12) {
					return '上班时间必须大于0小于12';
				}
			},
			setFinish : function(value) {
				if (value > 24 || value < 12) {
					return '下班时间必须大于12小于24';
				}
			}
		});

		$(document).on('click', '#submitAccount', function() 
		{
			$.ajax({
				url : 'AttendanceRuleController/updateAttendanceRule',
				data:$("#submitFeedBackForm").serialize(),
				async : true,//是否为异步请求
				cache : false,//是否缓存结果
				type : 'post',//请求方式为POST
				dataType : 'json',
				success : function(result) {
					if (result.status) {
						layer.msg(result.message, {
							icon : 1
						}, function() {
							window.location.reload();//刷新父页面
							parent.layer.close();//关闭当前弹窗
						});
					} else {
						layer.msg(result.message, {
							icon : 2
						}, function() {
							window.location.reload();//刷新父页面
							parent.layer.close();//关闭当前弹窗
						});
					}
				}
			});
		});
	});
</script>
</body>
</html>

