package crds.zhaosheng.web.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.company.web.form.Formcy;
import crds.zhaosheng.bo.IBOzs;
import crds.zhaosheng.web.form.FormZs;

public final class ActionZs extends MappingDispatchActionSupport{
	@SuppressWarnings("unused")
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;	 
	public IBOzs getBO() {
		return (IBOzs) this.getWebApplicationContext().getBean("BOzs");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward BroStud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{

		FormZs formzs=(FormZs)form;
	
		if(formzs.getRowsPerPage()==0)
		{
			formzs.setRowsPerPage(12);
		}
		
		List list = getBO().Bro_stud(formzs);

		request.setAttribute("list",list);
		request.setAttribute("formzs",formzs);
		return mapping.findForward("succ");	
}
	@SuppressWarnings("unchecked")
	public ActionForward BroCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		FormZs formzs=(FormZs)form;

		if(formzs.getRowsPerPage()==0)
		{
			formzs.setRowsPerPage(12);
		}
		
		List list = getBO().Bro_cy(formzs);
		request.setAttribute("list",list);
		request.setAttribute("formzs",formzs);
		return mapping.findForward("succ");	
}
	
	public ActionForward Update_jiuye(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		FormZs formzs=(FormZs)form;
		
		int Savelist = getBO().Update_jiuye(formzs);
		
		if(Savelist!=0)
		{
			request.setAttribute("message","修改成功！");	
			return mapping.findForward("succ");
		}
		else
		{
			request.setAttribute("message","修改失败！");	
			return mapping.findForward("fail");
		}
		
}
	public ActionForward del_jiuye(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		FormZs formzs=(FormZs)form;
		int Savelist = getBO().del_jiuye(formzs);
		
		if(Savelist!=0)
		{
			request.setAttribute("message","修改成功！");	
			return mapping.findForward("succ");
		}
		else
		{
			request.setAttribute("message","修改失败！");	
			return mapping.findForward("fail");
		}
}
}
