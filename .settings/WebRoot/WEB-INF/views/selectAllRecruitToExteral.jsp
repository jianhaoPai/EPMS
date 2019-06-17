<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>外部人员查询招聘</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body>
<blockquote class="layui-elem-quote layui-text">
    <h3>- 外部人员查询招聘 -</h3>
 </blockquote>
<table class="layui-table" id="table" border="5px" lay-filter="testForm"></table>
<script type="text/html" id="barDemo">
  <button class="layui-btn layui-btn-sm" lay-event="select">查看详情</button>
  <button class="layui-btn layui-btn-sm" lay-event="submit">提交简历</button>
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
            ,url:'RecruitController/selectAllRecruitToEmployee'  //数据请求路径
            ,cellMinWidth: 80
            ,cols: [[
            	   {type:'numbers',title:'序号',fixed:'left',align: 'center'}
                  ,{field:'department_name', title:'招聘部门',align: 'center',sort:'true'}
                  ,{field:'occupation_name', title:'招聘职务',align: 'center',sort:'true'}
                  ,{field:'sum',title:'人数',align:'center'}
                  ,{fixed:'right',title:'操作',toolbar:'#barDemo'}
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
			    if(layEvent==='select'){
			    	layer.open({
		            type:2,
		            title:"查看详情",
		            skin:"myclass",
		            area:["35%","45%"],
		            content:["requestPageNoLogin?page=detailSelectAllRecruitByWriteId","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        var textList = body.find("textarea");
                        $(inputList[0]).val(data.id);
                        $(textList[0]).val(data.demand); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(textList[1]).val(data.function_introduce);
		            },
		            end:function(result){
		              window.location.reload();//刷新父页面
		            }
		        });
		        /*渲染表单*/
		        form.render(); 
			    }
			     if(layEvent==='submit'){
			    	layer.open({
		            type:2,
		            title:"提交简历",
		            skin:"myclass",
		            area:["60%","100%"],
		            content:["requestPageNoLogin?page=ExternalResume","no"],
		            success:function(layero,index){
		                var body = layer.getChildFrame('body', index);  
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();  
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        $(inputList[0]).val(data.department_name); 
                        $(inputList[1]).val(data.occupation_name);
		            },
		            end:function(result){
		              window.location.reload();//刷新父页面
		            }
		        });
		        form.render(); 
			    }
			});
			 
});		
</script>
</body>
</html>