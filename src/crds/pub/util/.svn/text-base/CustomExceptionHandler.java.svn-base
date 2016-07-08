/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;

/**
 * @specification :系统异常处理器
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Jun 20, 2008 3:25:03 PM
 * @email : luguohui99@gmail.com
 */
public class CustomExceptionHandler extends ExceptionHandler {

	static Logger logger = Logger.getLogger(CustomExceptionHandler.class);

	/**
	 * @tip :
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception,
	 *      org.apache.struts.config.ExceptionConfig,
	 *      org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @specification :系统异常处理
	 * @param :Exception
	 *            ex
	 * @param :ExceptionConfig
	 *            ae
	 * @param :ActionMapping
	 *            mapping
	 * @param :ActionForm
	 *            formInstance
	 * @param :HttpServletRequest
	 *            request
	 * @param :HttpServletResponse
	 *            response
	 * @return :ActionForward
	 * @exception :ServletException
	 */
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// 异常处理
		super.execute(ex, ae, mapping, formInstance, request, response);
		String errorCode = ex.getMessage();
		String errorComment = "";
		if(errorCode == null){
			errorCode = "";			
		}else{			
			if(ex instanceof TransactionException){
				errorCode = "J000005";				
			}else if(ex instanceof DataAccessException){
				errorCode = "J000005";
			}
			ExceptionHelper eh = new ExceptionHelper();
			errorComment = eh.generateComment(errorCode, request);	
			if(errorComment == null){
				errorComment = "please config errorCode!";
			}
		}
		request.setAttribute("errorCode", errorCode);
		request.setAttribute("errorComment", errorComment);
		
		// 记录日志
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateTime = dateFormat.format(now);
		StringBuffer errorString = new StringBuffer("dateTime:")
				.append(dateTime);
		errorString.append(" | errorCode:").append(errorCode);
		errorString.append(" | errorComment:").append(errorComment);		
		errorString.append(" | reason:\n");		
		for(int i=0;i<ex.getStackTrace().length;i++){
			errorString.append(i+1).append(".").append(ex.getStackTrace()[i]).append("; \n");
		}		
		ex.printStackTrace();
		logger.error(errorString);
		return mapping.findForward("failure");
	}

}
