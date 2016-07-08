package crds.statistics.web.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;
import crds.statistics.bo.IBOSta;
import crds.statistics.web.form.FormSta;

public final class ActionSta extends MappingDispatchActionSupport{
	@SuppressWarnings("unused")
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;	 
	public IBOSta getBO() {
		return (IBOSta) this.getWebApplicationContext().getBean("BOSta");
	}
	
	
	public ActionForward Jump(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		return mapping.findForward("succ");	

	}
	@SuppressWarnings("unchecked")
	public ActionForward cy_Bro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		FormSta formsta=(FormSta)form;
		if(formsta.getRowsPerPage()==0)
		{
			formsta.setRowsPerPage(12);
		}
		
		List listmessage = getBO().cy_Bro(formsta);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formsta",formsta);
		return mapping.findForward("succ");	
}
	@SuppressWarnings("unchecked")
	public ActionForward cy_spec(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		FormSta formsta=(FormSta)form;
		if(formsta.getRowsPerPage()==0)
		{
			formsta.setRowsPerPage(12);
		}
		
		List listmessage = getBO().cy_spec(formsta);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formsta",formsta);
		return mapping.findForward("succ");	
}
}
