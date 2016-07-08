package crds.pub.operationLog.dao;
import java.util.List;

import crds.pub.operationLog.web.form.Formoperationlog;
/**
 * @specification :操作记录dao
 * @version : 1.0
 * @auther : yangy
 * @date : 2008-7-1 下午05:01:54
 * @email : qilaug@gmail.com
 */
public interface IDAOoperationlog {
	//得到操作记录的总条数
	public int getCount();
	//得到按日期查询操作记录的总条数
	//public int getCountByDate(String begin_date, String end_date);
	//查询所有的操作记录
	@SuppressWarnings("unchecked")
	public List getOperationLog(int startRow,int rowsCount);
	//增加操作记录
	public int add(Formoperationlog f);
	//按照公司代码和名称查询操作记录
	//public List getOperationLogByCompany(String begin_date,String end_date,int startRow,int rowsCount);
	//新的查询及分页
	public List<Formoperationlog> queryOperationLogInfo(Formoperationlog form);
}
