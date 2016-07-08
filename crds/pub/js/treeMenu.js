var ImgDir;
var indexOfEntries = new Array;
var nEntries = 0;
var doc = document;
var browserVersion = 0;
var auxs = new Array;
var FolderOpenIcon = "fopen.gif";
var FolderCloseIcon = "fclose.gif";
var treeClass=0;
var parentLevel=0;
var preId=0;

function SetFolderIcon(iOpen, iClose)
{
	var aux;
	if(!iOpen)
	{
		iOpen = "fopen.gif";
		iClose = "fclose.gif";
	}

	if(!iClose)iClose = iOpen;

	aux = new Image();
	aux.src= ImgDir + iOpen;
	aux = new Image();
	aux.src= ImgDir + iClose;

	FolderOpenIcon = iOpen;
	FolderCloseIcon = iClose;
}

function loadImages()
{
	var aux;

	aux = new Image();
	aux.src= ImgDir + "blank.gif";
	aux = new Image();
	aux.src= ImgDir + "lastnode.gif";
	//aux = new Image();
	//aux.src= ImgDir + "link.gif";
	aux = new Image();
	aux.src= ImgDir + "mlastnode.gif";
	aux = new Image();
	aux.src= ImgDir + "mnode.gif";
	aux = new Image();
	aux.src= ImgDir + "node.gif";
	aux = new Image();
	aux.src= ImgDir + "plastnode.gif";
	aux = new Image();
	aux.src= ImgDir + "pnode.gif";
	aux = new Image();
	aux.src= ImgDir + "vertline.gif";
}

function Folder(level,folderDescription, isDefOpen)
{
	this.desc = folderDescription;
	this.id = -1;
	this.navObj = 0;
	this.iconImg = 0;
	this.nodeImg = 0;
	this.isLastNode = 0;
	this.isDefOpen = isDefOpen;

    this.bLevel=level;
	this.isOpen = true;
	this.iconSrc = FolderOpenIcon;
	this.iconOpen = FolderOpenIcon;
	this.iconClose = FolderCloseIcon;
	this.children = new Array;
	this.nChildren = 0;

	this.initialize = initializeFolder;
	this.setState = setStateFolder;
	this.addChild = addChild;
	this.createIndex = createEntryIndex;
	this.hide = hideFolder;
	this.display = display;
	this.renderOb = drawFolder;
	this.totalHeight = totalHeight;
	this.subEntries = folderSubEntries;
}

function setStateFolder(isOpen)
{
	var subEntries;
	var totalHeight;
	var fIt = 0;
	var i=0;

	if (isOpen == this.isOpen)
		return;

	if (browserVersion == 2)
	{
		totalHeight = 0;
		for (i=0; i < this.nChildren; i++)
			totalHeight = totalHeight + this.children[i].navObj.clip.height;
			subEntries = this.subEntries();
		if (this.isOpen)
			totalHeight = 0 - totalHeight;
		for (fIt = this.id + subEntries + 1; fIt < nEntries; fIt++)
			indexOfEntries[fIt].navObj.moveBy(0, totalHeight);
	}
	this.isOpen = isOpen;
	propagateChangesInState(this);
}

function propagateChangesInState(folder)
{
	var i=0;

	if (folder.isOpen)
	{
		if (folder.nodeImg)
			if (folder.isLastNode)
				folder.nodeImg.src = ImgDir + "mlastnode.gif";
			else
				folder.nodeImg.src = ImgDir + "mnode.gif";
		folder.iconImg.src = ImgDir + folder.iconOpen;
		for (i=0; i<folder.nChildren; i++)
			folder.children[i].display();
	}
	else
	{
		if (folder.nodeImg)
			if (folder.isLastNode)
				folder.nodeImg.src = ImgDir + "plastnode.gif";
			else
				folder.nodeImg.src = ImgDir + "pnode.gif";
		folder.iconImg.src = ImgDir + folder.iconClose;
		for (i=0; i<folder.nChildren; i++)
			folder.children[i].hide();
	} 
}

function hideFolder()
{
	if (browserVersion == 1)
	{
		if (this.navObj.style.display == "none")
			return;
		this.navObj.style.display = "none";
	}else
	{
		if (this.navObj.visibility == "hiden")
			return;
		this.navObj.visibility = "hiden";
	}

	this.setState(0);
}

