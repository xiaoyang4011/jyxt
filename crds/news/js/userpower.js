// 2005-02-17 zhoujf 增加补充注释
//页面的操作权限判断 
//获取页面名称 geturl=getPageUrl(); geturl="http://career.buaa.edu.cn/website/script/userpower.htm"
//页面跳转：<body onload="toPageUrl('gen,cls,dep','gen,cls,cls')"> 
	//当用户角色是gen,页面跳转到以gen为后缀页面;当用户角色是cls,dep,页面跳转到以cls为后缀页面
//判断显示：var str="要显示内容";
	//  document.write(getPower('gen,cls,dep',str));
	//  当用户角色是gen,cls,dep时，显示str;

function getPageUrl()   //获取页面名称
{
	var url = '';
	try{
		url = opener.top.location.href;
	}catch(e){
		url = top.location.href;
	}
	url += '';
	return url;
}

/**页面跳转
* @param name_str  判断字符str
* @param url_str   对应跳转页标志到url
*/
function toPageUrl(name_str,url_str)   
{
	var geturl=getPageUrl();
	var loadurl='';
	var selfurl=self.location.href;
	var name_array=name_str.split(",");
	var url_array=url_str.split(",");
	var urlname = '';
		urlname = selfurl.substring(0, selfurl.indexOf('.htm'));
	for(var i=0;i<name_array.length;i++)
	{
		if(geturl.indexOf(name_array[i])>=0)
		if(url_array[i]!='')
			{ loadurl=url_array[i];break;}
		else 
			self.location.href="http://career.buaa.edu.cn/pages/error.htm";
	}
	if(loadurl!=''&&urlname.indexOf(loadurl)<0)   //避免重复加载
		//loadurl=''+urlname+'_'+loadurl+".htm";
	    self.location.href=''+urlname+'_'+loadurl+".htm";
}

/**判断显示
* @param name_str     判断字符str
* @param display_str  显示字符str
* return get_str 
*/
function  getPower(name_str,display_str)      
{
	var url=getPageUrl();
	var name_array=name_str.split(",");
	var get_str = '';
	for(var i=0;i<name_array.length;i++)
	{
		if(url.indexOf(name_array[i])>=0)
		{ get_str=display_str;break;}
	}	
	return get_str;
}


// 取通过URL传过来的参数 (格式如 ?Param1=Value1&Param2=Value2)
var URLParams = new Object() ;
var uParams = document.location.href.split('?');
var aParams = document.location.search.substr(1).split('&') ;
for (i=0 ; i < aParams.length ; i++) {
	var aParam = aParams[i].split('=') ;
	URLParams[aParam[0]] = aParam[1] ;
}