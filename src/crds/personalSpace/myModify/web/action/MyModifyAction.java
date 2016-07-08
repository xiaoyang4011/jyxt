package crds.personalSpace.myModify.web.action;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.personalSpace.myBrowse.bo.IBOMyBrowse;
import crds.personalSpace.myBrowse.web.form.FormMyBrowse;
import crds.personalSpace.myModify.bo.IBOMyModify;
import crds.personalSpace.myModify.web.form.FormMyModify;


public final class MyModifyAction extends MappingDispatchActionSupport {
	
	@SuppressWarnings("unused")
	private Map<String, BufferedImage> map = null;
	@SuppressWarnings("unused")
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;
	public IBOMyModify getBO() {
		return (IBOMyModify) this.getWebApplicationContext().getBean(
		"BOMyModify");
	}

	@SuppressWarnings("unchecked")
	public ActionForward MyModify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FormMyModify form1=(FormMyModify)form;
		if (form1.getRowsPerPage() == 0) {
			form1.setRowsPerPage(10);
			}
		List list=null;
		if(("1").equals(request.getParameter("listPage"))){
			list=getBO().myload1(form1);
		}else{
			 list=getBO().myload(form1);
		}

		request.setAttribute("student", list);
		return mapping.findForward("succ");
	}
}
