var xmlhttp;
function sendReq(url,content,funcName){
	try{
		if(!xmlhttp){
			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		}
    }catch(e){
        try{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }catch(e){
            try{
                xmlhttp = new XMLHttpRequest();
                if(xmlhttp.overrideMimeType){
                    xmlhttp.overrideMimeType("text/xml");
                }
            }catch(e){}
        }
    }
    if(!xmlhttp){
       // window.alert("不能创建XMLHttpRequest对象实例");
        return false;
    }else {
	    //window.alert("创建XMLHttpRequest对象实例成功！");
    	if(content!=null){
	        xmlhttp.open("post",url,true);
			xmlhttp.onreadystatechange = funcName;
	        //xmlhttp.setRequestHeader( "Content-Type","application/x-www-form-urlencoded");
	        
	        xmlhttp.send(content);
    	}else{
        	xmlhttp.open("get",url,true);
        	xmlhttp.onreadystatechange = funcName;
        	//window.alert("创建XMLHttpRequest对象实例成功！");
       	 	xmlhttp.send();
    	} 
	}
}

/**
 * 定义用于发送ajax的类
 */

var Class = {
	forName:function(name){
		if (!name || !name.length) {
        	return null;
    	}
    	var npacks = name.split(".");
    	var nobj = window;
    	for (var i = 0; i<npacks.length; i++) {
        	nobj[npacks[i]] = nobj[npacks[i]] || { };
        	nobj = nobj[npacks[i]];
    	}
    	return nobj;
	}
};

Class.forName("swoky"); 
swoky.Ajax = function(){ };

swoky.Ajax.prototype = {
	createXMLHttpRequest:function () {
		var req;
		if (typeof XMLHttpRequest != "undefined") {
			req = new XMLHttpRequest();
		} else {
			var _msxmlhttp = new Array("Msxml2.XMLHTTP.6.0", 
			                           "Msxml2.XMLHTTP.3.0", 
			                           "Msxml2.XMLHTTP", 
			                           "Microsoft.XMLHTTP");
			for (var i = 0; i < _msxmlhttp.length; i++) {
				try {
					if (req = new ActiveXObject(_msxmlhttp[i])) {
						break;
					}
				}
				catch (e) {
					req = null;
				}
			}
		}
		if (!req) {
			alert("Could not create XMLHttpRequest object.");
		}
		return req;
    }, 
    open:function (url, data, callback,async ) {
		var method = data != null ? "POST" : "GET";
		var request = this.createXMLHttpRequest();
		request.open(method, url, async==null?true:false);
		request.onreadystatechange = function () {
			if (request.readyState == 4) {
				switch (request.status) {
			  	case 200:
			  		if(callback != null)
						callback(request.responseXML);
					break;
				case 400:
			  	case 404:
			  	case 500:
					alert(request.statusText);
				}
				request = null;
			}
		};
		//注意这一句必需要放在下面
		if(method == "POST")
//			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			request.setRequestHeader("Content-Type", "text/xml");
		request.send(data);
		data = null;
	}
};

Class.forName("zw"); 
zw.Ajax = function(){};

zw.Ajax.prototype = {
	createXMLHttpRequest:function () {
		var req;
		if (typeof XMLHttpRequest != "undefined") {
			req = new XMLHttpRequest();
		} else {
			var _msxmlhttp = new Array("Msxml2.XMLHTTP.6.0", 
			                           "Msxml2.XMLHTTP.3.0", 
			                           "Msxml2.XMLHTTP", 
			                           "Microsoft.XMLHTTP");
			for (var i = 0; i < _msxmlhttp.length; i++) {
				try {
					if (req = new ActiveXObject(_msxmlhttp[i])) {
						break;
					}
				}
				catch (e) {
					req = null;
				}
			}
		}
		if (!req) {
			alert("Could not create XMLHttpRequest object.");
		}
		return req;
    }, 
    open:function (url, data, callback,async ) {
		var method = data != null ? "POST" : "GET";
		var request = this.createXMLHttpRequest();
		request.open(method, url, async==null?true:false);
		request.onreadystatechange = function () {
			if (request.readyState == 4) {
				switch (request.status) {
			  	case 200:
			  		if(callback != null)
						callback(request.responseText);
					break;
				case 400:
			  	case 404:
			  	case 500:
					alert(request.statusText);
				}
				request = null;
			}
		};
		//注意这一句必需要放在下面
		if(method == "POST")
//			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			request.setRequestHeader("Content-Type", "text/xml");
		request.send(data);
		data = null;
	}
};
function loadxml(xmlFile)
{
    var xmlDoc;
    if(window.ActiveXObject)
    {
        xmlDoc    = new ActiveXObject('Microsoft.XMLDOM');
        xmlDoc.async    = false;
        xmlDoc.loadXML(xmlFile);
    }
    else if (document.implementation&&document.implementation.createDocument)
    {
        xmlDoc    = document.implementation.createDocument('', 'doc', null);
        xmlDoc.async    = false;
        xmlDoc.loadXML(xmlFile);
    }
    else
    {
        return null;
    }
    
    return xmlDoc;
}


//得到xml的文档对象
		function XmlDocument(XmlPath){
			var xmlDocNew;
			if(window.ActiveXObject)
			xmlDocNew=new ActiveXObject("Microsoft.XMLDOM");
			else if (document.implementation && document.implementation.createDocument)
			xmlDocNew=document.implementation.createDocument('','doc',null);
			xmlDocNew.async=false;
			xmlDocNew.load(XmlPath);
			return xmlDocNew;
		}