function initializeFolder(level, lastNode, leftSide)
{
	var j=0;
	var i=0;
	var numberOfFolders;
	var numberOfDocs;
	var nc;
	nc = this.nChildren;

	this.createIndex();

	var auxEv = "";
	
	if (browserVersion > 0)
	{
		auxEv = "<a href='#' onclick='clickOnNode_new("+this.id+","+level+");'";
		auxEv = auxEv + ' title=\"' + this.desc + '\"><font color=#000000>';
	}
	else
	{
		auxEv = "<a><font color=#000000>";
	}

	if (level>0)
		if (lastNode)
		{
			this.renderOb(leftSide + auxEv + "<img name='nodeIcon" + this.id + "' src='" + ImgDir + "mlastnode.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0 border=0>");
			leftSide = leftSide + "<img src='" + ImgDir + "blank.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0>";
			this.isLastNode = 1;
		}else
		{
			this.renderOb(leftSide + auxEv + "<img name='nodeIcon" + this.id + "' src='" + ImgDir + "mnode.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0 border=0>");
			leftSide = leftSide + "<img src='" + ImgDir + "vertline.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0>";
			this.isLastNode = 0;
		}
	else
		this.renderOb(auxEv);

	if (nc > 0)
	{
		level = level + 1;
		for (i=0 ; i < this.nChildren; i++)
		{
			if (i == this.nChildren-1)
				this.children[i].initialize(level, 1, leftSide);
			else
				this.children[i].initialize(level, 0, leftSide);
			}
	}
}

function drawFolder(leftSide)
{
	if (browserVersion == 2)
	{
		if (!doc.yPos)
			doc.yPos=100;
		doc.write("<layer id='folder" + this.id + "' top=" + doc.yPos + ">\n<style type=\"text/css\">\ntd {font-size: 9pt}\na {color: #000000; text-decoration:none;}\n</style>");
	}
	doc.write("<table ");
	if (browserVersion == 1)
		doc.write(" id='folder" + this.id + "' style='position:block;' ");
	doc.write(" border=0 cellspacing=0 cellpadding=0>");
	doc.write("<tr valign=top><td nowrap>");
	doc.write(leftSide);
	 doc.write("<img name='folderIcon" + this.id + "' ");
	doc.write("src='" + ImgDir + this.iconSrc+"'  width=18 height=18 align=left border=0 vspace=0 hspace=0 border=0>");
	doc.write(this.desc + "</font></a>");
	doc.write("</td>");
	doc.write("</table>");
	
	if(browserVersion == 2)
		doc.write("</layer>");

	if (browserVersion == 1)
	{
		this.navObj = doc.all["folder"+this.id];
		this.iconImg = doc.all["folderIcon"+this.id];
		this.nodeImg = doc.all["nodeIcon"+this.id];
	}else if (browserVersion == 2)
	{
		this.navObj = doc.layers["folder"+this.id];
		this.iconImg = this.navObj.document.images["folderIcon"+this.id];
		this.nodeImg = this.navObj.document.images["nodeIcon"+this.id];
		doc.yPos=doc.yPos+this.navObj.clip.height;
	}
}

function addChild(childNode)
{
	this.children[this.nChildren] = childNode;
	this.nChildren++;
	return childNode;
}

function folderSubEntries()
{
	var i = 0;
	var se = this.nChildren;

	for (i=0; i < this.nChildren; i++)
	{
		if (this.children[i].children)
			se = se + this.children[i].subEntries();
	}

	return se;
}

function Item(itemDescription, itemLink, itemIcon)
{
	var aux

	this.desc = itemDescription;
	this.link = itemLink;
	this.id = -1;
	this.navObj = 0;
	this.iconImg = 0;
	if(itemIcon)
	{
		this.iconSrc = itemIcon;

		aux = new Image();
		aux.src= ImgDir + itemIcon;
	}else this.iconSrc = "doc.gif";

	this.initialize = initializeItem;
	this.createIndex = createEntryIndex;
	this.hide = hideItem;
	this.display = display;
	this.renderOb = drawItem;
	this.totalHeight = totalHeight;
}

function hideItem()
{
	if (browserVersion == 1)
	{
		if (this.navObj.style.display == "none")
			return;
		this.navObj.style.display = "none";
	}else
	{
		if (this.navObj.visibility == "hiden")
			return;
		this.navObj.visibility = "hiden";
	}
}

function initializeItem(level, lastNode, leftSide)
{
	this.createIndex();

	if (level>0)
		if (lastNode)
		{
			this.renderOb(leftSide + "<img src='" + ImgDir + "lastnode.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0>");
			leftSide = leftSide + "<img src='" + ImgDir + "blank.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0>" ;
		}
		else
		{
			this.renderOb(leftSide + "<img src='" + ImgDir + "node.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0>");
			leftSide = leftSide + "<img src='" + ImgDir + "vertline.gif' width=18 height=18 align=left border=0 vspace=0 hspace=0 align=left border=0 vspace=0 hspace=0>";
		}
	else
		this.renderOb("");
}

