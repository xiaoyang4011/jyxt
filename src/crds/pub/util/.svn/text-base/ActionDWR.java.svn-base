/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import crds.pub.upload.UploadTool;

/**
 * @specification DWR 服务
 * @version 1.0
 * @auther MaCi Hotesion
 * @date Jun 18, 2009 9:37:30 AM
 * @email houtingsong163@163.com
 */
public class ActionDWR extends AbstractDWR {

	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(ActionDWR.class);

	/**
	 * @param jdbcTemplate
	 * @return void
	 * @exception
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * @specification 获得属性(字段)联动信息
	 * @param relationSQL 关联字段SQL	
	 * @param relationFieldNum 关联字段个数	
	 * @return String[] 关联字段值集
	 * @exception :Exception J000005
	 */
	public String[] getRelationInfo(String relationSQL,int relationFieldNum) throws Exception {
		//参数校验
		if (relationSQL == null || relationSQL == ""
			|| relationFieldNum == 0) {
			throw new Exception("J000005");
		}
		//获取结果
		try {
			SqlRowSet relationRS = jdbcTemplate.queryForRowSet(relationSQL, null);
			if(relationRS.next()){
				String[] relationFieldValues = new String[relationFieldNum];				
				for (int i = 0; i < relationFieldNum; i++) {
					relationFieldValues[i] = relationRS.getString(i+1);
				}				
				return relationFieldValues;
			}else{
				return null;
			}			
		} catch (Exception e) {			
			throw new Exception("J000005");
		}		
	}
	
	/**
	 * @specification 取得用户名称
	 * @param user_id 用户ID
	 * @return String 用户名称
	 * @exception :Exception
	 */
	public String getUserName(String user_id) throws Exception {
		// 参数校验
		if (user_id == null || ("").equals(user_id)) {
			return "";
		}
		// 获取结果
		String sql = "select user_name from system_user_info where user_id = ?";
		SqlRowSet userNameRS = jdbcTemplate.queryForRowSet(sql,new Object[] { user_id });
		String userName = user_id;
		if(userNameRS.next()){
			userName = userNameRS.getString("user_name");
		}
		return userName;
	}
	
	/**
	 * @specification 取得行业类别名称
	 * @param code 行业类别代码
	 * @return String 行业类别名称
	 * @exception :Exception
	 */
	public String getIndustryClassName(String code) throws Exception {
		// 参数校验
		if (code == null || ("").equals(code)) {
			return "";
		}
		//行业类别代码和名称字符
		String[][] industry_class = new String[][]{
			new String[]{"internal_one_code","internal_one_name","([1-9][0-9]|[0-9][1-9])000000"},//一级行业
			new String[]{"internal_two_code","internal_two_name","[\\d]{2}([1-9][0-9]|[0-9][1-9])0000"},//二级行业
			new String[]{"internal_three_code","internal_three_name","[\\d]{4}([1-9][0-9]|[0-9][1-9])00"},//三级行业
			new String[]{"internal_four_code","internal_four_name","[\\d]{6}([1-9][0-9]|[0-9][1-9])"}//四级行业
		};
		//匹配相应的行业代码级别
		int index = 0;
		for(int i=0; i<industry_class.length; i++){
			String reg = industry_class[i][2];
			Pattern pattern = Pattern.compile(reg);
			Matcher isIC = pattern.matcher(code);
			if (isIC.matches()) {
				index = i;
				break;
			}		
		}
		
		String name = code;
		//如果均不匹配
		if(index<industry_class.length){
			// 获取结果
			String sql = "select "+industry_class[index][1]+" industry_class_name from system_inter_indus_info where "+industry_class[index][0]+" = ?";
			SqlRowSet nameRS = jdbcTemplate.queryForRowSet(sql,new Object[] { code });
			
			if(nameRS.next()){
				name = nameRS.getString("industry_class_name");
			}			
		}
		return name;
	}
	/**
	 * @specification 判断登陆用户Session是否超时
	 * @return String 0[用户session超时]、1[未超时]
	 */
	public String sessionIsOverTime(HttpServletRequest request){
		if(null == Constant.getFormUserOperation(request))
			return "0";
		return "1";
	}
	
