<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试的用户展示页</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">

</head>
<body>

  
  <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
	  &nbsp;&nbsp;搜索管理：
      <div class="layui-inline">
        <select name="menuNameReload" id="menuNameReload">
          <option value="">请选择管理</option>
          <option value="人事档案管理">人事档案管理</option>
          <option value="薪酬管理">薪酬管理</option>
          <option value="绩效管理">绩效管理</option>
          <option value="考勤管理">考勤管理</option>
          <option value="培训管理">培训管理</option>
          <option value="招聘管理">招聘管理</option>
          <option value="权限管理">权限管理</option>
        </select>
      </div>

	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>
  
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

<div id="viewmember" style="display:none;">
	<table class="layui-table" id="membertable" border="5px" lay-filter="peopleForm"></table>
</div>



<div style="display:none;">
<input type="text" id="aaa" />
</div>

<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
</script>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
//JavaScript代码区域

layui.use(['jquery','table','layer','form'],function(){
			var table=layui.table;
			var layer=layui.layer;
			var form=layui.form;
			var id=parent.layui.$("#aaa").val();

            table.render({
            elem: '#table'  //绑定table id
            ,url:'showGroupsLackRights?id='+id  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'menu_name',title:'名称',align:'center'}
                  ,{field:'mark',title:'备注',align:'center'}
                  ,{field:'pare_name', title:'所属管理',align: 'center',sort:'true'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test' 
            ,text:{
				none:'没有需要添加的权限'
			} 
          });
          
          var $ = layui.$, active = {
		    reload: function(){
		      var menuNameReload=$('#menuNameReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            menuName:menuNameReload
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
				url:'addGroupsLackRights',
				data:{
					menuId:data.menuId,
					id:id
				},
				async:true,//是否为异步请求
				cache:false,//是否缓存结果
				type:'post',//请求方式为POST
				dataType:'json',
				success:function(result){
					if(result.status){
						layer.msg(result.msg,{icon:1,time:500},function(){
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