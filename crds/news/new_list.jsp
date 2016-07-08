<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>新闻列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style>
#pagelist {border:4px solid #ccc; padding:10px; width:530px; font-size:12px; list-style-type:none; }
#pagelist li {width:530px; height:30px;}
#pagelist li a .lbt {display:block; width:448px; float:left; text-indent:20px; text-decoration:none; white-space:nowrap; text-overflow:ellipsis; overflow: hidden; display:inline;}
#pagelist li a .ldt {display:block; width:75px; float:right; text-align:center; color:#069; text-decoration:none; display:inline;}
#pagelist li a {width:530px; height:30px; display:block; line-height:30px; color:#666; text-decoration:none; cursor:hand; background:url(20070807_bg.png) no-repeat 0 0;}
#pagelist li a:hover{ color:#03c; text-decoration:none; background:url(20070807_bg.png) no-repeat 0 -30px;}
#pagelist li a:hover .ldt {color:#000;}
</style>
<style type="text/css" id="LB_ADBLOCK_0">[src*="&sourcesuninfo=ad-"],[id^="youku_ad_"],#Class_1_ad>.footad,#body>#myads,[align="center"]>a>[src^="http://drmcmm.baidu.com/media/"][width="960"][height="80"],[id^="span_myads"],[id^="lovexin"],#ks>.DaKuang,.ks>.DaKuang,#topad>.tad,.box1>.ad,[class="center margintop border clear data"]>.margintop,[class="center margintop border clear data"]>[width="880"][height="90"],[class="center margintop border clear"]>.margintop,#Class_1_ad>[id^="china_ads_div"],#foot>#foot2>#Class_1_ad,#floatDiv>#rightFloat,#top>.topSponsor.mt10,.mainArea.px9>.bottomSponsor,.pause-ad,.pause-ad,img[src^="http://user.hongdou.gxnews.com.cn/upload/index/"],[src^=" http://soft.mumayi.net/js/"],.a_fr.a_cb,.adarea,.adarea_top,#lovexin1,#lovexin2,.m_ad,#topBanner,.ad1,.ad2,.ad3,.ad4,.ad5,.ad6,.ad7,.ad8,.ad9,.ad_240_h,.side-Rad,.ol_ad_xiala,iframe[src^="http://a.cntv.cn"],.h8r.font0,.topbod>.topbodr,.topbodr>table[width="670"][height="70"],.widget_ad_slot_wrapper,.gg01,.gg02,.gg03,.gg04,.gg05,.gg06,.gg07,.gg08,.gg09,#_SNYU_Ad_Wrap,[id^="snyu_slot_"],.random-box>.random-banner,.top-ads-list,.palm01-ad,.topAd950,[class^="ad-banner"][id^="ad_"],.gonglue_rightad,iframe[src^="http://www.37cs.com/html/click/"],#AdZoneRa,#AdZoneRb,#AdZoneRc,#AdZoneRd,#AdZoneRe,#AdZoneRf,#AdZoneRg,div[id="adAreaBbox"],.absolute.a_cover,#QQCOM_Width3,#auto_gen_6,.l_qq_com,#cj_ad,[href^="http://c.l.qq.com/adsclick?oid="],.right_ad_lefttext,.right_ad_righttext,.AdTop-Article-QQ,.business-Article-QQ,.qiye-Article-QQ,.AdBox-Article-QQ,td[width="960"][height="90"][align="center"],.adclass,.ad1,[id^="tonglan_"],.ads5,.adv,.ads220_60,.ad-h60,.ad-h65,.ggs,[class^="ads360"],.news_ad_box,#XianAd,.Ad3Top-Article-QQ,.AdTop2-Article-QQ,.adbutton-Aritcle-QQ,#AdRight-Article-QQ,[id^=ad-block],.sidBoxNoborder.mar-b8,#ent_ad,[class="ad"][id="ad_bottom"],[class="ad"][id="ad_top"],.plrad,.ad300,#top_ad1,#top_ad2,#top_ad3,#top_ad4,#top_ad5,#top_ad6,#top_ad7,#top_ad8,#top_ad9,#mid_ad1,#mid_ad2,#mid_ad3,#mid_ad4,#mid_ad5,#mid_ad6,#mid_ad7,#mid_ad8,#mid_ad9,#ads1,#ads2,#ads3,#ads4,#ads5,#ads6,#ads7,#ads8,#ads9,.dlAd,.changeAd,.unionCpv,#Advertisement,iframe[src*="/advertisement."],img[src*="/advertisement."],.ad_headerbanner,#ad_headerbanner,div[class^=ad_textlink],iframe[src*="guanggao"],img[src*="guanggao"],#ads-top,#ads-bot,.adBanner,#topAd,.top_ad,.topAds,.topAd,.ad_column,#ad1,#ad2,#ad3,#ad4,#ad5,#ad6,#ad7,#ad8,#ad9,.ad_footerbanner,#adleft,#adright,.advleft,.advright,.ad980X60,.banner-ad,.top-ad,#adright,#AdLayer1,#AdLayer2,#AdLayer3,div[href*="click.mediav.com"],div[class=top_ad],div[class^="headads"],.txtad,.guanggao,#guanggao,.adclass,div[id*="AdsLayer"],.ad950,.guangg,.header-ads-top,#adleft,#adright,#ad_show,.ad_pic,#fullScreenAd,div[class^="adbox"],#topADImg,div[class^="ad_box"],div[id^="adbox"],div[class^="ads_"],div[alt*="￥&#65465;&#65471;￥&#65425;&#65418;￤&#65469;&#65421;"],div[id^="ads_"],div[src*="/adfile/"],.delayadv,#vplayad,.jadv,div[src*="/ads/"],div[src*="/advpic/"],div[id*="_adbox"],div[id*="-adbox"],div[class^="showad"],div[id^="adshow"],#bottomads,.ad_column,div[id^="_AdSame"],iframe[src^="http://drmcmm.baidu.com"],div[src^="http://drmcmm.baidu.com"],frame[src^="http://drmcmm.baidu.com"],div[href^="http://www.baidu.com/cpro.php?"],iframe[href^="http://www.baidu.com/cpro.php?"],div[src^="http://cpro.baidu.com"],div[src^="http://eiv.baidu.com"],div[href^="http://www.baidu.com/baidu.php?url="],div[href^="http://www.baidu.com/adrc.php?url="],.ad_text,div[href^="http://click.cm.sandai.net"],div.adA.area,div[src*=".qq937.com"],iframe[src*=".qq937.com"],div[src*=".88210212.com"],iframe[src*=".88210212.com"],.adBox,.adRight,.adLeft,.banner-ads,.right_ad,.left_ad,.content_ad,.post-top-gg,div[class*="_ad_slot"],.col_ad,.block_ad,div[class^="adList"],.adBlue,.mar_ad,div[id^="ArpAdPro"],.adItem,.ggarea,.adiframe,iframe[src*="/adiframe/"],#bottom_ad,.bottom_ad,.crumb_ad,.topadna,.topadbod,div[src*="qq494.cn"],iframe[src*="qq494.cn"],.topadbod,embed[src*="gamefiles.qq937.com"],embed[src*="17kuxun.com"],.crazy_ad_layer,#crazy_ad_layer,.bannerad,iframe[src*="/ads/"],img[src*="/ads/"],embed[src*="/ads/"],#crazy_ad_float,.crazy_ad_float,.main_ad,.topads,div[class^="txtadsblk"],.head-ad,div[src*="/728x90."],img[src*="/728x90."],embed[src*="/728x90."],iframe[src*="/gg/"],img[src*="/gg/"],iframe[src^="http://www.460.com.cn"],#bg_ad,.ad_pic,iframe[src*="gg.yxdown.com"],.ad_top,#baiduSpFrame,.flashad,#flashad,#ShowAD,[onclick^="ad_clicked"],[class^="ad_video_"],#ad_240,.wp.a_f,.a_mu,#hd_ad,#top_ads,#header_ad,#adbanner,#adbanner_1,#Left_bottomAd,#Right_bottomAd,#ad_alimama,#vipTip,.ad_pip,#show-gg,.ad-box,.advbox,.widget-ads.banner,.a760x100,.a200x375,.a760x100,.a200x100,.ad_left,.ad_right,[id="ad"][class="toJd"]{display:none !important;}</style>
</head>




<body>
	<div align="center">
	  <ul id="pagelist">
	    <c:if test="${not empty listmessage}">
	  <%--如果不为空进行循环 显示直到为空--%>
	  <c:forEach items="${listmessage}" var="map" varStatus="cnt">      
	    <li>
	      <div align="left"><a href="<%=path%>/browse2.do?new_id=${map.new_id}" target="_blank"><span class="lbt">${map.new_title}</span><span class="ldt">${map.new_date}</span></a></div>
	    </li>
	  </c:forEach>
	  </c:if>	
      </ul>
          
	  
</div>
</body>
</html>
