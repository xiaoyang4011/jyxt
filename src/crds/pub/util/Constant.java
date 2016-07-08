package crds.pub.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @specification :
 * @version : 1.0
 * @author : houtingsong(MaCi Hotesion)
 * @date : Jun 24, 2009 3:37:37 PM
 * @email : houtingsong163@163.com
 */
public final class Constant implements ConstantSupport,Serializable {
	private static Logger logger = Logger.getLogger(Constant.class);
	private static final long serialVersionUID = 1L;
	
	/** 取得用户及发行人信息 */
	public static FormUserOperation getFormUserOperation(HttpServletRequest request){
		return getFormUserOperation(request.getSession());
	}
	
	/** 取得用户及发行人信息 */
	public static FormUserOperation getFormUserOperation(HttpSession session){
		return (FormUserOperation) session.getAttribute(LoginUserSessionKey);
	}
	
	/** 将用户及发行人信息存入到session中 */
	public static void setFormUserOperation(HttpServletRequest request,FormUserOperation formUserOperation){
		setFormUserOperation(request.getSession(),formUserOperation);
	}
	
	/** 将用户及发行人信息存入到session中 */
	public static void setFormUserOperation(HttpSession session,FormUserOperation formUserOperation){
		session.removeAttribute(LoginUserSessionKey);
		session.setAttribute(LoginUserSessionKey,formUserOperation);
	}
	
	/**
	 * 判断内部行业的级别,1-一级,2-二级,3-三级,4-四级,-1-未知级别.
	 * @param industryCode
	 * @return
	 */
	public static int internalIndustryGrade(String industryCode) {
		if(null != industryCode && (industryCode = industryCode.trim()).length()==8){
			if(industryCode.endsWith("000000")){//内部行业一级
				return 1;
			} else if(industryCode.endsWith("0000")){//内部行业二级
				return 2;
			} else if(industryCode.endsWith("00")){//内部行业三级
				return 3;
			} else if(industryCode.matches("^[1-9]\\d{7}$")){//内部行业四级
				return 4;
			}
		}
		logger.warn("错误的内部行业代码:"+industryCode+",不能根据此代码判定内部行业级别.");
		return -1;
	}
	
