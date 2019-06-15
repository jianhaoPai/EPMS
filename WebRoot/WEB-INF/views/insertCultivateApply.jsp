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

	<form class="layui-form" method="post" id="submitFeedBackForm">

		<div class="layui-form-item">
			<label class="layui-form-label">培训名称</label>
			<div class="layui-input-inline">
				<input name="trainName" class="layui-input" type="text"
					placeholder="请输入" autocomplete="off" lay-verify="required">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">培训类型</label>
			<div class="layui-input-block">
				<select name="cultivateType.cultivateId" lay-filter="aihao">
					<option value=""></option>
					<option value="1">内部培训</option>
					<option value="2">外出培训</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">面对人员</label>
			<div class="layui-input-block">
				<select name="facePeople" lay-filter="aihao">
					<option value=""></option>
					<option value="针对本部门实习员工">针对本部门实习员工</option>
					<option value="针对全公司实习员工">针对全公司实习员工</option>
					<option value="本部门人员">本部门人员</option>
					<option value="全公司人员">全公司人员</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">老师</label>
			<div class="layui-input-block">
				<select name="teacher.id" lay-filter="aihao">
					<option value=""></option>
					<option value="1">hjh</option>
					<option value="2">zmx</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">报名人数</label>
			<div class="layui-input-inline">
				<input name="sum" class="layui-input" type="text" placeholder="请输入"
					autocomplete="off" lay-verify="required|number|sum">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">开始时间:</label>
			<div class="layui-input-block">
				<input name="startDate" class="layui-input" id="date" type="text"
					placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">结束时间:</label>
			<div class="layui-input-block">
				<input name="finishDate" class="layui-input" id="date" type="text"
					placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">培训地点</label>
			<div class="layui-input-inline">
				<input name="site" class="layui-input" type="text" placeholder="请输入"
					autocomplete="off" lay-verify="required">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">预计花销</label>
			<div class="layui-input-inline">
				<input name="cost" class="layui-input" type="text" placeholder="请输入"
					autocomplete="off" lay-verify="required|cost|number">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">培训介绍</label>
			<div class="layui-input-block">
				<textarea name="introduce" class="layui-textarea"
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
	<script>
		layui
				.use(
						[ 'jquery', 'table', 'layer', 'form' ],
						function() {
							var table = layui.table;
							var layer = layui.layer;
							var form = layui.form;
							var $ = layui.$;
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
																				url : 'CultivateApplyController/insertCultivateApply',
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
								sum : function(value) {
									if (value < 0) {
										return '参与人数不能小于0';
									}
								},
								cost : function(value) {
									if (value < 0) {
										return '花费不能小于0';
									}
								}
							});
						});
	</script>
</body>
</html>