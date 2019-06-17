<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>查询自己提交给上级的培训计划</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
  <blockquote class="layui-elem-quote layui-text">
    <h3>- 查询自己提交给上级的培训计划 -</h3>
  </blockquote>
  
  <form class="layui-form layui-form-pane" action="">
  <div class="demoTable">
      <div class="layui-inline">
        <select name="cultivateId" id="cultivateIdReload">
            <option value="">请选择培训类型</option>
            <option value="1">内部培训</option>
            <option value="2">外出培训</option>
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
  <button class="layui-btn layui-btn-sm" lay-event="select">查看详情</button>
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
            ,url:'CultivateApplyController/selectAllCultivateApplyByWriteId'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'train_name', title:'培训名称',align: 'center',sort:'true'}
                  ,{field:'cultivate_name', title:'培训类型',align: 'center',sort:'true'}
                  ,{field:'department_name', title:'发起部门',align: 'center',sort:'true'}  
                  ,{field:'face_people', title:'面对人员类型',align: 'center',sort:'true'}               
                  ,{field:'start_date', title:'开始时间',align: 'center'}
                  ,{field:'finish_date', title:'结束时间',align: 'center'}
                  ,{field:'status',title:'状态',align:'center'} 
                  ,{fixed:'right',title:'查看详情',toolbar:'#barDemo'}
                  //一个工具栏  具体请查看layui官网
            ]]
            
           ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'test'  
          });
          
           var $ = layui.$, active = {
		    reload: function(){
		      var cultivateIdReload = $('#cultivateIdReload').val();
		      var statusReload = $('#statusReload').val();
		      //执行重载
		      table.reload('test', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		            cultivateId: cultivateIdReload,
		            status: statusReload
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
			    if(layEvent==='select'){
			    	layer.open({
		            type:2,
		            title:"查看培训计划详情",
		            skin:"myclass",
		            area:["40%","50%"],
		            content:["requestPage?page=detailSelectAllCultivateApplyByWriteId","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        var inputList = body.find("input");//获取到子窗口的所有的input标签  
                        var textList = body.find("textarea");
                        $(inputList[0]).val(data.site);
                        $(inputList[1]).val(data.approval_date);
                        $(inputList[2]).val(data.sum);
                        $(textList[0]).val(data.introduce); //遍历子窗口的input标签，将之前数组中的值一次放入显示
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
