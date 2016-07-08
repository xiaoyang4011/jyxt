<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,crds.pub.util.*,java.lang.Math;"%>
<%
	String path = request.getContextPath();
%>
<script language="Javascript" src="<%=path%>/pub/js/JSClass.js"></script>
<script language="Javascript" src="<%=path%>/pub/js/SpryPanelControl.js"></script>
<script language="Javascript" src="<%=path%>/pub/js/jquery-1.3.2.min.js"></script>
<script language="Javascript">jQuery.noConflict();</script>
<script language="Javascript" src="<%=path%>/pub/js/crds_common.js"></script>
<SCRIPT LANGUAGE="JavaScript" src="<%=path%>/pub/js/crds_fixed_datasource.js"></Script>
<link rel="StyleSheet" href="<%=path%>/pub/css/defaultSkin.css" type="text/css" />