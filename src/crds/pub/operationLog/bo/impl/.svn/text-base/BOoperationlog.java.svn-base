package crds.pub.operationLog.bo.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import crds.pub.operationLog.bo.IBOoperationlog;
import crds.pub.operationLog.dao.IDAOoperationlog;
import crds.pub.operationLog.web.form.Formoperationlog;
import crds.pub.util.Constant;
import crds.pub.util.DataDict;
import crds.pub.util.FormUserOperation;

/**
 * 
 * @specification :操作记录bo
 * @version : 1.0
 * @author : maogf
 * @date : Oct 19, 2009 3:04:07 PM
 * @email : maogenfeng@gmail.com
 */
public class BOoperationlog implements IBOoperationlog {
	static Logger logger = Logger.getLogger(BOoperationlog.class);

	private IDAOoperationlog dao;
	public void setDAOoperationlog(IDAOoperationlog dao) {
		this.dao = dao;
	}

	/**
	 * @specification :增加操作记录
	 * @param :String
	 *            操作code HttpServletRequest request
	 * @return :int 记录数
	 * @exception:
	 */
	public int addOperationLog(String operation_code, String[] source,
			String[] change, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String memo = getMemoStr(operation_code, source, change);
		if (!"".equals(memo)) {//无操作内容不需要存入数据库
			FormUserOperation formUser = Constant.getFormUserOperation(request);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar MyDate = Calendar.getInstance();
			String operation_date = df.format(MyDate.getTime());
			String company_code = formUser.getCompany_code();// 当前发行人
			String user_id = formUser.getUser_id();// 用户ID
			String user_name = formUser.getUser_name();// 用户名称

			Formoperationlog f = new Formoperationlog();
			f.setCompany_code(company_code);
			f.setOperation_code(operation_code);// 操作代码
			f.setOperation_memo(memo);// 操作内容
			f.setUser_id(user_id);
			f.setUser_name(user_name);
			f.setOperation_date(operation_date);
			return dao.add(f);
		} else {
			return 0;
		}
	}

	/**
	 * @specification : 得到操作记录的总条数
	 * @param :
	 * @return :int 记录数
	 * @exception:
	 */
	public int getCount() {
		// TODO Auto-generated method stub
		return dao.getCount();
	}

	/**
	 * @specification : 查询所有的操作记录
	 * @param :int从第几条开始
	 *            int取多少条
	 * @return :List 查询结果
	 * @exception:
	 */
	@SuppressWarnings("unchecked")
	public List getOperationLog(int startRow, int rowsCount) {
		// TODO Auto-generated method stub
		return dao.getOperationLog(startRow, rowsCount);
	}

	/**
	 * @specification : 按照日期查询操作记录
	 * @param :String开始时间
	 *            String结束时间 int从第几条开始 int取多少条
	 * @return :List 查询结果
	 * @exception:
	 * 
	 * public List getOperationLogByCompany(String begin_date, String
	 * end_date,int startRow, int rowsCount) { // TODO Auto-generated method
	 * stub return dao.getOperationLogByCompany(begin_date, end_date, startRow,
	 * rowsCount); } /**
	 * @specification : 得到按日期查询操作记录的总条数
	 * @param :String开始时间
	 *            String结束时间
	 * @return :List 查询结果
	 * @exception:
	 * 
	 * public int getCountByDate(String begin_date, String end_date) { // TODO
	 * Auto-generated method stub return dao.getCountByDate(begin_date,
	 * end_date); }
	 */
	/**
	 * 
	 * @tip :
	 * @specification :新的查询及分页
	 * @param :
	 * @return :
	 * @exception :
	 */
	public List<Formoperationlog> queryOperationLogInfo(Formoperationlog form) {
		return dao.queryOperationLogInfo(form);
	}

	/***************************************************************************
	 * 获取操作记录内容 操作代码，原数组，变化后数组
	 */
	private String getMemoStr(String operation_code, String source[],
			String change[]) {
		StringBuffer memoStr = new StringBuffer();
		String[] memos = DataDict.OperationMemoMap.get(operation_code);

		if (change == null) {// 没有变化的数组，如删除的时候或对于财务的操作
			if (source.length == 1) {// 删除时可以传一个单独的删除项过来
				memoStr.append(memos[memos.length - 1].replaceAll("#0#",
						source[0]));
			} else {
				for (int i = 0; i < source.length; i++) {
					if (source[i] != null) {
						memoStr.append(memos[i].replaceAll("#0#", source[i]));
					}
				}
			}
		} else {
			if ("MB".equals(operation_code)) {// 基本信息变化时，内容需两个参数
				for (int i = 0; i < source.length; i++) {
					if (!source[i].equals(change[i])) {
						memoStr.append(memos[i].replaceAll("#0#", source[i])
								.replaceAll("#1#", change[i]));
						if (i < source.length - 1) {
							memoStr.append(";");
						}
					}
				}
			} else {// 内容需三个参数
				for (int i = 0; i < source.length; i++) {
					if (!source[i].equals(change[i])) {
						memoStr.append(memos[i].replaceAll("#0#", change[0])
								.replaceAll("#1#", source[i]).replaceAll("#2#",
										change[i]));
						if (i < source.length - 1) {
							memoStr.append(";");
						}
					}
				}
			}
		}

		String ms = memoStr.toString();
		if (ms.endsWith(";")) {// 去除结尾的分号
			ms = ms.substring(0, ms.length() - 1);
		}
		return ms;
	}

}
