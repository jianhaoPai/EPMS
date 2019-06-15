<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
  <head>
    <base href="<%=basePath%>">
    
    <title>企业人事管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="layui/css/layui.css"  media="all">
<style>
  .layui-form-item{
    float:left;
  }

  #loginbox{
    position:absolute;
    left:38%;
    top:20%;
    width:300px;
    height:300px;
  }
</style>
  </head>
  
  <body >
  <div id="loginbox">
  <h2 style=" margin-left:100px;" >企业人事管理系统</h2><br>
    <form class="layui-form" style="border:5px">
    <div  class="layui-form-item">
      <label class="layui-form-label"><i class="layui-icon layui-icon-username" style="font-size: 25px; color: #1E9FFF;"></i></label>
        <div class="layui-input-block">
          <input path="jobId" type="text" name="jobId" lay-verify="jobId" autocomplete="off" class="layui-input" placeholder="请输入工号" />
      </div>
    </div>
    
    <div class="layui-form-item">
      <label class="layui-form-label"><i class="layui-icon layui-icon-password" style="font-size: 25px; color: #1E9FFF;"></i></label>
      <div class="layui-input-block">
      <tr>
        <input type="password" path="password" name="password" lay-verify="password" autocomplete="off" class="layui-input" placeholder="请输入密码" />
      </tr>
      </div>
    </div>
    
    <div class="layui-form-item">
      <div  style="width: 230px; margin: 10px 0px 0px 80px;">
      <tr>
        <td><input type="button" value="登录" id="submit" lay-submit style="width: 216px;" class="layui-btn" /></td>
      </tr>
      </div>
    </div>
    
    <div style="margin:0px 0px 0px 100px;">
      <a href="resetPassword.jsp" >忘记密码？</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="requestPageNoLogin?page=Iframe" >加入我们？</a>
    </div>
    
  </form>
  </div>
  

    
    
<script src="layui/layui.all.js" charset="utf-8"></script>
<script >
layui.use(['form','layer','jquery'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,$=layui.$
	
	$(document).ready(function () {
	    if (window != top) {
	        top.location.href = location.href;
	    }
	});
	
	$(document).on('click','#submit',function(){
		$.ajax({
			url:'login',
			data:$("form").serialize(),
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(result){
				if(result){
					window.location.href="requestPage?page=main";
				}else{
					layer.msg("账号或密码错误");
				}
			}
			
		});
	});

});


</script>
  </body>
</html>
