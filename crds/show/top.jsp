<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导航</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) {
		var navLi = $("#nav-div ul li"),
		    navUl = $("#nav-div ul"),
		    speed = 200;
			function OnClick(){
				 n =  navUl.find("li.on").index();
			     navUl.stop().animate({backgroundPosition:navLi.width()*n},speed);
				}
			 OnClick();	
		    navLi.hover(
		      function(){
			     n = $(this).index();
				 navUl.stop().animate({backgroundPosition:navLi.width()*n},speed);
			   },
			  function(){
				OnClick();
			   })
		       navLi.click(function(){
			     $(this).addClass("on").siblings().removeClass("on")
			   });
	$(window).scroll(function() {
	  if($(window).scrollTop() >  $("#nav").height()+50){
          $("#nav").addClass("scoll_nav")
	    }
	  else{
		 $("#nav").removeClass(	"scoll_nav")
			}
		 });	   			   	 		    
})
</script>
<style type="text/css">
body,html{ padding:0; margin:0;}
a{ text-decoration:none; color:#FFF; font-weight:bold; font-size:14px;}
div,ul,li{ padding:0; margin:0;}
#nav-div{ width:100%px; height:44px; background:#007FFF; margin:auto; position:relative;}
ul{ width:100%; height:42px;list-style:none; cursor:pointer; background: url(images/nav_on.png) no-repeat;}
#nav-div ul li{ width:98px; height:42px; line-height:42px; text-align:center; margin:auto; float:left; color:#FFF; cursor:pointer;}
#nav-div ul li:hover a{ color: #FF6;}
#liItemPaner{ width:100px; height:40px; background:#FC0; position:absolute;  }
.nav-side{ width:100%px; margin:auto;height:42px; background: #1BA2E1; }
.scoll_nav{width:100%;position:fixed; top:10px;  z-index:10000;}
#nav .nav-on { color:#F00;}
</style>
</head>
<body>
<div id="nav" class="nav-side">
<div id="nav-div">
             <ul><li></li><li></li>
                <li class="on"><a href="index.jsp" target="_parent">首页</a></li>
		        <li><a href="xwgg.jsp" target="_blank">新闻公告</a></li>
		        <li><a href="qyxx.jsp" target="_blank">企业信息</a></li>
                <li><a href="zpxx.jsp" target="_blank">招聘信息</a></li>
		        <li><a href="jyzc.jsp" target="_blank">就业政策</a></li>
		        <li><a href="jyzd.jsp" target="_blank">就业指导</a></li>
		       <li><a href="bysxx.jsp" target="_blank">毕业生信息</a></li>
		       <li><a href="cyds.jsp" target="_blank">创业大赛</a></li>
               <li><a href="xzzl.jsp" target="_blank">下载专区</a></li>
               <li><a href="xyfc.jsp" target="_blank">校友风采</a></li>
           </ul>
       </div>
    </div>
</body>
</html>
