/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @specification : 产生系统所用的业务编号和唯一编号
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Jun 18, 2008 9:37:30 AM
 * @email : luguohui99@gmail.com
 */
public class BusinessID {

	private JdbcTemplate jdbcTemplate;

	/**
	 * @specification :set JdbcTemplate
	 * @param :JdbcTemplate
	 *            jdbcTemplate
	 * @return :void
	 * @exception :NAN
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @specification :产生系统使用的唯一编号	
	 * @param :String businessType 业务类型
	 * @return :String (1位的业务类型+8位的yyyyMMdd+3位的针对某业务类型某天的唯一编号)
	 * @exception :Exception num is J000005
	 */
	public synchronized String generate(String businessType) throws Exception {
		try{			
			java.util.Date now = new Date();			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
			String date=dateFormat.format(now);
			String companyCode=businessType+date;
			//获得前一天的日期
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(now);
			gregorianCalendar.add(GregorianCalendar.DAY_OF_YEAR,-1);			
			String old_date=dateFormat.format(gregorianCalendar.getTime());
			String old_companyCode=businessType+old_date;
			//获取序列号
			Long sequenceID = null;
			try{
				jdbcTemplate.update("delete from system_business_id_info where company_code = ?",
						new Object[]{old_companyCode});
				sequenceID = jdbcTemplate.queryForLong(
						"select business_id from system_business_id_info where company_code = ?",
						new Object[]{companyCode});
			}catch(Exception e){
				sequenceID = 0l;
			}
			//更新序列号
			if (sequenceID == 0) {
				sequenceID++;
				jdbcTemplate.update("insert into system_business_id_info(company_code,business_id) values(?,?)",
						new Object[]{companyCode,sequenceID});
			} else {
				sequenceID++;
				jdbcTemplate.update("update system_business_id_info set business_id = ? where company_code = ?",
						new Object[]{sequenceID,companyCode});
			}
			//构建业务号			
			StringBuffer businessID = new StringBuffer(businessType);			
			businessID.append(date);
			if (sequenceID.toString().length() >= 3) {
				businessID.append(sequenceID);
			}else{			
				for(int i = 0;i < 3 - sequenceID.toString().length();i++){
					businessID.append("0");
				}
				businessID.append(sequenceID);
			}
			
			return businessID.toString();
		}catch(Exception e){
			throw new Exception("J000005");
		}		
	}
	
	/**
	 * @specification :产生系统使用的唯一编号	
	 * @param :String businessType 业务类型
	 * @return :String (1位的业务类型+11位的针对某业务类型的唯一编号)
	 * @exception :Exception num is J000005
	 */
	public synchronized String generateID(String businessType) throws Exception {
		try{
			String companyCode=businessType+"00000000000";
			//获取序列号
			Long sequenceID = null;
			try{
				sequenceID = jdbcTemplate.queryForLong(
						"select business_id from system_business_id_info where company_code = ?",
						new Object[]{companyCode});
			}catch(Exception e){
				sequenceID = 0l;
			}
			//更新序列号
			if (sequenceID == 0) {
				sequenceID++;
				jdbcTemplate.update("insert into system_business_id_info(company_code,business_id) values(?,?)",
						new Object[]{companyCode,sequenceID});
			} else {
				sequenceID++;
				jdbcTemplate.update("update system_business_id_info set business_id = ? where company_code = ?",
						new Object[]{sequenceID,companyCode});
			}
			//构建业务号			
			StringBuffer businessID = new StringBuffer(businessType);			
			if (sequenceID.toString().length() >= 11) {
				businessID.append(sequenceID);
			}else{			
				for(int i = 0;i < 11 - sequenceID.toString().length();i++){
					businessID.append("0");
				}
				businessID.append(sequenceID);
			}
			
			return businessID.toString();
		}catch(Exception e){
			throw new Exception("J000005");
		}		
	}

}
