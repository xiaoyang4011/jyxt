package crds.system.backMain.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.system.backMain.web.form.formologin;

public class Actionlogin extends MappingDispatchActionSupport{
	//public class Actionlogin extends ActionSupport{
	formologin login;
	public formologin getLogin() {
		return login;
	}
	public void setLogin(formologin login) {
		this.login = login;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward studentPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.print("userid"+login.getUserid());
		System.out.print("ps"+login.getPw());
		System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return mapping.findForward("succ");
	}
}
