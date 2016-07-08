/**
 * product name :CRDS
 */
package crds.pub.mail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

public final class ActionEmail extends MappingDispatchActionSupport {
	static Logger logger = Logger.getLogger(ActionEmail.class);
	
	public EmailJDBC getJDBC() {
		return (EmailJDBC) getWebApplicationContext().getBean("EmailJDBC");
	}

	/**
	 * 债项评级完成发送Email的信息内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward debtRatFinishSendEmailContent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String companyCode = request.getParameter("companyCode");
		String debtCode = request.getParameter("debtCode");
		request.setAttribute("companyMainList", getJDBC().debtRatFinishSendEmailContent(1, companyCode, new String[]{}));
		request.setAttribute("companyDebtList", getJDBC().debtRatFinishSendEmailContent(2, companyCode, debtCode.split(",")));
		return mapping.findForward("success");
	}
}
