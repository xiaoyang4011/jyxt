  	provinceItems = [
    {id:'11',name:'北京'},
    {id:'12',name:'天津'},
    {id:'13',name:'河北'},
    {id:'14',name:'山西'},
    {id:'15',name:'内蒙古'},
    {id:'21',name:'辽宁'},
    {id:'22',name:'吉林'},
    {id:'23',name:'黑龙江'},
    {id:'31',name:'上海'},
    {id:'32',name:'江苏'},
    {id:'33',name:'浙江'},
    {id:'34',name:'安徽'},
    {id:'35',name:'福建'},
    {id:'36',name:'江西'},
    {id:'37',name:'山东'},
    {id:'41',name:'河南'},
    {id:'42',name:'湖北'},
    {id:'43',name:'湖南'},
    {id:'44',name:'广东'},
    {id:'45',name:'广西'},
    {id:'46',name:'海南'},
    {id:'50',name:'重庆'},
    {id:'51',name:'四川'},
    {id:'52',name:'贵州'},
    {id:'53',name:'云南'},
    {id:'54',name:'西藏'},
    {id:'61',name:'陕西'},
    {id:'62',name:'甘肃'},
    {id:'63',name:'青海'},
    {id:'64',name:'宁夏'},
    {id:'65',name:'新疆'}

    ];
	//解析数据集合，有置顶，无分页，每单位只有一条职位
	function onloadlst(board,viewdivID,psize,category,jobType,natureCode)
	{
		var params = getParams(psize,category,jobType,natureCode);
		$.getJSON('http://buaa.ncss.org.cn/json/general_searchp?callback=?',params,
				function(data)
				{	var lists = '';
					var divElement="#"+viewdivID;
					var date ;
					var areaCode;
					var area="";
					var topFlag="";
					var jobType="";
					if(board=="index"){//招聘
						$.each(data.lst, function(i)
						{							
							date = this.postDate.substring(0,10);
							areaCode = this.areaCode.substring(0,2);	
							jobType = this.jobType;			
							$.each(provinceItems, function(){
								if(this.id==areaCode){area=this.name;}			 
							});
								lists += '<li><span class="item"><img src="../images/b2.gif"/*tpa=http://career.buaa.edu.cn/website/res_base/career/default/images/b2.gif*/ /><a'
								+' href="http://buaa.ncss.org.cn/job/view_job?jobId='+ this.jobId+'" target="_blank" title="'
								+ this.jobTitle
								+'&#10单位名称：' +this.recName+ '&#10单位行业：' +this.industry+'&#10单位性质：' +this.nature
								+ '">';
								if(this.priority==1){
									lists +='<font color="red">【置顶】</font>';
								}
								//lists +='【'+area+'】'
								//+this.recName
								//判断职位性质，如果为兼职或者实习，则显示“兼职”或者“实习”。
								//如果为全职或者不限，则显示单位所在地
								if(jobType=='兼职'||jobType=='实习'){
									lists +='【'+jobType+'】'
								}else{
									lists +='【'+area+'】';
								}
								lists +=this.recName
								+'</a> </span>'
								+'<span class="date">'
								+ date
								+'</span></li>';
						
						});	
						
				}
					
					if(lists!='')
						{
							
							$(divElement).html(lists);
						}else
						{
							$(divElement).html("页面加载中...");
						}
				}
		);
	}
	    	
	//初始化查询参数
	function getParams(psize,category,jobType,natureCode)
	{
	   var params = {
			recName:"",
			natureCode:natureCode,
			industryCode:"",
			recScale:"",
			jobTitle:"",
			category:category,
			jobType:jobType,
			areaCode:"",
			degreeCode:"",
		    dayLimit:"-1",
			siteId:"",//默认空为全部，00为中心，10001为北京大学
		    psize: parseInt(psize),//每页条数
		    //pindex: parseInt(pindex),//显示第？页
			callback:"test"};
		   return (params);
	}
