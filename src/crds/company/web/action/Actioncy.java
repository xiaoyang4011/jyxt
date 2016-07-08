package crds.company.web.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.company.bo.IBOcy;
import crds.company.web.form.Formcy;

public final class Actioncy extends MappingDispatchActionSupport {
	@SuppressWarnings("unused")
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;	 
	public IBOcy getBO() {
		return (IBOcy) this.getWebApplicationContext().getBean("BOcy");
	}

	public ActionForward AddCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		Formcy formcy=(Formcy)form;
		int Messagelist = getBO().Add_cy(formcy);
		if (Messagelist!=0)
		{
			request.setAttribute("message","保存成功！");	
			return mapping.findForward("succ");
		}
		else
		{
			request.setAttribute("message","保存失败！");	
			return mapping.findForward("succ");
		}
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward BroCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formcy formcy=(Formcy)form;
		if(formcy.getRowsPerPage()==0)
		{
			formcy.setRowsPerPage(12);
		}
		
		List list = getBO().Bro_cy(formcy);
		request.setAttribute("list",list);
		request.setAttribute("formcy",formcy);
		return mapping.findForward("succ");	
}
	
	
	
//跳转Action 1
	public ActionForward JumpCy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		return mapping.findForward("succ");	

	}
//跳转传值Action 2
	@SuppressWarnings("unchecked")
	public ActionForward JumpCy2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		
		Formcy formcy=(Formcy)form;
		List listmessage = getBO().Jump_cy(formcy);
		request.setAttribute("listmessage", listmessage);
		request.setAttribute("formcy", formcy);
		return mapping.findForward("succ");	

	}
	@SuppressWarnings("unchecked")
	public ActionForward UpdateCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formcy formcy=(Formcy)form;
		int Savelist = getBO().Update_cy(formcy);
		//判断Savelist的值确定跳转页面
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
	
	public ActionForward DelCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		Formcy formcy=(Formcy)form;
	    int Dellist = getBO().Del_cy(formcy);
	  //判断Dellist的值确定跳转页面
		if(Dellist!=0){
			request.setAttribute("message","删除成功！");	
			return mapping.findForward("succ");
		}else{
			request.setAttribute("message","删除失败！");
			return mapping.findForward("fail");
		}
	}

}
