package crds.pub.operationLog.bo;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import crds.pub.operationLog.web.form.Formoperationlog;
/**
 * @specification :操作记录bo
 * @version : 1.0
 * @auther : yangy
 * @date : 2008-7-1 下午05:00:12
 * @email : qilaug@gmail.com
 * @author ljy
 * @version 2.0
 */
public interface IBOoperationlog {
	//得到操作记录的总条数
	public int getCount();
	//查询所有的操作记录
	@SuppressWarnings("unchecked")
	public List getOperationLog(int startRow,int rowsCount);
	//增加操作记录
	public int addOperationLog(String operation_code,String[] source,String[] change,HttpServletRequest request) throws Exception;
	
	//新的查询及分页
	public List<Formoperationlog> queryOperationLogInfo(Formoperationlog form);
	
}
