<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>员工每月信息记录</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 员工每月信息记录(默认当前月份) -</h3>
  </blockquote>
  
  <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
       <div class="layui-inline">
	    <input class="layui-input" name="year" id="yearReload" autocomplete="off" placeholder="输入年份" />
	  </div>
	  <div class="layui-inline">
	    <input class="layui-input" name="month" id="monthReload" autocomplete="off" placeholder="输入月份" />
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
			url : 'TotalDataController/selectTotalData' //数据请求路径
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
				field : 'totalVacate',
				title : '请假天数',
				align : 'center'
			}, {
				field : 'totalEarlyleave',
				title : '早退次数',
				align : 'center',
				sort : 'true'
			}, {
				field : 'totalLately',
				title : '迟到次数',
				align : 'center'
			}, 
			{
				field : 'totalWorkOverTime',
				title : '工作日加班时长(半小时)',
				align : 'center'
			}, {
				field : 'totalNotWorkOverTime',
				title : '节假日加班时长(半小时)',
				align : 'center',
				sort : 'true'
			}, {
				field : 'totalAbsence',
				title : '缺勤天数',
				align : 'center'
			}, {
				field : 'totalCaltivateDay',
				title : '培训天数',
				align : 'center'
			}
			] ],
			page : true //开启分页
			,
			limit : 10 //默认十条数据一页
			,
			limits : [ 10, 20, 30, 50 ] //数据分页条
			,
			id : 'test',
			text : {
				none : '暂时无所查数据记录'
			}
		});

		var $ = layui.$, active = {
			reload : function() {
				var yearReload = $('#yearReload').val();
				var monthReload = $('#monthReload').val();
				//执行重载
				table.reload('test', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						year : yearReload,
						month : monthReload
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
