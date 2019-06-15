<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>所有员工联系方式</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 员工联系方式 -</h3>
  </blockquote>
  <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
       <div class="layui-inline">
	    <input class="layui-input" name="jobId" id="jobIdReload" autocomplete="off" placeholder="输入工号查询" />
	  </div>
	  <div class="layui-inline">
	    <input class="layui-input" name="name" id="nameReload" autocomplete="off" placeholder="输入姓名查询" />
	  </div>
      <div class="layui-inline">
        <select name="departmentId" id="departmentIdReload">
            <option value="">请选择部门</option>
            <option value="11">人事部</option>
            <option value="22">财务部</option>
            <option value="33">行政部</option>
            <option value="44">技术部</option>
            <option value="66">管理层</option>
        </select>
      </div>
	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>

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
            ,url:'CommunicationController/selectAllCom'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	  {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'job_id', title:'工号',align: 'center',sort:'true'}
                  ,{field:'name', title:'姓名',align: 'center',sort:'true'}
                  ,{field:'department_name',title:'部门',align:'center',sort:'true'}
                  ,{field:'occupation_name',title:'职位',align:'center'}
                  ,{field:'office_phone',title:'办公室电话',align:'center'}
                  ,{field:'email',title:'邮箱',align:'center',sort:'true'}
                  ,{field:'office_address', title:'办公室地址',align: 'center'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
          });
          
           var $ = layui.$, active = {
		    reload: function(){
		      var departmentIdReload = $('#departmentIdReload').val();
		      var jobIdReload = $('#jobIdReload').val();
		      var nameReload = $('#nameReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            jobId: jobIdReload,
		            departmentId: departmentIdReload,
		            name: nameReload
		        }
		      });
		    }
		  };
          
          $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
          
});

</script>
</body>
</html>
