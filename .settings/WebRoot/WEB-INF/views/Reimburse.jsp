<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Reimburse.jsp' starting page</title>
    
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
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 报销费用 -</h3>
  </blockquote>
    <div style="padding:15px;" class="layui-row"  id="reimburse"">
	<div class="">
		<form id="form"  class="layui-form" method="post">
			<div class="layui-form-item">
	        <label class="layui-form-label">报销类型：</label>
	        <div class="layui-input-block">
	          <select id="type" lay-verify="required" name="type" lay-filter="leaveType">
	          <option value="">请选择</option>
	            <option value="办公费">办公费</option>
	            <option value="差旅费">差旅费</option>
	            <option value="交通费">交通费</option>
	          </select>
	        </div>
      		</div>
			
			<div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">报销内容：</label>
			    <div class="layui-input-block">
			      <textarea name="content" lay-verify="required"  class="layui-textarea"></textarea>
			    </div>
			 </div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">时间：</label>
				<div class="layui-inline">
					<input type="text" id="date" lay-verify="date"  name="time" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">金额：</label>
				<div class="layui-inline">
					<input type="text" lay-verify="required|number" id="amount" name="amount" class="layui-input"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<div align="center">
					<button type="button" id="submit" lay-submit class="layui-btn">提交</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="layui/layui.js" charset="utf-8"></script>
  <script>
  layui.use(['form','jquery','layer','table','laydate'],function(){
      var $=layui.$;
      var layer=layui.layer;
      var form=layui.form;
      var table=layui.table;
      var laydate=layui.laydate;
      var index=parent.layer.getFrameIndex(window.name);
      
      laydate.render({
	    elem: '#date'
	  });
      
      $(document).on('click','#submit',function(){
        $.ajax({
		    url:'addReimburse',
			data:$("form").serialize(),
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(result){
			    if(result.status==true){
			    	layer.alert(result.msg,{icon:1,time:2000},function(){
			    		window.location.reload();//重载父页面，进行数据刷新
			    	});
			    }else{
			    	layer.alert(result.msg,{icon:2,time:2000});
			    }
				
			}
		});
	  });
      		
      		
      		
      		    
  });
  </script>
  </body>
</html>
