<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>内部员工提交的全部简历信息</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 内部员工提交的全部简历信息 -</h3>
  </blockquote>
  
   <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
      <div class="layui-inline">
        <select name="departmentId" id="departmentIdReload">
            <option value="">请选择部门</option>
            <option value="11">人事部</option>
            <option value="22">财务部</option>
            <option value="33">行政部</option>
            <option value="66">管理层</option>
        </select>
      </div>
      <div class="layui-inline">
        <select name="occupationId" id="occupationIdReload">
            <option value="">请选择职务</option>
            <option value="1">职员</option>
            <option value="2">部门经理</option>
        </select>
      </div>
      <div class="layui-inline">
        <select name="status" id="statusReload">
            <option value="">请选择状态</option>
            <option value="通过">通过</option>
            <option value="未通过">未通过</option>
            <option value="待审核">待审核</option>
        </select>
      </div>
	  <button type="button" class="layui-btn" data-type="reload">搜索</button>
  </div>
  </form>
  
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>
<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="update">查看详情</button>
</script>
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
            ,url:'InteralResumeController/selectAllInteralResume'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
            	  ,{field:'name',title:'姓名',align:'center'}
                  ,{field:'department_name',title:'申请部门',align:'center'}
                  ,{field:'occupation_name',title:'申请职位',align:'center',sort:'true'}
                  ,{field:'nowDepartment',title:'所在部门',align:'center'}
                  ,{field:'nowOccupation',title:'所在职位',align:'center',sort:'true'}
                  ,{field:'submit_date',title:'提交时间',align:'center'}
                  ,{field:'status',title:'状态',align:'center',sort:'true'}
                  ,{field:'approval_date',title:'审批时间',align:'center'}
                  ,{fixed:'right',title:'查看',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
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
		            title:"查看简历详情",
		            skin:"myclass",
		            area:["35%","45%"],
		            content:["requestPage?page=detailInteralResumeByJobId","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        var textList = body.find("textarea");//获取到子窗口的所有的input标签
                        $(inputList[0]).val(data.interview_date); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(textList[0]).val(data.work_experience);
		            },
		            end:function(result){
		              window.location.reload();//刷新父页面
		            }
		        });
		        /*渲染表单*/
		        form.render(); 
			    }
			});
			var $ = layui.$, active = {
		    reload: function(){
		      var departmentIdReload = $('#departmentIdReload').val();
		      var occupationIdReload = $('#occupationIdReload').val();
		      var statusReload = $('#statusReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            occupationId: occupationIdReload,
		            departmentId: departmentIdReload,
		            status: statusReload
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
