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
  <div class="layui-row" >
	<div class="layui-col-md10">
		<form  class="layui-form" id="editForm" method="post">
			<div class="layui-form-item"><br>
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="required" id="name" name="name" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">所属人员：</label>
				<div class="layui-input-block">
					<input type="text" lay-verify="required" id="content" name="content" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<div align="center">
					<input type="button" lay-submit id="submitBtn" value="保存"  class="layui-btn layui-btn-normal"/>
				</div>
			</div>
		</form>
	</div>
	</div>
	<div style="display:none;">
		<input type="text"  id="oldName" name="oldName" />
	</div>

  <script src="layui/layui.js" charset="utf-8"></script>
  <script>
  layui.use(['form','jquery','layer'],function(){
      var $=layui.$;
      var layer=layui.layer;
      var form=layui.form;
      var index=parent.layer.getFrameIndex(window.name);
      $(document).on('click','#submitBtn',function(){
        var name=document.getElementById("name").value;
	    var content=document.getElementById("content").value;
	    var oldName=document.getElementById("oldName").value;
        $.ajax({
		    url:'editRightsGroups',
			data:{
				name:name,
				content:content,
				oldName:oldName
			},
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(data){
			    if(data){
			    	layer.alert("保存成功",{icon:1,time:2000},function(){
			    		layer.close(index);
			    		window.parent.location.reload();//重载父页面，进行数据刷新
			    	});
			    }else{
			    	layer.alert("保存失败",{icon:2,time:2000});
			    }
				
			}
		});
	  });
      		
      		
      		
      		    
  });
  </script>
  </body>
</html>
