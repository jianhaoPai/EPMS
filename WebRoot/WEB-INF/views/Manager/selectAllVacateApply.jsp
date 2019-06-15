<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>所有直接下属提交的请假申请信息</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 员工请假申请信息-</h3>
  </blockquote>
  
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
            <option value="">请选择请假类型</option>
            <option value="年假">年假</option>
            <option value="产假">产假</option>
            <option value="事假">事假</option>
        </select>
      </div>
      <div class="layui-inline">
        <select name="department_id" id="department_idReload">
            <option value="">请选择部门</option>
            <option value="11">财务部</option>
            <option value="22">人事部</option>
            <option value="33">行政部</option>
            <option value="66">管理层</option>
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
            ,url:'VacateController/selectAllVacateApply'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'name',title:'姓名',align:'center'}
                  ,{field:'department_name',title:'部门',align:'center',sort:'true'}
                  ,{field:'type',title:'请假类型',align:'center',sort:'true'}
                  ,{field:'day_sum',title:'请假天数',align:'center',sort:'true'}
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
			none:'暂时无员工请假申请记录'
			}
          });
          
          var $ = layui.$, active = {
		    reload: function(){
		      var typeReload = $('#typeReload').val();
		      var stateReload = $('#stateReload').val();
		      var department_idReload = $('#department_idReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            type: typeReload,
		            state: stateReload,
		            department_id: department_idReload
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
		            title:"审批请假表",
		            skin:"myclass",
		            area:["45%","90%"],
		            content:["requestPage?page=Manager/detailVacateApply","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        var textareaList = body.find("textarea");
                        $(inputList[0]).val(data.id); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(inputList[1]).val(data.start_date); 
                        $(inputList[2]).val(data.finish_date); 
                        $(textareaList[0]).val(data.reason);
                        $(inputList[3]).val(data.cancel_date); 
		            },
		            end:function(result){
		              window.location.reload();//刷新父页面
		            }
		        });
		        /*渲染表单*/
		        form.render(); 
			    }
			});
			
			
          
});

</script>
</body>
</html>
