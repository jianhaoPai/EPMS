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
  
  <body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 反馈表 -</h3>
  </blockquote>
  
 <form class="layui-form" id="addAccountForm" method="post">
      <div class="layui-form-item">
        <label class="layui-form-label">反馈类型:</label>
        <div class="layui-input-block">
          <select name="feedbackType" lay-filter="leaveType">
            <option value="">请选择反馈信息类型</option>
            <option value="假期信息有误">假期信息有误</option>
            <option value="合同信息有误">合同信息有误</option>
            <option value="请假信息有误">请假信息有误</option>
            <option value="保险信息有误">保险信息有误</option>
            <option value="反馈信息有误">反馈信息有误</option>
            <option value="党信息有误">党信息有误</option>
            <option value="薪资调动信息有误">薪资调动信息有误</option>
            <option value="职位调动信息有误">职位调动信息有误</option>
          </select>
        </div>
      </div>
      
      <div class="layui-form-item">
        <label class="layui-form-label">反馈具体原因:</label>
        <div class="layui-input-block">
          <textarea name="reason" placeholder="请输入反馈具体原因" class="layui-textarea"
                    lay-verify="required"></textarea>
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
      </div>
    </form>
    <script src="layui/layui.all.js"></script>
<script>
layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
          
          $(document).on('click','#submitAccount',function(){			
				$.ajax({
					url:'FeedBackController/insertFeedbackInfo',
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
</script>
  </body>
</html>
