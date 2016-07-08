package crds.pub.util;

public abstract class SystemParamConfig {
	/** 公司规模表信息添加的最多记录 */
	public final static int companySizeAddMaxRecord = 100;
	/** 外部评级最多添加的记录 */
	public final static int externalRateAddMaxRecord = 50;
	/** 是否进行财务预警 0[不预警] 1[预警] */
	public final static int isFinancialWarning = 1;
}
