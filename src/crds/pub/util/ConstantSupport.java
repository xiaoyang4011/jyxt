package crds.pub.util;

import java.awt.Color;

/**
 * @specification : 系统相关参数定义
 * @version : 1.0
 * @author : houtingsong(MaCi Hotesion)
 * @date : May 4, 2009 5:32:41 PM
 * @email : houtingsong163@163.com
 */
public interface ConstantSupport extends SystemDateSupport {
	//==============================登录用户设置到session中的Key======================
	/** 登陆用户SessionKey */
	String LoginUserSessionKey = "formUserOperation";
	/** 登陆用户操作列表 */
	String LoginUserOperationList = "listOperation";
	//========================================流程状态================================
	
	//========================================任务类型================================
	String TASK_TYPE_TF = "TF";//财务暂存
	String TASK_TYPE_TU = "TU";//联合评级
	String TASK_TYPE_TM = "TM";//主体评级
	String TASK_TYPE_TD = "TD";//债项评级
	String TASK_TYPE_TC = "TC";//质押变化
	String[][] TaskTypes = new String[][]{{TASK_TYPE_TF,"财务暂存"}, {TASK_TYPE_TU,"信用评级"}, {TASK_TYPE_TM,"主体评级"}, {TASK_TYPE_TD,"债项评级"}, {TASK_TYPE_TC,"质押变化"}};
	//==================================公司评级状态(包含任务状态)=====================
	String RAT_STATUS_RH = "RH";//评级历史
	String RAT_STATUS_RV = "RV";//当前有效
	String RAT_STATUS_SA = "SA";//暂存
	String RAT_STATUS_CO = "CO";//待审批
	String RAT_STATUS_RT = "RT";//退回
	String RAT_STATUS_RC = "RC";//评级重检
	String[][] RatStatus = new String[][]{{RAT_STATUS_RH,"历史评级"}, {RAT_STATUS_RV,"当前有效"}, {RAT_STATUS_SA,"暂存"}, {RAT_STATUS_CO,"审批中"}, {RAT_STATUS_RT,"退回"}, {RAT_STATUS_RC,"评级重检"}};
	//========================================公司类别================================
	/** 公司类别(ET-一般企业,BT-商业银行,IT-保险公司,ST-证券公司) */
	String CompanyType_ET = "ET";
	/** 公司类别(ET-一般企业,BT-商业银行,IT-保险公司,ST-证券公司) */
	String CompanyType_BT = "BT";
	/** 公司类别(ET-一般企业,BT-商业银行,IT-保险公司,ST-证券公司) */
	String CompanyType_IT = "IT";
	/** 公司类别(ET-一般企业,BT-商业银行,IT-保险公司,ST-证券公司) */
	String CompanyType_ST = "ST";
	/** 公司类型代码列表 */
	String[] CompanyTypeCode = new String[]{CompanyType_ET,CompanyType_BT,CompanyType_IT,CompanyType_ST};
	/** 公司类型代码与名称列表 */
	String[][] CompanyType = new String[][]{{CompanyType_ET,"一般企业"},{CompanyType_BT,"商业银行"},{CompanyType_IT,"保险公司"},{CompanyType_ST,"证券公司"}};
	//=========================================其它===================================
	/** 是否允许用户自定义二级指标 0不允许 1允许 */
	String USER_CAN_DEFIN_SEC_INDIC = "1";
	/** 债券池查看选项,true-所有基金经理所管组合的所有入池债项, false-所有分析师所管公司的所有入池债项*/
	boolean bondPoolViewItem = false;
	/** 评级流程是否跨部门提交 */
	boolean RatFlowIsAcrossDebtSubmit = true;
	/** 主体和债项评级审批完成侯是否发送邮件,0-不发送,1-主体和债项都发送,2-主体发送,3-债项发送 */
	int ratApproveFinishIsSendEmail = 0;
	//==============================系统中的一些需要转发的URL==========================
	/** session超时转发地址 */
	String doURLSessionTimeoutRedirect = "/login.do";
	/** 未定位公司转发地址 */
	String doURLUnCompanyCodeRedirect = "/to_queryHomeCompanyInfo.do";
	/** 公司基本信息转发地址 */
	String doURLCompanyInfoRedirect = "/to_modify_basicinfo.do";
	/** 公司概况转发地址 */
	String doURLCompanyDetailRedirect = doURLCompanyInfoRedirect+"?toDetail=1";
	//==============================系统中的一些对外开放的URL==========================
	String[] openURL = new String[]{"debtRatFinishSendEmailContent.do","show_externaFile.do","to_modify_rat_info.do"};
	//====================================公司规模与模板规模======================================
	/** 模板模型规模代码: BS,MS,SS,BM,MX,AS */
	String tempModelSizeCodes = "BS,MS,SS,BM,MX,AS";
	/** 模板模型规模([["BS","大型],["MS","中型"],["SS","小型"],["BM","大中型"],["MX","中小型"],["AS","全部"]]) */
	String[][] tempModelSizes = new String[][]{{"BS","大型"},{"MS","中型"},{"SS","小型"},{"BM","大中型"},{"MX","中小型"},{"AS","全部"}};
	/** 公司规模([["BS","大型],["MS","中型"],["SS","小型"]]) */
	String[][] CompanySizes = new String[][]{{"BS","大型"},{"MS","中型"},{"SS","小型"}};
	//======================================================================
	/** 需要乘100的比率 */
	String percentPar = ",inventry2cost,curr_funds2current_assets,liab2asst,debt2liab,operating_profit_mrgn,grss_mrgn,tot_asst_rtrn,sale_grwth,net_premium_growth_rate,total_return_on_equity,premium_collection_rate,rate_of_assets_liabilities,cover_leverage,growth_rate_of_total_assets,loan2deposit_ratio,net_interest_proportion,fee_income_accounted,non_performing_loan_ratio,provision_coverage,capital_adequacy_ratio,operating_income_growth_rate,operating_margin,operating_expenses_rate,total_capitalization_ratio,";
	/** 系统JfreeChart图颜色配置(红,绿,黄,蓝,青,背景色(淡蓝),深黄) */
	Color[] colors = new Color[]{new Color(178,34,34),new Color(34,139,34),new Color(218,165,32),new Color(30,144,255),new Color(0,139,139),new Color(238,  238,  255),new Color(238, 238,  0)};
	//================================人员基本角色信息==============================
	/** R00-分析师/客户经理 */
	String ROLE_R00 = "R00";
	/** R01-投资经理/基金经理 */
	String ROLE_R01 = "R01";
	/** R02-投资总监/部门经理 */
	String ROLE_R02 = "R02";
	/** R03-风险经理 */
	String ROLE_R03 = "R03";
	/** R04-信息录入员 */
	String ROLE_R04 = "R04";
	/** R05-任务分配人 */
	String ROLE_R05 = "R05";
	/** R06-信息监察员 */
	String ROLE_R06 = "R06";
	/** R07-日常维护员 */
	String ROLE_R07 = "R07";
	/** R08-交易员 */
	String ROLE_R08 = "R08";
	/** R09-研究员 */
	String ROLE_R09 = "R09";
	/** R10-业务系统管理员 */
	String ROLE_R10 = "R10";
	/** 人员基本角色列表R00-R10 */
	String[] ROLE_CODE = new String[]{ROLE_R00,ROLE_R01,ROLE_R02,ROLE_R03,ROLE_R04,ROLE_R05,ROLE_R06,ROLE_R07,ROLE_R08,ROLE_R09,ROLE_R10};
	/** 系统基本角色{{角色代码,角色名称,是否参与流程(Y-是,N-否)}[,{角色代码,角色名称,是否参与流程(Y-是,N-否)}]} */
	String[][] ROLE = new String[][]{{ROLE_R00,"分析师(客户经理)","Y"},{ROLE_R01,"基金经理","N"},{ROLE_R02,"投资经理","N"},{ROLE_R03,"风险经理","N"},{ROLE_R04,"信息录入员","N"},{ROLE_R05,"任务分配人","Y"},{ROLE_R06,"信息监察员","N"},{ROLE_R07,"日常维护员","N"},{ROLE_R08,"交易员","N"},{ROLE_R09,"研究员","N"},{ROLE_R10,"业务系统管理员","N"},{"R11","1级任务审批人","N"}};
	//================================日志类型==============================
	/** 日志类型 {{"DB","删除基本信息"},{"MB","更改基本信息"},{"DF","删除财务信息"},{"MF","更改财务信息"},{"DD","删除债项信息"},{"MD","更改债项信息"}} */
	String[][] LogType = new String[][]{{"DB","删除基本信息"},{"MB","更改基本信息"},{"DF","删除财务信息"},{"MF","更改财务信息"},{"DD","删除债项信息"},{"MD","更改债项信息"}};
	/** 报表口径 集团合并 */
	String FIN_ENTITY_UNION = "11";
}

interface SystemDateSupport {
	/** yyyy-MM-dd */
	String dateFormat0 = "yyyy-MM-dd";
	/** yyyy-MM-dd HH:mm:ss */
	String dateFormat1 = "yyyy-MM-dd HH:mm:ss";
}
