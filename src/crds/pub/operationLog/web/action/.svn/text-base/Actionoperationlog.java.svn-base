package crds.pub.operationLog.web.action;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.pub.operationLog.bo.IBOoperationlog;
import crds.pub.operationLog.web.form.Formoperationlog;
import crds.pub.util.Constant;
import crds.pub.util.FormUserOperation;
/**
 * @specification :操作记录action
 * @version : 1.0
 * @auther : yangy
 * @date : Jul 01, 2008 16:07:22 PM
 * @email : qilaug@gmail.com
 * @author ljy
 * @version 2.0
 */
public final class Actionoperationlog extends MappingDispatchActionSupport {
	static Logger logger = Logger.getLogger(Actionoperationlog.class);
	
	public IBOoperationlog getBO() {
		return (IBOoperationlog) this.getWebApplicationContext().getBean(
				"BOoperationlog");
	}
	
	/**
	 * @specification :分页查询操作记录信息
	 * @param :
	 * @return :
	 * @exception :
	 */
	public ActionForward to_modify_operationlog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Formoperationlog pageForm=(Formoperationlog)form;
		
		// 设置每页行数
		if (pageForm.getRowsPerPage() == 0) {
			pageForm.setRowsPerPage(10);
		}
		FormUserOperation formUser = Constant.getFormUserOperation(request);
		String company_code = formUser.getCompany_code();
		
		logger.info("compaddny_code="+company_code);
		if(company_code == null || (company_code != null && company_code.equals("NA"))){
			logger.info("---请首先定位发行人--");
			String str = "请首先定位发行人!";
			request.setAttribute("message",str);
			if(request.getAttribute("company_code") == null){
				request.setAttribute("company_code","");
			}
			if(request.getAttribute("company_name") == null){
				request.setAttribute("company_name","");
			}
			request.setAttribute("pageForm", pageForm);
			return mapping.findForward("toFrame");	
		}else{
		//if(request.getParameter("listPage") != null && request.getParameter("listPage").equals("1")){
			String temp_code = pageForm.getCompany_code();
			String temp_name = pageForm.getCompany_name();
			if((temp_code == null || temp_code.equals(""))&&(temp_name == null || temp_name.equals(""))){
				pageForm.setCompany_code(company_code);
			}
			List<Formoperationlog> resultList = getBO().queryOperationLogInfo(pageForm);
			// 对页面属性赋值
			request.setAttribute("pageForm", pageForm);
			request.setAttribute("resultList", resultList);
			
			
			request.setAttribute("company_code", temp_code==null?"":temp_code);
			request.setAttribute("company_name", temp_name==null?"":temp_name);
			
			//return mapping.findForward("toList");	
			return mapping.findForward("toFrame");	

		}
	}
}