	/** 判断字符串是否为null或"" */
	public static boolean isEmpty(Object object){
		return isNull(object) || (object.getClass().isArray() && Array.getLength(object)==0) || (object instanceof Collection<?> && ((Collection<?>)object).size()==0) || (object instanceof Map<?,?> && ((Map<?,?>)object).size() == 0) || (object instanceof CharSequence && ((CharSequence)object).length()==0);
	}
	/** 判断数组中的字符串是否都null或空字符串 */
	public static boolean isEmptys(String ... array){
		if(!isNull(array) && array.length>0){
			for (String string : array){
				if(isEmpty(string))
					return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * 判断参数obj是否等于array中的其中一个,只要有一个等于则返回true
	 * @param obj
	 * @param array
	 * @return
	 */
	public static <T> boolean isEqsAny(T obj, T...array ){
		if(obj==null){
			if(array==null){
				return true;
			} else {
				for (Object object : array) {
					if(object == null) return true;
				}
				return false;
			}
		} else {
			if(array==null){
				return false;
			} else {
				for (Object object : array) {
					if(obj.equals(object)) return true;
				}
				return false;
			}
		}
	}
	/**
	 * 只要参数obj等于数组array中的其中一个就返回eqDefault,否则返回neqDefault.
	 * @param <T>
	 * @param obj
	 * @param eqDefault
	 * @param neqDefault
	 * @param array
	 * @return
	 */
	public static <T> T eqsAnyDefault(T obj, T eqDefault, T neqDefault,T...array){
		return isEqsAny(obj,array)?eqDefault:neqDefault;
	}
	/**
	 * 如果字符串eqString等于eqVal则返回eqDefaultVal,否则返回eqString
	 * @param eqString
	 * @param eqVal
	 * @param eqDefaultVal
	 * @return
	 */
	public static <T> T eqDefault(T eqString, T eqVal,T eqDefaultVal){
		return eqDefault(eqString,eqVal,eqDefaultVal,eqString);
	}
	/**
	 * 如果字符串eqString等于eqVal则返回eqDefaultVal,否则返回neqDefaultVal
	 * @param eqString
	 * @param eqVal
	 * @param eqDefaultVal
	 * @param neqDefaultVal
	 * @return
	 */
	public static <T> T eqDefault(T eqString, T eqVal,T eqDefaultVal,T neqDefaultVal){
		if(eqString==null)
			return eqVal == null ? eqDefaultVal : neqDefaultVal;
		return eqString.equals(eqVal) ? eqDefaultVal : neqDefaultVal;
	}
	
	/**
	 * 如果字符串nnteString等于[null,"","  ",null","NULL"]则返回eqDefaultVal,否则返回nnteString
	 * @param nnteString
	 * @param defaultValue
	 * @return
	 */
	public static String nnetDefault(String nnteString, String defaultValue){
		return nnetDefault(nnteString,defaultValue,nnteString);
	}
	/**
	 * 如果字符串nnteString等于[null,"","  ","null","NULL"]则返回eqDefaultVal,否则返回nDefaultValue
	 * @param nnteString
	 * @param defaultValue
	 * @param nDefaultValue
	 * @return
	 */
	public static String nnetDefault(String nnteString, String defaultValue, String nDefaultValue){
		return trimIsEmpty(nnteString) || "null".equalsIgnoreCase(nnteString.trim()) ? defaultValue : nDefaultValue;
	}
	/**
	 * 如果字符串nneString等于[null,"","null","NULL"]则返回eqDefaultVal,否则返回nneString
	 * @param nneString
	 * @param defaultValue
	 * @return
	 */
	public static String nneDefault(String nneString, String defaultValue){
		return nneDefault(nneString,defaultValue,nneString);
	}
	/**
	 * 如果字符串nneString等于[null,"","null","NULL"]则返回eqDefaultVal,否则返回nDefaultValue
	 * @param nneString
	 * @param defaultValue
	 * @param nDefaultValue
	 * @return
	 */
	public static String nneDefault(String nneString, String defaultValue, String nDefaultValue){
		return isEmpty(nneString) || "null".equalsIgnoreCase(nneString) ? defaultValue : nDefaultValue;
	}
	
	/** 判断字符串是否为null或去掉两边的空格之后是否为"" */
	public static boolean trimIsEmpty(String string){
		return isNull(string) || string.trim().length()==0;
	}
	
	/**
	 * 判空赋值,如果参数emptyStr为null或"",则返回默认值defaultValue
	 * @param emptyStr 被检验是否为空的字符串
	 * @param defaultValue 默认值
	 * ex:
	 * emptyDefault("","123");return "123";
	 * emptyDefault("  ","123");return "  ";
	 * emptyDefault(null,"4444");return "4444";
	 */
	public static String emptyDefault(String emptyStr,String defaultValue){
		return emptyDefault(emptyStr, defaultValue, emptyStr);
	}
	
	public static String emptyDefault(String emptyStr,String emptyDefaultValue,String notEmptyDefaultValue){
		return isEmpty(emptyStr) ? emptyDefaultValue : notEmptyDefaultValue;
	}
	
	/**
	 * 判空赋值,如果参数emptyStr为null或""或者"    ",则返回默认值defaultValue
	 * @param emptyStr 被检验是否为空的字符串
	 * @param defaultValue 默认值
	 * ex:
	 * trimEmptyDefault("","123");return "123";
	 * trimEmptyDefault("  ","123");return "123";
	 * trimEmptyDefault(null,"4444");return "4444";
	 */
	public static String trimEmptyDefault(String emptyStr,String defaultValue){
		return trimEmptyDefault(emptyStr, defaultValue,emptyStr);
	}
	
	public static String trimEmptyDefault(String emptyStr,String emptyDefaultValue,String notEmptyDefaultValue){
		return trimIsEmpty(emptyStr) ? emptyDefaultValue : notEmptyDefaultValue;
	}
	/**
	 * 求取一系列数据的平均值、总和、标准偏差...具体视参数calModel的取值而定.
	 * @param calModel 必选项,计算模式,字符串类型,取值为:SUM[默认]、AVG、OV、AOSD、OSD、SV、ASSD、SSD、RSD、AD、RAD.
	 *     SUM(sum 求和[默认]),
	 *     AVG(Average 平均值),
	 *     OV(Overall Variance 总体方差),
	 *     AOSD(Average Overall Standard Deviation 平均总体标准偏差),
	 *     OSD(Overall Standard Deviation 总体标准偏差),
	 *     SV(Sample Variance 样本方差),
	 *     ASSD(Average Sample Standard Deviation 平均样本标准偏差),
	 *     SSD(Sample Standard Deviation 样本标准偏差)stddev,
	 *     RSD(Relative Standard Deviation 相对标准偏差),
	 *     AD(Average Deviation 平均偏差),
	 *     RAD(Relative Average Deviation 相对平均偏差)
	 * @return Number
	 */
	public static double numberArraySummary(double[] numbers,String calModel){
		if(numbers == null || numbers.length==0) return 0;
		double sum = 0;
		int count = numbers.length;
		calModel = null == calModel || (calModel=calModel.trim()).length()==0?"SUM":calModel;
		for (double d : numbers) sum += d;
		//根据计算模式返回相应的结果
		if("SUM".equals(calModel)){//求和
			return sum;
		} else {
			double avgNum = sum/count;//平均值
			if("AVG".equals(calModel)){//求平均值
				return avgNum;
			} else {
				double avgSum = 0;
				double avgDevSum = 0;
				for (double d : numbers){
					avgSum += Math.pow(d-avgNum,2);//此项求和便于求[方差、其它标准偏差]
					avgDevSum += Math.abs(d-avgNum);//此项求和便于求[平均偏差]
				}
				if("OV".equals(calModel)) {//总体方差
					return avgSum/count;
				} else if("AOSD".equals(calModel)) {//平均总体标准偏差
					return Math.sqrt(avgSum/(count*count));
				} else if("OSD".equals(calModel)) {//总体标准偏差
					return Math.sqrt(avgSum/count);
				} else if("SV".equals(calModel)) {//样本方差
					return count>1?avgSum/(count-1) : 0;
				} else if("ASSD".equals(calModel)) {//平均样本标准偏差
					return count>1?Math.sqrt(avgSum/(count*(count-1))) : 0;
				} else if("SSD".equals(calModel)) {//样本标准偏差
					return count>1?Math.sqrt(avgSum/(count-1)) : 0;
				} else if("RSD".equals(calModel)) {//相对标准偏差
					return count>1 ? Math.sqrt(avgSum/(count-1))/avgNum : 0;
				} else if("AD".equals(calModel)) {//平均偏差
					return avgDevSum/count;
				} else if("RAD".equals(calModel)) {//相对平均偏差
					return (avgDevSum/count)/avgNum;
				} else {
					return avgSum;
				}
			}
		}
	}
	
	/**
	 * 取出一系列数据中的最大值
	 * @param numbers
	 * @return
	 */
	public static double greatest(double...numbers){
		if(numbers == null || numbers.length==0) return 0;
		double maxDouble = 0;
		for (double d : numbers) maxDouble = (maxDouble < d) ? d : maxDouble;
		return maxDouble;
	}
	/**
	 * 取出一系列数据中的最小值
	 * @param numbers
	 * @return
	 */
	public static double least(double...numbers){
		if(numbers == null || numbers.length==0) return 0;
		double minDouble = 0;
		for (double d : numbers) minDouble = (minDouble > d) ? d : minDouble;
		return minDouble;
	}
	/**
	 * 如果某一个对象为null,则返回一个默认值
	 * @param nullObj 被判断是否为null的对象
	 * @param defaultValue 将要被返回的默认值.
	 * @return 
	 */
	public static <T> T nullDefault(T nullObj,T defaultValue){
		return nullDefault(nullObj, defaultValue,nullObj);
	}
	public static <T> T nullDefault(T nullObj,T nullDefaultValue, T notNullDefaultValue){
		return isNull(nullObj) ? nullDefaultValue : notNullDefaultValue;
	}
	
	/**
	 * 如果某一个字符串数组中的值为null,则将其赋值为""
	 * @param array 被判断是否为null的字符串数组对象
	 * @return 
	 */
	public static String[] nullsToEmpty(String...array){
		if(hasSize(array)){
			for (int i = 0; i < array.length; i++) {
				array[i] = nullToEmpty(array[i]);
			}
		}
		return array;
	}

	/**
	 * 如果某一个字符串的值为null,则将其赋值为""
	 * @param array 被判断是否为null的字符串数组对象
	 * @return 
	 */
	public static String nullToEmpty(String str){
		return nullDefault(str,"");
	}
	/**
	 * 判断某一个对象是否为null;
	 */
	public static <T> boolean isNull(T object){
		return null == object;
	}
	/** 判断数组、Collection、Map、String中是否有值 */
	public static boolean hasSize(Object object){
		if(!isNull(object)){
			if(object.getClass().isArray())
				return Array.getLength(object) > 0;
			else if(object instanceof Collection)
				return ((Collection<?>)object).size() > 0;
			else if(object instanceof Map)
				return ((Map<?,?>)object).size() > 0;
			else if(object instanceof CharSequence)
				return ((CharSequence) object).length() > 0;
			else
				return true;
		}
		return false;
	}
	/**
	 * 将字符串数组转换为字符串,有必要的情况下可用字符串spy将每个元素分割组合为新的字符串.<br>
	 * 如:<br>
	 * (1) "'"+arraysToString("','","1#2#3#4".split("#"))+"'" 得到的结果为:"'1','2','3','4'"<br>
	 * (2) "'"+arraysToString(null,"1#2#3#4".split("#"))+"'"  得到的结果为:"'1234'"<br>
	 * (3) arraysToString("#",null)                           得到的结果为:""
	 * @param spy 分隔符
	 * @param array 字符串数组
	 * @return
	 */
	public static String arraysToString(String spy, String...array){
		if(array != null && array.length > 0){
			StringBuffer sb = new StringBuffer();
			spy= spy==null ? "":spy;
			for (String str : array) {
				sb.append(spy).append(str);
			}
			return sb.substring(spy.length());
		}
		return "";
	}
	/**
	 * 将字符串数组转换为字符串,有必要的情况下可用字符串spy将每个元素分割组合为新的字符串,最后再用字符串bothSidesStr作为两边的字符串与组合后的字符串串联起来.<br>
	 * 如:<br>
	 * (1) arraysToString("'","','","1#2#3#4".split("#")) 得到的结果为:"'1','2','3','4'"<br>
	 * (2) arraysToString("#","','","1#2#3#4".split("#")) 得到的结果为:"#1','2','3','4#"<br>
	 * (3) arraysToString("'",null,"1#2#3#4".split("#"))  得到的结果为:"'1234'"<br>
	 * (4) arraysToString("$",null,"1#2#3#4".split("#"))  得到的结果为:"$1234$"<br>
	 * (5) arraysToString("'","#",null)                   得到的结果为:"''"<br>
	 * (6) arraysToString("$","#",null)                   得到的结果为:"$$"
	 * @param bothSidesStr 两边的分隔字符
	 * @param midSpy 中间的分隔字符
	 * @param array 字符串数组
	 * @return
	 */
	public static String arraysToString(String bothSidesStr,String midSpy, String...array){
		bothSidesStr = nullDefault(bothSidesStr, "");
		return bothSidesStr+arraysToString(midSpy,array)+bothSidesStr;
	}
	/**
	 * 格式化某个日期对象date为指定的格式fmt
	 * @param date
	 * @param fmt 如果为空则默认为 {@link Constant#dateFormat0}
	 * @return
	 */
	public static String fmtDate(Date date, String fmt){
		return new SimpleDateFormat(null != fmt && (fmt=fmt.trim()).length() > 0 ? fmt : dateFormat0).format(date);
	}
	/** 获取系统的当前时间 */
	public static Date getCurrDate() {
		return Calendar.getInstance().getTime();
	}
	/** 获取系统的当前时间,并按照指定的格式返回 */
	public static String getCurrDate(String format){
		return fmtDate(getCurrDate(),format);
	}
	/** 获取系统的当前时间,并按照默认的格式"yyyy-MM-dd"返回 */
	public static String getCurrDateDefault(){
		return getCurrDate(dateFormat0);
	}
	/**将一个字符串日期strDate转换为日期Date对象,注:参数字符串日期必须符合格式:yyyy-MM-dd*/
	public static Date toDate(String strDate){
		try {
			return new SimpleDateFormat(dateFormat0).parse(strDate);
		} catch (ParseException e) {
			logger.warn("传入的参数字符串日期:"+strDate+"不符合格式:"+dateFormat0+",系统将返回当前日期.");
			return getCurrDate();
		}
	}
	/**将一个字符串日期strDate按照指定的格式dateFmt转换为日期Date对象*/
	public static Date toDate(String strDate,String dateFmt){
		try {
			return new SimpleDateFormat(dateFmt).parse(strDate);
		} catch (ParseException e) {
			logger.warn("传入的参数字符串日期:"+strDate+"不符合格式:"+dateFmt+",系统将返回当前日期.");
			return getCurrDate();
		}
	}
	/**将一个字符串日期strDate转换为日期Calendar对象,注:参数字符串日期必须符合格式:yyyy-MM-dd*/
	public static Calendar toCalendar(String strDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate(strDate));
		return cal;
	}
	/**将一个字符串日期strDate按照指定的格式dateFmt转换为日期Calendar对象*/
	public static Calendar toCalendar(String strDate,String dateFmt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate(strDate,dateFmt));
		return cal;
	}
	/** 判断某个角色roleId是否是系统基本角色 */
	public static boolean isSystemBasicRole(String roleId){
		for(String[] id : ROLE){
			if(id[0].equals(roleId))
				return true;
		}
		return false;
	}
	/** 将CLOB对象转换为字符串 */
	public static String clobToString(Clob clob) {
		if(null == clob)
			return "";
		try {
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			String readLine = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (readLine != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(readLine);
				readLine = br.readLine();
			}
			return sb.toString();
		} catch (SQLException e) {
			return "";
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * @param sel
	 * @param flag 0-[高于,低于],1-[大于,小于]
	 * @return
	 */
	public static String condSelToName(String sel,int flag){
		if(condSelJudge(sel,0)){
			return "全部";
		} else if(condSelJudge(sel,1)){
			return flag==0?"高于":"大于";
		} else if(condSelJudge(sel,2)){
			return flag==0?"低于":"小于";
		} else if(condSelJudge(sel,3)){
			return "介于";
		}
		return "请选择";
	}
	
	/**
	 * 条件选择判断
	 * @param sel 选择的值
	 * @param flag 0-all, 1-min, 2-max, 3-between
	 * @return
	 */
	public static boolean condSelJudge(String sel,int flag){
		if(flag==1){
			return "min".equalsIgnoreCase(sel);
		} else if(flag==2){
			return "max".equalsIgnoreCase(sel);
		} else if(flag==3){
			return "between".equalsIgnoreCase(sel);
		}
		return "all".equalsIgnoreCase(sel) || "na".equalsIgnoreCase(sel) || "".equalsIgnoreCase(sel) || null==sel;
	}
	
	/**
	 * @param array    数组对象
	 * @param row      定位起始行位置
	 * @param startCol 定位起始列位置
	 * @param text     文字描述
	 * @param selCond  所选条件(all|min|max|between)
	 * @param flag     根据所选条件值为min和max时,筛选所选条件对应的文字.0-[高于,低于],1-[大于,小于]
	 * @param min      最小值
	 * @param max      最大值
	 * @param unit     计量单位
	 */
	public static void toExcelSetArray(String[][] array, int row, int startCol, String text,String selCond, int flag, String min,String max,String unit){
		array[row][startCol++] = text;
		array[row][startCol++] = condSelToName(selCond,flag);
		if(condSelJudge(selCond,1)){
			array[row][startCol++] = Constant.nullDefault(min, "");
		} else if(condSelJudge(selCond,2)){
			array[row][startCol++] = Constant.nullDefault(max, "");
		} else if(condSelJudge(selCond,3)){
			array[row][startCol++] = Constant.nullDefault(min, "");
			array[row][startCol++] = Constant.nullDefault(max, "");
		}
		if(!condSelJudge(selCond,0) && !Constant.isEmpty(unit))
			array[row][startCol++] = unit;
	}
	/**
	 * @param array    数组对象
	 * @param row      定位起始行位置
	 * @param startCol 定位起始列位置
	 * @param text     文字描述
	 * @param val      值
	 * @param unit     计量单位
	 */
	public static void toExcelSetArray(String[][] array, int row, int startCol, String text,String val,String unit){
		array[row][startCol++] = text;
		array[row][startCol++] = val;
		if(!Constant.isEmpty(unit))
			array[row][startCol++] = unit;
	}
	
	/**
	 * 
	 * @param numValue 值,为null或发生异常时返回""
	 * @param scale 小数点位数
	 * @return
	 */
	public static String toFloat(String numValue,int scale,String nullTrimEmptyDefaul){
		if(null == numValue || numValue.trim().length()==0)
			return nullTrimEmptyDefaul;
		try {
			BigDecimal big = new BigDecimal(numValue);
			String format = "###";
			if(-1 < big.doubleValue() && big.doubleValue() < 1)
				format += "0";
			String point = CRDSFunctions.extendStr(scale,"#");
			format += point.length()==0 ? "" : ("."+point);
			return new java.text.DecimalFormat(format).format(big);
		} catch (Exception e) {
			logger.info("参数错误，非浮点型数据:"+numValue);
			return nullTrimEmptyDefaul;
		}
	}
	/**
	 * 
	 * @param numValue 值,为null或发生异常时返回""
	 * @param scale 小数点位数
	 * @return
	 */
	public static String toFloat(String numValue,int scale){
		return toFloat(numValue,scale,"");
	}
	/**
	 * 对".数字"之类的数据前面置0.
	 * 如: 
	 * pointAfterZero(".123") return "0.123";
	 * pointAfterZero("123")  return "123";
	 * @param data
	 * @return
	 */
	public static String toFloat(String data){
		return toFloat(data,40);
	}
	public static boolean isOpenURL(String accURL){
		for(String url:openURL){
			if(accURL.indexOf(url)>=0)
				return true;
		}
		return false;
	}
	//======================================================================================
	//流程角色
	public static String[] EVALUATION_FLOW;
	//回退时候的流程角色 
	public static String[] EVALUATION_RETURN_FLOW;
}
