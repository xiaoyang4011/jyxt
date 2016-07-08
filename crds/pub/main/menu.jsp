<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="crds.pub.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%String path = request.getContextPath();%>
<HTML>
<HEAD>
<TITLE>导航菜单</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="<%=path%>/pub1/css/images/default.css" type=text/css rel=stylesheet>
<STYLE type=text/css>.ttl {
	CURSOR: hand; COLOR: #ffffff; PADDING-TOP: 4px
}
</STYLE>
<script language="javascript" src="<%=path%>/pub1/inc/common.js"></script>
<SCRIPT language=javascript>
function showHide(obj) {
  var oStyle;
	if (!isIE())
		oStyle = obj.parentNode.parentNode.parentNode.rows[1].style;
	else
		oStyle = obj.parentElement.parentElement.parentElement.rows[1].style;
    oStyle.display == "none" ? oStyle.display = "" : oStyle.display = "none";
}
</SCRIPT>
<META content="MSHTML 6.00.3790.259" name=GENERATOR>
</HEAD>
<BODY  bgColor=#9aadcd leftMargin=0 topMargin=0>

<TABLE cellSpacing=0 cellPadding=0 width=159 align=center border=0>
  <TBODY>
  <TR>
    <TD width=23><IMG height=25 
      src="<%=path%>/pub1/images/box_topleft.gif" width=23></TD>
    <TD class=ttl onclick=showHide(this) width=129 
    background="<%=path%>/pub1/images/box_topbg.gif">
	基础数据
	</TD>
    <TD width=7><IMG height=25 
      src="<%=path%>/pub1/images/box_topright.gif" width=7></TD></TR>
  <TR style="DISPLAY: none">
    <TD 
    style="PADDING-RIGHT: 3px; PADDING-LEFT: 3px; PADDING-BOTTOM: 3px; PADDING-TOP: 3px" 
    background="<%=path%>/pub1/images/box_bg.gif" colSpan=3>
      <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/dep_browse.do" 
	            target=mainFrame>机构管理</A></TD></TR>
	        
              </TBODY></TABLE>
                   <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/query1.do?dep_code=0" 
	            target=mainFrame>专业管理</A></TD></TR>
	        
              </TBODY></TABLE>
                   <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/Class.do" 
	            target=mainFrame>班级管理</A></TD></TR>
	        
              </TBODY></TABLE>
                   <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/userindoPage.do" 
	            target=mainFrame>用户管理</A></TD></TR>
	        
              </TBODY></TABLE>
                   <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/bro_cy.do" 
	            target=mainFrame>企业管理</A></TD></TR>
	        
              </TBODY></TABLE>
	  </TD></TR>
  <TR>
    <TD colSpan=3><IMG height=10 
      src="<%=path%>/pub1/images/box_bottom.gif" 
width=159></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=159 align=center border=0>
  <TBODY>
  <TR>
    <TD width=23><IMG height=25 
      src="<%=path%>/pub1/images/box_topleft.gif" width=23></TD>
    <TD class=ttl onclick=showHide(this) width=129 
    background="<%=path%>/pub1/images/box_topbg.gif">
	新闻系统
	</TD>
    <TD width=7><IMG height=25 
      src="<%=path%>/pub1/images/box_topright.gif" width=7></TD></TR>
  <TR style="DISPLAY: none">
    <TD 
    style="PADDING-RIGHT: 3px; PADDING-LEFT: 3px; PADDING-BOTTOM: 3px; PADDING-TOP: 3px" 
    background="<%=path%>/pub1/images/box_bg.gif" colSpan=3>
      <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/browse.do" 
	            target=mainFrame>新闻管理</A></TD></TR>
	        
              </TBODY></TABLE>
                    <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="http://xgcal.sinaapp.com/demo2/" 
	            target=mainFrame>日程管理</A></TD></TR>
	        
              </TBODY></TABLE>
	  </TD></TR>
  <TR>
    <TD colSpan=3><IMG height=10 
      src="<%=path%>/pub1/images/box_bottom.gif" 
width=159></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=159 align=center border=0>
  <TBODY>
  <TR>
    <TD width=23><IMG height=25 
      src="<%=path%>/pub1/images/box_topleft.gif" width=23></TD>
    <TD class=ttl onclick=showHide(this) width=129 
    background="<%=path%>/pub1/images/box_topbg.gif">
	就业系统
	</TD>
    <TD width=7><IMG height=25 
      src="<%=path%>/pub1/images/box_topright.gif" width=7></TD></TR>
  <TR style="DISPLAY: none">
    <TD 
    style="PADDING-RIGHT: 3px; PADDING-LEFT: 3px; PADDING-BOTTOM: 3px; PADDING-TOP: 3px" 
    background="<%=path%>/pub1/images/box_bg.gif" colSpan=3>
      <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/bro_stud.do" 
	            target=mainFrame>学生就业</A></TD></TR>
	        
              </TBODY></TABLE>
                    <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/browse.do" 
	            target=mainFrame>学生信息</A></TD></TR>
	        
              </TBODY></TABLE>
	  </TD></TR>
  <TR>
    <TD colSpan=3><IMG height=10 
      src="<%=path%>/pub1/images/box_bottom.gif" 
width=159></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=159 align=center border=0>
  <TBODY>
  <TR>
    <TD width=23><IMG height=25 
      src="<%=path%>/pub1/images/box_topleft.gif" width=23></TD>
    <TD class=ttl onclick=showHide(this) width=129 
    background="<%=path%>/pub1/images/box_topbg.gif">
	统计查询
	</TD>
    <TD width=7><IMG height=25 
      src="<%=path%>/pub1/images/box_topright.gif" width=7></TD></TR>
  <TR style="DISPLAY: none">
    <TD 
    style="PADDING-RIGHT: 3px; PADDING-LEFT: 3px; PADDING-BOTTOM: 3px; PADDING-TOP: 3px" 
    background="<%=path%>/pub1/images/box_bg.gif" colSpan=3>
      <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/bro_sta.do" 
	            target=mainFrame>单位统计</A></TD></TR>
	        
              </TBODY></TABLE>
               <TABLE width="100%">
        <TBODY>
	        <TR>
	          <TD><IMG height=7 hspace=5 
	            src="<%=path%>/pub1/images/arrow.gif" width=5 align=absMiddle> 
	            <A 
	            href="<%=path%>/bro_spec.do" 
	            target=mainFrame>机构统计</A></TD></TR>
	        
              </TBODY></TABLE>
	  </TD></TR>
  <TR>
    <TD colSpan=3><IMG height=10 
      src="<%=path%>/pub1/images/box_bottom.gif" 
width=159></TD></TR></TBODY></TABLE>

</BODY></HTML>
