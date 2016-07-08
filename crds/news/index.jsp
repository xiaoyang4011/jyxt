<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>中国石油大学胜利学院就业信息网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/layout.css" />
		<link rel="stylesheet" type="text/css" href="css/front.css" />
		<link rel="stylesheet" type="text/css" href="css/calendar-system.css" />
		<script type="text/javascript" src="js/general.js"></script>
		<script type='text/javascript' src="js/engine.js"></script>
		<script type="text/javascript" src="js/util.js"></script>
		<script type='text/javascript' src="js/generic.js"></script>
		<script type='text/javascript' src="js/RPC_Common.js"></script>
		<script src="js/jquery-1.2.6.min.js"></script>
		<style type="text/css">
.calendarnone {
	DISPLAY: none
}

.schedule1 {
	BORDER-RIGHT: #b4b4b4 1px solid;
	BORDER-TOP: #b4b4b4 1px solid;
	DISPLAY: none;
	BACKGROUND: #fcfcfc;
	BORDER-LEFT: #b4b4b4 1px solid;
	WIDTH: 150px;
	BORDER-BOTTOM: #b4b4b4 1px solid;
	POSITION: absolute
}
a{TEXT-DECORATION:none}
</style>
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0">
		<table width="939" border="0" align="center" cellpadding="0"
			cellspacing="0" id="__">
			<tr>
				<td background="pic/index_01.gif" width="939" height="139"
					colspan="2"></td>
			</tr>
			<tr>
				<td width="939" height="34" valign="bottom"
					background="pic/index_02.gif" colspan="2">
					<table width="939" height="28" border="0" align="center"
						cellpadding="0" cellspacing="0"
						style="font-weight: 600; color: #FFF; font-size: 14px;">
						<tr align="center">
							<td width="117"
								style="color: #000; font-size: 13px; background-repeat: no-repeat"
								background="pic/tab.gif">
								<a href="index.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #000;">首页</a>
							</td>
							<td width="117" style="font-size: 13px;">
								<a href="xxgk.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #FFE;">学校概况</a>
							</td>
							<td width="117" style="font-size: 13px;">
								<a href="xwdt.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #FFF;">新闻动态</a>
							</td>
							<td width="117" style="font-size: 13px;">
								<a href="dwzq.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #FFF;">单位服务</a>
							</td>
							<td width="117" style="font-size: 13px;">
								<a href="jyzd.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #FFF;">职业辅导</a>
							</td>
							<td width="117" style="font-size: 13px;">
								<a href="jyzc.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #FFF;">就业政策</a>
							</td>
							<td width="117" style="font-size: 13px;">
								<a href="zxjj.h.htm"
									style="TEXT-DECORATION: none; font-size: 13px; color: #FFF;">自主创业</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="8" colspan="2"></td>
			</tr>
			<tr>
				<td valign="top" width="220">
					<iframe src="login.jsp" frameborder="0"
						style="width: 206px; height: 201px;" align="center" scrolling="no"></iframe>
					<table height="8" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<td></td>
					</table>
					<table width="206" height="200" border="0" align="center"
						cellpadding="0" cellspacing="0" style="border: 1px solid #c1e1f9;">
						
					</table>
			<table height="8" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<td>
					<iframe src="calendar.jsp" frameborder="0"
						style="width: 206px; height: 201px;" align="center" scrolling="no"></iframe><!--日历  -->
				</td>
			</table>
			<table width="206" height="90" border="0" align="center"
				cellpadding="0" cellspacing="0" style="border: 1px solid #c1e1f9;">
				<tr align="center">
					<td height="25" align="left" background="pic/title_bg.gif"
						class="style1">
						&nbsp;
						<img src="pic/b1.gif">
						友情连接
					</td>
				</tr>
				<tr align="center">

					<td height="100%">

						<form name="linkForm" id="linkForm" target="_blank" method="post">

							<div class="list_content">
								<div class="c1-body">
									<select name="linkSelectlink1" id="linkSelectlink1"
										style="WIDTH: 80%" onChange="changeLink('link1');">
										<option value="" style="font-weight: 900">
											常用连接
										</option>
										<option value="http://www.chinahr.com">
											中华英才网
										</option>
										<option value="http://www.zhaopin.com">
											智联招聘
										</option>
									</select>
								</div>
							</div>
							<br>
							<div class="list_content">
								<div class="c1-body">
									<select name="linkSelectlink2" id="linkSelectlink2"
										style="WIDTH: 80%" onChange="changeLink('link2');">
										<option value="" style="font-weight: 900">
											单位连接
										</option>
										<option value="http://www.moe.edu.cn">
											教育部
										</option>
										<option value="http://csicc.moe.edu.cn">
											教育部就业中心
										</option>
										<option value="http://www.sina.com.cn">
											新浪
										</option>
									</select>
								</div>
							</div>
							</form>
					</td>

				</tr>

			</table>
			<script type="text/javascript">
	function changeLink(type) {
		var linkSelect = document.getElementById('linkSelect' + type);
		if (!linkSelect || !linkSelect.options.length > 0) {
			return;
		}
		var url = '';
		for ( var i = 0; i < linkSelect.options.length; i++) {
			if (linkSelect.options[i].selected) {
				url = linkSelect.options[i].value;
				break;
			}
		}
		if (url) {
			document.getElementById('linkForm').action = url;
			document.getElementById('linkForm').submit();
		}
	}
