<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
  </head>
  
  <body>
    <div style="padding:15px;" class="layui-row" id="GeneralManagerUpdateStatus"">
	<div class="layui-col-md10">
		<form  class="layui-form" method="post" id="submitFeedBackForm">
		<div class="layui-form-item">
				<label class="layui-form-label">事项id：</label>
				<div class="layui-inline">
					<input type="text" id="id"  name="id" class="layui-input"/>
				</div>
			</div>
	<div class="layui-form-item">
				<label class="layui-form-label">面试人员：</label>
				<div class="layui-inline">
					<input type="text" id="interview_id"  name="interview_id" class="layui-input"/>
				</div>
			</div>
<div class="layui-form-item">
			<label class="layui-form-label">面试时间:</label>
			<div class="layui-input-block">
				<input name="interviewDate" class="layui-input" id="date" type="text"
					   placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date">
			</div>
		</div>
   
   <div class="layui-form-item">
	        <label class="layui-form-label">状态：</label>
	        <div class="layui-input-block">
	          <select name="status" lay-filter="leaveType">
	            <option value="通过">通过</option>
	            <option value="未通过">未通过</option>
	          </select>
	        </div>
      </div>
      <div class="layui-form-item">
				<div align="center">
					<button type="button" id="submitAccount" class="layui-btn">提交</button>
				</div>
			</div>
		</form>
	</div>
</div>
 <script src="layui/layui.all.js" charset="utf-8"></script>
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
							
      $(document).on('click','#submitAccount',function(){
				$.ajax({
					url:'ResumeController/updateAllExternalResume',
					data:$("#submitFeedBackForm").serialize(),
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',//改为text
					success:function(result){
						console.log(result);
						if(result){ 
							layer.alert("提交成功！",function(){
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
							});	
						}else{
							layer.alert("提交失败，不可重复审批！",function(){
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
					        });	 
						}
					}
				});
			});
  });
  });
  </script>
  </body>
</html>
