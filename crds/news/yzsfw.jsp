<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<style type="text/css">
<!--
.yzs_all{width:495px; margin:-8px 0 0 -8px; padding:0}
.yzs_pic{width:490px; margin:auto;float:left;}
.yzs_logo{width:300px; height:66px; margin-left:0px;float:left;}
.yzs_button{float:left;}
.yzs_tutorial{float:left; font-size:12px;margin:2px 0 0 5px;line-height:27px;}
.yzs_tutorial a{color:#00f; text-decoration:none;}
.yzs_tutorial a:hover{color:#00f; text-decoration:none;}
.yzs_tutorial a:visited{color:#00f; text-decoration:none;}
.yzs_content{clear:both; width:490px; overflow:hidden; margin:0; text-align:left;border:1px solid #c1e1f9;float:left;}
.yzs_content ul{margin:0;padding:0 0 5px 0;float:left;}
.yzs_content ul li{margin:0 5px 0 10px;line-height:26px; height:26px; overflow:hidden; font-size:12px; border-bottom:dotted 1px #ccc;padding:0 12px 0 7px; float:left;}
.yzs_content ul li a{color:#000; text-decoration:none;margin-left:15px;}
.yzs_content ul li a:hover{color:#000; text-decoration:none;}
.yzs_content ul li a:visited{color:#000; text-decoration:none;}
.yzs_content .title{ font-size:12px; font-weight:600; padding-left:7px; line-height:25px; height:25px; background:url("../images/title_bg.gif")/*tpa=http://career.buaa.edu.cn/website/res_base/career/default/images/title_bg.gif*//*tpa=http://career.buaa.edu.cn/website/res_base/career/default/images/title_bg.gif*/; color: #0071bc;}
.yzs_content .title a{text-decoration:none; color: #0071bc;}
.yzs_content .title a:hover{text-decoration:none; color: #0071bc;}
.yzs_content .title a:visited{text-decoration:none; color: #0071bc;}
.date{margin:0;padding:0;text-align:right;float:left;  font-family:"宋体"; }
.item{margin:0px; padding:0; width:388px; overflow:hidden;float:left; }
.yzs_button{margin-left:10px; padding:0px;}
.yzs_button input{margin-top:4px;}
-->
</style>
<script type="text/javascript" src="js/embed_index.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"> 
	$().ready(function(){
		onloadlst("index","info-list1",6,"","全职","");//版位标示，列表层ID，条数，职位类别，职位性质，单位性质
	});
</script>

</head>
<body>
<div class="yzs_all">
	<!--全国1站式招聘 开始-->
	<div class="yzs_pic">
		<div class="yzs_logo"><img src="pic/yzsfw.gif" width=300 height=66></div>
		<div class="yzs_button">	    <form>
				<input type="button" value="学生登录" onclick="javascript:window.open('http://pusc.ncss.org.cn/login');">
				<br/>
				<input type="button" value="单位登录" onclick="javascript:window.open('http://pusc.ncss.org.cn/login');">
				</form>
		</div>
		<div class="yzs_tutorial"><a href="resources/yzs_tutorial_for_students.pdf">学生指导手册</a><br /><a href="resources/yzs_tutorial_for_corp.pdf">用人单位指导手册</a></div>
	</div>
	
	<div class="yzs_content" >
		<div class="title"><img src="pic/b1.gif">&nbsp;&nbsp;<a href="javascript:if(confirm('http://pusc.ncss.org.cn/job/'))window.location='http://pusc.ncss.org.cn/job/'" target="_blank" title="点击查看更多">
		全国1站式招聘信息</a></div>
		<ul id="info-list1"></ul> 
	</div>
	<!--全国1站式招聘 结束-->
</div>
</body>
</html>
