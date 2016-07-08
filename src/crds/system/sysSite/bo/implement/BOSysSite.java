package crds.system.sysSite.bo.implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import crds.system.sysSite.dao.IDAOSysSite;
import crds.system.sysSite.bo.IBOSysSite;
import crds.system.sysSite.web.form.BaseParasForm;
import crds.system.sysSite.web.form.IPSectionForm;
import crds.system.sysSite.web.form.PointRulerForm;
import crds.system.sysSite.web.form.ResourceTypeForm;

public class BOSysSite implements IBOSysSite {

	private IDAOSysSite dao;
	
	public void setDAOSysSite(IDAOSysSite dao) {
		this.dao = dao;
	}
	
	public IDAOSysSite getDAOSysSite() {
		return this.dao;
	}
	
	//获取BaseParasForm接口对象的各个字段数据
	public BaseParasForm getFormBin4SourceConfig() throws Exception {
		// TODO Auto-generated method stub
		BaseParasForm bPForm=new BaseParasForm();
		Map<String,Object> map=dao.getMap4SC();
         bPForm.setSite_name(map.get("site_name").toString());
		 bPForm.setSite_description(map.get("site_description").toString());
		 bPForm.setSite_open(map.get("site_open").toString());
		 bPForm.setSite_key(map.get("site_key").toString());
		 bPForm.setSite_anonymous(map.get("site_anonymous").toString());
		 bPForm.setCopyright(map.get("copyright").toString());
		 bPForm.setWords_filter(map.get("words_filter").toString());
		 bPForm.setScript_filter(map.get("script_filter").toString());
		 bPForm.setRegister_open(map.get("register_open").toString());
		 bPForm.setTimeout(Integer.parseInt(map.get("timeout").toString()));
		 bPForm.setRegister_check(map.get("register_check").toString());
		 bPForm.setMaxlength(Integer.parseInt(map.get("maxlength").toString()));
		 bPForm.setMinlength(Integer.parseInt(map.get("minlength").toString()));
	     bPForm.setDeny_name(map.get("deny_name").toString());
	     bPForm.setFile_maxsize(Integer.parseInt(map.get("file_maxsize").toString()));     
		 return bPForm;	
	}
	//获取PointRulerForm接口对象的各个字段数据
	public PointRulerForm getFormBin4Point_Ruler() throws Exception
	{
		PointRulerForm pRForm=new PointRulerForm();
		Map<String,Object> map=dao.getMap4PR();
		pRForm.setRuler_id(Long.parseLong(map.get("ruler_id")
					.toString()));
		pRForm.setInit_point(Integer.parseInt(map.get("init_point")
					.toString()));
		pRForm.setPoint_multiple(Integer.parseInt(map.get(
					"point_multiple").toString()));
		pRForm.setDefault_point(Integer.parseInt(map.get(
					"default_point").toString()));
		pRForm.setMax_point(Integer.parseInt(map.get("max_point")
					.toString()));
		pRForm.setStart_point(Integer.parseInt(map.get(
					"start_point").toString()));
		pRForm.setAward_point(Integer.parseInt(map.get("award_point")
				.toString()));
		pRForm.setAdd_point(Integer.parseInt(map.get("add_point")
				.toString()));
		return pRForm;
	}
	//获取SourceConfiguration表数据
	public boolean getMap4SC(BaseParasForm baseParasForm) throws Exception
   {
		List list=dao.getList4SC();
		if (list.size()>0)
		{
			Map<String, Object> map=dao.getMap4SC();
			if (map.containsKey("site_name"))
			 baseParasForm.setSite_name(map.get("site_name").toString());
			if (map.containsKey("site_description"))
			 baseParasForm.setSite_description(map.get("site_description").toString());
			if (map.containsKey("site_open"))
			  baseParasForm.setSite_open(map.get("site_open").toString());
			if (map.containsKey("site_key"))
			  baseParasForm.setSite_key(map.get("site_key").toString());
			if (map.containsKey("site_anonymous"))
			 baseParasForm.setSite_anonymous(map.get("site_anonymous").toString());
			if (map.containsKey("copyright"))
			 baseParasForm.setCopyright(map.get("copyright").toString());
			if (map.containsKey("words_filter"))
			 baseParasForm.setWords_filter(map.get("words_filter").toString());
			if (map.containsKey("script_filter"))
			  baseParasForm.setScript_filter(map.get("script_filter").toString());
			if (map.containsKey("register_open"))
			  baseParasForm.setRegister_open(map.get("register_open").toString());
			if (map.containsKey("timeout"))
			 baseParasForm.setTimeout(Integer.parseInt(map.get("timeout").toString()));
			if (map.containsKey("register_check"))
			 baseParasForm.setRegister_check(map.get("register_check").toString());
			if (map.containsKey("maxlength"))
			  baseParasForm.setMaxlength(Integer.parseInt(map.get("maxlength").toString()));
			if (map.containsKey("minlength"))
			  baseParasForm.setMinlength(Integer.parseInt(map.get("minlength").toString()));
			if (map.containsKey("deny_name"))
		     baseParasForm.setDeny_name(map.get("deny_name").toString());
			if (map.containsKey("file_maxsize"))
			     baseParasForm.setFile_maxsize(Integer.parseInt(map.get("file_maxsize").toString()));
		}
		else
		{
			baseParasForm.setSite_name("");
			baseParasForm.setSite_description("");
			baseParasForm.setSite_open("");
			baseParasForm.setSite_key("");
			baseParasForm.setSite_anonymous("");
			baseParasForm.setCopyright("");
			baseParasForm.setWords_filter("");
			baseParasForm.setScript_filter("");
			baseParasForm.setRegister_open("");
			baseParasForm.setTimeout(0);
			baseParasForm.setRegister_check("");
			baseParasForm.setMaxlength(0);
			baseParasForm.setMinlength(0);
			baseParasForm.setDeny_name("");
			baseParasForm.setFile_maxsize(0);
		}
		return true;
	}
	//获取积分规则表PointRuler数据
	  public boolean  getMap4PR(PointRulerForm pointRulerForm) throws Exception
	  {
		  List list=dao.getList4PR();
		if (list.size() > 0) {
			Map<String, Object> map = dao.getMap4PR();
			if (map.containsKey("init_point"))
				pointRulerForm.setInit_point(Integer.parseInt(map.get(
						"init_point").toString()));
			if (map.containsKey("point_multiple"))
				pointRulerForm.setPoint_multiple(Integer.parseInt(map.get(
						"point_multiple").toString()));
			if (map.containsKey("default_point"))
				pointRulerForm.setDefault_point(Integer.parseInt(map.get(
						"default_point").toString()));
			if (map.containsKey("max_point"))
				pointRulerForm.setMax_point(Integer.parseInt(map.get(
						"max_point").toString()));
			if (map.containsKey("start_point"))
				pointRulerForm.setStart_point(Integer.parseInt(map.get(
						"start_point").toString()));
			if (map.containsKey("award_point"))
				pointRulerForm.setAward_point(Integer.parseInt(map.get(
						"award_point").toString()));
			if (map.containsKey("add_point"))
				pointRulerForm.setAdd_point(Integer.parseInt(map.get(
						"add_point").toString()));
		} else {
			pointRulerForm.setInit_point(0);
			pointRulerForm.setPoint_multiple(0);
			pointRulerForm.setDefault_point(0);
			pointRulerForm.setMax_point(0);
			pointRulerForm.setStart_point(0);
			pointRulerForm.setAward_point(0);
			pointRulerForm.setAdd_point(0);
		}
		return true;			
	  }
	  
