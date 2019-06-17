<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>企业人事管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
#wrap {
	height: 100%;
	width: 100%;
	background-image:url(images/bg1.png);
	background-repeat: no-repeat;
	background-position:center;
	position: relative;
}
#head {
	height: 120px;
	width: 100%;
	background-color:#A6CAF0;
	text-align: center;
	position: relative;
}
#foot {
	width: 100%;
	height: 126px;
	background-color: #CC9933;
	position: relative;
}
#wrap .logGet {
	height: 408px;
	width: 368px;  
	position: absolute;
	background-color: #FFFFFF;
	top: 20%;
	right: 35%;
}
.logC a button {
	width: 100%;
	height: 45px;
	background-color: #ee7700;
	border: none;
	color: white;
	font-size: 18px;
}
.logGet .logD.logDtip .p1 {
	display: inline-block;
	font-size: 28px;
	margin-top: 30px;
	width: 86%;
}
#wrap .logGet .logD.logDtip {
	width: 86%;
	border-bottom: 1px solid #ee7700;
	margin-bottom: 60px;
	margin-top: 0px;
	margin-right: auto;
	margin-left: auto;
}
.logGet .lgD img {
	position: absolute;
	top: 12px;
	left: 8px;
}
.logGet .lgD input {
	width: 100%;
	height: 42px;
	text-indent: 2.5rem;
}
#wrap .logGet .lgD {
	width: 86%;
	position: relative;
	margin-bottom: 30px;
	margin-top: 30px;
	margin-right: auto;
	margin-left: auto;
}
#wrap .logGet .logC {
	width: 86%;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
}
 
 
.title {
	font-family: "宋体";
	color: #FFFFFF;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
	font-size: 36px;
	height: 40px;
	width: 30%;
}
 
.copyright {
	font-family: "宋体";
	color: #FFFFFF;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
	height: 60px;
	width: 40%;
	text-align:center;
}
	
 
#foot .copyright .img {
	width: 100%;
	height: 24px;
	position: relative;
}
.copyright .img .icon {
	display: inline-block;
	width: 24px;
	height: 24px;
	margin-left: 22px;
	vertical-align: middle;
	background-image:url(images/email.jpeg);
	background-repeat: no-repeat;
	vertical-align: middle;
	margin-right: 5px;
}
	
.copyright .img .icon1 {
	display: inline-block;
	width: 24px;
	height: 24px;
	margin-left: 22px;
	vertical-align: middle;
	background-image:url(images/house.jpeg);
	background-repeat: no-repeat;
	vertical-align: middle;
	margin-right: 5px;
}
.copyright .img .icon2 {
	display: inline-block;
	width: 24px;
	height: 24px;
	margin-left: 22px;
	vertical-align: middle;
	background-image:url(images/phone.jpeg);
	background-repeat: no-repeat;
	vertical-align: middle;
	margin-right: 5px;
}
#foot .copyright p {
	height: 24px;
	width: 100%;
}
#div{
	margin:10px;
	font-family: "宋体";
	color:#000;
	text-decoration:none;
	float:right;
	text-align:right;
}
</style>
  </head>
  
  <body >
    
    <div class="wrap" id="wrap">
    <form>
	<div class="logGet">
			<!-- 头部提示信息 -->
			<div class="logD logDtip">
				<p class="p1">登录</p>
			</div>
			<!-- 输入框 -->
			<div class="lgD">
				<img src="images/user.jpeg" width="20" height="20" alt=""/>
				<input type="text" name="jobId" lay-verify="jobId" autocomplete="off" class="layui-input" placeholder="请输入工号" />
			</div>
			<div class="lgD">
				<img src="images/lock.jpeg" width="20" height="20" alt=""/>
				<input type="password" name="password" lay-verify="password" autocomplete="off" class="layui-input" placeholder="请输入密码" />
			</div>
			<div class="logC">
				<a href="javascript:;"><button type="button" id="submit">登 录</button></a>
			    <a href="requestPageNoLogin?page=resetPassword" id="div">忘记密码？</a>
                <a href="requestPageNoLogin?page=Iframe" id="div">加入我们？</a>
            </div>
		</div>
		</form>
</div>
    
  
  
  
<div class="footer" id="foot">
  <div class="copyright">
    <p>Copyright © 2019 Qunar.com Inc. All Rights Reserved.</p>
    <div class="img">
		<i class="icon"></i><span>联系邮箱：jiankangsun@yahoo.com</span>
	</div>
	
	<div class="img">
		<i class="icon1"></i><span>联系地址：吉林大学珠海学院</span>
	</div>
	  
	<div class="img">
	  <i class="icon2"></i><span>联系电话：13750092063</span>
	</div>
 
 
  </div>
	
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
