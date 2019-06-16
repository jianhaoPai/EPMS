<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人信息</title>
    
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
	<h3>- 个人信息-</h3>
</blockquote>
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

	<form class="layui-form" id="submitFeedBackForm" method="post">
	
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">工号:</label>
				<div class="layui-input-inline">
					<input name="jobId" class="layui-input" type="text" autocomplete="off" 
					       value="${requestScope.personalinfo.jobId}" readonly>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">姓名:</label>
				<div class="layui-input-inline">
					<input name="name" class="layui-input" type="text"
						autocomplete="off" lay-verify="required"
						value="${requestScope.personalinfo.name}">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">入职时间:</label>
				<div class="layui-input-inline">
					<input name="entryDate" class="layui-input" type="text"
						value="${requestScope.personalinfo.entryDate}" readonly>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">性别:</label>
				<div class="layui-input-inline">
					<input name="sex" class="layui-input" type="text"
						autocomplete="off" lay-verify="required|sex"
						value="${requestScope.personalinfo.sex}">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">年龄:</label>
				<div class="layui-input-inline">
					<input name="age" class="layui-input" type="text"
						autocomplete="off" value="${requestScope.personalinfo.age}" readonly>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">生日:</label>
				<div class="layui-input-inline">
					<input name="birthday" class="layui-input" id="date"
						   placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date"
						   value="${requestScope.personalinfo.birthday}">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">手机:</label>
				<div class="layui-input-inline">
					<input name="phone" class="layui-input" type="tel"
						autocomplete="off" lay-verify="required|phone" 
						value="${requestScope.personalinfo.phone}">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">邮箱:</label>
				<div class="layui-input-inline">
					<input name="email" class="layui-input" type="text"
						autocomplete="off" lay-verify="email"
						value="${requestScope.personalinfo.email}">
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">部门:</label>
				<div class="layui-input-inline">
					<input name="department.departmentName" class="layui-input" type="text"
						autocomplete="off" readonly
						value="${requestScope.personalinfo.department.departmentName}">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">职务:</label>
				<div class="layui-input-inline">
					<input name="occupation.occupationName" class="layui-input" type="text"
						autocomplete="off" readonly
						value="${requestScope.personalinfo.occupation.occupationName}">
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">家庭住址:</label>
				<div class="layui-input-inline">
					<input name="address" class="layui-input" type="text"
						autocomplete="off" value="${requestScope.personalinfo.address}"
						lay-verify="required">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">籍贯:</label>
				<div class="layui-input-inline">
					<input name="census" class="layui-input" type="text"
						autocomplete="off" lay-verify="required"
						value="${requestScope.personalinfo.census}">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">婚姻状况:</label>
				<div class="layui-input-inline">
					<input name="marital" class="layui-input" type="text"
						autocomplete="off" value="${requestScope.personalinfo.marital}"
						lay-verify="required">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">身份证:</label>
				<div class="layui-input-inline">
					<input name="identityCard" class="layui-input" type="text"
						autocomplete="off" lay-verify="identity"
						value="${requestScope.personalinfo.identityCard}">
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">入校时间:</label>
				<div class="layui-input-inline">
					<input name="education.inDate" class="layui-input" id="date" type="text"
						placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date"
						value="${requestScope.personalinfo.education.inDate}">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">毕业时间:</label>
				<div class="layui-input-inline">
					<input name="education.outDate" class="layui-input" id="date" type="text"
						placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date"
						value="${requestScope.personalinfo.education.outDate}">
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">毕业学校:</label>
				<div class="layui-input-inline">
					<input name="education.schoolName" class="layui-input" type="text"
						value="${requestScope.personalinfo.education.schoolName}" lay-verify="required">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">最高学历:</label>
				<div class="layui-input-inline">
					<input name="education.education" class="layui-input"type="text"
						value="${requestScope.personalinfo.education.education}" lay-verify="required">
				</div>
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
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  var $=layui.$;
  
   //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  	
	form.verify({
			sex : [ /^['男'|'女']$/, '性别必须为男或女' ]
		});

		$(document).on('click', '#submitAccount', function() {
			$.ajax({
				url : 'PersonalinfoController/updatePersonal',
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