<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addRightsPeople.jsp' starting page</title>
    
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
		<h3>- 权限组用户 -</h3>
	</blockquote>
<form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
	  &nbsp;&nbsp;搜索用户：
	  <div class="layui-inline">
	    <input class="layui-input" name="jobId" id="jobIdReload" autocomplete="off" placeholder="输入工号查询" />
	  </div>
	  <div class="layui-inline">
	    <input class="layui-input" name="name" id="nameReload" autocomplete="off" placeholder="输入姓名查询" />
	  </div>
      <div class="layui-inline">
        <select name="dename" id="denameReload">
          <option value="">请选择部门</option>
          <option value="人事部">人事部</option>
          <option value="财务部">财务部</option>
          <option value="行政部">行政部</option>
          <option value="技术部">技术部</option>
          <option value="管理层">管理层</option>
        </select>
      </div>

	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>

<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
</script>
<script src="layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var id=parent.layui.$("#aaa").val();
			
            table.render({
            elem: '#table'  //绑定table id
            ,url:'showRightsPeople?id='+id  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'jobId', title:'工号',align: 'center',sort:'true'}
                  ,{field:'name',title:'姓名',align:'center'}
                  ,{field:'dename',title:'部门',align:'center',sort:'true'}
                  ,{field:'ocname',title:'职位',align:'center',sort:'true'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
          });
          
          var $ = layui.$, active = {
		    reload: function(){
		      $("#aaa").val(name);
		      var jobIdReload = $('#jobIdReload').val();
		      var nameReload = $('#nameReload').val();
		      var denameReload=$('#denameReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            jobId: jobIdReload,
		            name:nameReload,
		            dename:denameReload
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
		
		if(layEvent==='add'){
			$.ajax({
				url:'addRightsPeople',
				data:{
					jobId:data.jobId,
					id:id
				},
				async:true,//是否为异步请求
				cache:false,//是否缓存结果
				type:'post',//请求方式为POST
				dataType:'json',
				success:function(result){
					if(result){
						layer.msg("操作成功",{icon:1,time:1000},function(){
							window.location.reload();//刷新父页面
						});	
					}else{
						layer.msg("操作失败",{icon:2,time:1000},function(){
							window.location.reload();//刷新父页面
						});
					}
				},end:function(){
					parent.reload();
				}
			});
		}
		
	});
			
			
          
});
</script>
  </body>
</html>