	  public boolean getList4RT(ResourceTypeForm resourceTypeForm) throws Exception
	  {
		  List list=null;
		  boolean flag=false;
		  list=dao.getList4RT();
		  if (list.size()>0)
		  {
			  Iterator item=list.iterator();
			  while (item.hasNext())
			  {
				Map<String, String> map = (Map) item.next();
				if ((map.get("restype_name").toString().trim()).equals("文本"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setTextType("");
						resourceTypeForm.setAttach4Text("");
					}
					else
					{
					  resourceTypeForm.setTextType(map.get("file_type")
							.toString());
					  resourceTypeForm.setAttach4Text(map.get("attachment_type").toString());			  
					}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("图形/图像"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setImageType("");
						resourceTypeForm.setAttach4Image("");
				}
				else
				{
					resourceTypeForm.setImageType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4Image(map.get("attachment_type").toString());
				}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("动画"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setCartoonType("");
						resourceTypeForm.setAttach4Cartoon("");
				}
				else
				{
					resourceTypeForm.setCartoonType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4Cartoon(map.get("attachment_type").toString());
				}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("视频"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setVideoType("");
						resourceTypeForm.setAttach4Video("");
				}
				else
				{
					resourceTypeForm.setVideoType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4Video(map.get("attachment_type").toString());
				}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("教学录像"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setEducateVideo("");
						resourceTypeForm.setAttach4EduVideo("");
					}
					else
					{
					resourceTypeForm.setEducateVideo(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4EduVideo(map.get("attachment_type").toString());
					}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("网络课件库"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setNetWareType("");
						resourceTypeForm.setAttach4NetWare("");
				}
				else
				{
					resourceTypeForm.setNetWareType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4NetWare(map.get("attachment_type").toString());
				}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("教案计划"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setPlanType("");
						resourceTypeForm .setAttach4Plan("");
				}
				else
				{
					resourceTypeForm.setPlanType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4Plan(map.get("attachment_type").toString());
				}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("案例库"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setCaseType("");
						resourceTypeForm.setAttach4Case("");
					}
					else
					{
					resourceTypeForm.setCaseType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4Case(map.get("attachment_type").toString());
					}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("文献资料库"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setDocumentType("");
						resourceTypeForm.setAttach4Document("");
					}
					else
					{
					resourceTypeForm.setDocumentType(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4Document(map.get("attachment_type").toString());
					}
				}
				else if ((map.get("restype_name").toString().trim())
						.equals("标准大全"))
				{
					if (map.get("file_type")==null)
					{
						resourceTypeForm.setStandarsLib("");
						resourceTypeForm.setAttach4StandLib("");
					}
					else
					{
					resourceTypeForm.setStandarsLib(map.get("file_type")
							.toString());
					resourceTypeForm.setAttach4StandLib(map.get("attachment_type").toString());
					}
				}
			}
			 flag=true; 
		  }
		  else
			  flag=false;
		  return flag;
	  }

	// 修改SourceConfiguration
		public boolean updateSiteConfige(BaseParasForm baseParasForm) throws Exception
		{
			String sql4update=new String("update siteconfige set site_name=?,site_description=?,site_open=?,site_key=?,site_anonymous=?,copyright=?,words_filter=?,script_filter=?,timeout=?,register_open=?,register_check=?, maxlength=?,minlength=?,deny_name=?,file_maxsize=?");
			String sql4insert=new String("insert into siteconfige (site_name,site_description,site_open,site_key,site_anonymous,copyright,words_filter,script_filter,timeout,register_open,register_check, maxlength,minlength,deny_name) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			List paras = new ArrayList();
			paras.add(baseParasForm.getSite_name().trim());
			paras.add(baseParasForm.getSite_description().trim());
			paras.add(baseParasForm.getSite_open().trim());
			paras.add(baseParasForm.getSite_key().trim());
			paras.add(baseParasForm.getSite_anonymous().trim());
			paras.add(baseParasForm.getCopyright().trim());
			paras.add(baseParasForm.getWords_filter().trim());
			paras.add(baseParasForm.getScript_filter().trim());
			paras.add(baseParasForm.getTimeout());
			paras.add(baseParasForm.getRegister_open().trim());
			paras.add(baseParasForm.getRegister_check().trim());
			paras.add(baseParasForm.getMaxlength());
			paras.add(baseParasForm.getMinlength());
			paras.add(baseParasForm.getDeny_name().trim());
			paras.add(baseParasForm.getFile_maxsize());
			List list=dao.getList4SC();
			if (list.size()>0)
			    return dao.updateConfige(sql4update, paras);
			else
				return dao.insertConfige(sql4insert, paras);
		}
	//修改积分规则表PointRuler
		  public boolean updatePointRuler(PointRulerForm pointRulerForm) throws Exception
		  {
				String sql4edit=new String("update point_ruler set ruler_id=111,init_point=?, point_multiple=?,default_point=?,max_point=?,start_point=?,award_point=?,add_point=?");
				String sql4append=new String("insert into point_ruler (ruler_id,init_point, point_multiple,default_point,max_point,start_point,add_point) values (111,?,?,?,?,?,?,?)");
				List paras = new ArrayList();
				//set content for parameters
				paras.add(pointRulerForm.getInit_point());
				paras.add(pointRulerForm.getPoint_multiple());
				paras.add(pointRulerForm.getDefault_point());
				paras.add(pointRulerForm.getMax_point());
				paras.add(pointRulerForm.getStart_point());
				paras.add(pointRulerForm.getAward_point());
				paras.add(pointRulerForm.getAdd_point());
				List list=dao.getList4PR();
				if (list.size()>0)
				    return dao.updateConfige(sql4edit, paras);
				else
					return dao.insertConfige(sql4append, paras); 
		  }
	  // 修改资源类型表数据
	  public boolean updateResourceType(ResourceTypeForm resourceTypeForm) throws Exception
	  {
		  ArrayList<String> upRTSql = new ArrayList<String>();
			//文本类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getTextType().trim()+"',attachment_type='"+resourceTypeForm.getAttach4Text().trim()+"' where restype_name='文本'");
			//图形/图像类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getImageType() .trim()+"',attachment_type='"+resourceTypeForm.getAttach4Image().trim()+"' where restype_name='图形/图像'");
			//动画类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getCartoonType() .trim()+"',attachment_type='"+resourceTypeForm.getAttach4Cartoon().trim()+"' where restype_name='动画'");
			//视频类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getVideoType().trim()+"',attachment_type='"+resourceTypeForm.getAttach4Video().trim()+"' where restype_name='视频'");
			//教学录像类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getEducateVideo() .trim()+"',attachment_type='"+resourceTypeForm.getAttach4EduVideo().trim()+"' where restype_name='教学录像'"); 
			//网络课件库类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getNetWareType() .trim()+"',attachment_type='"+resourceTypeForm.getAttach4NetWare().trim()+"' where restype_name='网络课件库'");
			//教案计划类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getPlanType().trim()+"',attachment_type='"+resourceTypeForm.getAttach4Plan().trim()+"' where restype_name='教案计划'");
			//案例库类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getCaseType() .trim()+"',attachment_type='"+resourceTypeForm.getAttach4Case().trim()+"' where restype_name='案例库'");
			//文献资料库类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getDocumentType() .trim()+"',attachment_type='"+resourceTypeForm.getAttach4Document().trim()+"' where restype_name='文献资料库'");			
			//标准大全类型修改
			upRTSql.add("update resource_type set file_type='"+resourceTypeForm.getStandarsLib().trim()+"',attachment_type='"+resourceTypeForm.getAttach4StandLib().trim()+"' where restype_name='标准大全'");
			return dao.updateResourceType(upRTSql); 
	  }
		String textType;  //文本
		String attach4Text; //文本附件
		String imageType;  //图形/图像
		String attach4Image;  //图形图像附件
		String cartoonType;  //动画
		String attach4Cartoon;//动画附件
		String videoType;  //视频
		String attach4Video;//视频附件
		String educateVideo;//教学录像
		String attach4EduVideo;//教学录像附件
		String netWareType;  //网络课件库
		String attach4NetWare;//网络课件附件
		String planType;//教案计划
		String attach4Plan;//教案计划附件
		String caseType;  //案例库
		String attach4Case;//案例附件
		String documentType;  //文献资料库
		String attach4Document;//文献资料附件
		String standarsLib; //标准大全
		String attach4StandLib;//标准大全附件
  //获取当前合法IP段址的列表 
    public List getList4IPSections() throws Exception
	  {
		  List list=null;
		  list=dao.getList4IPSections();
		  return list;
	  }

    //设置并重新获取当前合法IP段址的列表 
      public List set_get_IPSectionList(IPSectionForm IPSForm) throws Exception
  	  {
    	  String message=null;
  		List list=null;
  		String sql4From=null;
  		String sql4To=null;
  		//将IPSectionForm中的IP段址追加到数据库
  		sql4From=new String(IPSForm.getStart_one_segment()+".");
  		sql4From+=IPSForm.getStart_two_segment()+".";
  		sql4From+=IPSForm.getStart_three_segment()+".";
  		sql4From+=IPSForm.getStart_four_segment();
  		
  		sql4To=new String(IPSForm.getEnd_one_segment()+".");
  		sql4To+=IPSForm.getEnd_two_segment()+".";
  		sql4To+=IPSForm.getEnd_three_segment()+".";
  		sql4To+=IPSForm.getEnd_four_segment();
  		boolean ok=dao.insertIPIntoTable(sql4From,sql4To);
  		//重新获取当前合法IP段址的列表
  		if (!ok)
  		{
  			message=new String("提交出错!");
  		}
  		list=dao.getList4IPSections();
  		return list;
  	  }
  	public boolean del_SelectIP(String delIPID) throws Exception
	{
		int OpFlag=dao.del_SelectIP(delIPID);
		if (OpFlag>0)
			return true;
		else
			return false;
	}
  //更新一条IP段数据
	  public boolean updateIPIntoDB(IPSectionForm IPSForm) throws Exception
	  {
		    String id_IP=IPSForm.getIpsection_id().trim();
			String from_IP=IPSForm.getFrom_IPAddress().trim();
			String to_IP=IPSForm.getTo_IPAddress().trim();
			String sql=new String("update ipsection set from_ip=?,to_ip=? where ipsection_id=?");
			List paras = new ArrayList();
			//set content for parameters
			paras.add(from_IP);
			paras.add(to_IP);
			paras.add(id_IP);
           return dao.updateIPSection(sql, paras);
	  }
	//将IP地址转化为字符串的方法
	public String convert(String ipAddress) throws Exception
	{
		String strIP=new String("");
		String[] sections4IP=ipAddress.split("\\.");
		for (int k=0;k<sections4IP.length;k++)
		{
			strIP+=(sections4IP[k].length()==1)?("00"+sections4IP[k]):((sections4IP[k].length()==2)?("0"+sections4IP[k]):sections4IP[k]);
		}
		return strIP.trim();
	}
	//验证IP地址是否在给定的范围内容的方法
	  public boolean isIPBetweenRange(String checkedIP,String fromIP,String toIP) throws Exception {
			String check_IP=convert(checkedIP.trim());
			String from_IP=convert(fromIP.trim());
			String to_IP=convert(toIP.trim());
			if ((check_IP.compareTo(from_IP)>=0)&&(check_IP.compareTo(to_IP)<=0))
				return true;
			else
		        return false;
		}
	//验证所给IP地址是否有效的接口方法
		public boolean valid4IPAddress(String ipAddress) throws Exception 
		{
			List list=dao.getList4IPSections();
			boolean isValid=false;
			String fromIP=null;
			String toIP=null;
			if (list.size()>0)
			{
				Iterator item=list.iterator();
				while (item.hasNext())
				{
					Map<String, String> map = (Map) item.next();
					fromIP=map.get("from_ip").toString().trim();
					toIP=map.get("to_ip").toString().trim();
					isValid=isIPBetweenRange(ipAddress,fromIP,toIP);
					if (isValid)
						return true;
				}
				return isValid;
			}
			else
				return true;	
		}
}
