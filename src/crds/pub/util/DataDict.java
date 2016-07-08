package crds.pub.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @specification :数据字典
 * @version : 1.0
 * @author : maogf
 * @date : Dec 1, 2009 3:39:15 PM
 * @email : maogenfeng@gmail.com
 */

public final class DataDict {
	private static final long serialVersionUID = 1L;
	/**
	 * 通过代码获取名称的数据表
	 */
	public static final Map<String, String> DataDictMap = initDataDictMap();
	/**
	 * 带百分号的字段
	 */
	public static final String FiledWithPresent = ",OPERATING_PROFIT_MRGN,COM_SALE_GRWTH,LIAB2ASST,CSH_RATIO,LIAB2TNGBL_ASST,DEBT2LIAB,DEBT2CPTL,LNGASST2LNGLIAB,ADJ_LIAB2TNGBL_ASST,WRKG_CPTL2TNGBL_ASST,FXD_ASST_RATIO,GRSS_MRGN,EBITDA_MRGN,EBIT_MRGN,NET_MRGN,TOT_ASST_RTRN,TNGBLASST_RTRN,NETASST_RTRN,MNG_EXPNS_RATIO,NON_CSH_EXPNS_RATIO,CSH2LIAB,TOT_ASST_GRWTH,SALE_GRWTH,EBITDA_GRWTH,EBIT_GRWTH,COS_RATIO,SALE_TAX,OTHER_REV,SELL_FEE,GA_FEE,INV_INC,ASST_PRV,DEPRE_RATIO,INTEREST_RATE,TAX_RATE,DVD_RATIO,CASH_RATIO,ST_LT_DEBT,CAPEXRATIO,OT_INCOME,LVRG,ROE,ROL,M_RATIO_1,M_RATIO_2,M_RATIO_3,M_RATIO_4,M_RATIO_6,M_RATIO_8,OTH_CURR_ASST_RATIO,PREMIUM_GROWTH_RATE,NET_PREMIUM_GROWTH_RATE,NET_RATE_OF_RETURN_ON_ASSETS,UNDERWRITING_PROFIT_RATE,INVESTMENT_RATE_OF_RETURN,PREMIUM_COLLECTION_RATE,SOLVENCY_ADEQUACY_RATIO,RATE_OF_ASSETS_LIABILITIES,COVER_LEVERAGE,LIQUIDITY,EBIM,ROI,GROWTH_RATE_OF_TOTAL_ASSETS,GROWTH_RATE_OF_LOANS,GROWTH_RATE_OF_DEPOSITS,LIQUIDITY_RATIO,LOAN2DEPOSIT_RATIO,NET_INTEREST_PROPORTION,FEE_INCOME_ACCOUNTED,INVESTMENT_INCOME_ACCOUNTED,TOTAL_RETURN_ON_EQUITY,RETURN_ON_EQUITY,LOAN_NET_INTEREST_YIELD,NON_PERFORMING_LOAN_RATIO,PROVISION_COVERAGE,CAPITAL_ADEQUACY_RATIO,OPERATING_INCOME_GROWTH_RATE,OPERATING_PROFIT_GROWTH,OPERATING_MARGIN,OPERATING_EXPENSES_RATE,N_RATE_OF_ASSETS_LIABILITIES,TOTAL_CAPITALIZATION_RATIO,NET_CAPITAL_RATIO,ASST_REWD,";
	/**
	 * 带万元的字段
	 */
	public static final String FiledWithWanYuan = "";
	/**
	 * 同业比较中需从小到大排序的变量资产负债率(%) 排序 有息债务/债务总额(%) 排序 应收帐款周转天数(天) 排序 存货周转天数(天) 排序
	 * 应付帐款周转天数(天)
	 */
	public static final String FiledWithSortL2U = ",LIAB2ASST,DEBT2LIAB,AR_DAY,INVNTRY_DAY,AP_DAY,";
	/**
	 * 系统类型为F的字段表
	 */
	public static final Map<String, String> FSystemMap = initFSystemMap();
	/**
	 * 系统类型为B的字段表
	 */
	public static final Map<String, String> BSystemMap = initBSystemMap();
	/**
	 * 操作记录内容表
	 */
	public static Map<String, String[]> OperationMemoMap = initOperationMemoMap();
	/**
	 * 公司类别表
	 */
	public static Map<String, String> CompanyTypeMap = initCompanyTypeMap();

