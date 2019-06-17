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
	
	<link rel="stylesheet" type="text/css" href="layui/css/layui.css">

  </head>
<style>
  .layui-layout-admin .layui-body{
     bottom:0;
  }
  body {
            height: 100%;width: 100%;
            background: url('layui/images/jizhu.jpg');
            background-size:cover;
        }
</style> 

<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">企业人事管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item">
        <a href="javascript:;">考勤打卡</a>
        <dl class="layui-nav-child">
          <dd><a id="CheckIn" href="javascript:;">上班打卡</a></dd>
          <dd><a id="CheckOut" href="javascript:;">下班打卡</a></dd>
        </dl>
      </li>
      
    </ul>
    
    
    
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          ${username}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">修改密码</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="outLogin">退出系统</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul id="nav" class="layui-nav layui-nav-tree"  lay-filter="test">
 		<!-- 菜单栏内容，由ajax请求查询用户所属菜单栏动态生成 -->
      </ul>
    </div>
  </div>
  
  
  
  <div  class="layui-body" >
    <!-- 内容主体区域 -->
    <div style="padding: 10px;">
		<iframe  class="layadmin-iframe"  src="requestPage?page=homePage"  name="right" frameborder="0" scrolling="auto" width="100%" height="96%"></iframe>
	</div>
  </div>
  
</div>

<script src="layui/layui.all.js"></script>
<script src="jquery/jquery-3.4.0.min.js"></script>
<script>
$(function(){
    $.ajax({
        url:'showMenuByRole?jobId='+${jobId},
        type:'post',
        dataType:'json',
        success:function(data){
            var menu="";//定义变量储存
            for(var i=0;i<data.length;i++){
                menu+="<li class='layui-nav-item'>"
                if(data[i].pareMenuId==0){
                    menu+=        "<a href='javascript:;'>"+data[i].menuName+"<span class='layui-nav-more'></span></a>"
                    for(var j=0;j<data.length;j++){//继续遍历数据
                        if(data[j].mid>data[i].mid&&data[j].mid<(data[i].mid)+1000){//取出父元素中的子元素
                            menu+=    "<dl class='layui-nav-child'>"
                            menu+=        "<dd>"
                            menu+=            "<a href='"+data[j].menuPath+"' target='right'>"+data[j].menuName+"</a>"
                            menu+=        "</dd>"
                            menu+=    "</dl>"
                           
                        }
                    }
                }
                menu+=    "</li>"
            }
            $("#nav").html(menu);
            var element=layui.element;
            element.init();//初始化element时间，使菜单展开
        }
    
    });

})

layui.use(['jquery','table','layer','form'],function(){
	var $=layui.$;
	var table=layui.table;
	var layer=layui.layer;
	var form=layui.form;
	$(document).on('click','#CheckIn',function(){
		$.ajax({
			url:'AttendanceController/CheckIn',
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'get',//请求方式为POST
			dataType:'json',
			success:function(result){
				if(result.status){
					layer.alert(result.msg);
				}
					
			}
			
		});
	});
	
	$(document).on('click','#CheckOut',function(){
		$.ajax({
			url:'AttendanceController/CheckOut',
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(result){
				if(result.status){
					layer.alert(result.msg);
				}	
			}
			
		});
	});
	
});  

</script>
</body>
</html>
