<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改假期天数</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<!-- 需要弹出的添加账号界面 -->
<div class="layui-row" id="addAccount" >
	<div class="layui-col-md10">
		<form  class="layui-form" id="addAccountForm" method="post">
			<div class="layui-form-item"><br>
				<label class="layui-form-label">假期类型：</label>
				<div class="layui-inline">
					<input type="text" id="vacationType" name="vacationType" 
					       class="layui-input" readonly/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">天数：</label>
				<div class="layui-inline">
					<input type="text"  id="day" name="day" class="layui-input" 
					       lay-verify="required|number|day"/>
				</div>
			</div>
			<div class="layui-form-item">
				<div align="center">
					<input type="button" lay-submit id="submitAccount" value="提交"  
					       class="layui-btn layui-btn-normal"/>
					<input type="reset" value="重置" class="layui-btn layui-btn-normal"/>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="layui/layui.js" charset="utf-8"></script>
<script>

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
          
          form.verify({
          	day: function(value){
                 if(value<0){
                 return '天数不能小于0';}
              }
          });
          
          $(document).on('click','#submitAccount',function(){
			    var day=document.getElementById("day").value;
			    var vacationType=document.getElementById("vacationType").value;
				
				$.ajax({
					url:'VacationRuleController/updateVacationRule',
					data:{
						day:day,
						vacationType:vacationType
					},
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success:function(result)
					{
						if(result.status)
						{
							layer.msg(result.message,{icon:1},function()
							{
								window.location.reload();//刷新父页面
								parent.layer.close();//关闭当前弹窗
							});	
						}else{
							layer.msg(result.message,{icon:2},function()
							{
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
