package crds.pub.operationLog.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import crds.pub.operationLog.dao.IDAOoperationlog;
import crds.pub.operationLog.web.form.Formoperationlog;
import crds.pub.util.JdbcTemplateExtend;
/**
 * @specification :操作记录dao
 * @version : 1.0
 * @auther : yangy
 * @date : 2008-7-1 下午05:01:36
 * @email : qilaug@gmail.com
 */
public class DAOoperationlog implements IDAOoperationlog {
	
	Logger log = Logger.getLogger(DAOoperationlog.class);
	//重写的JdbcTemplateExtend 	
	private JdbcTemplateExtend	jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * @specification :增加操作记录
	 * @param :String 操作id
	 * 		   HttpServletRequest request
	 * @return :int 记录数
	 * @exception:
	 */
	public int add(Formoperationlog f) {
		String company_code=f.getCompany_code();
		String operation_code=f.getOperation_code();
		String operation_memo=f.getOperation_memo();
		String user_id=f.getUser_id();
		String user_name=f.getUser_name();
		String operation_date=f.getOperation_date();
		
		List<String> param = new ArrayList<String>();
		param.add(company_code);
		param.add(operation_code);
		param.add(operation_memo);
		param.add(user_id);
		param.add(user_name);
		param.add(operation_date);
		int rows=jdbcTemplate.update("INSERT INTO system_operation_log_info(company_code,operation_code,operation_memo,user_id,user_name, operation_date)" +
				 " VALUES (?,?,?,?,?,?)",param.toArray());
		return rows;
	}
	/**
	 * @specification :	得到操作记录的总条数
	 * @param :
	 * @return :int 记录数
	 * @exception:
	 */
	public int getCount() {
		int rows=jdbcTemplate.queryForInt("SELECT count(*) FROM  system_operation_log_info");
		return rows;
	}
	/**
	 * @specification :	查询所有的操作记录
	 * @param :int从第几条开始
	 * 			int取多少条
	 * @return :List 查询结果
	 * @exception:
	 */
	@SuppressWarnings("unchecked")
	public List getOperationLog(int startRow,int rowsCount) {
		List listRows=jdbcTemplate.queryForListPage("select operation_id,operation_code,user_name, Convert(Varchar(10),operation_date,120)operation_date,company_code,company_name from system_operation_log_info order by operation_date desc", startRow, rowsCount);
		return listRows;
	}
	/**********************************
	 * @specification :	按照公司代码和公司名称查询操作记录
	 * @param :String 公司代码
	 * 		　　String　公司名称
	 * 			int　　从第几条开始
	 * 			int　　取多少条
	 * @return :List 查询结果
	 * @exception:
	 *
	public List getOperationLogByCompany(String company_code, String company_name, int startRow, int rowsCount) {
		StringBuffer sql=new StringBuffer();
		sql.append("select operation_id,operation_code,user_name, Convert(Varchar(10),operation_date,120)  operation_date,company_code,company_name from system_operation_log_info  where 1=1 ");
		if(company_code!=null)
		{
			sql.append(" and company_code=");
			sql.append(company_code);
		}
		if(company_name!=null)
		{
			sql.append(" and company_name=");
			sql.append(company_name);
		}
		sql.append("   order by operation_date desc");
		List listRows=jdbcTemplate.queryForListPage(sql.toString(), startRow, rowsCount);
		return listRows;
	}
	/**
	 * @specification :	得到按日期查询操作记录的总条数
	 * @param :String　开始时间
	 * 		　　String　结束时间
	 * @return :List 查询结果
	 * @exception:
	 *
	public int getCountByDate(String begin_date, String end_date) {
		int rows=jdbcTemplate.queryForInt("SELECT COUNT(*) FROM  system_operation_log_info where operation_date>=convert(datetime,'"+begin_date+"') and operation_date<=convert(datetime,'"+end_date+"')");
		return rows;
	}
	*/
	/**
	 * 
	 * @specification :分页查询
	 * @param :
	 * @return :
	 * @exception :
	 */
	@SuppressWarnings("unchecked")
	public List<Formoperationlog> queryOperationLogInfo(Formoperationlog form){
//		String sql = "select a.operation_code,a.operation_id,a.operation_date,a.company_code,a.company_name,b.user_name"
//				   + " from system_operation_log_info a left join system_user_info b "
//				   + " on a.user_name=b.user_id where 1=1";
//		
		//String sql="select a.operation_code,a.operation_id,Convert(Varchar(10),a.operation_date,120)operation_date,a.company_code,a.company_name,b.user_name"
		String sql="select a.operation_code,a.operation_id,operation_date,a.company_code,a.company_name,b.user_name"
				   + " from system_operation_log_info a left join system_user_info b "
				   + " on a.user_name=b.user_id where 1=1";
		//String sql = "select * from system_operation_log_info where 1=1";
		String countSql = "select count(*) from system_operation_log_info a where 1=1";
		
		//String tempSql = "";
		String tempSql = "";
		List tempList = new ArrayList();
		if(form != null){
			String begin_date = form.getBegin_date();
			String end_date = form.getEnd_date();
			String operation_code = form.getOperation_code();
			String operation_date = form.getOperation_date();
			String company_code = form.getCompany_code();
			String company_name = form.getCompany_name();//发行人名称
					
			if(begin_date != null && !begin_date.equals("")){
			  tempSql = tempSql + "  and and a.operation_date>=?" ;
			  tempList.add("" + begin_date.trim() + " 00:00:00");
			}
			if(end_date != null && !end_date.equals("")){
			  tempSql = tempSql + "  and a.operation_date <=?" ;
			  tempList.add("%" + end_date.trim() + " 23:59:59%");
			}
			
			if(operation_code != null && !operation_code.equals("")){
			  tempSql = tempSql + "  and operation_code like ?" ;
			  tempList.add("%" + operation_code.trim() + "%");
			}
			
			if(operation_date != null && !operation_date.equals("")){
			  tempSql = tempSql + "  and operation_date like ?" ;
			  tempList.add("%" + operation_date.trim() + "%");
			}
			if(company_code != null && !company_code.equals("")){
			  tempSql = tempSql + "  and company_code like ?" ;
			  tempList.add("%" + company_code.trim() + "%");
			}
			if(company_name != null && !company_name.equals("")){
			  tempSql = tempSql + "  and company_name like ?" ;
			  tempList.add("%" + company_name.trim() + "%");
			}	
		}		
	    String orderSql = " order by a.operation_date desc";
		
		sql = sql +  tempSql + orderSql;
		
		countSql = countSql + tempSql;
		
		form.setRowsCount(jdbcTemplate.queryForInt(countSql,tempList.toArray()));
		List<Formoperationlog> ll = (List<Formoperationlog>) jdbcTemplate.queryForListPage(sql,tempList.toArray(),form.getStartPosition(), form.getRowsPerPage());
		return ll;
	}
}
