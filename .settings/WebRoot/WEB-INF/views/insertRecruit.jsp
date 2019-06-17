<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>提交招聘计划</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 提交招聘计划 -</h3>
  </blockquote>
  <br>
	<form class="layui-form" method="post" id="submitFeedBackForm">

		<div class="layui-form-item">
			<label class="layui-form-label">招聘人数:</label>
			<div class="layui-input-block">
				<input name="sum" class="layui-input" type="text" placeholder="请输入"
					autocomplete="off" lay-verify="required|number|sum">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">招聘部门:</label>
			<div class="layui-input-block">
				<select name="department.departmentId" lay-filter="aihao">
					<option value=""></option>
					<option value="11">人事部</option>
					<option value="22">财务部</option>
					<option value="33">行政部</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">招聘职位:</label>
			<div class="layui-input-block">
				<select name="occupation.occupationId" lay-filter="aihao">
					<option value=""></option>
					<option value="1">职员</option>
					<option value="2">部门经理</option>
				</select>
			</div>
		</div> 
		<div class="layui-form-item">
			<label class="layui-form-label">职位介绍:</label>
			<div class="layui-input-block">
				<textarea name="functionIntrduce" class="layui-textarea"
					placeholder="请输入内容" lay-verify="required"></textarea>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">职位要求:</label>
			<div class="layui-input-block">
				<textarea name="demand" class="layui-textarea"
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
	<script src="layui/layui.all.js"></script>
	${requestScope.insertRecruitFormTotalManagerMess}
<script>
layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
		layui
				.use(
						[ 'form', 'layedit', 'laydate' ],
						function() {
							var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
							var $ = layui.$;

							//日期
							laydate.render({
								elem : '#date'
							});
							laydate.render({
								elem : '#date1'
							});

							$(document)
									.on(
											'click',
											'#submitAccount',
											function() {
												$
														.ajax({
															url : 'RecruitController/insertRecruit',
															data : $(
																	"#submitFeedBackForm")
																	.serialize(),
															async : true,//是否为异步请求
															cache : false,//是否缓存结果
															type : 'post',//请求方式为POST
															dataType : 'json',//改为text
															success : function(
																	result) {
																if (result.status) {
																	layer
																			.msg(
																					result.message,
																					{
																						icon : 1
																					},
																					function() {
																						//window.location.reload();//刷新父页面
																						parent.layer
																								.close();
																					});
																} else {
																	layer
																			.msg(
																					result.message,
																					{
																						icon : 2
																					},
																					function() {
																						//window.location.reload();//刷新父页面
																						parent.layer
																								.close();
																					});
																}
															}
														});
											});
						});
						
			 form.verify({
          	sum: function(value){
                 if(value<0){
                 return '所需人数不能小于0';}
              }
          });
	});
</script>
</body>
</html>
