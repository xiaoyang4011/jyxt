<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="css/tab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/tab.js"></script>
</head>
<body>

<div class="tab">

	<div class="tab_pic">
		<ul>
			<li><a href="#"><img src="images/1.jpg" width="225" height="283" /></a></li>
			<li><a href="#"><img src="images/2.jpg" width="225" height="283" /></a></li>
			<li><a href="#"><img src="images/3.jpg" width="225" height="283" /></a></li>
		</ul>
		<span class="tab_san"><img src="images/tab_san.png" width="17" height="34" /></span>
		<span class="btn_ct"><a class="next"></a><a class="prev"></a></span>
		<span class="btn_bg"></span>
	</div>
	
	<div class="tab_txt">
		<ul>
			<li>
				<h3>生活家装饰主任设计师：钟晓洁1</h3>
				<p><b>毕业学校:</b>四川理工大学<br /><b>代表作品:</b>华侨城、君汇上品、中海锦城、圣路易名邸、东公元、果壳里的城、金科天籁城、九龙仓御园、大峰景、新熙门、华韵天府 </p>
				<p><b>设计风格:</b>现代、简欧、田园风格</p>
			</li>
			<li>
				<h3>生活家装饰主任设计师：钟晓洁2</h3>
				<p><b>毕业学校:</b>四川理工大学<br /><b>代表作品:</b>华侨城、君汇上品、中海锦城、圣路易名邸、东公元、果壳里的城、金科天籁城、九龙仓御园、大峰景、新熙门、华韵天府 </p>
				<p><b>设计风格:</b>现代、简欧、田园风格</p>
			</li>
			<li>
				<h3>生活家装饰主任设计师：钟晓洁3</h3>
				<p><b>毕业学校:</b>四川理工大学<br /><b>代表作品:</b>华侨城、君汇上品、中海锦城、圣路易名邸、东公元、果壳里的城、金科天籁城、九龙仓御园、大峰景、新熙门、华韵天府 </p>
				<p><b>设计风格:</b>现代、简欧、田园风格</p>
			</li>
		</ul>
	</div>

</div>
</body>
</html>
