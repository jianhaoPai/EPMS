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
   <div style="padding:15px;" class="layui-row" id="editAccount"">
	<div class="layui-col-md10">
		<form  class="layui-form" method="post" id="submitFeedBackForm">
		   <div class="layui-form-item">
				<label class="layui-form-label">事项id:</label>
				<div class="layui-inline">
					<input type="text" id="applyId"  name="applyId" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">调动类型:</label>
				<div class="layui-inline">
					<input type="text" id="salaryadjustType"  name="salaryadjustType" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">调动金额:</label>
				<div class="layui-inline">
					<input type="text" id="salaryadjustMoneny"  name="salaryadjustMoneny" class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">具体原因:</label>
				<div class="layui-inline">
				<textarea id="reason" name="reason" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
	        <label class="layui-form-label">审批事项:</label>
	        <div class="layui-input-block">
	          <select name="state" lay-filter="leaveType">
	            <option value="同意">同意</option>
	            <option value="不同意">不同意</option>
	          </select>
	        </div>
	        </div>
	       
			<div class="layui-form-item">
				<div align="center">
				    <input type="button" id="submitAccount" value="提交"  class="layui-btn layui-btn-normal"/>
				</div>
			</div>
		</form>
	</div>
</div>

  <script src="layui/layui.all.js" charset="utf-8"></script>
  <script>
  layui.use(['form','jquery','layer'],function(){
      var $=layui.$;
      var layer=layui.layer;
      var form=layui.form;
      var index=parent.layer.getFrameIndex(window.name);
	  
	  $(document).on('click','#submitAccount',function(){
				$.ajax({
					url:'SalaryAdjustController/updateSalaryAdjust',
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
  </script>
  </body>
</html>