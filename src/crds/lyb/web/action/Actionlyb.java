package crds.lyb.web.action;

import java.util.List;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import crds.lyb.web.form.Formlyb;
import crds.lyb.bo.IBOlyb;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;


public final class Actionlyb extends MappingDispatchActionSupport {
	@SuppressWarnings("unused")
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;	 
	public IBOlyb getBO() {
		return (IBOlyb) this.getWebApplicationContext().getBean("BOlyb");
	}

	@SuppressWarnings("unchecked")
	public ActionForward AddMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		Formlyb formlyb=(Formlyb)form;
		int Messagelist = getBO().Add(formlyb);
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
	public ActionForward BroMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		if(formlyb.getRowsPerPage()==0)
		{
			formlyb.setRowsPerPage(12);
		}
		
		List listmessage = getBO().Bro(formlyb);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formlyb",formlyb);
		return mapping.findForward("succ");	
}
	
	
	
//跳转Action 1
	public ActionForward Jump(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		return mapping.findForward("succ");	

	}
//跳转传值Action 2
	@SuppressWarnings("unchecked")
	public ActionForward Jump2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		
		Formlyb formlyb =(Formlyb)form;
		List listmessage = getBO().Jump(formlyb);
		request.setAttribute("listmessage", listmessage);
		request.setAttribute("formlyb", formlyb);
		return mapping.findForward("succ");	

	}
	@SuppressWarnings("unchecked")
	public ActionForward Update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb =(Formlyb)form;
		int Savelist = getBO().Update(formlyb);
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
	
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		Formlyb formlyb =(Formlyb)form;
	    int Dellist = getBO().Del(formlyb);
	  //判断Dellist的值确定跳转页面
		if(Dellist!=0){
			request.setAttribute("message","删除成功！");	
			return mapping.findForward("succ");
		}else{
			request.setAttribute("message","删除失败！");
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward BroMessage1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		List listmessage = getBO().Bro1(formlyb);
		request.setAttribute("listmessage",listmessage);
		return mapping.findForward("succ");	
	
		
}
	@SuppressWarnings("unchecked")
	public ActionForward zxdt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		if(formlyb.getRowsPerPage()==0)
		{
			formlyb.setRowsPerPage(12);
		}
		
		List listmessage = getBO().zxdt(formlyb);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formlyb",formlyb);
		return mapping.findForward("succ");	
}
	@SuppressWarnings("unchecked")
	public ActionForward xnzph(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		if(formlyb.getRowsPerPage()==0)
		{
			formlyb.setRowsPerPage(12);
		}
		
		List listmessage = getBO().xnzph(formlyb);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formlyb",formlyb);
		return mapping.findForward("succ");	
}
	@SuppressWarnings("unchecked")
	public ActionForward xwzph(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		if(formlyb.getRowsPerPage()==0)
		{
			formlyb.setRowsPerPage(12);
		}
		
		List listmessage = getBO().xwzph(formlyb);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formlyb",formlyb);
		return mapping.findForward("succ");	
}
	@SuppressWarnings("unchecked")
	public ActionForward jyzc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		if(formlyb.getRowsPerPage()==0)
		{
			formlyb.setRowsPerPage(12);
		}
		
		List listmessage = getBO().jyzc(formlyb);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formlyb",formlyb);
		return mapping.findForward("succ");	
}
	@SuppressWarnings("unchecked")
	public ActionForward jyzd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		Formlyb formlyb=(Formlyb)form;
		if(formlyb.getRowsPerPage()==0)
		{
			formlyb.setRowsPerPage(12);
		}
		
		List listmessage = getBO().jyzd(formlyb);
		request.setAttribute("listmessage",listmessage);
		request.setAttribute("formlyb",formlyb);
		return mapping.findForward("succ");	
}
}