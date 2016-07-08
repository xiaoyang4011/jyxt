function $(s){return document.getElementById(s);}
function $$(s){return document.frames?document.frames[s]:$(s).contentWindow;}
function $c(s){return document.createElement(s);}
function exist(s){return $(s)!=null;}
function dw(s){document.write(s);}
function hide(s){$(s).style.display=$(s).style.display=="none"?"":"none";}
function isNull(_sVal){return (_sVal == "" || _sVal == null || _sVal == "undefined");}

function detectBrowser(){
	var sUA = navigator.userAgent.toLowerCase();
	var sIE = sUA.indexOf("msie");
	var sOpera = sUA.indexOf("opera");
	var sMoz = sUA.indexOf("gecko");
	if (sOpera != -1) return "opera";
	if (sIE != -1){
		nIeVer = parseFloat(sUA.substr(sIE + 5));
		if (nIeVer >= 6) return "ie6";
		else if (nIeVer >= 5.5) return "ie55";
		else if (nIeVer >= 5 ) return "ie5";
	}
	if (sMoz != -1)	return "moz";
	return "other";
}

function isIE() {
	return detectBrowser().indexOf("ie")>-1;
}

function getRadioValue(radionname) {
	var radioboxs = document.all.item(radionname);
	if (radioboxs!=null)
	{
		for (i=0; i<radioboxs.length; i++)
		{
			if (radioboxs[i].type=="radio" && radioboxs[i].checked)
			{ 
				return radioboxs[i].value;
			}
		}
		return radioboxs.value
	}
	return "";
}

function setRadioValue(myitem, v)
{
     var radioboxs = document.all.item(myitem);
     if (radioboxs!=null)
     {
       for (i=0; i<radioboxs.length; i++)
          {
            if (radioboxs[i].type=="radio")
              {
                 if (radioboxs[i].value==v)
				 	radioboxs[i].checked = true;
              }
          }
     }
}

function getCheckboxValue(checkboxname){
	var checkboxboxs = document.all.item(checkboxname);
	var CheckboxValue = '';
	if (checkboxboxs!=null)
	{
		// 如果只有一个元素
		if (checkboxboxs.length==null) {
			if (checkboxboxs.checked) {
				return checkboxboxs.value;
			}
		}
		for (i=0; i<checkboxboxs.length; i++)
		{
			if (checkboxboxs[i].type=="checkbox" && checkboxboxs[i].checked)
			{
				if (CheckboxValue==''){
					CheckboxValue += checkboxboxs[i].value;
				}
				else{
					CheckboxValue += ","+ checkboxboxs[i].value;
				}
			}
		}
	}
	return CheckboxValue;
}

function setCheckboxChecked(checkboxname, v) {
	var checkboxboxs = document.all.item(checkboxname);
	if (checkboxboxs!=null)
	{
		// 如果只有一个元素
		if (checkboxboxs.length==null) {
			if (checkboxboxs.value == v) {
				checkboxboxs.checked = true;
			}
		}
		for (i=0; i<checkboxboxs.length; i++)
		{
			if (checkboxboxs[i].type=="checkbox" && checkboxboxs[i].value == v)
			{
				checkboxboxs[i].checked = true;
			}
		}
	}
}

/*置多个checkbox，参数v如果有多个值，则通过,号分隔*/
function setMultiCheckboxChecked(checkboxname, v) {
	var checkboxboxs = document.all.item(checkboxname);
	if (checkboxboxs!=null)
	{
		// 如果只有一个元素
		if (checkboxboxs.length==null) {
			if (checkboxboxs.value == v) {
				checkboxboxs.checked = true;
			}
		}
		var ary = v.split(",");
		for (i=0; i<checkboxboxs.length; i++)
		{
			if (checkboxboxs[i].type=="checkbox")
			{
				for (j=0; j<ary.length; j++) {
					if (checkboxboxs[i].value == ary[j]) {
						
						checkboxboxs[i].checked = true;
					}
				}
			}
		}
	}
}

function isNumeric(str) {
	if (str==null || str=="")
		return false;
	return !isNaN(str);
}

function isNotCn(str) {
	if (/[^\x00-\xff]/g.test(str))
		return false;
	else
		return true;
}