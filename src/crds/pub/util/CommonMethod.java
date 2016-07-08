/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


import crds.pub.upload.UploadFileInfo;
import crds.pub.upload.UploadTool;


/**
 * 
 * @specification :公共调用方法
 * @version : 1.0
 * @author : maoxk
 * @date : May 7, 2009 3:36:52 PM
 * @email : maoxk1984@gmail.com
 */
public class CommonMethod {
	private static Logger logger = Logger.getLogger(CommonMethod.class);
	private static JdbcTemplate jdbcTemplate;
	private static InitCrds initCrds;

	/**
	 * @specification :set JdbcTemplate
	 * @param :JdbcTemplate
	 *            jdbcTemplate
	 * @return :void
	 * @exception :NAN
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		CommonMethod.jdbcTemplate = jdbcTemplate;
	}

	public void setInitCrds(InitCrds iu) {
		initCrds = iu;
	}
	/**
	 * 获取系统设置的类型列表
	 * @param typeFlag    1-债券类型
	 * @param levelFilter 级别过滤
	 * @param prevCode    上一级的代码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getTypeList(int typeFlag,int levelFilter,String prevCode){
		StringBuffer querySQL = new StringBuffer();
		if(typeFlag == 1){//债券类型
			if(levelFilter==1){
				querySQL.append("select distinct debt_product_1_code code, debt_product_1_name name from SYSTEM_RISK_DEBT_ITEM_CCF_INFO");
			} else if(levelFilter==2){
				querySQL.append("select distinct debt_product_2_code code, debt_product_2_name name from SYSTEM_RISK_DEBT_ITEM_CCF_INFO").append(prevCode==null || prevCode.length()==0 ? "" : (" where debt_product_1_code='"+prevCode+"'"));
			} else {
				querySQL.append("select * from (");
				querySQL.append(" select distinct debt_product_1_code code, debt_product_1_name name from SYSTEM_RISK_DEBT_ITEM_CCF_INFO");
				querySQL.append(" union");
				querySQL.append(" select distinct debt_product_2_code code, debt_product_2_name name from SYSTEM_RISK_DEBT_ITEM_CCF_INFO)");
				querySQL.append("tab");
			}
		} else {
			throw new IllegalArgumentException("参数[typeFlag="+typeFlag+"]的值错误.");
		}
		return (Map<String,String>)jdbcTemplate.query(querySQL.toString(), new ResultSetExtractor(){
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String,String> map = new LinkedHashMap<String, String>();
				while(rs.next()){
					map.put(rs.getString("CODE"), rs.getString("NAME"));
				}
				return map;
			}
		});
	}

	/**
	 * 根据行业类型获取相关的行业列表,主要是获取表[system_security_indus_info,system_inter_indus_info,system_exter_indus_info,system_model_indus_info]中的数据
	 * @param listType 行业类型 1-证监会行业(system_security_indus_info),2-内部行业(system_inter_indus_info),3-外部行业(system_exter_indus_info),4-模型行业(system_model_indus_info)
	 * @param interIndusLevelFilter 内部行业级别过滤(此参数主要是针对内部行业,即参数listType=2时使用) 1-一级行业,2-二级行业,3-三级行业,4-四级行业
	 * @param interIndusPrevCode 内部行业级别过滤,内部行业的上一级代码(此参数主要是针对内部行业,即参数listType=2,interIndusLevelFilter=[2,3,4]时使用),如果此参数不是8位的数字,将会返回所有的内部[一级或二级或三级或四级]行业
	 * @return
	 */
	public static List<?> getIndustryList(int listType,int interIndusLevelFilter,String interIndusPrevCode){
		StringBuffer querySQL = new StringBuffer();
		if(listType==1) {//证监会行业
			querySQL.append("select security_indus_code code, security_indus_name name from system_security_indus_info");
		} else if(listType==2) {//内部行业
			if(interIndusLevelFilter==1){//1级内部行业
				querySQL.append("select internal_one_code code,internal_one_name name from system_inter_indus_info group by internal_one_code,internal_one_name");
			} else if(interIndusLevelFilter==2){//2级内部行业
				querySQL.append("select internal_two_code code,internal_two_name name from system_inter_indus_info ").append(interIndusPrevCode!=null && interIndusPrevCode.matches("\\d{8}") ? "where internal_one_code='"+interIndusPrevCode+"'":"").append(" group by internal_two_code,internal_two_name");
			} else if(interIndusLevelFilter==3){//3级内部行业
				querySQL.append("select internal_three_code code,internal_three_name name from system_inter_indus_info ").append(interIndusPrevCode!=null && interIndusPrevCode.matches("\\d{8}") ? "where internal_two_code='"+interIndusPrevCode+"'":"").append(" group by internal_three_code,internal_three_name");
			} else if(interIndusLevelFilter==4){//4级内部行业
				querySQL.append("select internal_four_code code,internal_four_name name from system_inter_indus_info ").append(interIndusPrevCode!=null && interIndusPrevCode.matches("\\d{8}") ? "where internal_three_code='"+interIndusPrevCode+"'":"").append(" group by internal_four_code,internal_four_name");
			} else {//全部返回1/2/3/4级内部行业
				querySQL.append("select internal_one_code code,internal_one_name name from system_inter_indus_info group by internal_one_code,internal_one_name ");
				querySQL.append("union");
				querySQL.append("select internal_two_code code,internal_two_name name from system_inter_indus_info group by internal_two_code,internal_two_name ");
				querySQL.append("union");
				querySQL.append("select internal_three_code code,internal_three_name name from system_inter_indus_info group by internal_three_code,internal_three_name ");
				querySQL.append("union");
				querySQL.append("select internal_four_code code,internal_four_name name from system_inter_indus_info group by internal_four_code,internal_four_name ");
				if(interIndusPrevCode!=null&& interIndusPrevCode.trim().length()>0){
					querySQL.insert(0, "select * from (").append(") tab where code='"+interIndusPrevCode+"'");
				}
			}
		} else if(listType==3) {//外部行业
			querySQL.append("select external_code code,external_name name from system_exter_indus_info;");
		} else if(listType==4) {//模型行业
			querySQL.append("select model_indus_code code, model_indus_name name from system_model_indus_info");
		} else {
			throw new IllegalArgumentException("参数[listType="+listType+"]的值错误.");
		}
		return jdbcTemplate.queryForList(querySQL.toString());
	}
	/**
	 * 根据行业代码获取相应的行业名称,主要是获取表[system_security_indus_info,system_inter_indus_info,system_exter_indus_info,system_model_indus_info]中的数据
	 * @param industryCode 行业代码
	 * @param listType 行业类型 1-证券会行业(system_security_indus_info),2-内部行业(system_inter_indus_info),3-外部行业(system_exter_indus_info),4-模型行业(system_model_indus_info)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getIndustryName(String industryCode, int listType){
		if(industryCode==null||"".equals(industryCode))
			return "";
		StringBuffer querySQL = new StringBuffer();
		if(listType==1) {
			querySQL.append("select security_indus_name INDUS_NAME from system_security_indus_info where security_indus_code=?");
		} else if(listType==2) {
			int grade = Constant.internalIndustryGrade(industryCode);
			if(grade==1){//内部行业一级
				querySQL.append("select distinct internal_one_name INDUS_NAME from system_inter_indus_info where internal_one_code=?");
			} else if(grade==2){//内部行业二级
				querySQL.append("select distinct internal_two_name INDUS_NAME from system_inter_indus_info where internal_two_code=?");
			} else if(grade==3){//内部行业三级
				querySQL.append("select distinct internal_three_name INDUS_NAME from system_inter_indus_info where internal_three_code=?");
			} else if(grade==4) {//内部行业四级
				querySQL.append("select distinct internal_four_name INDUS_NAME from system_inter_indus_info where internal_four_code=?");
			} else {
				return "";
			}
		} else if(listType==3) {
			querySQL.append("select external_name INDUS_NAME from system_exter_indus_info where external_code=?");
		} else if(listType==4) {
			querySQL.append("select model_indus_name INDUS_NAME from system_model_indus_info where model_indus_code=?");
		} else {
			throw new IllegalArgumentException("参数[industryType="+listType+"]的值错误.");
		}
		List result = jdbcTemplate.queryForList(querySQL.toString(),new String[]{industryCode});
		if(result != null && !result.isEmpty())
			return (String)((Map)result.get(0)).get("INDUS_NAME");
		return "";
	}
	/**
	 * 查找系统的评级列表:外部主体,外部债项,内部主体(1-穆迪主体,2-标普主体,其它-内部主体),内部债项(一维,二维),小企业
	 * @param tabType 查询表标识:1-外部主体[company_mrating_reference_info],2-外部债项[company_drating_reference_info],3-内部主体[SYSTEM_INOUT_RATING_INFO],4-内部债项[system_bond_rating_info]或[system_bond2_rating_info],5-小企业
	 * @param flagType 针对参数tabType=[3,4]时有效,tabType=3[flagType=1-穆迪主体,2-标普主体,其它-内部主体],内部债项=4[flagType=1-一维,2-二维,其它-自动查找一维或二维]
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> getRatingList(int tabType, int flagType){
		StringBuffer sql = new StringBuffer();
		if(tabType==1) {
			sql.append("select distinct RATING_NUMBER, rating_class RATING_LETTER from (select cast(b.RATING_NUMBER as int) RATING_NUMBER,a.rating_class  from company_mrating_reference_info a, SYSTEM_INOUT_RATING_INFO b where a.rating_class = b.bra_rating) c order by RATING_NUMBER");//sql.append("select distinct rating_class RATING_NUMBER, rating_class RATING_LETTER from company_mrating_reference_info order by cast(rating_class as int)");
		} else if(tabType==2) {
			sql.append("select distinct RATING_NUMBER, rating_class RATING_LETTER from (select cast(b.RATING_NUMBER as int) RATING_NUMBER,a.rating_class  from company_drating_reference_info a, SYSTEM_INOUT_RATING_INFO b where a.rating_class = b.bra_rating) c order by RATING_NUMBER");//sql.append("select distinct rating_class RATING_NUMBER, rating_class RATING_LETTER from company_drating_reference_info order by cast(rating_class as int)");
		} else if(tabType==3) {
			if(flagType==1)
				sql.append("select RATING_NUMBER, moody_rating RATING_LETTER");
			else if(flagType==2)
				sql.append("select RATING_NUMBER, standard_rating RATING_LETTER");
			else
				sql.append("select RATING_NUMBER, bra_rating RATING_LETTER");
			sql.append(" from SYSTEM_INOUT_RATING_INFO order by cast(RATING_NUMBER as int)");
		} else if(tabType==4) {//内部债项(一维,二维)
			if(flagType!=1 && flagType !=2)
				flagType = getDebtRatDimension();
			if(flagType==1){//内部债项(一维)
				sql.append("select distinct RATING_NUMBER, bra_rating RATING_LETTER");//sql.append("select distinct a.bond_rating RATING_NUMBER,b.bra_rating RATING_LETTER");
				sql.append(" from (select cast(a.bond_rating as int) RATING_NUMBER,b.bra_rating  from system_bond_rating_info a, SYSTEM_INOUT_RATING_INFO b where a.bond_rating = b.RATING_NUMBER) c");//sql.append(" from system_bond_rating_info a, SYSTEM_INOUT_RATING_INFO b");
				//sql.append(" where a.bond_rating = b.RATING_NUMBER");
				sql.append(" order by RATING_NUMBER ");
			} else {//内部债项(二维)
				sql.append("select RATING_NUMBER,RATING_LETTER from SYSTEM_BOND2_RATING_INFO order by cast(RATING_NUMBER as int)");
			}
		} else if(tabType==5) {//小企业
			sql.append("select RATING_NUMBER, bond_rating RATING_LETTER from system_small_info order by cast(RATING_NUMBER as int)");
		} else {
			logger.warn("param tabType is error");
		}
		return jdbcTemplate.queryForList(sql.toString());
	}
	/**
	 * 查找系统的评级列表:外部主体,外部债项,内部主体(1-穆迪主体,2-标普主体,其它-内部主体),内部债项(一维,二维),小企业;用来在页面产生一个js数组.详情请参阅{@link CommonMethod#getRatingList(int, int)}
	 * @param tabType
	 * @param flagType
	 * @return
	 */
	public static String getRatingJSArrayString(int tabType, int flagType){
		List<Map<String,String>> rating = CommonMethod.getRatingList(tabType, flagType);
		StringBuffer ratingArray = new StringBuffer("[]");
		if(rating !=null && !rating.isEmpty()){
			for (Map<String, String> map : rating) {
				ratingArray.append(",[").append(map.get("RATING_NUMBER")).append(",\"").append(map.get("RATING_LETTER")).append("\"]");
			}
			ratingArray.delete(0, 3);
			ratingArray.insert(0, "[").append("]");
		}
		return ratingArray.toString();
	}
	@SuppressWarnings("unchecked")
	public static String ratingManualAdjustSelectTag(){
		List<Map<String,String>> list = jdbcTemplate.queryForList("select * from system_inout_rating_info");
		if(list!=null && !list.isEmpty()){
			StringBuffer htmlSelect = new StringBuffer("<select id='ratingManualAdjustSelectTag' style='display:none;'>");
			Set<String> keys = list.get(0).keySet();
			for (Map<String, String> map : list) {
				htmlSelect.append("<option ").append("value='").append(map.get("RATING_NUMBER")).append("'");
				for (String key : keys) {
					htmlSelect.append(" ").append(key.toLowerCase()).append("='").append(map.get(key)).append("'");
				}
				htmlSelect.append("></option>");
			}
			htmlSelect.append("</select>");
			return htmlSelect.toString();
		}
		return "";
	}
	/**
	 * 查询某个公司是否有某种类型的[保存]或[暂存]报表
	 * @param companyCode 公司代码
	 * @param type 报表类型
	 * @param state 报表状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean queryComHasReport(String companyCode,String type, String state){
		StringBuffer sql = new StringBuffer();
		sql.append("select company_code from financial_fin_head_info where 1=1");
		if(!Constant.isEmpty(companyCode))
			sql.append(" and company_code='").append(companyCode).append("'");
		if(!Constant.isEmpty(type))
			sql.append(" and fin_state_type like '%").append(type).append("%'");
		if(!Constant.isEmpty(state))
			sql.append(" and fin_state='").append(state).append("'");
		List<String> list = jdbcTemplate.queryForList(sql.toString(),String.class);
		return null != list && !list.isEmpty();
	}
	
	/**
	 * 查询某个公司财务报表的最小日期和最大日期
	 * @param companyCode  公司代码
	 * @param finStateType 报表类型
	 * @param finStatus    报表状态
	 * @return minDate#maxDate
	 */
	@SuppressWarnings("unchecked")
	public static String queryComFinanReportMinMaxDate(String companyCode,String finStateType,String finStatus){
		//String querySQL = "select convert(varchar(30),min(convert(datetime,fin_date,111)),111) as MINDATE,convert(varchar(30),max(convert(datetime,fin_date,111)),111) as MAXDATE from FINANCIAL_FIN_HEAD_INFO where 1=1";
		String querySQL ="select case when company_code is null then company_code else convert(varchar(30),min(convert(datetime,fin_date,111)),111) end as MINDATE," +
				" case when company_code is null then company_code else convert(varchar(30),max(convert(datetime,fin_date,111)),111) end as MAXDATE " +
				" from FINANCIAL_FIN_HEAD_INFO where 1=1 ";
		if(!Constant.isEmpty(companyCode))
			querySQL += " and company_code='"+companyCode+"'";
		if(!Constant.isEmpty(finStateType))
			querySQL += " and fin_state_type='"+finStateType+"'";
		if(!Constant.isEmpty(finStatus))
			querySQL += " and fin_state='"+finStatus+"'";
		querySQL+=" group by company_code";
		List<Map<String,String>> list = jdbcTemplate.queryForList(querySQL);
		return null != list && !list.isEmpty() ? 
			((list.get(0)).get("MINDATE")+"#"+(list.get(0)).get("MAXDATE")):"#";
	}
	/**
	 * 根据部门ID和角色ID查询是否有相应的用户存在
	 * @param deptId
	 * @param roleId
	 * @return int 0-没有,1-有
	 */
	public static int havePersonByDeptRole(String deptId,String...roleIds){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from system_user_info sui,system_department_info sdi,system_role_info sri where sui.dept_id='"+deptId+"'");
		sql.append(" and sui.role_id=sri.role_id and sui.dept_id=sdi.dept_id");
		if(roleIds!=null && roleIds.length>0)
			sql.append(" and sui.role_id in (").append(Constant.arraysToString("'", "','",roleIds)).append(")");
		int cnt = jdbcTemplate.queryForInt(sql.toString());
		return cnt>0 ? 1 : 0;
	}
	/**
	 * 查找某个用户是否存在(主体或债项)的评级流程任务
	 * @param companyCode 定位的公司的公司代码
	 * @param loginUserId 登录用户的ID
	 * @param loginRoleId 登录用户的角色ID
	 * @return int 0-没有任何流程,1-有信用评级(主体和债项都有评级[此时主体和债项的taskId一样]),2-有主体评级,3-有债项评级
	 */
	public static int userIsHaveMDRatFlow(String companyCode,String loginUserId,String loginRoleId){
		int[] ratInfo = userIsHaveMDRat(companyCode, loginUserId, loginRoleId);
		if(ratInfo[0]>0){
			return 1;
		} else if(ratInfo[1]>0){
			return 2;
		} else if(ratInfo[2]>0){
			return 3;
		} else {
			return 0;
		}
	}
	/**
	 * 查找某个用户是否存在(主体或债项)的评级流程任务
	 * @param companyCode 定位的公司的公司代码
	 * @param loginUserId 登录用户的ID
	 * @param loginRoleId 登录用户的角色ID
	 * @return int[]  [信用评级,主体评级,债券评级]
	 */
	@SuppressWarnings("unchecked")
	public static int[] userIsHaveMDRat(String companyCode,String loginUserId,String loginRoleId){
		int role = 0;
		if(loginRoleId!=null && (Constant.ROLE_R00.equals(loginRoleId) || ((role=Integer.parseInt(loginRoleId.substring(1)))>10 && role<21))){//分析师和审批人角色
			StringBuffer sql = new StringBuffer();
			sql.append("  select");
			sql.append("    (select count(*) from crds_flow_info where company_code='"+companyCode+"' and process_user='"+loginUserId+"' and task_type='TU' and task_status in("+(Constant.ROLE_R00.equals(loginRoleId)?"'RT','SA'":"'CO'")+")) TU,");
			sql.append("    (select count(*) from crds_flow_info where company_code='"+companyCode+"' and process_user='"+loginUserId+"' and task_type='TM' and task_status in("+(Constant.ROLE_R00.equals(loginRoleId)?"'RT','SA','RC'":"'CO'")+")) TM,");
			sql.append("    (select count(*) from crds_flow_info where company_code='"+companyCode+"' and process_user='"+loginUserId+"' and task_type='TD' and task_status in("+(Constant.ROLE_R00.equals(loginRoleId)?"'RT','SA','RC'":"'CO'")+")) TD");
			//sql.append(" from dual");
			Map<String,Integer> map = jdbcTemplate.queryForMap(sql.toString());
			return new int[]{map.get("TU").intValue(),map.get("TM").intValue(),map.get("TD").intValue()};
		}
		return new int[]{0,0,0};
	}
	/**
	 * 获取某个公司的某种评级状态的债项评级列表
	 * @param companyCode
	 * @param ratStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> getCompanyDebtList(String companyCode,String ratStatus){
		return jdbcTemplate.queryForList("select TASK_ID,DEBT_CODE from EVALUATION_D_RATING_INFO where COMPANY_CODE='"+companyCode+"' and RATING_STATUS='"+ratStatus+"'");
	}
	/**
	 * 公司发起新评级约束<br>
	 * 主体有[暂存,审批中,退回,重检]的记录则不能发起新的[主体评级]或[债项评级]<br>
	 * 债项有[暂存,审批中,退回]的记录则不能发起新的[主体评级]或[债项评级]<br>
	 * 主体没有[当前有效RV]的记录则不能发起新的[债项评级]<br>
	 * @param companyCode 公司代码
	 * @return 0-可以评级,1-有暂存的主体评级,2-有审批中的主体评级,3-有退回的主体评级,4-有重检的主体评级,5-有暂存的债项评级,6-有审批中的债项评级,7-有退回的债项评级,8-有重检的债券评级,9-没有有效的主体评级
	 */
	@SuppressWarnings("unchecked")
	public static int companyNewRatCheck(String companyCode){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" (select count(*) from evaluation_m_rating_info where company_code='").append(companyCode).append("' and rating_status='SA') TMSA,");
		sql.append(" (select count(*) from evaluation_m_rating_info where company_code='").append(companyCode).append("' and rating_status='CO') TMCO,");
		sql.append(" (select count(*) from evaluation_m_rating_info where company_code='").append(companyCode).append("' and rating_status='RT') TMRT,");
		sql.append(" (select count(*) from evaluation_m_rating_info where company_code='").append(companyCode).append("' and rating_status='RC') TMRC,");
		sql.append(" (select count(*) from evaluation_d_rating_info where company_code='").append(companyCode).append("' and rating_status='SA') TDSA,");
		sql.append(" (select count(*) from evaluation_d_rating_info where company_code='").append(companyCode).append("' and rating_status='CO') TDCO,");
		sql.append(" (select count(*) from evaluation_d_rating_info where company_code='").append(companyCode).append("' and rating_status='RT') TDRT,");
		sql.append(" (select count(*) from evaluation_d_rating_info where company_code='").append(companyCode).append("' and rating_status='RC') TDRC,");
		sql.append(" (select count(*) from evaluation_m_rating_info where company_code='").append(companyCode).append("' and rating_status='RV') TMRV");
		//sql.append(" from dual");
		Map<String,Integer> map = jdbcTemplate.queryForMap(sql.toString());
		if(map.get("TMSA").intValue()>0){
			return 1;
		} else if(map.get("TMCO").intValue()>0){
			return 2;
		} else if(map.get("TMRT").intValue()>0){
			return 3;
		} else if(map.get("TMRC").intValue()>0){
			return 4;
		} else if(map.get("TDSA").intValue()>0){
			return 5;
		} else if(map.get("TDCO").intValue()>0){
			return 6;
		} else if(map.get("TDRT").intValue()>0){
			return 7;
		} else if(map.get("TDRC").intValue()>0){
			return 8;
		} else if(map.get("TMRV").intValue()==0){
			return 9;
		}
		return 0;
	}
	/**
	 * 查询公司或公司债项是否有某种评级状态的评级信息;</br>
	 * 当参数deptCode的值为null或空字符串时查询公司主体是否有某种评级状态的评级信息,</br>
	 * 当参数deptCode的值为allDebt时则查询公司是否有某种评级状态的债项评级信息,</br>
	 * 当参数deptCode有值但不是allDebt时则查询公司的某个债项是否有某种评级状态的评级信息.</br>
	 * @param companyCode 公司代码
	 * @param debtCode    债项代码
	 * @param ratingStatus 评级状态[RH-评级历史,RV-当前有效,NR-未评级,SA-暂存,CO-审批中,RT-退回,RC-重检]
	 * @return int 0-没有,1-有
	 */
	public static int companyHaveRat(String companyCode,String debtCode,String...ratingStatus){
		StringBuffer sql = new StringBuffer("select count(*) from ").append(Constant.emptyDefault(debtCode, "evaluation_m_rating_info", "evaluation_d_rating_info"));
		sql.append(" where company_code='").append(companyCode).append("'");
		if(!Constant.isEmpty(debtCode) && !"allDebt".equals(debtCode))
			sql.append(" and debt_code='").append(debtCode).append("'");
		if(ratingStatus!=null && ratingStatus.length>0)
			sql.append(" and rating_status in (").append(Constant.arraysToString("'", "','", ratingStatus)).append(")");
		return jdbcTemplate.queryForInt(sql.toString())>0?1:0;
	}

	/**
	 * 根据文件ID获取某个文件的信息
	 * @param file_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getUploadFileInfo(String file_id){
		String sql = "select * from evaluation_external_file_info where file_id='"+file_id+"'";
		List list = jdbcTemplate.queryForList(sql);
		return list !=null && !list.isEmpty() ? (Map)list.get(0):null;
	}
	/**
	 * 根据文件ID获取某个文件的路径
	 * @param file_id
	 * @return
	 */
	public static String getUploadFilePath(String file_id){
		Map<String,String> fileInfo = getUploadFileInfo(file_id);
		if(fileInfo!=null){
			return fileInfo.get("FILE_PATH");
		}
		return "";
	}
	public static int uploadFileInfoInsertToDB(UploadFileInfo ufi,boolean isUpdate) {
		StringBuffer sql = null;
		ufi.setFile_path(ufi.getFile_path().replace(UploadTool.webappsRootPath, ""));
		String company_code = ufi.getCompany_code();//文件所属
		String debt_code = ufi.getDebt_code();//债券代码(内部代码)
		String file_id = ufi.getFile_id();//文件ID
		String file_name = ufi.getFile_name();//文件名称(不包括后缀)
		String file_desc = ufi.getFile_desc();//文件描述
		String upload_date = Constant.trimEmptyDefault(ufi.getUpload_date(),Constant.getCurrDateDefault());//文件上传日期
		String file_path = ufi.getFile_path();//保存到服务器上的文件路径
		String upload_user = ufi.getUpload_user();//上传文件的用户名称
		
		List<String> param = new ArrayList<String>();
		String field = "";
		if(!Constant.trimIsEmpty(company_code)){
			field += ",company_code=?"; param.add(company_code);
		}
		if(!Constant.trimIsEmpty(file_desc)){
			field += ",file_desc=?"; param.add(file_desc);
		}
		if(!Constant.trimIsEmpty(file_name)){
			field += ",file_name=?"; param.add(file_name);
		}
		if(!Constant.trimIsEmpty(upload_date)){
			field += ",upload_date=?"; param.add(upload_date);
		}
		if(!Constant.trimIsEmpty(file_path)){
			field += ",file_path=?"; param.add(file_path);
		}
		if(!Constant.trimIsEmpty(upload_user)){
			field += ",upload_user=?"; param.add(upload_user);
		}
		if(!Constant.trimIsEmpty(debt_code)){
			field += ",debt_code=?"; param.add(debt_code);
		}
		field += ",file_type=?"; param.add(ufi.getUpload_file_type());
		field = field.substring(1);
		//构建SQL语句
		if(!isUpdate){
			field = field.replace("=?", "") + ",file_id "; 
			param.add(file_id);
			String values = (","+field).replaceAll("[^,]", "").replace(",",",?").substring(1);
			sql = new StringBuffer("insert into evaluation_external_file_info("+field+") values("+values+")");
		} else {
			String where = " where file_id=?";
			param.add(file_id);
			sql = new StringBuffer("update evaluation_external_file_info set "+field+where);
		}
		return jdbcTemplate.update(sql.toString(),param.toArray());
	}
	/***************************************************************************
	 * 
	 * @specification :根据组合分析偏差率 取得结论
	 * @param :
	 *            mc 偏差率
	 * @return :String
	 * @exception :
	 */
	@SuppressWarnings("unchecked")
	public static String getSimilarity_conclusion(String segment_0,
			String segment_1, String segment_2) {
		String similarity_conclusion = "不相似";
		if (segment_0 != null && !"".equals(segment_0) && segment_1 != null
				&& !"".equals(segment_1) && segment_2 != null
				&& !"".equals(segment_2)) {
			String sql = "select sbi.bs_name,sbi.similarity_segment_0_down,sbi.similarity_segment_0_up,"
				+ " sbi.similarity_segment_1_down, sbi.similarity_segment_1_up,"
				+ " sbi.similarity_segment_2_down,sbi.similarity_segment_2_up "
				+ " from system_bs_info sbi where sbi.flag = 'S'";
			List list = jdbcTemplate.queryForList(sql);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// FormRatingAnalysis form = new
					// FormRatingAnalysis();//=//(FormRatingAnalysis)
					// ((Map)list.get(0)).get(key);
					// String v_segment_0_down =
					// ((Map)list.get(0)).get("SIMILARITY_SEGMENT_0_DOWN");
					if (((Map) list.get(i)).get("SIMILARITY_SEGMENT_0_DOWN") != null
							&& (Double.parseDouble(segment_0) >= Double
									.parseDouble(((Map) list.get(i)).get(
									"SIMILARITY_SEGMENT_0_DOWN")
									.toString()))) {
						if (((Map) list.get(i)).get("SIMILARITY_SEGMENT_0_UP") != null
								&& (Double.parseDouble(segment_0) < Double
										.parseDouble(((Map) list.get(i)).get(
										"SIMILARITY_SEGMENT_0_UP")
										.toString()))) {
							if (((Map) list.get(i))
									.get("SIMILARITY_SEGMENT_1_DOWN") != null
									&& (Double.parseDouble(segment_1) >= Double
											.parseDouble(((Map) list.get(i))
													.get(
													"SIMILARITY_SEGMENT_1_DOWN")
													.toString()))) {
								if (((Map) list.get(i))
										.get("SIMILARITY_SEGMENT_1_UP") != null
										&& (Double.parseDouble(segment_1) < Double
												.parseDouble(((Map) list.get(i))
														.get(
														"SIMILARITY_SEGMENT_1_UP")
														.toString()))) {
									if (((Map) list.get(i))
											.get("SIMILARITY_SEGMENT_2_DOWN") != null
											&& (Double.parseDouble(segment_2) >= Double
													.parseDouble(((Map) list
															.get(i))
															.get(
															"SIMILARITY_SEGMENT_2_DOWN")
															.toString()))) {
										if (((Map) list.get(i))
												.get("SIMILARITY_SEGMENT_2_UP") != null
												&& (Double
														.parseDouble(segment_2) < Double
														.parseDouble(((Map) list
																.get(i))
																.get(
																"SIMILARITY_SEGMENT_2_UP")
																.toString()))) {
											similarity_conclusion = ((Map) list
													.get(i)).get("bs_name")
													.toString();
											return similarity_conclusion;
										}
									}
								}
							} else {
								continue;
							}
						} else {
							continue;
						}
					} else {
						continue;
					}
				}
			} else {
				return similarity_conclusion = "请设置分析结论信息";
			}
		}
		return similarity_conclusion;
	}

	// 根据key从map里取值
	@SuppressWarnings("unchecked")
	public static String initRatio(Map map, String ratioName) {
		Object val = null;
		return map != null && (val=map.get(ratioName)) != null && isNumeric(val.toString()) ? val.toString() : "0";
	}

	// 格式化数字
	public static String formatNum(String num) {
		if(num.equals("0")){
			return num;
		}
		if (!num.equals("0") && num != null && !"".equals(num) && !"NULL".equalsIgnoreCase(num)) {
			double d = 0;
			try {
				d = Double.parseDouble(!"".equals(num) ? num : "0");
			} catch (Exception e) {
				return "";
			}
			// // 绝对值值大于1 取整数
			// if (Math.abs(Double.parseDouble(num)) > 1) {
			// NumberFormat nf = NumberFormat.getInstance();
			// return nf.format(round(d, 2));
			// } else {
			// // 绝对值小于1保留4位小数
			// return round(d, 2);
			// }
			return round(d, 2);
		}

		return "";
	}

	// 取整
	public static String integralNum(String num) {
		try {
			return round(Double.parseDouble(num),0);
		} catch (Exception e) {
			return "";
		}
	}

	// 格式化数字int n:保留的位数
	public static String formatNum(String num, int n) {
		try {
			return round(Double.parseDouble(num),n);
		} catch (Exception e) {
			return "";
		}
	}

	// 四舍五入 scale：要保留的小数位
	public static String round(double value, int scale) {
		if(value==0.5){//0.5四舍五入有误，故加上0.00000001校正
			value=0.50000001;
		}
		String temp = Math.abs(value) < 1 ? "0":"###,###";
		if (scale > 0) {
			temp += ".";
			for (int i = 0; i < scale; i++) {
				temp += "0";
			}
		}
		return new DecimalFormat(temp).format(value);
	}

	/** 验证是否是数字 */
	public static boolean isNumeric(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 获取在线人数最少的服务器IP地址
	@SuppressWarnings("unchecked")
	public synchronized static String getServer(String path, String server_ip) {
		Comparator<Map> comparator = new Comparator<Map>() {// 自定义排序方法
			public int compare(Map m1, Map m2) {
				return (int) (Integer.parseInt(m1.get("NUM_SERVER").toString()) - Integer
						.parseInt(m2.get("NUM_SERVER").toString()));
			}
		};
		String server = "";
		// String sql = "select s.server_ip,count(u.user_id) num_server from
		// system_server_info s "
		// + "left join system_user_info u on u.out_in_state='0' and
		// s.server_ip=u.server_ip "
		// + "group by u.server_ip, s.server_ip order by num_server";
		String sql = "select tu.server_ip,count(tu.user_id) num_server from system_user_info tu where "
			+ "tu.out_in_state='0' and  tu.server_ip is not null and tu.server_ip!='null' group by "
			+ "tu.server_ip order by num_server";
		List<String> slist = initCrds.getServerList();// 配置的web服务器地址

		if (slist != null && slist.size() > 0) {
			List<Map> list = jdbcTemplate.queryForList(sql);// 在线服务器和人数
			List<Map> resultList = new ArrayList<Map>();// 最终服务器和在线人数列表
			for (String s : slist) {// 初始化配置的web服务器
				Map map = new HashMap();
				map.put("SERVER_IP", s);
				map.put("NUM_SERVER", "0");
				resultList.add(map);
			}
			if (list != null && list.size() > 0) {
				for (Map sm : resultList) {// 重新设置当前在线人数
					for (Map m : list) {
						if (sm.get("SERVER_IP").equals(m.get("SERVER_IP"))) {
							sm.put("NUM_SERVER", (m.get("NUM_SERVER")
									.toString()));
						}
					}
				}
				Collections.sort(resultList, comparator);// 排序
				for (Map<String, String> map : resultList) {
					if (!map.get("SERVER_IP").split(":")[0].equals(server_ip)) {// 是否是当前服务器
						if (isConnect("http://" + map.get("SERVER_IP") + path)) {
							server = map.get("SERVER_IP");
							break;
						}
					}
				}
			}
		}

		return !server.equals("") ? server : server_ip;
	}

	// 更新用户状态(在线或不在线)
	public synchronized static boolean updateUserState(String userId,
			String userState, String server_ip) {
		try {
			String server = (!"".equals(server_ip)&&!"null".equals(server_ip)&&!"localhost".equals(server_ip)) ? (",server_ip='" + server_ip
					+ "'" ): "";
			jdbcTemplate.update("UPDATE system_user_info SET out_in_state = '"
					+ userState + "'" + server + " WHERE user_id = '" + userId
					+ "'");
			return true;
		} catch (Exception e) {
			logger.error("CommonMethod.updateUserState()更新用户状态错误。");
			return false;
		}
	}

	// 检测当前URL是否可连接或是否有效
	private synchronized static boolean isConnect(String url) {
		URL urlStr;
		HttpURLConnection connection;

		int state = -1;
		int counts = 0;
		boolean succ = false;
		if (url == null || url.length() <= 0) {
			return succ;
		}
		while (counts < 1) {// 尝试连接次数
			try {
				urlStr = new URL(url);
				connection = (HttpURLConnection) urlStr.openConnection();
				connection.setConnectTimeout(3000);// 超时设置 3秒
				state = connection.getResponseCode();
				if (state == 200) {
					succ = true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return succ;
	}

	/**
	 * 获取货币类型列表
	 * @return List<?>
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> getCurrencyTypeList() {
		String sql = "select distinct t.one_currency_code CURRCODE,t.one_currency_name CURRNAME from system_risk_exchange_rate_info t";
		sql += " union ";
		sql += " select distinct t.two_currency_code CURRCODE, t.two_currency_name CURRNAME from system_risk_exchange_rate_info t ";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 通过货币代码获取货币类型名称
	 * @return String
	 */
	public static String getCurrencyName(String currencyCode){
		List<Map<String,String>> list = null;
		if(null == currencyCode || currencyCode.length()==0 || null == (list = getCurrencyTypeList()) || list.isEmpty())
			return "";
		for (Map<String, String> map : list) {
			if(currencyCode.equals(map.get("CURRCODE")))
				return map.get("CURRNAME");
		}
		return currencyCode;
	}



	/**
	 * @specification 是否是登录用户添加的公司
	 * @param companyCode 公司代码
	 * @param userID 用户ID
	 * @return boolean true-是,false-不是
	 */
	public static boolean isLoginUserAddCompany(String companyCode, String userID) {
		if (!Constant.trimIsEmpty(companyCode) && !Constant.trimIsEmpty(userID))
			return 1 == jdbcTemplate.queryForInt("select count(*) from company_basic_info where company_code='" + companyCode + "' and user_id='" + userID + "'");
		return false;
	}

	/**
	 * 获取用户[基本角色]列表,或获取用户[任务审批人角色]列表
	 * @param flag 1-基本角色,0-任务审批人角色
	 */
	@SuppressWarnings("unchecked")
	public static String[] getUserBasicRoleList(String flag) {
		String where = "1".equals(flag) ? "" : "";
		String orderBy = "1".equals(flag) ? "" : " desc";
		//修改romnum将其换为top
		String sql = "select top 1 * from system_role_info where role_id "
			+ ("1".equals(flag) ? "" : " not ")
			+ " in("+Constant.arraysToString("'", "','", Constant.ROLE_CODE)+") "
			+ where + " order by role_id " + orderBy;
		List<Map<String, String>> list = jdbcTemplate.queryForList(sql);
		if (list != null && !list.isEmpty()) {
			List<String> roleList = new ArrayList<String>();
			for (Map<String, String> map : list) {
				roleList.add(map.get("ROLE_ID"));
			}
			return roleList.toArray(new String[roleList.size()]);
		}
		return null;
	}

	/**
	 * 根据用户ID去匹配用户的名称,然后获取与此名称一致的其它角色.
	 * @param userId 用户ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> switchUserRole(String userId) {
		StringBuffer sql = new StringBuffer();
		/*sql.append("select u1.user_id,u1.user_name,u1.user_password,u1.role_id,r.role_name");
		sql.append("  from system_user_info u1, system_user_info u2,system_role_info r");
		sql.append(" where u2.user_id = '").append(userId).append("'");
		sql.append("   and u1.user_status = 'Y'");
		sql.append("   and u1.user_name = u2.user_name");
		sql.append("   and u1.dept_id = u2.dept_id");
		sql.append("   and u1.user_password = u2.user_password");
		sql.append("   and u1.role_id = r.role_id");*/
		sql.append("select u1.user_id,u1.user_name,u1.user_password,r.role_id,r1.role_name ");
		sql.append("  from system_user_info u1,system_role_info r1,system_user_role_info r");
		sql.append(" where r.user_id='").append(userId).append("'");
		sql.append(" and r.role_id=r1.role_id and u1.user_id=r.user_id");
		return (List<Map<String, String>>) jdbcTemplate.query(sql.toString(), new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Map<String, String>> data = new ArrayList<Map<String, String>>();
				while (rs.next()) {
					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put("user_id", rs.getString("user_id"));
					map.put("user_name", rs.getString("user_name"));
					try {
						map.put("user_password", rs.getString("user_password"));
					} catch (Exception e) {
						map.put("user_password", rs.getString("user_password"));
					}
					map.put("role_id", rs.getString("role_id"));
					map.put("role_name", rs.getString("role_name"));
					data.add(map);
				}
				return data;
			}
		});
	}
	/**
	 * @specification 查询设置某公司信息
	 * @param FormUserOperation formUserOperation 登陆用户相关信息Form类
	 * @return FormUserOperation 登陆用户相关信息Form类
	 * @exception  NAN 无或未知
	 */
	@SuppressWarnings("unchecked")
	public static FormUserOperation queryCompanyInfo(FormUserOperation frmUO) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select company_code,company_name,company_type,industry_class,user_id as analyse_person_id,company_property as company_prop");
		sql.append(" from company_basic_info where company_code = '").append(frmUO.getCompany_code()).append("'");
		List<FormUserOperation> companyList = (List<FormUserOperation>) jdbcTemplate.queryForList(sql.toString(),FormUserOperation.class);
		if (companyList != null && companyList.size() > 0) {
			FormUserOperation user = (FormUserOperation) companyList.get(0);
			frmUO.setCompany_name(user.getCompany_name());
			frmUO.setCompany_type(user.getCompany_type());
			frmUO.setIndustry_class(user.getIndustry_class());
			frmUO.setAnalyse_person_id(user.getAnalyse_person_id());
			frmUO.setCompany_prop(user.getCompany_prop());
		}
		return frmUO;
	}
	
	/** 系统第一次使用时,数据导入 */
	public static int dataImport(String insertSQL) {
		return jdbcTemplate.update(insertSQL);
	}

	/** 系统使用数据导入时,删除旧数据 */
	public static void deleteData() {
		// 删除权限表
		jdbcTemplate.update("delete from system_operation_info");
		// 删除角色权限关系表
		jdbcTemplate.update("delete from system_role_operation_info");
	}

	/** 根据模板类型获取评级模型类型列表 */
	public static String getRatingTemplateModelTypeList(String templateType) {
		String sql = "select template_model_type from system_rat_templ_info where template_type='"
			+ templateType + "'";
		return (String) jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				StringBuffer sb = new StringBuffer();
				while (rs.next()) {
					sb.append(",").append(rs.getString("TEMPLATE_MODEL_TYPE"));
				}
				return sb.length() > 0 ? sb.substring(1) : sb.toString();
			}
		});
	}

	/**
	 * 获取系统的债项评级维度
	 * @return 1-一维评级(默认),2-二维评级
	 */
	public static int getDebtRatDimension() {
		return Integer.parseInt((String) jdbcTemplate.query("select top 1 DEBT_RATING_DIMENSION from SYSTEM_RISK_COMPU_PARA_INFO", new ResultSetExtractor() {
			public Object extractData(ResultSet rs) {
				try {
					while (rs.next()) {
						return rs.getString("debt_rating_dimension");
					}
				} catch (SQLException e) {
					return "1";
				}
				return "1";
			}
		}));
	}

	/**
	 * 
	 * @specification :获取IP地址
	 * @param :
	 * @return :
	 * @exception :
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 根据客户端IP地址获取相应的服务器IP地址(内网IP或外网IP)，提高访问性能
	public static String getServerIP(HttpServletRequest request) {
		if(System.getProperty("os.name").indexOf("Windows")!=-1){
			String clientIP = getClientIP(request);
			//return clientIP;
			try {
				String ip=InetAddress.getLocalHost().toString();
				String arrip[] = ip.split("/");
				return arrip[arrip.length-1];
			} catch (UnknownHostException e) {
				logger.error("CommonMethod.getServerIP():获取IP错误");
				return "10.1.18.100";
			}
			/*
			Enumeration<NetworkInterface> interfaces = null;
			try {
				interfaces = NetworkInterface.getNetworkInterfaces();
			} catch (SocketException e1) {
				logger.error("CommonMethod.getServerIP():获取IP错误");
				return "127.0.0.1";
			}
			while (interfaces.hasMoreElements()) {
				NetworkInterface ni = interfaces.nextElement();
				Enumeration<InetAddress> e = ni.getInetAddresses();
				while (e.hasMoreElements()) {
					String ip = e.nextElement().toString().replaceAll("/", "");
					String arrip[] = ip.split("\\.");
					System.out.println("ip========"+ip);
					System.out.println("clientIP========"+clientIP);
					if(arrip.length>2){
					if (!clientIP.equals("::1")&&clientIP.indexOf(arrip[0] + "." + arrip[1]) != -1) {
						return ip;
					}
					}else{
						return null; 
					}
				}
			}
			InetAddress inet = null;
			try {
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				logger.error("CommonMethod.getServerIP():获取IP错误");
				return "127.0.0.1";
			}
			return inet.getHostAddress();// 获取当前服务器IP地址
			*/
		}else{
			return getLinuxLocalIP();
		}
	}
	//Linux下获取IP
	static String getLinuxLocalIP() {
		String sIP = "127.0.0.1";
		InetAddress ip = null;
		try{

			boolean bFindIP = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
			.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if(bFindIP){
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				//----------特定情况，可以考虑用ni.getName判断
				//遍历所有ip
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if( ip.isSiteLocalAddress()   
							&& !ip.isLoopbackAddress()   //127.开头的都是lookback地址
							&& ip.getHostAddress().indexOf(":")==-1){
						bFindIP = true;
						break;   
					} 
				}
			}
		} 
		catch (Exception e) {
			logger.error("获取Linux IP错误"+e.getMessage());
		}  

		if(null != ip){
			sIP = ip.getHostAddress();
		}
		return sIP;

	}


	

	

	// 获取在流程中的角色
	@SuppressWarnings("unchecked")
	public static String[] getRoleInFlow(String dept_id, String orderFlag) {
		if (dept_id != null) {
			String sql = "select distinct(role_id) from system_user_info "
				+ " where role_id in (select role_id from system_role_info where is_flow='Y' and role_id!='R05') "
				+ " and dept_id='" + dept_id + "' order by role_id " + orderFlag;
			List<Map<String,String>> list = jdbcTemplate.queryForList(sql);
			if(null != list && !list.isEmpty()){
				List<String> role = new ArrayList<String>();
				for (Map<String, String> map : list) {
					role.add(map.get("ROLE_ID"));
				}
				return role.toArray(new String[role.size()]);
			}
		}
		return new String[]{};
	}

	private static String formatDate(Date date){
		return formatDate(date,"yyyy-MM-dd");
	}
	private static String formatDate(Date date,String format){
		format = format==null || format.trim().length()==0 ? "yyyy-MM-dd":format.trim();
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 功能：得到当前日期 格式为：xxxx-yy-zz (eg: 2007-12-05)
	 * @return String
	 * @author pure
	 */
	public static String today() {
		return formatDate(Calendar.getInstance().getTime());
	}
	
	private static Calendar calNullToCurrCal(Calendar currCal){
		return currCal!=null ? (Calendar)currCal.clone():Calendar.getInstance();
	}

	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的最后一个月(年末)的月初,即某一年的最后一个月的月初
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的最后一个月的月初
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearLastMonthStart(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.set(Calendar.MONTH, 11);//注意月份为:0-11
		cal.set(Calendar.DATE, 1);
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的最后一个月(年末)的月底,即某一年的年末
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的年末
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearLastMonthEnd(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.set(Calendar.MONTH, 11);//注意月份为:0-11
		cal.set(Calendar.DATE, 31);
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的第一个月的月初,即某一年的年初
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的年初
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearFirstMonthStart(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.set(Calendar.MONTH, 0);//注意月份为:0-11
		cal.set(Calendar.DATE, 1);
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的第一个月的月末,即某一年的第一个月的月末
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的第一个月的月末
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearFirstMonthEnd(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.set(Calendar.MONTH, 0);//注意月份为:0-11
		cal.set(Calendar.DATE, 31);
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的最近月份的月初,即某一年的最近月份的月初
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的最近月份的月初
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearRecentMonthStart(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.add(Calendar.DATE, -cal.get(Calendar.DATE));
		cal.set(Calendar.DATE, 1);
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的最近月份的月末,即某一年的最近月份的月末
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的最近月份的月末
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearRecentMonthEnd(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.add(Calendar.DATE, -cal.get(Calendar.DATE));
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的相同月份的月初,即某一年的相同月份的月初
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的当月月初
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearSameMonthStart(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		cal.set(Calendar.DATE, 1);
		return formatDate(cal.getTime());
	}
	/**
	 * 得到当前日期currCal的上devYear年或下devYear年的相同月份的月末,即某一年的相同月份的月末
	 * @param currCal 如果为null则系统默认为当前系统时间
	 * @param devYear 偏差年份,0-则获取当前日期currCal的当月月末
	 * @return 字符串,格式为:yyyy-MM-dd
	 */
	public static String prevNextYearSameMonthEnd(Calendar currCal,int devYear) {
		Calendar cal = calNullToCurrCal(currCal);
		cal.add(Calendar.YEAR, devYear);
		int currMonthMaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, currMonthMaxDay);
		return formatDate(cal.getTime());
	}

	/**
	 * 
	 * @specification :取出传入组合中的最大日期
	 * @param :组合代码
	 *            格式 ：'10001','10002'
	 * @return :
	 * @exception :
	 */
	public static String queryMaxDataDate(String comCode,String date) {
		String sql = "select max(CONVERT(char(10),data_date,120)) data_date from combination_debt_info where combination_code in("
			+ comCode + ") ";
		if(date!=null){
			sql+=" and CONVERT(datetime,data_date,120)<=CONVERT(datetime,'"+date+"',120) ";
		}
		try {
			return (String) jdbcTemplate.queryForObject(sql, String.class);
		} catch (Exception e) {
			return today();
		}
	}

	// 根据债项评级数字获取评级字母
	public static String showRating(String rating_number) {
		String rating = "";
		try {
			int dimension = getDebtRatDimension();
			if(!isNull(rating_number)){
				if(Integer.parseInt(rating_number)<1){
					rating_number="1";
				}
				if(Integer.parseInt(rating_number)>20){
					rating_number="20";
				}
			}
			if (dimension == 1) {
				rating = (String) jdbcTemplate .queryForObject( "select inout.bra_rating from system_inout_rating_info inout where inout.rating_number='" + rating_number + "'", String.class);
			} else {
				rating = (String) jdbcTemplate .queryForObject( "select br.rating_letter from system_bond2_rating_info br where br.rating_number='" + rating_number + "'", String.class);
			}
		} catch (Exception e) {
			return "-";
		}
		return rating;
	}

	// 通过评级机构和数字获取评级字母
	public static String queryRatingValue(String rating_company,
			String ratingNumber) {
		String sql = "select " + rating_company
		+ " from system_inout_rating_info t where t.rating_number='"
		+ ratingNumber + "' ";
		try {
			return (String) jdbcTemplate.queryForObject(sql, String.class);
		} catch (Exception e) {
			return "";
		}
	}
	// 根据主体评级数字，评级类型 获取评级字母
	public static String queryRatValueByRatNumber(String ratNumber, String ratingType) {
		String ratValue = "";
		String table_name = "system_inout_rating_info";
		if (ratingType.equals("A")) {
			table_name = "system_small_info";
		}
		String sql = "select bra_rating from " + table_name
		+ " where rating_number='" + ratNumber + "'";
		try {
			ratValue = (String) jdbcTemplate.queryForObject(sql, String.class);
		} catch (Exception e) {
			ratValue = "";
		}
		return ratValue;
	}
	/*
	 * 取不同系统类型的显示字段名称
	 */
	public static String getFieldNameBySysType(HttpServletRequest request, String fieldFlag) {
		FormUserOperation user = Constant.getFormUserOperation(request);
		return getFieldNameBySysType(user.getSystype(), fieldFlag);
	}

	/*
	 * 取不同系统类型的显示字段名称
	 */
	public static String getFieldNameBySysType(String sysType, String fieldFlag) {
		String return_str = DataDict.FSystemMap.get(fieldFlag.toLowerCase());
		if (return_str == null) {
			return_str = fieldFlag.toUpperCase();
		}
		return return_str;
	}
	/**
	 * 查询所选组合中最大或最小日期
	 * @param comCode
	 * @param type 0：查询最大日期 1：查询最小日期
	 * @param maxBefore 最大时间的前三个月的时间,格式:yyyy-mm-dd
	 * @return
	 */
	public static String queryComDate(String sysType,String roleId,String comCode,int type,String maxBefore){
		String strType="max";
		if(type==0){
			strType="min";
		}
		StringBuffer comc=new StringBuffer();
		if(comCode.indexOf("#")!=-1){
			comc.append("'").append(Constant.arraysToString("','", comCode.split("#"))).append("'");
		}else{
			comc.append(comCode);
		}
		StringBuffer sql=new StringBuffer();
		if("F".equals(sysType)){
			if(Constant.ROLE_R00.equals(roleId)){
				sql.append(" select "+strType+"(CONVERT(char(10),a.data_date,120)) data_date ");
				sql.append(" from company_debt_dyna_info a,company_basic_info b where a.company_code=b.company_code and b.user_id in("+comc.toString()+") ");
			}else{
				sql.append(" select "+strType+"(CONVERT(char(10),a.data_date,120)) data_date ");
				sql.append(" from company_debt_dyna_info a,combination_debt_info b where a.debt_code=b.debt_code and a.company_code=b.company_code and b.combination_code in("+comc.toString()+") ");
			}
			if(null != maxBefore && maxBefore.length()==10)
				sql.append(" and a.data_date like '"+maxBefore.substring(0,7)+"%'");
		}else{
			sql.append(" select "+strType+"(CONVERT(char(10),rating_date,120)) rating_date ");
			sql.append(" from evaluation_d_rating_info ed,company_basic_info cb where ed.company_code=cb.company_code and cb.user_id in("+comc.toString()+") ");
			if(null != maxBefore && maxBefore.length()==10)
				sql.append(" and ed.rating_date like '"+maxBefore.substring(0,7)+"%'");
		}

		try{
			return (String)jdbcTemplate.queryForObject(sql.toString(), String.class);
		}catch (Exception e) {
			return "";
		}
	}
	/**
	 * 查询债项动态表中最大或最小日期
	 * @param resultType 0：查询最大日期 1：查询最小日期
	 * @param queryType 0：查询债项动态表 1：查询债项信息表
	 * @return
	 */
	public static String queryDebtDate(int resultType,int queryType){
		String strType="max";
		if(resultType==0){
			strType="min";
		}
		StringBuffer sql=new StringBuffer();
		if(queryType==0){
			sql.append(" select "+strType+"(CONVERT(char(10),data_date,120)) data_date ");
			sql.append(" from company_debt_dyna_info ");
		}else{
			sql.append(" select "+strType+"(CONVERT(char(10),begin_time,120)) data_date ");
			sql.append(" from company_debt_info ");
		}
		try{
			return (String)jdbcTemplate.queryForObject(sql.toString(), String.class);
		}catch (Exception e) {
			return "";
		}
	}
	//判断字符串是否为空
	public static boolean isNull(String str){
		return str==null || "".equals(str.trim()) || "NULL".equalsIgnoreCase(str);
	}
	//同一债项处理后的债项列表(传入角色,用户ID或部门ID,is_pool 是否入池)
	public static String getSameDebtQueryString(String roleId,String userId,boolean isPool){
		StringBuffer same_debt_info=new StringBuffer();
		same_debt_info.append(" select * from company_debt_info a where debt_code =( ");
		same_debt_info.append(" select max(debt_code) from company_debt_info b where a.company_code=b.company_code and a.debt_type=b.debt_type ");
		same_debt_info.append(" and a.debt_name=b.debt_name and a.begin_time=b.begin_time and a.debt_term=b.debt_term and (a.debt_type like 'CB%' or a.debt_type like 'DT%') ");
		same_debt_info.append(" and a.credit_amount=b.credit_amount and a.debt_rate=b.debt_rate "+(isPool?"and b.is_pool='Y'":"") );
		if(roleId!=null && userId!=null){
			same_debt_info.append(" and a.company_code in ( select cb.company_code from company_basic_info cb ");
			if(Constant.ROLE_R06.equals(roleId)){
				same_debt_info.append(" ,system_user_info su where cb.user_id=su.user_id and su.dept_id in("+userId+") ");
			}else{
				same_debt_info.append(" where cb.user_id in("+userId+") ");
			}
			same_debt_info.append(" ) ");
		}
		same_debt_info.append(" ) ");
		return same_debt_info.toString();
	}

	//同一债项处理后的债项列表(传入公司代码,is_pool 是否入池)
	public static String getSameDebtQueryString(String company_codes,boolean is_pool){
		StringBuffer same_debt_info=new StringBuffer();
		same_debt_info.append(" select * from company_debt_info a where debt_code =( ");
		same_debt_info.append(" select max(debt_code) from company_debt_info b where a.company_code=b.company_code and a.debt_type=b.debt_type ");
		same_debt_info.append(" and a.debt_name=b.debt_name and a.begin_time=b.begin_time and a.debt_term=b.debt_term and (a.debt_type like 'CB%' or a.debt_type like 'DT%') ");
		same_debt_info.append(" and a.credit_amount=b.credit_amount and a.debt_rate=b.debt_rate "+(is_pool?"and b.is_pool='Y'":"") +" and a.company_code in ("+company_codes+")) ");
		return same_debt_info.toString();
	}
	
	/**
	 * 获取同级或同部门下的基金经理或分析师的用户ID,当用户查看组合时,在组合选择中选择债券池时,所有查看的用户信息的数据
	 * @param loginUserId
	 * @param loginUserRoleId
	 * @param loginUserDeptId
	 * @param isSelDebtPool
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] getUsserIdsBySelDebtPool(String loginUserId,String loginUserRoleId,String loginUserDeptId,String isSelDebtPool){
		List<String> userIdList = new ArrayList<String>();
		if("1".equals(isSelDebtPool)){
			if(Constant.isEqsAny(loginUserRoleId, Constant.ROLE_R02, Constant.ROLE_R03, Constant.ROLE_R06, Constant.ROLE_R08)){
				String tempDeptSQL = Constant.ROLE_R06.equals(loginUserRoleId) ? ("length(dept_id)=length('"+loginUserDeptId+"')"):("dept_id='"+loginUserDeptId+"'");
				String sql = "select user_id from system_user_info where role_id='"+(Constant.bondPoolViewItem?Constant.ROLE_R01:Constant.ROLE_R00)+"' and " + tempDeptSQL;
				List<Map<String,String>> list = jdbcTemplate.queryForList(sql);
				if(list != null && !list.isEmpty()){
					for (Map<String, String> map : list) {
						userIdList.add(map.get("USER_ID"));
					}
				}
			} else {//分析师选择债券池
				userIdList.add(loginUserId);
			}
		}
		return userIdList.toArray(new String[userIdList.size()]);
	}
	
	/**
	 * 用户查看组合分析时,所选择的分析师或债券池所对应的债券SQL基本语句
	 * @param frmUO               登录用户信息
	 * @param isSelDebtPool       是否选择债券池,1-选择债券池,其它-非债券池
	 * @param analyUser_Combi_Ids 选择的分析师用户或组合代码的ID列表,格式:"ID1#ID2#ID3"或"ID1,ID2,ID3"
	 * @return
	 * @throws Exception
	 */
	public static String getCombiAnalyDebtBasicTableSQL(FormUserOperation frmUO,String isSelDebtPool,String analyUser_Combi_Ids) throws Exception{
		return getCombiAnalyDebtBasicTableSQL(frmUO.getUser_id(),frmUO.getRole_id(),frmUO.getDept_id(),isSelDebtPool,analyUser_Combi_Ids);
	}
	/**
	 * 用户查看组合分析时,所选择的分析师或债券池所对应的债券SQL基本语句
	 * @param loginUserId         登录用户的ID
	 * @param loginUserRoleId     登录用户的角色ID
	 * @param loginUserDeptId     登录用户所在部门的ID
	 * @param isSelDebtPool       是否选择债券池,1-选择债券池,其它-非债券池
	 * @param analyUser_Combi_Ids 选择的分析师用户或组合代码的ID列表,格式:"ID1#ID2#ID3"或"ID1,ID2,ID3"
	 * @return
	 * @throws Exception 
	 */
	public static String getCombiAnalyDebtBasicTableSQL(String loginUserId,String loginUserRoleId,String loginUserDeptId,String isSelDebtPool,String analyUser_Combi_Ids) throws Exception{
		if(Constant.isEmptys(loginUserId,loginUserRoleId,loginUserDeptId,isSelDebtPool,analyUser_Combi_Ids))
			throw new Exception("登录用户设置的信息不全[请将登录用户的ID和部门ID以及角色ID设置到对象"+FormUserOperation.class+"中]或者传入的参数isSelDebtPool以及analyUser_Combi_Ids的值为空.");
		StringBuffer debtBasicSQL = new StringBuffer("select * from company_debt_info ");
		String tmpSQL = "", tempDeptSQL = "";
		if("1".equals(isSelDebtPool)){//如果选择了债券池,R01-投资经理/基金经理不能选择债券池
			debtBasicSQL.append(" where is_pool='Y' ");
			//R02-部门经理,R03-风险经理,R06-信息监察员,R08-交易员
			if(Constant.isEqsAny(loginUserRoleId, Constant.ROLE_R02, Constant.ROLE_R03, Constant.ROLE_R06, Constant.ROLE_R08)){
				//[R06-信息检察员]查看同级部门,[部门经理,R03-风险经理,R08-交易员]查看同部门
				tempDeptSQL = Constant.ROLE_R06.equals(loginUserRoleId) ? ("length(dept_id)=length('"+loginUserDeptId+"')"):("dept_id='"+loginUserDeptId+"'");
				if(Constant.bondPoolViewItem){//查看同级或同部门下的所有基金经理所管组合中的所有入池债项
					tmpSQL += "select debt_code from combination_debt_info where combination_code in(";
					tmpSQL += "  select distinct combination_code from combination_manage_info where user_id in(";
					tmpSQL += "    select user_id from system_user_info where role_id='"+Constant.ROLE_R01+"' and "+tempDeptSQL;
					tmpSQL += "  ) or user_id_2 in(";
					tmpSQL += "    select user_id from system_user_info where role_id='"+Constant.ROLE_R01+"' and "+tempDeptSQL;
					tmpSQL += "  ))";
					debtBasicSQL.append(" and union_code in(").append(tmpSQL).append(")");
				} else {//查看同级或同部门下的所有分析师所管公司的所有入池债项
					tmpSQL += "select company_code from company_basic_info where user_id in(";
					tmpSQL += "    select user_id from system_user_info where role_id='"+Constant.ROLE_R00+"' and "+tempDeptSQL;
					tmpSQL += "  )";
					debtBasicSQL.append(" and company_code in(").append(tmpSQL).append(")");
				}
			} else if(Constant.ROLE_R00.equals(loginUserRoleId)){//R00-分析师/客户经理
				tmpSQL = "select company_code from company_basic_info where user_id='"+loginUserId+"'";
				debtBasicSQL.append(" and company_code in(").append(tmpSQL).append(")");
			}
		} else {//否则选择的是分析师ID或组合ID
			debtBasicSQL.append(" where ");
			if(Constant.isEqsAny(loginUserRoleId, Constant.ROLE_R02, Constant.ROLE_R03, Constant.ROLE_R06, Constant.ROLE_R08)){
				tmpSQL = "select debt_code from ("+getNewDataSQL(5)+") where combination_code in("+Constant.arraysToString("'", "','", analyUser_Combi_Ids.split("#|,"))+")";
				debtBasicSQL.append(" union_code in(").append(tmpSQL).append(")");
			} else if(Constant.ROLE_R00.equals(loginUserRoleId)){//R00-分析师/客户经理
				tmpSQL = "select company_code from company_basic_info where user_id in("+Constant.arraysToString("'", "','", analyUser_Combi_Ids.split("#|,"))+")";
				debtBasicSQL.append(" company_code in(").append(tmpSQL).append(")");
			}
		}
		return debtBasicSQL.toString();
	}
	
	/**
	 * 获取表中最新数据的SQL语句
	 * @param tabViewFlag 1[COMPANY_MRATING_REFERENCE_INFO],2[COMPANY_DRATING_REFERENCE_INFO],3[COMPANY_DEBT_DYNA_INFO],4[combination_value_info],5[combination_debt_info]
	 * @return
	 */
	public static String getNewDataSQL(int tabViewFlag){
		StringBuffer tabSQL = new StringBuffer();
		if(tabViewFlag==1){
			tabSQL.append(" select company_code,rating_company,rating_date,rating_class,data_source from (");
			tabSQL.append("   select t.*, max(CONVERT(char(10),RATING_DATE,120))over(partition by company_code,RATING_COMPANY) max_data_date");
			tabSQL.append("   from COMPANY_MRATING_REFERENCE_INFO t");
			tabSQL.append(" ) where to_char(rating_date,'yyyy-MM-dd')=max_data_date");
		} else if(tabViewFlag==2) {
			tabSQL.append(" select company_code,debt_code,rating_date,rating_company_name,rating_class,data_source from (");
			tabSQL.append("   select t.*, max(CONVERT(char(10),RATING_DATE,120))over(partition by company_code,debt_code,RATING_COMPANY_NAME) max_data_date");
			tabSQL.append("   from COMPANY_DRATING_REFERENCE_INFO t");
			tabSQL.append(" ) where to_char(rating_date,'yyyy-MM-dd')=max_data_date");
		} else if(tabViewFlag==3) {
			tabSQL.append(" select company_code,debt_code,data_date,yield_to_maturity,return_interest,spread_type,a_market_spread,b_market_spread from (");
			tabSQL.append("   select t.*, max(CONVERT(char(10),data_date,120))over(partition by company_code,debt_code) max_data_date");
			tabSQL.append("   from COMPANY_DEBT_DYNA_INFO t");
			tabSQL.append(" ) where to_char(data_date,'yyyy-MM-dd')=max_data_date");
		} else if(tabViewFlag==4) {
			tabSQL.append(" select combination_code,combination_name,data_date,combination_net_cost,combination_net_mkt,bond_invest_cost,bond_invest_mkt from (");
			tabSQL.append("   select t.*, max(CONVERT(char(10),data_date,120))over(partition by combination_code) max_data_date");
			tabSQL.append("   from combination_value_info t");
			tabSQL.append(" ) where to_char(data_date,'yyyy-MM-dd')=max_data_date");
		} else if(tabViewFlag==5) {
			tabSQL.append(" select company_code,combination_code,debt_code,data_date,debt_name,st_num,st_cost,mkt_val from (");
			tabSQL.append("   select t.*, max(CONVERT(char(10),data_date,120))over(partition by company_code,combination_code,debt_code) max_data_date");
			tabSQL.append("   from combination_debt_info t");
			tabSQL.append(" ) where to_char(data_date,'yyyy-MM-dd')=max_data_date");
		} else {
			logger.warn("参数值传递错误,");
		}
		return tabSQL.toString();
	}
	
	/**
	 * 获取公司主体或债项的外部评级的最新评级的SQL语句.
	 * @param tabFlag     1-主体,2-债项
	 * @param companyCode 公司代码
	 * @param debtCodes   债项代码列表
	 * @return
	 */
	public static String getMDRatReferenceMaxSQL(int tabFlag,String companyCode,String...debtCodes){
		StringBuffer tabSQL = new StringBuffer();
		String sql = " where 1=1 ";
		if(!Constant.trimIsEmpty(companyCode))
			sql += " and company_code='"+companyCode.trim()+"'";
		if(tabFlag==1){
			tabSQL.append("  select cmri.*");
			tabSQL.append("    from company_mrating_reference_info cmri,");
			tabSQL.append("         (select distinct company_code,");
			tabSQL.append("                 max(CONVERT(char(10),rating_date, 120)) over(partition by company_code) rating_date");
			tabSQL.append("            from company_mrating_reference_info ").append(sql).append(") cmri1");
			tabSQL.append("  where cmri.company_code=cmri1.company_code and CONVERT(char(10),cmri.rating_date,120)=cmri1.rating_date");
		} else if(tabFlag==2){
			tabSQL.append("  select cdri.*");
			tabSQL.append("    from company_drating_reference_info cdri,");
			tabSQL.append("         (select distinct company_code,debt_code,");
			tabSQL.append("                 max(CONVERT(char(10),rating_date, 120)) over(partition by company_code,debt_code) rating_date");
			tabSQL.append("            from company_drating_reference_info ");
			if(debtCodes!=null && debtCodes.length>0){
				boolean lenFlag = debtCodes.length>1;
				sql += " and debt_code"+(lenFlag?" in (":"=")+Constant.arraysToString("'", "','", debtCodes)+(lenFlag?")":"");
			}
			tabSQL.append(sql).append(") cdri1");
			tabSQL.append("  where cdri.company_code=cdri1.company_code and cdri.debt_code=cdri1.debt_code and CONVERT(char(10),cdri.rating_date,120)=CONVERT(char(10),cdri1.rating_date,120)");
		}
		return tabSQL.toString();
	}
	
	/**
	 * 获取公司债项无重复数据的SQL语句,主要是针对表company_debt_info的字段union_code.
	 * @param companyCode 公司代码
	 * @param debtCodes   公司债项列表
	 * @return
	 */
	public static String getCompanyDebtNoRepeatSQL(String companyCode,String...debtCodes){
		StringBuffer debtSQL = new StringBuffer();
		String sql = " where 1=1 ";
		if(!Constant.trimIsEmpty(companyCode)){
			sql += " and a.company_code='"+companyCode.trim()+"'";
		}
		if(debtCodes!=null && debtCodes.length>0){
			boolean lenFlag = debtCodes.length>1;
			sql += " and a.union_code"+(lenFlag?" in (":"=") + Constant.arraysToString("'", "','", debtCodes)+(lenFlag?")":"");
		}
		debtSQL.append("select a.* from company_debt_info a");
		debtSQL.append(" join (select distinct company_code,union_code,max(tran_market) over(partition by company_code,union_code) tran_market from company_debt_info) b");
		debtSQL.append(" on a.company_code=b.company_code and a.union_code=b.union_code and a.tran_market=b.tran_market");
		debtSQL.append(sql);
		return debtSQL.toString();
	}
	
	//读文件
	public static String readFileToString(String filePath,String encoding){
		File file=null;
		InputStream input = null;
		try {
			file=new File(filePath);
			if(file.exists()){
				input = new FileInputStream(file);
				return IOUtils.toString(input, encoding);
			}
		} catch (Exception e) {
			logger.error("读取文件异常。"+e.getMessage());
		} finally {
			IOUtils.closeQuietly(input);
		}
		return "";
	}
	// 写文件
	public static void writeFile(String filePath, String data, String encoding) {
		File file = null;
		OutputStream output = null;
		try {
			if (data != null) {
				file = new File(filePath);
				output = new FileOutputStream(file);

				if (encoding == null) {
					IOUtils.write(data, output);
				} else {
					output.write(data.getBytes(encoding));
				}
			}
		} catch (Exception e) {
			logger.error("写入文件异常。" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	//查询债项评级信息
	@SuppressWarnings("unchecked")
	public static Map<String,Map<String,String>> queryDebtRatingInfo(String company_code){
		Map result=new HashMap<String,Map<String,String>>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from evaluation_d_rating_info where task_id =(");
		sql.append("select task_id from (select task_id from evaluation_d_rating_info where company_code='");
		sql.append(company_code);
		sql.append("' order by CONVERT(char(10),rating_date,120) desc,task_id desc ) where rownum<2 )");
		List<Map> list=jdbcTemplate.queryForList(sql.toString());
		for(Map map:list){
			Map map_info=new HashMap<String,String>();
			map_info.put("LAST_RATING", map.get("LAST_RATING"));
			map_info.put("MANUAL_ADJUST", map.get("MANUAL_ADJUST"));
			map_info.put("FINALLY_RATING", map.get("FINALLY_RATING"));
			result.put(map.get("DEBT_CODE"), map_info);
		}
		return result;
	}

	/**
	 * 查询外部评级 如果debt_code为null，则查询公司的主体外部评级，反之，则查债项的外部评级
	 * 返回值为最新的一条数据
	 */
	public static String queryRefRating(String company_code,String debt_code){
		StringBuffer sql=new StringBuffer();
		sql.append("select rating_class from (select * from ");
		sql.append(debt_code!=null?"company_drating_reference_info":"company_mrating_reference_info");
		sql.append(" cr where company_code='");
		sql.append(company_code);
		sql.append("'");
		sql.append(debt_code!=null?" and debt_code='"+debt_code+"'":"");
		sql.append(" order by CONVERT(char(10),rating_date,120) desc) where rownum<2");
		try{
			return (String)jdbcTemplate.queryForObject(sql.toString(), String.class);
		}catch (Exception e) {
			return "";
		}
	}
	/**
	 * 查找公司分析师和此分析师所在同一部门的其它抄送人
	 * @param companyAnalyse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> getReceiveEmailUser(String companyAnalyse){
		StringBuffer sql = new StringBuffer();
		sql.append("select user_id, user_name, user_password, email_address, email_R_C, d.dept_id, d.dept_name");
		sql.append("  from system_user_info u, system_department_info d, system_role_info r");
		sql.append(" where is_receive_mail = 'Y' and user_status = 'Y' and u.dept_id=(select dept_id from system_user_info where user_id='"+companyAnalyse+"')");
		sql.append("   and (Email_R_C = 'C' or user_id = '"+companyAnalyse+"') and u.role_id = r.role_id and r.is_flow = 'Y' and u.dept_id = d.dept_id");
		return jdbcTemplate.queryForList(sql.toString());
	}
	public static void main(String[] args) {
	}
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> getRatingListto_oracle(int tabType, int flagType){
		StringBuffer sql = new StringBuffer();
		if(tabType==1) {
			sql.append("select distinct rating_class RATING_NUMBER, rating_class RATING_LETTER from company_mrating_reference_info order by rating_class");
		} else if(tabType==2) {
			sql.append("select distinct rating_class RATING_NUMBER, rating_class RATING_LETTER from company_drating_reference_info order by rating_class");
		} else if(tabType==3) {
			if(flagType==1)
				sql.append("select RATING_NUMBER, moody_rating RATING_LETTER");
			else if(flagType==2)
				sql.append("select RATING_NUMBER, standard_rating RATING_LETTER");
			else
				sql.append("select RATING_NUMBER, bra_rating RATING_LETTER");
			sql.append(" from SYSTEM_INOUT_RATING_INFO order by RATING_NUMBER");
		} else if(tabType==4) {//内部债项(一维,二维)
			if(flagType!=1 && flagType !=2)
				flagType = getDebtRatDimension();
			if(flagType==1){//内部债项(一维)
				sql.append("select distinct a.bond_rating RATING_NUMBER,b.bra_rating RATING_LETTER");
				sql.append(" from system_bond_rating_info a, SYSTEM_INOUT_RATING_INFO b");
				sql.append(" where a.bond_rating = b.RATING_NUMBER");
				sql.append(" order by CONVERT(numeric(18,4), (a.bond_rating))");
			} else {//内部债项(二维)
				sql.append("select RATING_NUMBER,RATING_LETTER from SYSTEM_BOND2_RATING_INFO order by CONVERT(numeric(18,4), (RATING_NUMBER))");
			}
		} else if(tabType==5) {//小企业
			sql.append("select RATING_NUMBER, bond_rating RATING_LETTER from system_small_info order by CONVERT(numeric(18,4), (RATING_NUMBER))");
		} else {
			logger.warn("param tabType is error");
		}
		return jdbcTemplate.queryForList(sql.toString());
	}
}
