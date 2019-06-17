<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>所有个人职位调动记录</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 个人职位调动记录 -</h3>
  </blockquote>

	<div class="layui-row" style="margin-top: 20px;">
		<div class="layui-col-md12">
			<button id="submitFeedBackBtn" class="layui-btn layui-btn-normal">
				<i class="layui-icon">&#xe654;</i>填写职位调动记录反馈表
			</button>
		</div>
	</div>
	<br>
	<form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
      <div class="layui-inline">
        <select name="state" id="stateReload">
            <option value="">请选择审核状态</option>
            <option value="待审核">待审核</option>
            <option value="同意">同意</option>
            <option value="不同意">不同意</option>
        </select>
      </div>
      <div class="layui-inline">
        <select name="type" id="typeReload">
             <option value="">请选择职位调动类型</option>
            <option value="平调">平调</option>
            <option value="升职">升职</option>
            <option value="降职">降职</option>
        </select>
      </div>
	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
</form>

<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="update">查看详情</button>
</script>

<div class="layui-row" id="submitFeedBack" style="display:none;">
		<div class="layui-col-md10">
			<br>
			<form class="layui-form" id="submitFeedBackForm" method="post">
				<label class="layui-form-label">反馈类型:</label>
				<div class="layui-input-inline">
					<select name="feedbackType" lay-filter="leaveType">
						<option value="">请选择</option>
						<option value="职位调动信息:降职原因不属实">降职原因不属实</option>
						<option value="职位调动信息:调动未起作用">调动未起作用</option>
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
            ,url:'OccupationAdjustController/selectAllOccupationAdjustByJobId'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'writeName',title:'填写人姓名',align:'center'}
                  ,{field:'type',title:'调动类型',align:'center',sort:'true'}
                  ,{field:'submit_date', title:'提交时间',align: 'center'}
                  ,{field:'approval_date', title:'审核时间',align: 'center'}
                  ,{field:'state', title:'状态',align: 'center'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
            ,text:{
			none:'暂时无职位调动记录'
			}
          });
          
          var $ = layui.$, active = {
		    reload: function(){
		      var typeReload = $('#typeReload').val();
		      var stateReload = $('#stateReload').val();
		      var pre_departmentidReload = $('#pre_departmentidReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            type: typeReload,
		            state: stateReload,
		            pre_departmentid: pre_departmentidReload
		        }
		      });
		    }
		  };
          
          $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
			
			table.on('tool(testForm)',function(obj){
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var tr = obj.tr; //获得当前行 tr 的DOM对象
				editList=[];
				$.each(data,function(name,value){
				    editList.push(value);
				});
			    if(layEvent==='update'){
			    	layer.open({
		            type:2,
		            title:"职位调动详情表",
		            skin:"myclass",
		            area:["40%","75%"],
		            content:["requestPage?page=detailOccupationAdjust","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var textareaList = body.find("textarea"); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        var inputList = body.find("input"); 
                        $(textareaList[0]).val(data.reason);
                        $(inputList[0]).val(data.PreDepartmentName);
                        $(inputList[1]).val(data.PreOccupationName);
                        $(inputList[2]).val(data.ToDepartmentName);
                        $(inputList[3]).val(data.ToOccupationName);
		            },
		            end:function(result){
		              window.location.reload();//刷新父页面
		            }
		        });
		        /*渲染表单*/
		        form.render(); 
			    }
			});
			
			$("#submitFeedBackBtn").click(function() {
				layer.open({
					type : 1,
					title : "职位调动记录反馈表",
					skin : "myclass",
					area : [ "40%", "50%" ],
					content : $("#submitFeedBack")
				});
				/*渲染表单*/
				form.render();
			});

			$(document).on('click', '#submitAccount', function() {
				$.ajax({
					url : 'FeedBackController/insertFeedbackInfo',
					data : $("#submitFeedBackForm").serialize(),
					async : true,//是否为异步请求
					cache : false,//是否缓存结果
					type : 'post',//请求方式为POST
					dataType : 'json',
					success : function(result) {
						if (result.status) {
							layer.msg(result.message, {
								icon : 1
							}, function() {
								parent.layer.close();
							});
						} else {
							layer.msg(result.message, {
								icon : 2
							}, function() {
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
