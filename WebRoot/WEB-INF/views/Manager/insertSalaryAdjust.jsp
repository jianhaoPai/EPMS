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
<link rel="stylesheet" type="text/css" href="layui/css/layui.css"
	media="all">
<style>
.layui-form-item .layui-form-label {
	width: 100px;
}

.layui-form-item .layui-input-block {
	margin-left: 130px;
}

.layui-inline .layui-form-label {
	width: 100px;
}

.layui-inline .layui-input-inline {
	margin-left: 0px;
}
</style>
</head>

<body>
	<blockquote class="layui-elem-quote layui-text">
		<h3>- 薪资调动申请表 -</h3>
	</blockquote>
	
   <form class="layui-form" id="addAccountForm" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">被申请人姓名:</label>
			<div class="layui-input-block">
				<input type="text" name="passiveName" placeholder="请输入事项直接关系人"
					autocomplete="off" class="layui-input" lay-verify="required">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">薪资调动类型:</label>
			<div class="layui-input-block">
				<select name="salaryadjustType" lay-filter="leaveType">
				    <option value="">请选择</option>
					<option value="加薪">加薪</option>
					<option value="降薪">降薪</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">薪资调动金额:</label>
			<div class="layui-input-block">
				<input type="text" name="salaryadjustMoneny"
					placeholder="可具体到小数点后两位" autocomplete="off" class="layui-input"
					lay-verify="required|number|salaryadjustMoneny">
			</div>
		</div>

		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">申请事项原因:</label>
			<div class="layui-input-block">
				<textarea name="apply.reason" placeholder="请输入调动薪资的具体原因"
					class="layui-textarea" lay-verify="required"></textarea>
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
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  var $=layui.$;
  
  //日期
  laydate.render({
    elem: '#startDate'
  });
  laydate.render({
    elem: '#finishDate'
  });
  
  //日期时间范围
  laydate.render({
    elem: '#test1'
    ,type: 'datetime'
    ,range: true
  });
  
  form.verify
  ({
    salaryadjustMoneny : function(value) 
	{
		if (value<=0) 
		{
			return '调动金额必须大于0';
		}
	}
	});
	
   $(document).on('click','#submitAccount',function(){			
				$.ajax({
					url:'SalaryAdjustController/insertSalaryAdjust',
					data:$("#addAccountForm").serialize(),
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success : function(result) {
					if (result.status) {
						layer.msg(result.message, {icon : 1}, function() 
						{
						});
					} else {
						layer.msg(result.message, {icon : 2}, function() {
						});
					}
				}
			});
		});
});
</script>
</body>
</html>
