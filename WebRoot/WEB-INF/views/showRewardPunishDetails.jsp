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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
  </head>
  
  <body>
    <div style="padding:15px;" class="layui-row" id="editAccount"">
	<div class="layui-col-md10">
		<form  class="layui-form" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">姓名：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input"  readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">奖惩类型：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">奖惩名称：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			 <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">奖惩原因：</label>
			    <div class="layui-input-block">
			      <textarea  class="layui-textarea" readonly style="border:none;"></textarea>
			    </div>
			 </div>
      		<div class="layui-form-item">
				<label class="layui-form-label">申请时间：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">申请人：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">审核人：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">审核时间：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">状态：</label>
				<div class="layui-inline">
					<input type="text"  class="layui-input" readonly style="border:none;"/>
				</div>
			</div>

		</form>
		
	</div>
</div>

  <script src="layui/layui.js" charset="utf-8"></script>
  <script>
  layui.use(['form','jquery','layer'],function(){
      var $=layui.$;
      var layer=layui.layer;
      var form=layui.form;
      var index=parent.layer.getFrameIndex(window.name);
      
      		
      		
      		
      		    
  });
  </script>
  </body>
</html>
