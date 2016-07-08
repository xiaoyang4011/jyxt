/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @specification DWR 服务
 * @version 1.0
 * @auther MaCi Hotesion
 * @date Jun 18, 2009 9:37:30 AM
 * @email houtingsong163@163.com
 */
public abstract class AbstractDWR {
	protected final String dateFMT = Constant.dateFormat0; 
	private static Logger logger = Logger.getLogger(AbstractDWR.class);
	/** session是否过期[true-过期,false-未过期] */
	protected boolean sessionIsExpired;
	protected String errorMsg;
	protected FormUserOperation frmUO;
	
	protected void sessionIsExpired(HttpServletRequest request) {
		frmUO = Constant.getFormUserOperation(request);
		sessionIsExpired = frmUO==null;
		if(sessionIsExpired){
			errorMsg = "登录信息Session已过期,不可进行操作!";
			logger.info(errorMsg);
		}
	}

}