	/**
	 * @specification 是否已经指定公司代码
	 * @param fin_date 报表日期
	 * @param fin_entity 报表口径(01-集团合并;02-非集团)
	 * @return String -1[用户session超时] 0[未指定公司] 1[已经指定]
	 */
	public String isAppointCompanyCode(HttpServletRequest request){
		FormUserOperation loginUser = Constant.getFormUserOperation(request);
		if(null == loginUser)
			return "-1";
		else if("".equals(loginUser.getCompany_code()))
			return "0";
		return "1";
	}
	//设置债券评级映射维度有效性
	public String setDebtRatDimensionValidity(String flag, HttpServletRequest request){
		flag = null == flag || flag.length()==0 ? "1" : flag;
		try{
			int rows = jdbcTemplate.update("update SYSTEM_RISK_COMPU_PARA_INFO set debt_rating_dimension='"+flag+"'");
			if(rows > 0){
				return "设置成功!";
			}
		} catch (DataAccessException dae){
			logger.error(dae.getMessage());
		}
		return "设置失败!";
	}
	/**
	 * 获取用户[基本角色]列表,或获取用户[任务审批人角色]列表
	 * @param flag 1-基本角色,0-任务审批人角色
	 */
	@SuppressWarnings("unchecked")
	public String[] getUserBasicRoleList(String flag, HttpServletRequest request){
		String where = "1".equals(flag) ? "" : " and rownum=1 ";
		String orderBy = "1".equals(flag) ? "" : " desc";
		String sql = "select * from system_role_info where role_id "+("1".equals(flag) ? "":" not ")+" in('R00','R01','R02','R03','R04','R05','R06') "+where+" order by role_id "+orderBy;
		List<Map<String,String>> list = jdbcTemplate.queryForList(sql);
		if(list != null && !list.isEmpty()) {
			List<String> roleList = new ArrayList<String>();
			for (Map<String, String> map : list) {
				roleList.add(map.get("ROLE_ID"));
			}
			return roleList.toArray(new String[roleList.size()]);
		}
		return null;
	}
	//查找某个角色对应的所有用户是否有未处理完的流程
	public int roleUserIsHaveFlow(String roleId, HttpServletRequest request){
		StringBuffer querySQL = new StringBuffer();
		querySQL.append(" select count(*) from ( ");
		querySQL.append(" select count(e.task_status) task_status ");
		querySQL.append("   from system_user_info s ");
		querySQL.append("   left join (select e.*, ed.process_person ed_process_person ");
		querySQL.append("                from crds_flow_info e ");
		querySQL.append("                left join evaluation_d_flow_info ed on e.task_id = ed.task_id ");
		querySQL.append("                left join evaluation_m_flow_info em on e.task_id = em.task_id) e ");
		querySQL.append("     on (s.user_id = e.process_user or s.user_id = e.draft_person or s.user_id = e.draft_person or s.user_id = e.ed_process_person or s.user_id = e.em_process_person) ");
		querySQL.append("     and e.task_status in ('CO', 'RT', 'SA') ");
		querySQL.append("  where s.role_id='").append(roleId).append("' ");
		querySQL.append("  ) tab where CONVERT(numeric(18,4), (tab.task_status))>0 ");
		int count = jdbcTemplate.queryForInt(querySQL.toString());
		return count;
	}
	/***
	 * 根据ul系数查询评级
	 * @param
	 * @return
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getBraRaing(String timeLimit,String ulIndex){
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("  select * from (");
		querySQL.append("    select * from (");
		querySQL.append("      select rating_number,bra_rating,pd_oneyear,");
		querySQL.append("        (nvl(ul_index,0)*(1+greatest(").append(timeLimit).append("-2.5,0)*power(0.11852-0.05478*ln(pd_oneyear),2)))+0.00001 ul_index");
		querySQL.append("      from system_inout_rating_info)");
		querySQL.append("    where ul_index >= ").append(ulIndex);
		querySQL.append("    order by CONVERT(numeric(18,4), (rating_number)) asc)");
		querySQL.append("  where rownum=1");
		List result = jdbcTemplate.queryForList(querySQL.toString());
		if(result==null || result.isEmpty()){
			Map<String,String> map = new HashMap<String,String>();
			map.put("BRA_RATING", "D");
			map.put("PD_ONEYEAR", "1");
			map.put("UL_INDEX", ulIndex);
			return map;
		} else {
			return (Map<String,String>)result.get(0);
		}
	}
	/**
	 * 删除上传的文件
	 * @param fileID 文件ID
	 * @param filePath 文件路径
	 * @return int 1-成功,0-失败
	 */
	public int delUploadFile(String fileID,String filePath,HttpServletRequest request){
		int rows = jdbcTemplate.update("delete evaluation_external_file_info where file_id='"+fileID+"'");
		if(rows > 0){
			boolean deleteOK = UploadTool.delUploadFile(UploadTool.webappsRootPath+filePath);//删除物理文件
			if(deleteOK==false)
				logger.info("删除失败,文件:"+filePath+"不存在.");
			return 1;
		}
		return 0;
	}
	
	
	/**
	 * 定位公司分析师用户ID,要同时定位多个公司时请用逗号","将参数companyCodes和userIds分隔,
	 * @param companyCodes 公司代码
	 * @param userIds 用户ID
	 * @param request
	 * @return int 
	 */
	public int posCompanyAnalystUserId(String companyCodes,String userIds, HttpServletRequest request){
		int rows = 0;
		if(!Constant.isEmpty(companyCodes) &&  !Constant.isEmpty(userIds)){
			String[] codes = companyCodes.split(",");
			String[] ids = userIds.split(",");
			if(!Constant.isEmpty(codes) &&  !Constant.isEmpty(ids) && codes.length==ids.length){
				for(int i = 0; i < codes.length; i++)
					rows += jdbcTemplate.update("update COMPANY_BASIC_INFO set USER_ID='"+ids[i]+"' where COMPANY_CODE='"+codes[i]+"'");
			}
			if(rows==1){
				Constant.getFormUserOperation(request).setAnalyse_person_id(ids[0]);
			}
		}
		return rows;
	}
	
