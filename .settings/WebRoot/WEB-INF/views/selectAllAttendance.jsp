<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>员工全部打卡时间</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 员工打卡时间 -</h3>
  </blockquote>
  
  <div class="layui-row" style="margin-top: 20px;">
      <div class="layui-col-md12">
          <button id="submitFeedBackBtn" class="layui-btn layui-btn-normal">
              <i class="layui-icon">&#xe654;</i>填写打卡信息反馈表
          </button>
      </div>
   </div>
   <br>
 <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
	  按日期搜索打卡记录：
	  <div class="layui-inline">
	    <input class="layui-input" name="today" id="todayReload" autocomplete="off" placeholder="0000-00-00" />
	  </div>
	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>
    
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

	<div class="layui-row" id="submitFeedBack" style="display:none;">
		<div class="layui-col-md10">
		<br>
			<form class="layui-form" id="submitFeedBackForm" method="post">
				<label class="layui-form-label">反馈类型:</label>
				<div class="layui-input-inline">
					<select name="feedbackType" lay-filter="leaveType">
						<option value="">请选择</option>
						<option value="打卡信息:上班打卡时间有误">上班打卡时间有误</option>
						<option value="打卡信息:下班打卡时间有误">下班打卡时间有误</option>
						<option value="打卡信息:加班时间有误">加班时间有误</option>
						<option value="打卡信息:早退时间有误">早退时间有误</option>
						<option value="打卡信息:迟到时间有误">迟到时间有误</option>
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
							class="layui-btn layui-btn-normal" /> 
						<input type="reset"
							value="重置" class="layui-btn layui-btn-normal" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="layui/layui.all.js" charset="utf-8"></script>
<script>
layui.use([ 'jquery', 'table', 'layer', 'form' ], function() {
		var table = layui.table;
		var layer = layui.layer;
		var form = layui.form;
		var $ = layui.$;

		table.render({
			elem : '#table' //绑定table id
			,
			url : 'AttendanceController/SelectAllAttendance' //数据请求路径
			,
			cellMinWidth : 80,
			cols : [ [ {
				type : 'numbers',
				title : '序号',
				fixed : 'left',
				align : 'center'
			}, {
				field : 'today',
				title : '打卡日期',
				align : 'center'
			}, {
				field : 'start_date',
				title : '上班打卡时间',
				align : 'center',
				sort : 'true'
			}, {
				field : 'finish_date',
				title : '下班打卡时间',
				align : 'center'
			}, {
				field : 'overtime',
				title : '加班时间(分钟)',
				align : 'center'
			}, {
				field : 'early_leave',
				title : '早退时间(分钟)',
				align : 'center',
				sort : 'true'
			}, {
				field : 'lately',
				title : '迟到时间(分钟)',
				align : 'center'
			}
			//一个工具栏  具体请查看layui官网
			] ],
			page : true //开启分页
			,
			limit : 10 //默认十条数据一页
			,
			limits : [ 10, 20, 30, 50 ] //数据分页条
			,
			id : 'test',
			text : {
				none : '暂时无打卡记录'
			}
		});

		var $ = layui.$, active = {
			reload : function() {
				var todayReload = $('#todayReload').val();
				//执行重载
				table.reload('test', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						today : todayReload
					}
				});
			}
		};

		$('.demoTable .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

		$("#submitFeedBackBtn").click(function() {
			layer.open({
				type : 1,
				title : "填写打卡信息反馈表",
				skin : "myclass",
				area : [ "40%", "55%" ],
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
