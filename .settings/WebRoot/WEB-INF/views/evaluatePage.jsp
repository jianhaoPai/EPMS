<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加评价</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" type="text/css" href="layui/css/layui.css"  media="all">
  </head>
  
  <body>
    <blockquote class="layui-elem-quote layui-text">
      <h3>- 绩效评价 -</h3>
    </blockquote>
    <form id="form" class="layui-form" >
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
      <legend>一.工作能力</legend>
    </fieldset>
        <div class="layui-col-sm6">1.是否按时完成工作<input id="pj1" name="assess1Score" type="text" style="display:none;"/></div><div class="layui-col-sm6" id="test1" ></div>
        <div class="layui-col-sm6">2.完成工作效率<input id="pj2" name="assess2Score" type="text" style="display:none;"/></div><div class="layui-col-sm6" id="test2"></div>
        <div class="layui-col-sm6">3.完成工作质量<input id="pj3" name="assess3Score" type="text" style="display:none;"/></div><div class="layui-col-sm6" id="test3"></div>
        <div class="layui-col-sm6">4.工作中错误次数<input id="pj4" name="assess4Score" type="text" style="display:none;"/></div><div class="layui-col-sm6" id="test4"></div>
        <div class="layui-col-sm6">5.对工作的负责程度<input id="pj5" name="assess5Score" type="text" style="display:none;"/></div><div class="layui-col-sm6" id="test5"></div>
    
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
      <legend>二.工作态度</legend>
    </fieldset>
        <div class="layui-col-sm6">1.主动工作和承担责任的态度<input id="pj6" name="assess6Score" type="text" style="display:none;"/></div><div id="test6"></div><br>
        <div class="layui-col-sm6">2.遵守规章制度情况和时间观念<input id="pj7" name="assess7Score" type="text" style="display:none;"/></div><div id="test7"></div><br>
        <div class="layui-col-sm6">3.服务意识方面<input id="pj8" name="assess8Score" type="text" style="display:none;"/></div><div id="test8"></div><br>
        <div class="layui-col-sm6">4.热情度、信用度、责任性<input id="pj9" name="assess9Score" type="text" style="display:none;"/></div><div id="test9"></div><br>
        <div class="layui-col-sm6">5.纪律性、团队协作精神<input id="pj10" name="assess10Score" type="text" style="display:none;"/></div><div id="test10"></div><br>
    
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
      <legend>三.业务能力</legend>
    </fieldset>
        <div class="layui-col-sm6">1.文字表达能力<input id="pj11" name="assess11Score" type="text" style="display:none;"/></div><div id="test11"></div><br>
        <div class="layui-col-sm6">2.逻辑思维能力<input id="pj12" name="assess12Score" type="text" style="display:none;"/></div><div id="test12"></div><br>
        <div class="layui-col-sm6">3.指导辅助能力<input id="pj13" name="assess13Score" type="text" style="display:none;"/></div><div id="test13"></div><br>
        <div class="layui-col-sm6">4.人际交往能力<input id="pj14" name="assess14Score" type="text" style="display:none;"/></div><div id="test14"></div><br>
        <div class="layui-col-sm6">5.组织、管理与协调能力<input id="pj15" name="assess15Score" type="text" style="display:none;"/></div><div id="test15"></div><br>
        
        
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
      <legend>四.个人素质</legend>
    </fieldset>
        <div class="layui-col-sm6">1.岗位职责履行情况<input id="pj16" name="assess16Score" type="text" style="display:none;"/></div><div id="test16"></div><br>
        <div class="layui-col-sm6">2.完成工作规定值标准及准确性<input id="pj17" name="assess17Score" type="text" style="display:none;"/></div><div id="test17"></div><br>
        <div class="layui-col-sm6">3.指定期限内完成指定工作的数量<input id="pj18" name="assess18Score" type="text" style="display:none;"/></div><div id="test18"></div><br>
        <div class="layui-col-sm6">4.工作量及难度<input id="pj19" name="assess19Score" type="text" style="display:none;"/></div><div id="test19"></div><br>
        <div class="layui-col-sm6">5.日常行为<input id="pj20" name="assess20Score" type="text" style="display:none;"/></div><div id="test20"></div><br>

	<div style="display:none;">
		<input type="text" name="evaluateId" />
		<input type="text" name="assessDate"/>
		<input type="text" name="type"/>
	</div>
	

    <div class="layui-form-item">
    <div class="layui-input-block" align="center">
      <button type="button" id="submit" class="layui-btn" lay-submit lay-filter="*">立即提交</button>
    </div>
    </div>
    </form>
    <script src="layui/layui.js"></script>
<script>
layui.use(['jquery','layer','rate'],function(){
  var rate = layui.rate;
  var $=layui.$;
  var layer=layui.layer;
  var index=parent.layer.getFrameIndex(window.name);
  
  
  $(document).on('click','#submit',function(){
		$.ajax({
			url:'addPerform',
			data:$("form").serialize(),
			async:true,//是否为异步请求
			cache:false,//是否缓存结果
			type:'post',//请求方式为POST
			dataType:'json',
			success:function(result){
			console.log(result);
				if(result){
					layer.alert("评价成功",{offset:'200px'},function(){
						parent.layer.closeAll();//关闭当前弹窗

					});	
				}else{
					layer.alert("评价失败",function(){
						parent.layer.close();//关闭当前弹窗
					});	
				}
			}
			
		});
	});
  //自定义文本
  rate.render({
    elem: '#test1'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj1").val(value);
    }
  })
  
  rate.render({
    elem: '#test2'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj2").val(value);
    }
  })
  
  rate.render({
    elem: '#test3'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj3").val(value);
    }
  })
  
  rate.render({
    elem: '#test4'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj4").val(value);
    }
  })
  
  rate.render({
    elem: '#test5'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj5").val(value);
    }
  })
  
  rate.render({
    elem: '#test6'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj6").val(value);
    }
  })
  
  rate.render({
    elem: '#test7'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj7").val(value);
    }
  })
  
  rate.render({
    elem: '#test8'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj8").val(value);
    }
  })
  
  rate.render({
    elem: '#test9'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj9").val(value);
    }
  })
  
  rate.render({
    elem: '#test10'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj10").val(value);
    }
  })
  
  rate.render({
    elem: '#test11'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj11").val(value);
    }
  })
  
  rate.render({
    elem: '#test12'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj12").val(value);
    }
  })
  
  rate.render({
    elem: '#test13'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj13").val(value);
    }
  })
  
  rate.render({
    elem: '#test14'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj14").val(value);
    }
  })
  
  rate.render({
    elem: '#test15'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj15").val(value);
    }
  })
  
  rate.render({
    elem: '#test16'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj16").val(value);
    }
  })
  
  rate.render({
    elem: '#test17'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj17").val(value);
    }
  })
  
  rate.render({
    elem: '#test18'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj18").val(value);
    }
  })
  
  rate.render({
    elem: '#test19'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj19").val(value);
    }
  })
  
  rate.render({
    elem: '#test20'
    ,value: 3
    ,text: true
    ,setText: function(value){ //自定义文本的回调
      var arrs = {
        '1': '极差'
        ,'2': '差'
        ,'3': '中等'
        ,'4': '好'
        ,'5': '极好'
      };
      this.span.text(arrs[value] || ( value + "星"));
      $("#pj20").val(value);
    }
  })
  
  
});
</script>    

  </body>
</html>
