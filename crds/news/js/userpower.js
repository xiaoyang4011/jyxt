// 2005-02-17 zhoujf ���Ӳ���ע��
//ҳ��Ĳ���Ȩ���ж� 
//��ȡҳ������ geturl=getPageUrl(); geturl="http://career.buaa.edu.cn/website/script/userpower.htm"
//ҳ����ת��<body onload="toPageUrl('gen,cls,dep','gen,cls,cls')"> 
	//���û���ɫ��gen,ҳ����ת����genΪ��׺ҳ��;���û���ɫ��cls,dep,ҳ����ת����clsΪ��׺ҳ��
//�ж���ʾ��var str="Ҫ��ʾ����";
	//  document.write(getPower('gen,cls,dep',str));
	//  ���û���ɫ��gen,cls,depʱ����ʾstr;

function getPageUrl()   //��ȡҳ������
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

/**ҳ����ת
* @param name_str  �ж��ַ�str
* @param url_str   ��Ӧ��תҳ��־��url
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
	if(loadurl!=''&&urlname.indexOf(loadurl)<0)   //�����ظ�����
		//loadurl=''+urlname+'_'+loadurl+".htm";
	    self.location.href=''+urlname+'_'+loadurl+".htm";
}

/**�ж���ʾ
* @param name_str     �ж��ַ�str
* @param display_str  ��ʾ�ַ�str
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


// ȡͨ��URL�������Ĳ��� (��ʽ�� ?Param1=Value1&Param2=Value2)
var URLParams = new Object() ;
var uParams = document.location.href.split('?');
var aParams = document.location.search.substr(1).split('&') ;
for (i=0 ; i < aParams.length ; i++) {
	var aParam = aParams[i].split('=') ;
	URLParams[aParam[0]] = aParam[1] ;
}