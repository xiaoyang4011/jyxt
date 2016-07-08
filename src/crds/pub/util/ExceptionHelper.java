/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;

/**
 * @specification :系统异常捕获器
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Jun 20, 2008 3:25:03 PM
 * @email : luguohui99@gmail.com
 */
public class ExceptionHelper extends Action{
	
	/**
	 * @specification :对系统异常进行捕获
	 * @param :String errorCode
	 * @param :HttpServletRequest request
	 * @return :String	 
	 */
	public String generateComment(String errorCode,HttpServletRequest request){
		MessageResources mr  = this.getResources(request);		
		return mr.getMessage(errorCode);
	}

}