	/**
	 * 切换公司或返回切换前的公司
	 * @param toCompanyCode
	 * @param request
	 * @return
	 */
	public String[] switchCompany(String toCompanyCode, HttpServletRequest request){
		//获得用户信息从session
		FormUserOperation frmUO = Constant.getFormUserOperation(request);
		String currCompanyCode = frmUO.getCompany_code();
		if(toCompanyCode==null || toCompanyCode.length()==0){//为空则为返回,此时从获取切换前的公司代码
			toCompanyCode= frmUO.getSwitchBeforeCompanyCode();
		} else {
			frmUO.setSwitchBeforeCompanyCode(currCompanyCode);
		}
		frmUO.setCompany_code(toCompanyCode);
		//查询某公司信息并保存到用户相关信息到session中
		try {
			frmUO = CommonMethod.queryCompanyInfo(frmUO);
		} catch (Exception e) {
			logger.warn("switch company failed. " + currCompanyCode + " switch to " + toCompanyCode + ".\nerror info:"+e.getMessage());
			frmUO.setCompany_code(currCompanyCode);
			try {
				frmUO = CommonMethod.queryCompanyInfo(frmUO);
			} catch (Exception e1) {
				logger.warn("switch company failed. " + currCompanyCode + " switch to " + toCompanyCode + ",return to company " + currCompanyCode + " failed.\nerror info:"+e.getMessage());
			}
		}
		Constant.setFormUserOperation(request, frmUO);
		return new String[]{frmUO.getCompany_code(),frmUO.getCompany_name()};
	}
	@SuppressWarnings("unchecked")
	public String revocation(HttpServletRequest request){
		sessionIsExpired(request);
		if(sessionIsExpired){
			return errorMsg;
		}
		String companyCode = frmUO.getCompany_code();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct TASK_TYPE,TASK_STATUS from (");
		sql.append("	select distinct  TASK_TYPE,TASK_STATUS from CRDS_FLOW_INFO where COMPANY_CODE='").append(companyCode).append("'");
		sql.append("	union");
		sql.append("	select distinct 'TM' TASK_TYPE, RATING_STATUS TASK_STATUS from EVALUATION_M_RATING_INFO where COMPANY_CODE='").append(companyCode).append("' and RATING_STATUS in ('SA','RT','CO','RC')");
		sql.append("	union");
		sql.append("	select distinct 'TD' TASK_TYPE, RATING_STATUS TASK_STATUS from EVALUATION_D_RATING_INFO where COMPANY_CODE='").append(companyCode).append("' and RATING_STATUS in ('SA','RT','CO','RC')");
		//sql.append("	union ");
		//sql.append("	select distinct 'TF' TASK_TYPE, FIN_STATE TASK_STATUS from FINANCIAL_FIN_HEAD_INFO where COMPANY_CODE='").append(companyCode).append("' and fin_state_type = 'HR' and FIN_STATE='TS' and substring(FIN_DATE,6,2)='12' and FIN_ENTITY='11'");
		sql.append(") flowTab");
		List<Map<String,String>> list = jdbcTemplate.queryForList(sql.toString());
		String alertMsg = "撤销操作失败!";
		if(list!=null && !list.isEmpty()){
			Map<String,String> map = list.get(0);
			String taskType = map.get("TASK_TYPE"), taskStatus = map.get("TASK_STATUS"), msg = taskStatus.replaceAll("SA|TS", "暂存").replace("RT", "退回").replace("CO", "审批").replace("RC", "重检");
			return "公司存在["+msg+"]的["+(taskType.replace("TM", "主体评级").replace("TD", "债项评级").replace("TF", "财务报表"))+"],不能进行撤销操作!";
		} else {
			int row = jdbcTemplate.update("update COMPANY_BASIC_INFO set user_id='' where company_code='"+companyCode+"'");
			if(row>0){
				Constant.getFormUserOperation(request).setAnalyse_person_id("");
				return "1";
			}
		}
		return alertMsg;
	}
}
