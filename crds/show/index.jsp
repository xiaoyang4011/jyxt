<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国石油大学胜利学院就业信息网</title>
<style type="text/css" media="screen, projection">
html,body {
	
	margin-top: 0px;
	margin-left:0px;
	margin-right:0px;
	margin-bottom: 0px;
	padding:0;
	margin:0;
}
.ziti {	color: #FFF;
	font-size: 18px;
}
a:link {
	color: #FFF;
	text-decoration: none;
}
a:visited {
	color: #FFF;
	text-decoration: none;
}
a:hover {
	color: #FFF;
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
.gengduo {font-size: 12px;
	color: #333;
	font-family: "微软雅黑";
}
.dizhi {	font-size: 14px;
	color: #014A99;
	font-weight: bold;
}
.STYLE1 {color: #014A99}

</style>
<LINK 
            href="css/css.css" type=text/css 
            rel=stylesheet>
<STYLE type=text/css>
            .style1 {
	COLOR: #ff0000
}
            </STYLE>
<LINK 
      href="css/finance.css" type=text/css rel=stylesheet>
<STYLE type=text/css>
      .sfontgreen {
	COLOR: #04a300
}
.sfontred {
	COLOR: #f00
}
      </STYLE>
<STYLE type=text/css>
.dizhi {
	color: #FFF;
}
.dizhi1 {color: #FFF;
}
.dizhi1 {color: #014A99;
}
.ziti2 {
	font-family: "宋体";
}
.ziti2 {
	font-size: 16px;
}
.ziti2 {
	color: #FFF;
}
.ziti2 {
	font-weight: bold;
}
.dizhi {
	color: #014A99;
}
.lianxi {
	font-size: 18px;
	color: #FFF;
	font-weight: bold;
}
</STYLE>
<LINK href="css/finance.css" type=text/css 
rel=stylesheet>
<SCRIPT>
		function getid(obj)
		{
			return document.getElementById(obj);
		}
		
		function gettag(obj,tag,name)
		{
			var t = obj.getElementsByTagName(tag);
			var list = new Array();
			for(i=0;i<t.length;i++)
			{
				if(t[i].getAttribute("name") == name )
				{
					list[list.length] = t[i];
				}
			}
			return list;
		}
		
		function changeFod(obj,info,name)
		{
			var p = obj.parentNode.getElementsByTagName("div");
			var f1 = getid(info);
			var t = gettag(f1,"table",name);
			for(i=0;i<p.length;i++)
			{
				if(p[i] == obj)
				{
					p[i].className = "s";
					t[i].className = "dis";
				}
				else
				{
					p[i].className = "";
					t[i].className = "undis";				
				}
			}
		}
</SCRIPT>
<META content="MSHTML 6.00.2800.1593" name=GENERATOR>
<style type="text/css">
.dizhi1 {font-size: 14px;
	color: #014A99;
	font-weight: bold;
}
.dizhi1 {	color: #FFF;
}
.dizhi1 {	color: #014A99;
}
</style>
</head>
<body>
<script type="text/javascript" src="js/yahoo.js"></script>
<script type="text/javascript" src="js/event.js"></script>
<script type="text/javascript" src="js/dom.js"></script>
<script type="text/javascript" src="js/animation.js"></script>
<script type="text/javascript">
	<!--
	YAHOO.example = function() {
		var $D = YAHOO.util.Dom;
		var $E = YAHOO.util.Event;
		var $A = YAHOO.util.Anim;
		var $M = YAHOO.util.Motion;
		var $DD = YAHOO.util.DD;
		var $ = $D.get;
		var x = 1;
		return {
			init : function() {
				$E.on(['move-left','move-right'], 'click', this.move);
			},
			move : function(e) {
				$E.stopEvent(e);
				switch(this.id) {
					case 'move-left':
						if ( x === 1 ) {
							return;
						}
						var attributes = {
							points : {
								by : [400, 0]
							}
						};
						x--;
					break;
					case 'move-right':
						if ( x === 3 ) {
							return;
						}
						var attributes = {
							points : {
								by : [-400, 0]
							}
						};
						x++;
					break;
				};
				var anim = new $M('themes', attributes, 0.5, YAHOO.util.Easing.easeOut);
				anim.animate();
			}
		};
	}();
	YAHOO.util.Event.onAvailable('doc',YAHOO.example.init, YAHOO.example, true);
	//-->
	</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="top"><iframe src="top.jsp" width="100%" height="50" scrolling="no" frameborder="0"></iframe></td>
  </tr>
</table>

<table width="1030" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#DCF1FC" bordercolorlight="#DCF1FC" bordercolordark="#DCF1FC" >
  <tr>
    <td bordercolor="#DCF1FC"><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#DCF1FC" bgcolor="#FFFFFF">
        <tr>
          <td width="1%" rowspan="3" bordercolor="#DCF1FC" background="im/bian.png" bgcolor="#FFFFFF"></td>
          <td width="98%"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
              <tr>
                <td width="79%"><img src="im/2.png" width="800" height="150" border="0" usemap="#Map4" />
                  <map name="Map4" id="Map4">
                    <area shape="rect" coords="3,1,804,149" href="index.jsp" />
                  </map></td>
                <td width="21%" align="right" valign="bottom"><a href="http://www.pusc.cn/"><img src="im/xysy.jpg" alt="" width="100" height="25" border="0" usemap="#Map3" /></a></td>
              </tr>
            </table></td>
          <td width="1%" rowspan="3" bordercolor="#DCF1FC" background="im/bian2.png" bgcolor="#FFFFFF"></td>
        </tr>
        <tr>
          <td height="8" bgcolor="#FFFFFF"></td>
        </tr>
        <tr>
          <td bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#DCF1FC">
              <tr>
                <td><table width="100%" border="0" align="center" bordercolor="#DCF1FC" bordercolorlight="#DCF1FC" bordercolordark="#DCF1FC">
                    <tr>
                      <td height="350" align="center" valign="top" bgcolor="#FFFFFF"><iframe src="picScroll.jsp" width="100%" height="360" scrolling="no" frameborder="0"></iframe></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td height="10" bgcolor="#FFFFFF"></td>
              </tr>
              <tr>
                <td><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#DCF1FC">
                    <tr>
                      <td bordercolor="#DCF1FC"><table width="100%" height="1167" border="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF">
                          <tr>
                            <td width="26" rowspan="10" bordercolor="#DCF1FC" bgcolor="#FFFFFF"></td>
                            <td width="32" height="250" valign="top" bordercolor="#DCF1FC" bgcolor="#FFFFFF" class="ziti"><table width="100%" height="248" border="0" bordercolor="#DCF1FC" background="im/beijing2.jpg">
                                <tr>
                                  <td height="244" bordercolor="#DCF1FC" class="ziti"><strong>最<br />
                                    新<br />
                                    动<br />
                                    态</strong></td>
                                </tr>
                              </table></td>
                            <td colspan="3" valign="top" bordercolor="#DCF1FC"><table width="100%" height="247" border="0" cellpadding="0" cellspacing="0" bordercolor="#DCF1FC">
                                <tr>
                                  <td width="49%" valign="top" bordercolor="#DCF1FC" ><div class="11" id="11" >
                                      <iframe name="listPage" src="<%=path%>/zxdt.do" width="100%" height="245" scrolling="no" frameborder="0"></iframe>
                                    </div></td>
                                  <td width="28%" valign="top" bordercolor="#DCF1FC" ><iframe src="login.jsp" width="100%" height="245" scrolling="no" frameborder="0"></iframe></td>
                                </tr>
                              </table></td>
                            <td width="33" rowspan="6" valign="top" bordercolor="#DCF1FC" bgcolor="#FFFFFF">&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="8" colspan="4" bordercolor="#DCF1FC" bgcolor="#FFFFFF"></td>
                          </tr>
                          <tr>
                            <td height="2" colspan="4" bordercolor="#DCF1FC" bgcolor="#FFFFFF"></td>
                          </tr>
                          <tr>
                            <td height="8" colspan="4" bordercolor="#DCF1FC" bgcolor="#FFFFFF"></td>
                          </tr>
                          <tr>
                            <td width="32" height="250" valign="top" bordercolor="#DCF1FC" bgcolor="#FFFFFF" class="ziti"><table width="100%" border="0" bordercolor="#DCF1FC" background="im/beijing2.jpg">
                                <tr>
                                  <td height="244" bordercolor="#DCF1FC" class="ziti"><strong>校<br />
                                    内<br />
                                    招<br />
                                    聘<br />
                                    会</strong></td>
                                </tr>
                              </table></td>
                            <td width="429" valign="top" bordercolor="#DCF1FC"><div class="111" id="111">
                                <iframe src="<%=path%>/xnzph.do" width="100%" height="245" scrolling="no" frameborder="0"></iframe>
                              </div></td>
                            <td width="32" height="250" valign="top" bordercolor="#DCF1FC" bgcolor="#FFFFFF" class="ziti"><table width="100%" border="0" bordercolor="#DCF1FC" background="im/beijing2.jpg">
                                <tr>
                                  <td height="244" class="ziti"><strong>校<br />
                                    外<br />
                                    招<br />
                                    聘<br />
                                    会</strong></td>
                                </tr>
                              </table></td>
                            <td width="431" valign="top" bordercolor="#DCF1FC"><div class="4" id="4">
                                <iframe src="<%=path%>/xwzph.do" width="100%" height="245" scrolling="no" frameborder="0"></iframe>
                              </div></td>
                          </tr>
                          <tr>
                            <td height="8" colspan="4" bordercolor="#DCF1FC" bgcolor="#FFFFFF"></td>
                          </tr>
                          <tr>
                            <td height="72" colspan="5" valign="top" bordercolor="#DCF1FC"><map name="Map2MapMap" id="Map2MapMap">
                                <area shape="rect" coords="1,1,71,32" href="http://pusc.ncss.org.cn/rec/login" target="new" />
                              </map>
                              <table width="100%" border="0" bordercolor="#DCF1FC">
                                <tr>
                                  <td height="10" bgcolor="#FFFFFF"><table width="100%" border="0" bordercolor="#DCF1FC">
                                      <tr>
                                        <td width="67%" bordercolor="#DCF1FC"><img src="im/2.jpg" alt="" width="666" height="55" /></td>
                                        <td width="16%" align="center" border="1" bordercolor="#DCF1FC"><img src="im/xsdl.gif" alt="" width="72" height="31" border="0" usemap="#MapMapMap" />
                                          <map name="MapMapMap" id="MapMapMap">
                                            <area shape="rect" coords="2,1,72,31" href="http://pusc.ncss.org.cn/login" target="new" />
                                          </map></td>
                                        <td width="17%" align="center" border="1" bordercolor="#DCF1FC"><img src="im/dwdl.gif" alt="" width="72" height="31" border="0" usemap="#Map2MapMapMap" />
                                          <map name="Map2MapMapMap" id="Map2MapMapMap">
                                            <area shape="rect" coords="0,0,70,31" href="http://pusc.ncss.org.cn/rec/login" target="new" />
                                          </map></td>
                                      </tr>
                                    </table></td>
                                </tr>
                              </table></td>
                          </tr>
                          <tr>
                            <td width="32" height="251" valign="top" bordercolor="#DCF1FC"><table width="100%" border="0" background="im/beijing2.jpg">
                                <tr>
                                  <td height="244" class="ziti"><p><strong>就<br />
                                      业<br />
                                      政<br />
                                      策</strong></p></td>
                                </tr>
                              </table></td>
                            <td valign="top" bordercolor="#DCF1FC"><div class="7" id="7">
                                <iframe src="<%=path%>/jyzc.do" width="100%" height="245" scrolling="no" frameborder="0"></iframe>
                              </div></td>
                            <td valign="top" bordercolor="#DCF1FC"><table width="100%" border="0" background="im/beijing2.jpg">
                                <tr>
                                  <td height="244" class="ziti"><strong>就<br />
                                    业<br />
                                    指<br />
                                    导</strong></td>
                                </tr>
                              </table></td>
                            <td valign="top" bordercolor="#DCF1FC"><div class="8" id="8">
                                <iframe src="<%=path%>/jyzd.do" width="100%" height="245" scrolling="no" frameborder="0">此处显示  class "8" id "8" 的内容</iframe>
                              </div></td>
                            <td width="33" valign="top" bordercolor="#DCF1FC" bgcolor="#FFFFFF">&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="10" colspan="5" bordercolor="#DCF1FC" bgcolor="#FFFFFF" ></td>
                          </tr>
                          <tr>
                            <td height="21" colspan="5" valign="top" bordercolor="#DCF1FC"><DIV style="DISPLAY: none">
                                <NOSCRIPT>
                                <IMG alt="" src="img/m.gif">
                                </NOSCRIPT>
                              </DIV>
                              <table width="100%" 
border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#CAD4DD" class="table_bd2 mb8">
                                <tbody>
                                  <tr valign="top">
                                    <td width="100%"><script>
		function getid(obj)
		{
			return document.getElementById(obj);
		}
		
		function gettag(obj,tag,name)
		{
			var t = obj.getElementsByTagName(tag);
			var list = new Array();
			for(i=0;i<t.length;i++)
			{
				if(t[i].getAttribute("name") == name )
				{
					list[list.length] = t[i];
				}
			}
			return list;
		}
		
		function changeFod(obj,info,name)
		{
			var p = obj.parentNode.getElementsByTagName("div");
			var f1 = getid(info);
			var t = gettag(f1,"table",name);
			for(i=0;i<p.length;i++)
			{
				if(p[i] == obj)
				{
					p[i].className = "s";
					t[i].className = "dis";
				}
				else
				{
					p[i].className = "";
					t[i].className = "undis";				
				}
			}
		}
	                          </script>
                                      <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                                        <tbody>
                                          <tr>
                                            <td class="fod"><div class="s" onmouseover="changeFod(this,'foda','a')">友情链接</div>
                                              <div 
        onmouseover="changeFod(this,'foda','a')">合作伙伴</div></td>
                                          </tr>
                                        </tbody>
                                      </table>
                                      <div id="foda">
                                        <table class="dis" cellspacing="0" cellpadding="12" width="100%" align="center" 
      border="0" name="a">
                                          <tbody>
                                            <tr>
                                              <td valign="top" class="lh22"><table width="100%" height="40" border="0" cellpadding="0" cellspacing="0" bgcolor="#CAD4DD">
                                                  <tr class="lianjieziti">
                                                    <td valign="middle"><a href="http://www.52xsj.com/" target="_new">新世纪人才网</a> <a href="http://www.slof.com/" target="_new">中国石化胜利油田</a> <a href="http://www.chsi.com.cn/" target="_new">中国高等教育学生信息网</a> <a href="http://rencai.dongying.gov.cn" target="_new">东营人才市场</a> <a href="http://www.sdbys.cn/index.htm" target="_new">山东高校毕业生就业信息网</a> <a href="http://www.sdlss.gov.cn/" target="_new">山东就业网</a></td>
                                                  </tr>
                                                </table>
                                                <br /></td>
                                            </tr>
                                          </tbody>
                                        </table>
                                        <table class="undis" cellspacing="0" cellpadding="12" width="100%" align="center" 
      border="0" name="a">
                                          <tbody>
                                            <tr>
                                              <td class="lh22"><table width="100%" height="40" border="0" cellpadding="0" cellspacing="0" bgcolor="#CAD4DD">
                                                  <tr class="lianjieziti">
                                                    <td valign="middle"><a href="http://dongying.myjob.com/" target="_new">我的工作网</a> <a href="http://www.52xsj.com/" target="_new">新世纪人才网</a> <a href="http://www.slof.com/" target="_new">中国石化胜利油田</a> <a href="http://www.chsi.com.cn/" target="_new">中国高等教育学生信息网</a> <a href="http://rencai.dongying.gov.cn" target="_new">东营人才市场</a> <a href="http://www.sdbys.cn/index.htm" target="_new">山东高校毕业生就业信息网</a> <a href="http://www.sdlss.gov.cn/" target="_new">山东就业网</a></td>
                                                  </tr>
                                                </table>
                                                <br /></td>
                                            </tr>
                                          </tbody>
                                        </table>
                                      </div>
                                      <div id="fodc">
                                        <table class="undis" cellspacing="0" cellpadding="12" width="100%" align="center" 
      border="0" name="c">
                                          <tbody>
                                            <tr>
                                              <td class="lh22">2<br /></td>
                                            </tr>
                                          </tbody>
                                        </table>
                                      </div></td>
                                  </tr>
                                </tbody>
                              </table></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td valign="top" bordercolor="#DCF1FC"><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#DCF1FC">
                          <tr>
                            <td height="10" bordercolor="#DCF1FC" bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td><div class="011" id="011">
                                      <iframe src="zhuyeziye/lxwm.jsp" width="100%" height="120" scrolling="no" frameborder="0"></iframe>
                                    </div></td>
                                </tr>
                              </table></td>
                          </tr>
                          <tr>
                            <td height="5" bordercolor="#DCF1FC"></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