</script>
			<table height="8" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<td></td>
			</table>
			<table width="206" height="200" border="0" align="center"
				cellpadding="0" cellspacing="0" style="border: 1px solid #c1e1f9;">
				<tr align="center">
					<td height="25" align="left" background="pic/title_bg.gif"
						class="style1">
						&nbsp;
						<img src="pic/b1.gif">
						联系方式
					</td>
				</tr>
				<tr>
					<td align="left" height="175">
						<table width="186" border="0" align="left" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<b>&nbsp;&nbsp;&nbsp;&nbsp;就业市场信息部，负责用人 单位招聘及信息发布事宜。</b>
									<br>
									<br>
								</td>
							</tr>
							<tr>
								<td height="120" background="pic/lx_bg.gif"
									style="LINE-HEIGHT: 150%;">
									联系电话：0564-7396601
									<br>
									地&nbsp;&nbsp;&nbsp;&nbsp;址：山东省东营市济南路1号
									<br>

									邮&nbsp;&nbsp;&nbsp;&nbsp;编：257097
									<br>
									E-mail：slxyjzq@sohu.com
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table height="8" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<td></td>
			</table>
			<table width="206" height="377" border="0" align="center"
				cellpadding="0" cellspacing="0" style="border: 1px solid rgb(193, 225, 249); height: 377px;">
				<tr align="center">
					<td height="25" align="left" background="pic/title_bg.gif"
						class="style1">
						&nbsp;
						<img src="pic/b1.gif">
						工作指南
					</td>
				</tr>
				<tr>
					<td align="center" valign="top" height="100%">
						<table width="185" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<br>
									<br>
									&nbsp;&nbsp;&nbsp; 同学咨询就业政策、条款等相关问题，请致电：
									<br>
									<br>
									<img src="pic/phone.gif" style="vertical-align: middle">
									<font color="#f77b00" size="2"><b> 0564-7396601 <br>
									</b> </font>
									<img src="pic/phone.gif" style="vertical-align: middle">
									<font color="#f77b00" size="2"><b> 0564-7396601</b> </font>
									<br>
									<br>
									<p>
										<br>
										<br>
								</td>
							</tr>
							<tr>
								<td height="106" background="pic/zn_bg.gif"
									style="LINE-HEIGHT: 150%;">
									<p>
										<b>&nbsp; 办公时间：</b>工作日周一至周五
										<br>
										&nbsp;&nbsp;&nbsp; 上午&nbsp; 8:00 - 11:30
										<br>
										&nbsp;&nbsp;&nbsp; 下午 14:00 - 18:00
									</p>
									<p>
										<b> &nbsp; 地址：</b>山东省东营市东营区济南路1号
									</p>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
			<td height="208" align="left" valign="top" width="720">
				<table border="0" align="left" cellpadding="0" cellspacing="0"
					width="720">
					<tr valign="top">
						<td height="208" align="left" valign="top" width="720">
							<table width="710" height="200" border="0" align="left"
								cellpadding="0" cellspacing="0">
								<tr height="200" valign="top">
									<td width="495" valign="top">
										<iframe name="listPage" src="<%=path%>/browse1.do" frameborder="0" scrolling="no"
												width="495" height="206"></iframe>
									</td>
									<td>
										<div
											style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; WIDTH: 206px; PADDING-TOP: 0px; float: right;">
											<table width="206" height="200" border="0" align="center"
												cellpadding="0" cellspacing="0">
												<td align="center">
													<!--焦点图Begin-->
													<img alt="testPic" src="pic/test_01.jpg">
												</td>
												<!--焦点图End-->
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr valign="top">

						<td height="207" valign="top">

							<table width="710" border="0" align="left" cellpadding="0"
								cellspacing="0">
								<tr valign="top">

									<td valign="top" width="495">
										<div
											style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; WIDTH: 206px; PADDING-TOP: 0px">
											<iframe src="test.jsp" frameborder="0" scrolling="no"
												width="495" height="206"></iframe>
										</div>
									</td>
									<td valign="top" align="right">
										<iframe src="gsgg.jsp" frameborder="0" scrolling="no"
												width="206" height="241"></iframe>
									</td>

								</tr>

							</table>
						</td>

					</tr>

					<tr>
						<td height="5"></td>
					</tr>
					<tr>

						<td valign="top" height="208">

							<table width="710" border="0" align="left" cellpadding="0"
								cellspacing="0">
								<tr height="200">

									<td width="495">
										<div
											style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; WIDTH: 206px; PADDING-TOP: 0px">
											<!--全国1站式招聘信息开始-->
											<iframe src="yzsfw.jsp" frameborder="0" scrolling="no"
												width="495" height="276"></iframe>
											<!--全国1站式招聘信息结束-->
										</div>
									</td>
									<td height="100%" valign="top" align="right">
										<iframe src="jylcjcjwt.jsp" frameborder="0" scrolling="no"
												width="206" height="276"></iframe>
									</td>

								</tr>

							</table>
						</td>

					</tr>

					<tr>
						<td height="5"></td>
					</tr>
					<tr valign="top">
						<td height="300" valign="top">
							<table width="710" border="0" cellpadding="0" cellspacing="0"
								valign="top">
								<tr height="300">

									<td width="495">

										<div
											style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; WIDTH: 206px; PADDING-TOP: 0px">
											<table width="495" height="200" border="0" align="center"
												cellpadding="0" cellspacing="0"
												style="border: 1px solid #c1e1f9;">
												<tr align="center">
													<td height="25" align="left" background="pic/title_bg.gif"
														class="style1">
														&nbsp;
														<img src="pic/b1.gif">
														<a href="." target="_self" class="style1" title="点击查看更多">
															就业中心发布的招聘信息 </a>
													</td>
												</tr>
												<tr align="center">
													<td width="100%">
														<tr align="left">
															<td>
																<ul>
																	<li class="dotted_line">
																		<table height="100%" valign="top" width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>
																					&nbsp;
																					<img src="pic/b2.gif">
																					<a href="." target="_blank" title="数据库获取数据">
																						&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																				</td>
																			</tr>
																		</table>
																	</li>
																</ul>
															</td>
														</tr>
														<tr align="left">
															<td>
																<ul>
																	<li class="dotted_line">
																		<table height="100%" valign="top" width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>
																					&nbsp;
																					<img src="pic/b2.gif">
																					<a href="." target="_blank" title="数据库获取数据">
																						&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																				</td>
																			</tr>
																		</table>
																	</li>
																</ul>
															</td>
														</tr>
														<tr align="left">
															<td>
																<ul>
																	<li class="dotted_line">
																		<table height="100%" valign="top" width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>
																					&nbsp;
																					<img src="pic/b2.gif">
																					<a href="." target="_blank" title="数据库获取数据">
																						&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																				</td>
																			</tr>
																		</table>
																	</li>
																</ul>
															</td>
														</tr>
														<tr align="left">
															<td>
																<ul>
																	<li class="dotted_line">
																		<table height="100%" valign="top" width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>
																					&nbsp;
																					<img src="pic/b2.gif">
																					<a href="." target="_blank" title="数据库获取数据">
																						&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																				</td>
																			</tr>
																		</table>
																	</li>
																</ul>
															</td>
														</tr>
														<tr align="left">
															<td>
																<ul>
																	<li class="dotted_line">
																		<table height="100%" valign="top" width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>
																					&nbsp;
																					<img src="pic/b2.gif">
																					<a href="." target="_blank" title="数据库获取数据">
																						&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																				</td>
																			</tr>
																		</table>
																	</li>
																</ul>
															</td>
														</tr>
														<tr align="left">
															<td>
																<ul>
																	<li class="dotted_line">
																		<table height="100%" valign="top" width="100%"
																			border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td>
																					&nbsp;
																					<img src="pic/b2.gif">
																					<a href="." target="_blank" title="数据库获取数据">
																						&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																				</td>
																			</tr>
																		</table>
																	</li>
																</ul>
															</td>
														</tr>
													</td>

												</tr>

											</table>
											<table width="495" height="200" border="0" align="center"
												cellpadding="0" cellspacing="0"
												style="border: 1px solid #c1e1f9; margin-top: 5px;">
												<tr align="center">
													<td height="25" align="left" background="pic/title_bg.gif"
														class="style1">
														&nbsp;
														<img src="pic/b1.gif">
														<a href="." target="_self" class="style1" title="点击查看更多">
															用人单位发布的招聘信息 </a>
													</td>
												</tr>
												<tr>
													<td>
														<ul>
															<li class="dotted_line">
																<table height="100%" valign="top" width="100%"
																	border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			&nbsp;
																			<img src="pic/b2.gif">
																			<a href="." target="_blank" title="数据库获取数据">
																				&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																		</td>
																	</tr>
																</table>
															</li>
														</ul>
													</td>
												</tr>
												<tr>
													<td>
														<ul>
															<li class="dotted_line">
																<table height="100%" valign="top" width="100%"
																	border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			&nbsp;
																			<img src="pic/b2.gif">
																			<a href="." target="_blank" title="数据库获取数据">
																				&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																		</td>
																	</tr>
																</table>
															</li>
														</ul>
													</td>
												</tr>
												<tr>
													<td>
														<ul>
															<li class="dotted_line">
																<table height="100%" valign="top" width="100%"
																	border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			&nbsp;
																			<img src="pic/b2.gif">
																			<a href="." target="_blank" title="数据库获取数据">
																				&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																		</td>
																	</tr>
																</table>
															</li>
														</ul>
													</td>
												</tr>
												<tr>
													<td>
														<ul>
															<li class="dotted_line">
																<table height="100%" valign="top" width="100%"
																	border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			&nbsp;
																			<img src="pic/b2.gif">
																			<a href="." target="_blank" title="数据库获取数据">
																				&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																		</td>
																	</tr>
																</table>
															</li>
														</ul>
													</td>
												</tr>
												<tr>
													<td>
														<ul>
															<li class="dotted_line">
																<table height="100%" valign="top" width="100%"
																	border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			&nbsp;
																			<img src="pic/b2.gif">
																			<a href="." target="_blank" title="数据库获取数据">
																				&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																		</td>
																	</tr>
																</table>
															</li>
														</ul>
													</td>
												</tr>
												<tr>
													<td>
														<ul>
															<li class="dotted_line">
																<table height="100%" valign="top" width="100%"
																	border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td>
																			&nbsp;
																			<img src="pic/b2.gif">
																			<a href="." target="_blank" title="数据库获取数据">
																				&nbsp; <font color="ff0000"> 数据库获取数据 </font> </a>
																		</td>
																	</tr>
																</table>
															</li>
														</ul>
													</td>
												</tr>
											</table>
										</div>
									</td>

									<td valign="top" align="right" height=400>
										<iframe src="xgdw.jsp" frameborder="0" scrolling="no"
												width="206" height="381"></iframe>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<table>
						</td>

						</tr>

					</table>
					</td>

					</tr>

				</table>
				<table width="940" border="0" align="center" cellpadding="0"
					cellspacing="0" id="__01">
					<tr>
						<td height="115" align="center">
							<table width="926" height="80" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td width="206" align="center">
										<table width="206" height="80" border="0" align="center"
											cellpadding="0" cellspacing="0"
											style="border: 1px solid #c1e1f9;">
											<tr align="center">
												<td height="25" align="left" background="pic/title_bg.gif"
													class="style1">
													&nbsp;
													<img src="pic/b1.gif">
													访问统计
												</td>
											</tr>
											<tr>
												<td align="center"
													style="color: #b04b59; font-weight: bold;" height="100%">
													访问总量：
													<script type="" language="javaScript">
                   	var syjsnumbers = "";
                   	var syjs = '9446338';
                   	for(var m = 0;m < 7 - syjs.length;m++){
                    	syjsnumbers = syjsnumbers + "0";
                   	}
                   	syjsnumbers = syjsnumbers + syjs;
                   	for(var m = 0;m <7;m++){
                    	document.write("<img src='/website/res_base/career/default/images/counter/" + syjsnumbers.charAt(m) + ".gif'  width='17' height='18' align='top'>");
                   	}
                	</script>
													<br>
													<br>
													日访问量：
													<script type="" language="javaScript">
                   	var syjsnumbers = "";
                   	var syjs = '14419';
                   	for(var m = 0;m < 7 - syjs.length;m++){
                    	syjsnumbers = syjsnumbers + "0";
                   	}
                   	syjsnumbers = syjsnumbers + syjs;
                   	for(var m = 0;m <7;m++){
                    	document.write("<img src='/website/res_base/career/default/images/counter/" + syjsnumbers.charAt(m) + ".gif'  width='17' height='18' align='top'>");
                   	}
                	</script>
												</td>
											</tr>
										</table>
									</td>
									<td width="733" align="right">
										<table width="705" height="107" border="0" align="center"
											cellpadding="0" cellspacing="0"
											style="border: 1px solid #c1e1f9;">
											<tr align="center">
												<td>
												<iframe src="bottom.jsp" marginwidth="0" marginheight="0" frameborder="0"
														scrolling="no" width="700" height="100">															
												</iframe>								
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="939" height="97" align="center"
							background="pic/index_09.gif">
							<p>
								<strong>中国石油大学胜利学院招生就业处 版权所有 &copy; 2013 </strong>
								<br>
								<br>
								地址：东营市济南路1号 中国石油大学胜利学院
								<br>
								<br>
								E-mail: slxyjzq@sohu.com 邮编：257097 联系电话：0546-7396601~7396610
							</p>
						</td>
					</tr>
				</table>
				<!-- End Save for Web Slices -->
	</body>
</html>

