<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>个人所有请假申请记录</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
	<blockquote class="layui-elem-quote layui-text">
		<h3>- 个人请假申请记录 -</h3>
	</blockquote>

	<div class="layui-row" style="margin-top: 20px;">
		<div class="layui-col-md12">
			<button id="submitFeedBackBtn" class="layui-btn layui-btn-normal">
				<i class="layui-icon">&#xe654;</i>填写请假申请记录反馈表
			</button>
		</div>
	</div>
	<br>
	<form class="layui-form layui-form-pane" action="">
		<div class="demoTable">
			<div class="layui-inline">
				<select name="state" id="stateReload">
					<option value="">请选择审核状态</option>
					<option value="待审核">待审核</option>
					<option value="同意">同意</option>
					<option value="不同意">不同意</option>
				</select>
			</div>
			<div class="layui-inline">
				<select name="type" id="typeReload">
					<option value="">请选择请假类型</option>
					<option value="年假">年假</option>
					<option value="产假">产假</option>
					<option value="事假">事假</option>
					<option value="病假">病假</option>
				</select>
			</div>
			<button type="button" class="layui-btn" data-type="reload">搜索</button>
		</div>
	</form>
	<table class="layui-table" id="table" border="5px"
		lay-filter="testForm"></table>

<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="update">查看详情</button>
  <button class="layui-btn layui-btn sm" lay-event="yes">销假</button>
</script>

	<div class="layui-row" id="submitFeedBack" style="display:none;">
		<div class="layui-col-md10">
			<br>
			<form class="layui-form" id="submitFeedBackForm" method="post">
				<label class="layui-form-label">反馈类型:</label>
				<div class="layui-input-inline">
					<select name="feedbackType" lay-filter="leaveType">
						<option value="">请选择</option>
						<option value="请假信息:请假时间有误">请假时间有误</option>
						<option value="请假信息:请假类型有误">请假类型有误</option>
						<option value="请假信息:请假审核状态有误">请假审核状态有误</option>
					</select>
				</div>

				<div class="layui-row" style="margin-top: 20px;">
					<div class="layui-col-md12">
						<label class="layui-form-label">具体原因:</label>
						<div class="layui-input-inline">
							<textarea name="reason" placeholder="请输入反馈具体原因"
								class="layui-textarea" lay-verify="required"></textarea>
						</div>
					</div>
				</div>

				<br>
				<div class="layui-form-item">
					<div align="center">
						<input type="button" lay-submit id="submitAccount" value="提交"
							class="layui-btn layui-btn-normal" /> <input type="reset"
							value="重置" class="layui-btn layui-btn-normal" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="layui/layui.all.js" charset="utf-8"></script>
	<script>
		//JavaScript代码区域

		layui.use([ 'jquery', 'table', 'layer', 'form' ], function() {
			var table = layui.table;
			var layer = layui.layer;
			var form = layui.form;
			var $ = layui.$;

			table.render({
				elem : '#table' //绑定table id
				,
				url : 'VacateController/selectAllVacateApplyByJobId' //数据请求路径
				,
				cellMinWidth : 80,
				cols : [ [ {
					type : 'numbers',
					title : '序号',
					fixed : 'left',
					align : 'center'
				}, {
					field : 'startDate',
					title : '开始时间',
					align : 'center',
					sort : 'true'
				}, {
					field : 'finishDate',
					title : '结束时间',
					align : 'center'
				}, {
					field : 'type',
					title : '类型',
					align : 'center',
					sort : 'true'
				}, {
					field : 'submitDate',
					title : '提交时间',
					align : 'center'
				}, {
					field : 'cancelDate',
					title : '销假时间',
					align : 'center'
				}, {
					field : 'state',
					title : '状态',
					align : 'center'
				}, {
					fixed : 'right',
					title : '操作',
					toolbar : '#barDemo'
				}
				//一个工具栏  具体请查看layui官网
				] ]

				,
				page : true //开启分页
				,
				limit : 10 //默认十条数据一页
				,
				limits : [ 10, 20, 30, 50 ] //数据分页条
				,
				id : 'test',
				text : {
					none : '暂时无所查请假申请记录'
				}
			});

			var $ = layui.$, active = {
				reload : function() {
					var typeReload = $('#typeReload').val();
					var stateReload = $('#stateReload').val();
					//执行重载
					table.reload('test', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						where : {
							type : typeReload,
							state : stateReload
						}
					});
				}
			};

			$('.demoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});

			table.on('tool(testForm)', function(obj) {
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var tr = obj.tr; //获得当前行 tr 的DOM对象
				editList = [];
				$.each(data, function(name, value) {
					editList.push(value);
				});
				if (layEvent === 'update') {
					layer
							.open({
								type : 2,
								title : "请假详情",
								skin : "myclass",
								area : [ "45%", "55%" ],
								content : [
										"requestPage?page=detailVacateApply",
										"no" ],
								success : function(layero, index) {
									var body = layer.getChildFrame('body',
											index);
									var iframeWin = window[layero
											.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
									var inputList = body.find("input");//获取到子窗口的所有的input标签
									var textareaList = body.find("textarea");
									$(inputList[0]).val(data.daySum); //遍历子窗口的input标签，将之前数组中的值一次放入显示
									$(inputList[1]).val(data.discountDay);
									$(inputList[2]).val(data.approvalDate);
									$(textareaList[0]).val(data.reason);
								},
								end : function(result) {
									window.location.reload();//刷新父页面
								}
							});
					/*渲染表单*/
					form.render();
				}
				if (layEvent === 'yes') {
					$.ajax({
						url : 'VacateController/cancelVacateApply',
						data : {
							id : data.id
						},
						async : true,//是否为异步请求
						cache : false,//是否缓存结果
						type : 'post',//请求方式为POST
						dataType : 'json',
						scriptCharset : 'utf-8',
						success : function(result) 
						{
							if (result.status) 
							{
								layer.msg(result.message, {icon : 1,time : 1000}, function() 
								{
									window.location.reload();//刷新父页面
								});
							} 
							else {layer.msg(result.message, {icon : 2,time : 1000}, function() 
							{
								/* window.location.reload();//刷新父页面 */
							});
							}
						}
					});
				}
			});

			$("#submitFeedBackBtn").click(function() {
				layer.open({
					type : 1,
					title : "填写请假申请记录反馈表",
					skin : "myclass",
					area : [ "40%", "50%" ],
					content : $("#submitFeedBack")
				});
				/*渲染表单*/
				form.render();
			});
			$(document).on('click', '#submitAccount', function() {
				$.ajax({
					url : 'FeedBackController/insertFeedbackInfo',
					data : $("#submitFeedBackForm").serialize(),
					async : true,//是否为异步请求
					cache : false,//是否缓存结果
					type : 'post',//请求方式为POST
					dataType : 'json',
					success : function(result) {
						if (result.status) {
							layer.msg(result.message, {
								icon : 1
							}, function() {
								parent.layer.close();
							});
						} else {
							layer.msg(result.message, {
								icon : 2
							}, function() {
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