function drawItem(leftSide)
{
	if (browserVersion == 2)
		doc.write("<layer id='item" + this.id + "' top=" + doc.yPos + ">");
	doc.write("<table ");
	if (browserVersion == 1)
		doc.write(" id='item" + this.id + "' style='position:block;' ");
	doc.write(" border=0 cellspacing=0 cellpadding=0>");
	doc.write("<tr valign=top><td nowrap>");
	doc.write(leftSide);
	doc.write("<a href=" + this.link );
	doc.write(' title=\"' + this.desc + '\"><font color=#000000 >');
	if(browserVersion == 2)
	{
		doc.write("<layer id=showname bgcolor=#FFFFD2 visibility=hide>"+this.desc+"</layer>");
	}
	
	doc.write("<img id='itemIcon"+this.id+"' ");
	doc.write("src='" + ImgDir + this.iconSrc+"'  width=18 height=18 align=left border=0 vspace=0 hspace=0 border=0>");
	doc.write(this.desc + "</font></a>");
	doc.write("</table>");

	if (browserVersion == 2)
		doc.write("</layer>");

	if (browserVersion == 1)
	{
		this.navObj = doc.all["item"+this.id];
		this.iconImg = doc.all["itemIcon"+this.id];
	}else if (browserVersion == 2)
	{
		this.navObj = doc.layers["item"+this.id];
		this.iconImg = this.navObj.document.images["itemIcon"+this.id];
		doc.yPos=doc.yPos+this.navObj.clip.height;
	}
}

function display()
{
	if (browserVersion == 1)
		this.navObj.style.display = "block";
	else
		this.navObj.visibility = "show";
}

function createEntryIndex()
{
	this.id = nEntries;
	indexOfEntries[nEntries] = this;
	nEntries++;
}

function totalHeight()
{
	var h = this.navObj.clip.height;
	var i = 0;

	if (this.isOpen)
		for (i=0 ; i < this.nChildren; i++);
			h = h + this.children[i].totalHeight();

	return h;
}

function clickOnNode(folderId)
{
	var clickedFolder = 0;
	var state = 0;

	clickedFolder = indexOfEntries[folderId];
	state = clickedFolder.isOpen;

	clickedFolder.setState(!state);
	if(!folderId)clickedFolder.setState(state);
	return false;
}
//////////////////////////////////////////
function clickOnNode_new(folderId,foldlevel)
{  //alert(folderId);
   //alert(foldlevel);
   var clickedFolder = 0;
   clickedFolder = indexOfEntries[preId];
   if(foldlevel==1 && clickedFolder.bLevel!=foldlevel)
     clickOnNode(0);
   if(clickedFolder.bLevel==foldlevel && preId!=folderId){
    if(clickedFolder.isOpen)
     clickedFolder.setState(!clickedFolder.isOpen);
   }
   preId=folderId;
   clickOnNode(folderId);
   if(clickedFolder.bLevel>foldlevel){
    clickOnNode(folderId);
    clickedFolder = indexOfEntries[folderId];
    if(!clickedFolder.isOpen)
     clickedFolder.setState(!clickedFolder.isOpen);
   }
}

function initializeDocument()
{
	loadImages();
	
	foldersTree = auxs[0];
	if (doc.all)
		browserVersion = 1;
	else
		if (doc.layers)
			browserVersion = 2;
		else
			browserVersion = 0;
    if(foldersTree!=null){
	foldersTree.initialize(0, 1, "");

	if (browserVersion > 0)
	{
		doc.write("<layer top="+indexOfEntries[nEntries-1].navObj.top+">&nbsp;</layer>");
		clickOnNode(0);
	}
	}
}

function treeMenuAddItem(level, text, url, target, icon)
{	
	if (!target)
	{
		parentLevel=level;
		if(level)
			auxs[level] = auxs[level - 1].addChild(new Folder(level,text,url));
		else auxs[0] = new Folder(level,text);
	}
	else
	{		
		if(level>parentLevel + 1)level = parentLevel + 1;
		auxs[level - 1].addChild(new Item(text, "'"+url+"' target=\"" + target + "\" onclick=set_frame(); ", icon));
	}	
}
//点击叶子链接 左边框收缩
function set_frame(){
   //parent.parent.ChangeCol.cols = "185,*";
  // parent.document.all.menuSwitch.innerHTML = "<font class=o1>8</font>";
   //parent.document.all.menuSwitch.title="ÏÔÊ¾²Ù×÷Ê÷"
   }