	// 初始化数据表
	private static Map<String, String> initDataDictMap() {
		Map<String, String> map = new HashMap<String, String>();
		// 评级机构代码、名称
		map.put("moody_rating", "穆迪");
		map.put("standard_rating", "标普");
		map.put("bra_rating", "量龙");
		// 交易市场
		map.put("1", "未上市");
		map.put("2", "非交易所交易");
		map.put("3", "深交所");
		map.put("4", "上交所");
		map.put("5", "港交所");
		map.put("6", "银行间");
		map.put("7", "固定收益平台");
		// 指标代码、名称
		map.put("OPERATING_PROFIT_MRGN", "营业利润率(%)");
		map.put("TRD_DAY", "贸易经营周期(天)");
		map.put("COM_SALE_GRWTH", "营业收入连续三年复合增长率(%)");
		map.put("LIAB2ASST", "资产负债率(%)");
		map.put("OPRTG_CF_DEBT_COV", "现金流债务覆盖比率(X)");
		map.put("FREE_CF", "自由现金流");
		map.put("OPRTG_CF", "经营活动现金流");
		map.put("EBIT", "EBIT");
		map.put("EBITDA", "EBITDA");
		map.put("CURR_RATIO", "流动比率(X)");
		map.put("QUCK_RATIO", "速动比率(X)");
		map.put("CSH_RATIO", "现金等价物占有形资产比率(%)");
		map.put("CSH_DAYS", "现金等价销售天数(天)");
		map.put("TURNVR2WRKG_CPTL", "营业收入/营运资本(X)");
		map.put("INVENTRY2COST", "存货占比");
		map.put("CURR_FUNDS2CURRENT_ASSETS", "货币资金占比");
		map.put("LVRG_RATIO", "杠杆比率(X)");
		map.put("LIAB2TNGBL_ASST", "有形资产负债率(%)");
		map.put("DEBT2LIAB", "有息债务/债务总额(%)");
		map.put("DEBT2CPTL", "有息债务/资本总额(%)");
		map.put("LNGASST2LNGLIAB", "长期资产适合率(%)");
		map.put("ADJ_LIAB2TNGBL_ASST", "调整后的有形资产负债率(%)");
		map.put("WRKG_CPTL2TNGBL_ASST", "营运资金/有形资产总额(%)");
		map.put("FXD_ASST_RATIO", "固定资产比率(%)");
		map.put("FREE_CF2EBITDA", "自由现金流/EBITDA(X)");
		map.put("FREE_CF2TURNVR", "自由现金流/营业收入(X)");
		map.put("RTND_CF2CAPEX", "留存现金流/资本支出(X)");
		map.put("EBIT_INTRST_COV", "EBIT利息保障倍数(X)");
		map.put("EBITDA_INTRST_COV", "EBITDA利息保障倍数(X)");
		map.put("EBITDA_FXDEXPNS_COV", "EBITDA固定支出保障倍数(X)");
		map.put("OPRTG_CF_FXDEXPNS_COV", "经营活动现金流固定支出保障倍数(X)");
		map.put("CSH2CURR_LIAB", "现金及短期投资/短期债务(X)");
		map.put("RTND_CF2DEBT", "留存现金流/有息债务(X)");
		map.put("FCF2TOT_LIAB", "三年平均自由现金流/负债合计(X)");
		map.put("FCF2ADJ_TOT_LIAB", "三年平均现金流/调整后的负债合计(X)");
		map.put("GRSS_MRGN", "毛利润率(%)");
		map.put("EBITDA_MRGN", "EBITDA 利润率(%)");
		map.put("EBIT_MRGN", "EBIT利润率(%)");
		map.put("NET_MRGN", "净利润率(%)");
		map.put("TOT_ASST_RTRN", "总资产收益率(%)");
		map.put("TNGBLASST_RTRN", "有形资产收益率(%)");
		map.put("NETASST_RTRN", "净资产收益率(%)");
		map.put("TURNOVER", "营业收入净额");
		map.put("OPRT_PRFT", "营业利润");
		map.put("TOT_ASST", "总资产");
		map.put("THR_YR_FCF", "三年平均自由现金流");
		map.put("WRKG_CPTL", "营运资本");
		map.put("TNGBL_NET_ASST", "有形净资产");
		map.put("TNGBL_TOT_ASST", "有形资产总额");
		map.put("SHRT_DEBT", "短期有息债务");
		map.put("LONG_DEBT", "长期有息债务");
		map.put("TOT_CPTL", "资本总额");
		map.put("GRSS_CF", "总现金流");
		map.put("RTND_CF", "留存现金流");
		map.put("TOT_EQTY", "所有者权益");
		map.put("MNG_EXPNS_RATIO", "管理费用比率(%)");
		map.put("NON_CSH_EXPNS_RATIO", "非现金支出比率(%)");
		map.put("CSH2LIAB", "现金负债总额比率(%)");
		map.put("DEBT2EBITDA", "调整后的有息债务/EBITDA");
		map.put("AR_DAY", "应收帐款周转天数(天)");
		map.put("INVNTRY_DAY", "存货周转天数(天)");
		map.put("AP_DAY", "应付帐款周转天数(天)");
		map.put("TOT_ASST_TURNVR", "总资产周转率(次)");
		map.put("FXD_ASST_TURNVR", "固定资产周转率(次)");
		map.put("TBGBLASST_TURNVR", "有形资产周转率(次)");
		map.put("TOT_ASST_GRWTH", "总资产增长率(%)");
		map.put("SALE_GRWTH", "营业收入增长率(%)");
		map.put("EBITDA_GRWTH", "EBITDA增长率(%)");
		map.put("EBIT_GRWTH", "EBIT增长率(%)");
		map.put("COS_RATIO", "营业成本比率(%)");
		map.put("SALE_TAX", "营业税比率(%)");
		map.put("OTHER_REV", "其他业务收入比率(%)");
		map.put("SELL_FEE", "销售费用比率(%)");
		map.put("GA_FEE", "管理费用比率(%)");
		map.put("INV_INC", "投资收益比率(%)");
		map.put("ASST_PRV", "资产减值损失(减值准备)比率(%)");
		map.put("DEPRE_RATIO", "平均折旧率(%)");
		map.put("INTEREST_RATE", "平均利息率(%)");
		map.put("TAX_RATE", "平均所得税率(%)");
		map.put("DVD_RATIO", "普通股利(%)");
		map.put("CASH_RATIO", "现金比率(%)");
		map.put("CAPEX", "资本支出");
		map.put("R_D", "开发支出");
		map.put("ST_LT_DEBT", "长短期有息债务比例(%)");
		map.put("ACCRCHGN", "应计费用的变化");
		map.put("CAPEXRATIO", "资本支出比率(%)");
		map.put("OTCARATIO", "其他流动资产比例");
		map.put("OTCLRATIO", "其他流动负债比例");
		map.put("TAX_PAY", "(应付税款+期末递延-期初递延)/所得税");
		map.put("OT_INCOME", "非经营利润率(%)");
		map.put("OT_LA", "其他长期资产比例");
		map.put("GRSS_MRGN_CHGN", "毛利率增长倍数");
		map.put("AR_CHGN", "应收帐款增长倍数");
		map.put("INVENTORY_CHGN", "存货增长倍数");
		map.put("ACCR_RATIO", "应计项目/资产总额");
		map.put("WRKC_TURN", "营运资本周转次数");
		map.put("LNGC_TURN", "长期资产周转次数");
		map.put("INTEREST", "利息支出");
		map.put("DEBT", "有息债务合计平均值");
		map.put("EQUITY", "所有者权益合计平均值");
		map.put("LVRG", "杠杆比率(%)");
		map.put("ROE", "净资产收益率(%)");
		map.put("ROL", "杠杆对权益资本收益贡献率(%)");
		map.put("M_RATIO_1", "有息债务占资本比例(%)");
		map.put("M_RATIO_2", "短期债务占资本比例(%)");
		map.put("M_RATIO_3", "营业利润率三年平均值(%)");
		map.put("M_RATIO_4", "资本收益率(%)");
		map.put("M_RATIO_5", "现金流违约距离");
		map.put("M_RATIO_6", "应收帐款及存货周转率(%)");
		map.put("M_RATIO_7", "模型所有者权益");
		map.put("M_RATIO_8", "资产负债率三年增加值(%)");
		map.put("RECEIVABLE_RATIO", "应收项目比例");
		map.put("COSTS_COPE_RATIO", "应付费用比例");
		map.put("OTH_CURR_ASST_RATIO", "其他流动资产比例(%)");
		map.put("TAX_CHANGE", "所得税率变化");
		map.put("TOT_ASST_TURNVR_CHANGE", "资产周转率变化");
		map.put("GRSS_MRGN_CHANGE", "毛利润率变化(X)");
		map.put("TOTAL_DEPOSITS", "存款总额");
		map.put("TOTAL_LOANS", "贷款总额");
		map.put("INSURANCE_REVENUE", "保险业务收入");
		map.put("NET_PREMIUM_INCOME", "净保费收入");
		map.put("UNDERWRITING_PROFIT", "承保利润");
		map.put("INVESTMENT_INCOME", "投资收益");
		map.put("NET_PROFIT", "净利润");
		map.put("PREMIUM_GROWTH_RATE", "保费增长率(%)");
		map.put("NET_PREMIUM_GROWTH_RATE", "净保费增长率(%)");
		map.put("NET_RATE_OF_RETURN_ON_ASSETS", "净资产回报率(%)");
		map.put("UNDERWRITING_PROFIT_RATE", "承保利润率(%)");
		map.put("INVESTMENT_RATE_OF_RETURN", "投资收益率(%)");
		map.put("PREMIUM_COLLECTION_RATE", "保费实收率(%)");
		map.put("SOLVENCY_ADEQUACY_RATIO", "偿付能力充足率(%)");
		map.put("RATE_OF_ASSETS_LIABILITIES", "资产负债率(%)");
		map.put("COVER_LEVERAGE", "承保杠杆比率(%)");
		map.put("LIQUIDITY", "拨备覆盖率(%)");
		map.put("OCF_DIF", "经营现金流差异");
		map.put("ACCR_TURN_CHGN", "应收项目周转率变化");
		map.put("EBIM", "息前可持续经营利润率(%)");
		map.put("CAP_TURN", "资本周转次数");
		map.put("ROI", "投资收益率(%)");
		map.put("PRE_PROVISION_PROFIT", "计提拨备前利润");
		map.put("GROWTH_RATE_OF_TOTAL_ASSETS", "总资产增长率(%)");
		map.put("GROWTH_RATE_OF_LOANS", "贷款余额增长率(%)");
		map.put("GROWTH_RATE_OF_DEPOSITS", "存款余额增长率(%)");
		map.put("LIQUIDITY_RATIO", "流动性比率(%)");
		map.put("LOAN2DEPOSIT_RATIO", "存贷比(%)");
		map.put("NET_INTEREST_PROPORTION", "利息净收入占比(%)");
		map.put("FEE_INCOME_ACCOUNTED", "手续费收入占比(%)");
		map.put("INVESTMENT_INCOME_ACCOUNTED", "投资收益占比(%)");
		map.put("TOTAL_RETURN_ON_EQUITY", "总资产收益率(减值准备前)(%)");
		map.put("RETURN_ON_EQUITY", "净资产收益率(%)");
		map.put("LOAN_NET_INTEREST_YIELD", "贷款净利息收益率(%)");
		map.put("NON_PERFORMING_LOAN_RATIO", "不良贷款率(%)");
		map.put("PROVISION_COVERAGE", "拨备覆盖率(%)");
		map.put("CAPITAL_ADEQUACY_RATIO", "资本充足率(%)");
		map.put("CORE_CAPITAL_ADEQUACY_RATIO", "核心资本充足率(%) ");
		map.put("OPERATING_INCOME", "营业收入");
		map.put("NET_ASSETS", "净资产");
		map.put("NET_CAPITAL", "净资本");
		map.put("OPERATING_INCOME_GROWTH_RATE", "营业收入增长率(%)");
		map.put("OPERATING_PROFIT_GROWTH", "营业利润增长率(%)");
		map.put("OPERATING_MARGIN", "营业毛利率(%)");
		map.put("OPERATING_EXPENSES_RATE", "营业费用率(%)");
		map.put("N_RATE_OF_ASSETS_LIABILITIES", "净资产负债率(%)");
		map.put("INTEREST_BEARING_DEBT", "有息债务");
		map.put("TOTAL_CAPITALIZATION_RATIO", "全部资本化比率(%)");
		map.put("NET_CAPITAL_RATIO", "净资本比率(%)");
		map.put("NET_CAPITAL2NET_ASSETS", "净资本/净资产(X)");
		map.put("DEBT_SEV", "有息债务保障倍数(X)");
		map.put("INTERST_COV", "利息保障倍数(X)");
		return map;
	}

