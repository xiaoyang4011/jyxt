package crds.system.sysSite.web.action;

import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.system.sysSite.web.form.PointRulerForm;
import crds.system.sysSite.web.form.BaseParasForm;
import crds.system.sysSite.web.form.ResourceTypeForm;
import crds.system.sysSite.web.form.IPSectionForm;
import crds.system.sysSite.dao.implement.DAOSysSite;
import crds.system.sysSite.dao.IDAOSysSite;
import crds.system.sysSite.bo.IBOSysSite;
import crds.system.sysSite.bo.implement.BOSysSite;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;
public class ActionSysSite extends MappingDispatchActionSupport {
	 public IBOSysSite getBO(){
	    	return (IBOSysSite)this.getWebApplicationContext().getBean("BOSysSite");
	    }
   
    //read record from database:siteconfige into formBean
    public ActionForward update_siteconfige(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    BaseParasForm baseParasForm=(BaseParasForm)form;
	boolean flag=getBO().updateSiteConfige(baseParasForm);
	if (flag)
	   return mapping.findForward("updateSysConfSucc");
	else
		return mapping.findForward("updateFail");
	}
    //处理siteconfige
    public ActionForward map_siteconfige(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    BaseParasForm baseParasForm=(BaseParasForm)form;
     if (getBO().getMap4SC(baseParasForm))
	    return mapping.findForward("getSiteConf");
     else
    	 return mapping.findForward("updateFail");
	}
    //修改积分规则PointRuler
    public ActionForward update_PointRuler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	PointRulerForm pointRulerForm=(PointRulerForm)form;
	   boolean flag=getBO().updatePointRuler(pointRulerForm);
	if (flag)
	   return mapping.findForward("updatePointRulerSucc");
	else
		return mapping.findForward("updateFail");
	}
  //处理积分规则PointRuler
    public ActionForward map_PointRuler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
	  PointRulerForm pointRulerForm = (PointRulerForm) form;
	  if (getBO().getMap4PR(pointRulerForm))
		return mapping.findForward("getPointRulerSucc");
	  else
		  return mapping.findForward("updateFail"); 
	}
    
	public ActionForward update_ResourceType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
	   ResourceTypeForm resourceTypeForm=(ResourceTypeForm)form;
       boolean flag=getBO().updateResourceType(resourceTypeForm);
	  if (flag)
	  {
		// request.setAttribute("formResourceType", resourceTypeForm);
	    return mapping.findForward("updateResourceTypeSucc");
	  }
	  else
		return mapping.findForward("updateFail");
	}
	
    public ActionForward map_ResourceType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	ResourceTypeForm resourceTypeForm=(ResourceTypeForm)form;
		boolean flag = getBO().getList4RT(resourceTypeForm);
        if (flag)
		    return mapping.findForward("getResourceTypeSucc");
        else
        	return mapping.findForward("updateFail");
	}
    //获取当前的可用IP段数据
    public ActionForward get_IPSections(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    //	IPSectionForm IPSForm=(IPSectionForm)form;
    	List  list = null;
		list = getBO().getList4IPSections();
		request.setAttribute("IPSectionList", list);
		return mapping.findForward("getIPConfigSucc");
	}
    //追加一条IP段数据
    public ActionForward app_IPConfig(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	IPSectionForm IPSForm=(IPSectionForm)form;
    	//manipulating start_ip_section
    	IPSForm.setStart_one_segment(request.getParameter("s_one_section").trim());
    	IPSForm.setStart_two_segment(request.getParameter("s_two_section").trim());
    	IPSForm.setStart_three_segment(request.getParameter("s_three_section").trim());
    	IPSForm.setStart_four_segment(request.getParameter("s_four_section").trim());
        //manipulating end_ip_section
    	IPSForm.setEnd_one_segment(request.getParameter("e_one_section").trim());
    	IPSForm.setEnd_two_segment(request.getParameter("e_two_section").trim());
    	IPSForm.setEnd_three_segment(request.getParameter("e_three_section").trim());
    	IPSForm.setEnd_four_segment(request.getParameter("e_four_section").trim());
    	List  list = null;
		list =getBO().set_get_IPSectionList(IPSForm);
		request.setAttribute("IPSectionList", list);
		return mapping.findForward("setIPConfigSucc");
	}
    //删除一条IP段数据
    public ActionForward del_IPSection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	List  list = null;
    	String delIPID=request.getParameter("key").trim();
    	if (getBO().del_SelectIP(delIPID))
    	{
    		list = getBO().getList4IPSections();
    		request.setAttribute("IPSectionList", list);
    		return mapping.findForward("eraseIPSectionSucc");	
    	}
    	else
    		return mapping.findForward("eraseIPSectionFail");
	}
    //修改一条IP段数据
    public ActionForward update_IPSection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	IPSectionForm IPSForm=(IPSectionForm)form;
    	String IPSID=request.getParameter("IPID").trim();
    	String from_IP=request.getParameter("fromIP").trim();
    	String to_IP=request.getParameter("toIP").trim();
    	IPSForm.setIpsection_id(IPSID);
    	IPSForm.setFrom_IPAddress(from_IP);
    	IPSForm.setTo_IPAddress(to_IP);
		return mapping.findForward("updateIPSectionSucc");
	}
    //提交一条IP段数据的修改成果
    public ActionForward store_IPSection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	IPSectionForm IPSForm=(IPSectionForm)form;
    	List list=null;
    	boolean flag=getBO().updateIPIntoDB(IPSForm);
    	if (flag)
    	{
    		list = getBO().getList4IPSections();
    		request.setAttribute("IPSectionList", list);
		    return mapping.findForward("submitIPSectionSucc");
    	}
    	else
    		return mapping.findForward("submitIPSectionFail");
	}
}
