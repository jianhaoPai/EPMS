<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>所有劳动合同信息</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 劳动合同信息 -</h3>
  </blockquote>
  
  <div class="layui-row" style="margin-top: 20px;">
      <div class="layui-col-md12">
          <button id="submitFeedBackBtn" class="layui-btn layui-btn-normal">
              <i class="layui-icon">&#xe654;</i>填写劳动合同信息反馈表
          </button>
      </div>
   </div>
<br>
	<form class="layui-form layui-form-pane" action="">

		<div class="demoTable">
		合同开始时间: 
			<div class="layui-inline">
		      <input class="layui-input" name="startDate"
					id="startDateReload" autocomplete="off" placeholder="0000-00-00" />
			</div>
			<div class="layui-inline">
				<select name="contractType" id="contractTypeReload">
					<option value="">请选择合同类型</option>
					<option value="正式合同">正式合同</option>
					<option value="实习合同">实习合同</option>
				</select>
			</div>
			<button type="button" class="layui-btn" data-type="reload">搜索</button>
		</div>
	</form>

	<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>


	<div class="layui-row" id="submitFeedBack" style="display:none;">
		<div class="layui-col-md10">
			<br>
			<form class="layui-form" id="submitFeedBackForm" method="post">
				<label class="layui-form-label">反馈类型:</label>
				<div class="layui-input-inline">
					<select name="feedbackType" lay-filter="leaveType">
						<option value="">请选择</option>
						<option value="合同信息:合同开始时间有误">合同开始时间有误</option>
						<option value="合同信息:合同结束时间有误">合同结束时间有误</option>
						<option value="合同信息:缺少某一合同信息">缺少某一合同信息</option>
					</select>
				</div>
				<div class="layui-row" style="margin-top: 20px;">
					<div class="layui-col-md12">
						<label class="layui-form-label">具体原因:</label>
						<div class="layui-input-inline">
							<textarea name="reason" placeholder="请输入反馈具体原因"
								class="layui-textarea" lay-verify="required"></textarea>
						</div>
					</div>
				</div>
				<br>
				<div class="layui-form-item">
					<div align="center">
						<input type="button" lay-submit id="submitAccount" value="提交"
							class="layui-btn layui-btn-normal" /> <input type="reset"
							value="重置" class="layui-btn layui-btn-normal" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="layui/layui.all.js" charset="utf-8"></script>
<script>
//JavaScript代码区域

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var $=layui.$;
			
            table.render({
            elem: '#table'  //绑定table id
            ,url:'ContractController/selectContract'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	  {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'startDate', title:'合同开始时间',align: 'center',sort:'true'}
                  ,{field:'finishDate',title:'合同结束时间',align:'center'}
                  ,{field:'contractType',title:'合同类型',align:'center'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
            ,text:{
			none:'暂无合同信息'
			}
          });
          
          var $ = layui.$, active = {
		    reload: function(){
		      var startDateReload = $('#startDateReload').val();
		      var contractTypeReload = $('#contractTypeReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            startDate: startDateReload,
		            contractType: contractTypeReload
		        }
		      });
		    }
		  };
          
          $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
          
           $("#submitFeedBackBtn").click(function(){
            layer.open({
              type:1,
              title:"填写劳动合同信息反馈表",
              skin:"myclass",
              area:["40%","50%"],
              content:$("#submitFeedBack")
            });
            /*渲染表单*/
            form.render();            
          });
          
          $(document).on('click','#submitAccount',function(){
				$.ajax({
					url:'FeedBackController/insertFeedbackInfo',
					data:$("#submitFeedBackForm").serialize(),
					async:true,//是否为异步请求
					cache:false,//是否缓存结果
					type:'post',//请求方式为POST
					dataType:'json',
					success : function(result) {
					if (result.status) {
						layer.msg(result.message, {icon : 1}, function() 
						{
						   parent.layer.close();
						});
					} else {
						layer.msg(result.message, {icon : 2}, function() 
						{
						   parent.layer.close();
						});
					}
				}
			});
		});
});

</script>
</body>
</html>
