package crds.system.user.web.action;



import java.util.List;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import crds.system.user.bo.IBOUser;
import crds.system.user.web.form.FormUser;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.taglibs.standard.lang.jstl.Logger;
import org.springframework.web.struts.MappingDispatchActionSupport;






public final class ActionUser extends MappingDispatchActionSupport {
	
	
 
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;
	 

	public IBOUser getBO() {
		return (IBOUser) this.getWebApplicationContext().getBean("BOUser");
	}
	
	/**  查询用户*/
	@SuppressWarnings("unchecked")
	public ActionForward queryUser( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		FormUser formuser=(FormUser)form;
		// 设置每页行数
		if (formuser.getRowsPerPage() == 0) {
			formuser.setRowsPerPage(5);
		}
		List  resultList = null;
		
	    resultList = getBO().queryUser(formuser);			
	    //formuser.setQuery_user_name("123");
		//request.setAttribute("form", formuser);
		request.setAttribute("userlist", resultList);
		return mapping.findForward("show");		
	}

	/**  增加用户*/
	@SuppressWarnings("unchecked")
	public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		/*IQuestionService ser = BOFactory.getQuestionService();
		List sublist = ser.findAllSubjects();
		Subject opt = new Subject();
		opt.setSubjectid(new Long(0));
		opt.setName("全部");
		sublist.add(0, opt);*/
		List deptlist = getBO().findAllDepts();
		request.setAttribute("deptlist", deptlist);
		//FormUser formuser = (FormUser)form;
		//formuser.setUser_name("2334");
		this.saveToken(request);
		return mapping.findForward("add");
	}
	/**  增加用户保存*/
	@SuppressWarnings("unchecked")
	public ActionForward addUserDo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		FormUser formuser = (FormUser)form;
		ActionForward forward = null;
		if(this.isTokenValid(request)) {
			//IStudentService ser = BOFactory.getStudentService();
			IBOUser bo=getBO();
			try {
				if(bo.checkUserExists(formuser)) {
					List deptlist = getBO().findAllDepts();
					request.setAttribute("deptlist", deptlist);
					request.setAttribute("message","用户名或idID已经存在");
					forward = mapping.findForward("add");					
				}else {
					bo.addUser(formuser);				
					request.setAttribute("message","增加成功");
					forward = mapping.findForward("show");
					this.resetToken(request);
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
				List deptlist = getBO().findAllDepts();
				request.setAttribute("deptlist", deptlist);
				request.setAttribute("message","注册失败");
				forward = mapping.findForward("add");					
			}
		}else {
			request.setAttribute("message","请不要重复提交");
			forward = mapping.findForward("show");
		}
		
		return forward;
	}
	/**  删除用户*/
	@SuppressWarnings("unchecked")
	public ActionForward delUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		return mapping.findForward("show");
	}
}
