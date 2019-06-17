<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta charset="utf-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        function timer(opj){
            $(opj).find('ul').animate({
                marginTop : "-3.5rem"  
                },500,function(){  
                $(this).css({marginTop : "0.7rem"}).find("li:first").appendTo(this);  
            })  
        }
        $(function(){ 
            var num = $('.notice_active').find('li').length;
            if(num > 1){
               var time=setInterval('timer(".notice_active")',3500);
                $('.gg_more a').mousemove(function(){
                    clearInterval(time);
                }).mouseout(function(){
                    time = setInterval('timer(".notice_active")',3500);
                }); 
            }
            
            $(".news_ck").click(function(){
                location.href = $(".notice_active .notice_active_ch").children(":input").val();
            })
        });
    </script>
    <style type="text/css">
/*border-bottom: 1px solid #F0F0F0;*/
        .huadong {width: 60rem;height:4rem;padding: 0.5rem 0 0.3rem 0; border-bottom: 1px solid #F0F0F0; position:relative;font: 12px/1.5 "Hiragino Sans GB","Microsoft YaHei",simsun;margin:0 auto;}
        .huadong .huabox {  margin:0 auto; width: 80%; line-height: 4rem;}
        .huadong .hdimg {float:left;line-height:3.5rem;}
        .huadong .hdimg img { width: 100%; vertical-align:middle;}
        .huadong h5 {font-size:1rem;float:left; line-height: 2.2rem; margin: 0.8rem 0 0 0; }
        .huadong .gg_more { float: left;}
        .huadong .gg_more a { font-size: 0.8rem; text-decoration:none;}
        
        .huadong .notice_active {
                float: left;
                width: 55%;
                height: 4rem;
                padding: 0;
                overflow: hidden;
                position: relative;
        }
        .huadong .notice_active li{list-style-type:none;line-height: 2.4rem;overflow: hidden;}
        /*.huadong .notice_active li.notice_active_ch {}*/
        .huadong .notice_active li.notice_active_ch span {color:#656972;font-size:1rem;display: block;overflow: hidden; width:80%;float: left;
        overflow: hidden;margin:0 0 2rem 0;}
        .huadong .gg_more .news_ck {
                float: left;
                margin: 0 1rem 0 1rem;
                color: #888;
                width: 5rem;
                height: 2rem;
                line-height: 2rem;
                display: block;
                border: 1px solid #656972;
                text-align: center;
                border-radius: 0.7rem;
                font-size: 0.8rem;
                margin-top: 1rem;
            }
        .huadong .gg_more .news_more {
            
        }
        .huadong .notice_active li.notice_active_ch em {text-align:right;float:right;color:#888;font-size:0.8rem;font-style:normal;  }
    </style>
    </head>
  
  <body onload="showtime()">
  <blockquote class="layui-elem-quote layui-text">
    <h3>${username}&nbsp;&nbsp;<i id="time"/></h3>
  </blockquote>
    
  <iframe allowtransparency="true" style="float:right;" frameborder="0" width="140" height="428" scrolling="no" src="//tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=1&d=5&bd=0&k=000000&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=71256&w=140&h=428&align=center"></iframe>
  <div class="huadong">
        <div class="huabox">
        <h5>【公告】</h5>
            <div class="notice_active">
                <ul>
                    <li class="notice_active_ch">
                        <span>请未进行绩效评价的员工于2019-06-20号前完成绩效评价</span>                
                        
                        <em>2019-06-18</em>
                    </li>
                    <li class="notice_active_ch">
                        <span>欢迎使用企业人事管理系统</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    <li class="notice_active_ch">
                        <span>使用系统有错误请联系管理员</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    
                </ul>   
        
            </div>

            <div class="gg_more">
                 <a class="news_ck" href="javascript:void(0)">查看详情</a>
                 <a title="news_more" href="#">更多+</a>
            </div><br />
            <h5>【公告】</h5>
            <div class="notice_active">
                <ul>
                    <li class="notice_active_ch">
                        <span>欢迎使用企业人事管理系统</span>                
                        
                        <em>2019-06-18</em>
                    </li>
                    <li class="notice_active_ch">
                        <span>请未进行绩效评价的员工于2019-06-20号前完成绩效评价</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    <li class="notice_active_ch">
                        <span>使用系统有错误请联系管理员</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    
                </ul>   
        
            </div>

            <div class="gg_more">
                 <a class="news_ck" href="javascript:void(0)">查看详情</a>
                 <a title="news_more" href="#">更多+</a>
            </div><br />
            <h5>【公告】</h5>
            <div class="notice_active">
                <ul>
                    <li class="notice_active_ch">
                        <span>使用系统有错误请联系管理员</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    <li class="notice_active_ch">
                        <span>欢迎使用企业人事管理系统</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    <li class="notice_active_ch">
                        <span>请未进行绩效评价的员工于2019-06-20号前完成绩效评价</span>                
                        
                        <em>2019-05-18</em>
                    </li>
                    
                </ul>   
        
            </div>

            <div class="gg_more">
                 <a class="news_ck" href="javascript:void(0)">查看详情</a>
                 <a title="news_more" href="#">更多+</a>
            </div>
        </div>
    </div>  
<script src="jquery/jquery-3.4.0.min.js" charset="utf-8"></script>
<script>
	function showtime () {
		var now = new Date();
		var year = now.getYear()+1900;
		var month=now.getMonth()+1;
		var hours=now.getHours();
		var date=now.getDate();
		var day=now.getDay();
		var weekday=new Array(7)
		weekday[0]="星期天"
		weekday[1]="星期一"
		weekday[2]="星期二"
		weekday[3]="星期三"
		weekday[4]="星期四"
		weekday[5]="星期五"
		weekday[6]="星期六"
		var timeValue = "" +((hours >= 12) ? "下午好，今天是 " : "上午好，今天是 " );
		timeValue += year+"年 ";
		timeValue +=month+"月 ";
		timeValue +=date+"日 ";
		timeValue +=weekday[day];
		$("#time").text(timeValue);
	}

</script>
</body>
</html>
