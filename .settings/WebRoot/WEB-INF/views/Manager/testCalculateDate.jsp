<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>员工打卡数据记录</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 员工打卡数据记录 -</h3>
  </blockquote>
  
 <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
	  按日期搜索打卡记录：
	  <div class="layui-inline">
	    <input class="layui-input" name="startDate" id="startDateReload" 
	           autocomplete="off" placeholder="0000-00-00" />
	  </div>
	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>
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
			url : 'AttendanceController/selectAttendanceSumDataByDate' //数据请求路径
			,
			cellMinWidth : 80,
			cols : [ [ {
				type : 'numbers',
				title : '序号',
				fixed : 'left',
				align : 'center'
			}, 
			{
				field : 'jobId',
				title : '工号',
				align : 'center'
			}, 
			{
				field : 'overTime',
				title : '加班总时间(分钟)',
				align : 'center'
			}, {
				field : 'earlyLeave',
				title : '早退总时间(分钟)',
				align : 'center',
				sort : 'true'
			}, {
				field : 'lately',
				title : '迟到总时间(分钟)',
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
				none : '暂时无所查日期打卡数据记录'
			}
		});

		var $ = layui.$, active = {
			reload : function() {
				var startDateReload = $('#startDateReload').val();
				//执行重载
				table.reload('test', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						startDate : startDateReload
					}
				});
			}
		};

		$('.demoTable .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
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