	// 初始化F系统字段表
	private static Map<String, String> initFSystemMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("raroc", "RAR");
		map.put("ead", "EAD");
		map.put("credit_amount", "发行额");
		return map;
	}

	// 初始化B系统字段表
	private static Map<String, String> initBSystemMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("raroc", "RAROC");
		map.put("ead", "EAD");
		map.put("credit_amount", "授信额度");
		return map;
	}

	// 初始化操作内容表
	private static Map<String, String[]> initOperationMemoMap() {
		Map<String, String[]> map = new HashMap<String, String[]>();
		String[] array_DB = new String[1];// 基本信息删除
		array_DB[0] = "#0#被删除";

		String[] array_MB = new String[6];// 基本信息变化
		array_MB[0] = "公司名称由“#0#”变成“#1#”";
		array_MB[1] = "公司类别由“#0#”变成“#1#”";
		array_MB[2] = "是否追踪由“#0#”变成“#1#”";
		array_MB[3] = "内部行业由“#0#”变为“#1#”";
		array_MB[4] = "证监会行业由“#0#”变成“#1#”";
		array_MB[5] = "实际控制人由“#0#”变为“#1#”";

		String[] array_MF = new String[2];// 财务信息变化
		array_MF[0] = "#0#由保存状态改为暂存状态";
		array_MF[1] = "#0#进行了删除";

		String[] array_MD = new String[7];// 债项信息变化
		array_MD[0] = "#0#债项名称由“#1#”变成“#2#”";
		array_MD[1] = "#0#债项期限由“#1#”年变成“#2#”年";
		array_MD[2] = "#0#债项发行总额由“#1#”万元变成“#2#”万元";
		array_MD[3] = "#0#债项的债项利率由“#1#%”变为“#2#%”";
		array_MD[4] = "#0#债项是否偿还由“#1#”变成“#2#”";
		array_MD[5] = "#0#债项是否优先债务由“#1#”变为“#2#”";
		array_MD[6] = "#0#债项被删除";

		String[] array_MP = new String[5];// 抵押信息变化
		array_MP[0] = "#0#抵押品名称由“#1#”变成“#2#”";
		array_MP[1] = "#1#抵押品类型由“#1#”变成“#2#”";
		array_MP[2] = "#0#抵押品评估价值由“#1#”万元变成“#2#”万元";
		array_MP[3] = "#0#抵押品抵押其他债金额由“#1#”万元变成“#2#”万元";
		array_MP[4] = "#0#抵押品被删除";

		String[] array_MA = new String[5];// 担保信息变化
		array_MA[0] = "#0#担保人名称由“#1#”变成“#2#”";
		array_MA[1] = "#0#担保人的担保方式由“#1#”变成“#2#”";
		array_MA[2] = "#0#担保人的担保金额由“#1#”万元变成“#2#”万元";
		array_MA[3] = "#0#担保人的担保终止日由“#1#”变成“#2#”";
		array_MA[4] = "#0#担保人被删除";

		map.put("DB", array_DB);
		map.put("MB", array_MB);
		map.put("MF", array_MF);
		map.put("MD", array_MD);
		map.put("MP", array_MP);
		map.put("MA", array_MA);
		return map;
	}

	// 初始化操作内容表
	private static Map<String, String> initCompanyTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ET", "一般企业");
		map.put("BT", "银行");
		map.put("ST", "证券");
		map.put("IT", "保险");
		map.put("SY", "政府的事业单位");
		map.put("ZF", "政府");
		map.put("DB", "多边发展银行");
		return map;
	}
}
