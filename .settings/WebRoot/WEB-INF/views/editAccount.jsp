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
  <style>
  input:focus{
    border-color: #66afe9;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
}
  </style>
  </head>
  
  <body>
    <div style="padding:15px;" class="layui-row" id="editAccount"">
	<div class="layui-col-md10">
		<form  class="layui-form" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">工号：</label>
				<div class="layui-inline">
					<input type="text" id="jobId"  name="jobId" class="layui-input" readonly style="border:none;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码：</label>
				<div class="layui-inline">
					<input type="text" lay-verify="password" id="password" name="password" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
		        <label class="layui-form-label">部门：</label>
		        <div class="layui-input-block">
		          <select id="denameId" lay-verify="required" lay-filter="denameType">
		            <option value="11">人事部</option>
		            <option value="22">财务部</option>
		            <option value="33">行政部</option>
		            <option value="44">技术部</option>
		            <option value="66">管理层</option>
		          </select>
		        </div>
		    </div>
		    <div class="layui-form-item">
		        <label class="layui-form-label">职位：</label>
		        <div class="layui-input-block">
		          <select id="ocnameId" lay-verify="required" >
		            <option value="0">实习生</option>
		            <option value="1">员工</option>
		            <option value="2">部门经理</option>
		            <option value="3">总经理</option>
		            <option value="4">董事</option>
		            <option value="5">管理员</option>         
		          </select>
		        </div>
		    </div>
			<div class="layui-form-item">
	        <label class="layui-form-label">就职情况：</label>
	        <div class="layui-input-block">
	          <select id="state" lay-filter="leaveType">
	            <option value="在职">在职</option>
	            <option value="离职">离职</option>
	          </select>
	        </div>
      		</div>
      		
			<div class="layui-form-item">
				<div align="center">
					<button type="button" lay-submit id="submitAccount" class="layui-btn">提交</button>
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
      
      form.verify({
          	password:[
          		/^[\S]{6,12}$/
          		,'密码必须为6到12位，且不能出现空格'
          	]
          });
      
      $(document).on('click','#submitAccount',function(){
        var jobId=document.getElementById("jobId").value;
	    var password=document.getElementById("password").value;
	    var state=document.getElementById("state").value;
	    var denameId=document.getElementById("denameId").value;
	    var ocnameId=document.getElementById("ocnameId").value;
        $.ajax({
		    url:'editAccount',
			data:{
				jobId:jobId,
				password:password,
				state:state,
				denameId:denameId,
				ocnameId:ocnameId
			},
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(data){
			    if(data.status==true){
			    	layer.alert(data.msg,{icon:1,time:2000},function(){
			    		layer.close(index);
			    		window.parent.location.reload();//重载父页面，进行数据刷新
			    	});
			    }else{
			    	layer.alert("修改失败",{icon:2,time:2000});
			    }
				
			}
		});
	  });
      		
      		
      		
      		    
  });
  </script>
  </body>
</html>
