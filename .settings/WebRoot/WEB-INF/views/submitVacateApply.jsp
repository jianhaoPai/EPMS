<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'leaveRequest.jsp' starting page</title>
    
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
<blockquote class="layui-elem-quote layui-text">
    <h3>- 请假申请 -</h3>
  </blockquote>
<body>
	<form class="layui-form" method="post" id="submitFeedBackForm">
	
		<div class="layui-form-item">
			<label class="layui-form-label">请假开始时间:</label>
			<div class="layui-input-block">
				<input name="startDate" class="layui-input" id="date" type="text"
					   placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">请假结束时间:</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" name="finishDate" id="date1"
					   placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">请假类型:</label>
			<div class="layui-input-block">
				<select name="type" lay-filter="leaveType">
					<option value="">请选择</option>
					<option value="事假">事假</option>
					<option value="产假">产假</option>
					<option value="年假">年假</option>
					<option value="病假">病假</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">请假具体原因</label>
			<div class="layui-input-block">
				<textarea name="reason" placeholder="请输入请假具体原因"
					class="layui-textarea" lay-verify="required"></textarea>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<div align="center">
					<input type="button" lay-submit id="submitAccount" value="提交"
						   class="layui-btn layui-btn-normal" /> 
					<input type="reset"
						   value="重置" class="layui-btn layui-btn-normal" />
				</div>
			</div>
		</div>
	</form>
<script src="layui/layui.all.js" charset="utf-8"></script>
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
  
  $(document).on('click','#submitAccount',function()
  {
	    $.ajax({
		url:'VacateController/submitVacateApply',
		data:$("#submitFeedBackForm").serialize(),
		async:true,//是否为异步请求
		cache:false,//是否缓存结果
		type:'post',//请求方式为POST
		dataType:'json',//改为text
		success:function(result)
		{
			if(result.status)
			{
				layer.msg(result.message,{icon:1},function()
				{
					//window.location.reload();//刷新父页面
					parent.layer.close();
				});	
			}
			else
			{
				layer.msg(result.message,{icon:2},function()
				{
					//window.location.reload();//刷新父页面
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
