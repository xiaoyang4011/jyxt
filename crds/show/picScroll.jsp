<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link rel="stylesheet" href="css/style1.css">
<script src="js/jquery-1.8.3.min.js"></script> 
<script src="js/jquery.slides.js"></script> 
<script src="js/zzsc.js"></script> 
</head>
<body>

<div class="si">
	<div class="container">
	<!--slider-->
	  <div class="sixteen columns clearfix">
  	  <div class="slider">
 	     <ul class="rslides" id="slider3">
  	      <li> <img src="images/slider/sa1.jpg" title="some title" alt="" />
    	    </li>
      	  <li> <img src="images/slider/sa2.jpg" title="some title" alt="" />
        	</li>
	        <li> <img src="images/slider/sa3.jpg" title="some title" alt="" />
  	      </li>
    	  </ul>
	    </div>
	  </div>
  <!--.slider--> 
  </div>
</div>

</body>
</html>