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
    <h3>- 申请员工奖惩 -</h3>
  </blockquote>
    <div style="padding:15px;" class="layui-row">
	<div class="">
		<form id="form"  class="layui-form" method="post">
		
			<div class="layui-form-item">
				<label class="layui-form-label">员工工号：</label>
				<div class="layui-inline">
					<input type="text"  lay-verify="number"  name="jobId" class="layui-input"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">员工姓名：</label>
				<div class="layui-inline">
					<input type="text" lay-verify="required"  name="name" class="layui-input"/>
				</div>
			</div>
			
			<div class="layui-form-item">
	        <label class="layui-form-label">奖惩类型：</label>
	        <div class="layui-input-block">
	          <select id="type"  lay-verify="required" name="type" lay-filter="leaveType">
	          <option value="">请选择</option>
	            <option value="奖励">奖励</option>
	            <option value="处罚">处罚</option>
	          </select>
	        </div>
      		</div>
			
			<div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">奖惩内容：</label>
			    <div class="layui-input-block">
			      <input type="text"  lay-verify="required"  name="content" class="layui-input"/>
			    </div>
			 </div>
			 
			 <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">奖惩原因：</label>
			    <div class="layui-input-block">
			      <textarea name="reason"  lay-verify="required"  class="layui-textarea"></textarea>
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
  layui.use(['form','jquery','layer','table',],function(){
      var $=layui.$;
      var layer=layui.layer;
      var form=layui.form;
      var table=layui.table;
  
      
      $(document).on('click','#submit',function(){
        $.ajax({
		    url:'addStaffRewardPunish',
			data:$("form").serialize(),
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(result){
				if(result.status==1){
					layer.alert(result.msg,{icon:2,time:2000});
				}
			    if(result.status==true){
			    	layer.alert(result.msg,{icon:1,time:2000},function(){
			    		window.location.reload();//重载父页面，进行数据刷新
			    	});
			    }else{
			    	layer.alert(result.msg,{icon:2,time:2000});
			    }
				
			}
		});
		return false;
	  });
      		
      		
      		
      		    
  });
  </script>
  </body>
</html>
