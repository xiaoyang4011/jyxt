package crds.pub.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * CRDS系统函数库,用于配置crdsFunctions.tld文件,便于页面采用EL表达式调用
 * @version : 1.0
 * @author : houtingsong(MaCi Hotesion)
 * @date : Jun 9, 2009 12:08:20 PM
 * @email : houtingsong163@163.com
 */
public class CRDSFunctions {
	/**
	 * 格式化一个字符串数字,当参数divNumber和mulNumber同时输入时,divNumber计算优先,忽略mulNumber的计算,即参数divNumber和mulNumber只能传一个或都不传.
	 * @param number 将要被格式化的字符串数字,如果为null或"",则为0;
	 * @param divNumber 除数字符串数字, 如果不为空,则将number除以divNumber运算之后再进行格式化
	 * @param mulNumber 乘数字符串数字, 如果不为空,则将number乘以mulNumber运算之后再进行格式化
	 * @param pointSize 要保留的小数点位数
	 * @param isSeparated 整数部分是否进行逗号分割,1[分割],0[不分割],如1,234,882.001;
	 * @param isFilledZero 当小数点实际位数不足pointSize时,是否在后面补0,1[补0],0[不补0];
	 * @param nezProcess 整数number为0、""、null时的处理,0[""、null时返回"",0时返回0],1[""、null时返回0,0时返回""],2[0、null、""视为""]、3[0、null、""视为"0"];
	 * @param divZeroProcess 当除数为0时的处理,N-(不做乘积计算,但继续做格式化操作),Y-(继续做乘积计算,然后做格式化操作),其它值-(返回这个值);当且仅当参数divNumber="0"时,此参数有效
	 * ex:<br>
	 * numberFormat("0","","",8,0,0,3,0)			return 0;//数字部分为空或0则当作0来处理,必须有8位小数点,不足8位则不追加0<br>
	 * numberFormat("","","",8,1,0,3,0)				return 0.00000000;//数字部分为空或0则当作0来处理,必须有8位小数点,不足8位则追加0<br>
	 * numberFormat("0","","",8,0,0,2,0)			return 0;//数字部分无论是0或空则返回""<br>
	 * numberFormat("","","",8,0,0,2,0)				return 0;//数字部分无论是0或空则返回""<br>
	 * numberFormat("0","","",8,0,0,1,0)			return 0;//数字部分为0则返回""<br>
	 * numberFormat("","","",8,0,1,0,0)				return 0;//数字部分为空则当作""<br>
	 * numberFormat("4123.012","","",8,0,1,2,0)		return 4123.01200000;//必须有8位小数点,不足8位则追加0<br>
	 * numberFormat("4123.012","","",8,0,0,2,0)		return 4123.012;//8位小数点,不足8位则不追加0<br>
	 * numberFormat("4123.012","","",8,1,1,2,0)		return 4,123.01200000;//整数部分以逗号分割,8位小数点,不足8位则追加0<br>
	 * numberFormat("4123.012","","",8,1,0,2,0)		return 4,123.012;//整数部分以逗号分割,8位小数点,不足8位则不追加0<br>
	 * numberFormat("4123.012","100","",8,1,0,2,0)	return 41.23012;//除以100,整数部分以逗号分割,8位小数点,不足8位则不追加0<br>
	 * numberFormat("4123.012","100","",8,1,1,2,0)	return 41.23012000;//除以100,整数部分以逗号分割,8位小数点,不足8位则追加0<br>
	 * numberFormat("4123.012","","100",8,1,0,2,0)	return 412301.2;//乘以100,整数部分以逗号分割,8位小数点,不足8位则不追加0<br>
	 * numberFormat("4123.012","","100",8,1,1,2,0)	return 412301.20000000;//乘以100,整数部分以逗号分割,8位小数点,不足8位则追加0<br>
	 */
	public static String numberFormat(String number,String divNumber,String mulNumber,int pointSize,int isSeparated,int isFilledZero,int nezProcess,String divZeroProcess){
		String format = isSeparated==1 ? ",###" : "###";//5667
		number = null == number ? "":number.trim();
		if((number.length() > 0 && new BigDecimal(number).doubleValue()==0 && nezProcess==1) || (number.equals("") && nezProcess==0) || ((number.equals("")||(number.length() > 0 && new BigDecimal(number).doubleValue()==0))&& nezProcess==2))
			return "";
		else if(number.equals(""))
			number = "0";
		if(Double.parseDouble(number)==0.5)
			number = "0.50000000001";//0.5时,四舍五入不精确,特处理,如:0.5四舍五入后为1,但处理结果为0。
		
		BigDecimal big = new BigDecimal(number);
		if(-1 < big.doubleValue() && big.doubleValue() < 1)
			format += "0";
		String pointText = extendStr(pointSize,isFilledZero==1 ? "0":"#");
		format += pointText.length()>0 ? ("."+pointText) : "";
		//被乘除数处理
		divNumber = divNumber == null?"" : divNumber.trim();
		mulNumber = mulNumber == null?"" : mulNumber.trim();
		if(!"".equals(mulNumber)){
			if(!"".equals(divNumber)){
				if(Double.parseDouble(divNumber)==0){//除数未0时的处理
					if("Y".equals(divZeroProcess))//除数为0时是否继续作乘积计算
						big = big.multiply(new BigDecimal(mulNumber));
					else if(!"N".equals(divZeroProcess))
						return divZeroProcess;
				} else {//除数不为0则同时做乘除计算
					big = big.multiply(new BigDecimal(mulNumber));
					big = big.divide(new BigDecimal(divNumber),20,BigDecimal.ROUND_HALF_UP);
				}
			} else {//未传入除数则只做乘积计算
				big = big.multiply(new BigDecimal(mulNumber));
			}
		} else if(!"".equals(divNumber)){
			if(Double.parseDouble(divNumber)!=0)
				big = big.divide(new BigDecimal(divNumber),20,BigDecimal.ROUND_HALF_UP);
			else if(!"Y".equals(divZeroProcess) && !"N".equals(divZeroProcess))
				return divZeroProcess;
		}
		return new DecimalFormat(format).format(big);
	}
	
	/**
	 * 格式化一个字符串数字,当参数divNumber和mulNumber同时输入时,divNumber计算优先,忽略mulNumber的计算,即参数divNumber和mulNumber只能传一个或都不传.
	 * @param number 将要被格式化的字符串数字,如果为null或"",则为0;
	 * @param pointSize 要保留的小数点位数
	 * @param isSeparated 整数部分是否进行逗号分割,1[分割],0[不分割],如1,234,882.001;
	 * @param isFilledZero 当小数点实际位数不足pointSize时,是否在后面填补0,1[补0],0[不补0];
	 * @param intZeroProcess 整数number为0、""、null时的处理,0[""、null时返回""],1[0变为"",返回""],2[0、null、""视为""]、3[0、null、""视为"0"];
	 * ex:<br>
	 * decimalFormat("-0",8,0,1,2)		return 0.00000000;//必须有8位小数点,不足8位则追加0<br>
	 * decimalFormat("-0",8,0,0,2)		return 0;//必须有8位小数点,不足8位则不追加0<br>
	 * decimalFormat("-0",8,0,0,1)		return "";//必须有8位小数点,不足8位则不追加0<br>
	 * decimalFormat("",8,0,0,0)		return "";//必须有8位小数点,不足8位则不追加0<br>
	 * decimalFormat("4123.012",8,0,1,1)	return 4123.01200000;//必须有8位小数点,不足8位则追加0<br>
	 * decimalFormat("4123.012",8,0,0,1)	return 4123.012;//8位小数点,不足8位则不追加0<br>
	 * decimalFormat("4123.012",8,1,1,1)	return 4,123.01200000;//整数部分以逗号分割,8位小数点,不足8位则追加0<br>
	 * decimalFormat("4123.012",8,1,0,1)	return 4,123.012;//整数部分以逗号分割,8位小数点,不足8位则不追加0<br>
	 */
	public static String decimalFormat(String number,int pointSize,int isSeparated,int isFilledZero,int intZeroProcess){
		return numberFormat(number,null,null,pointSize,isSeparated,isFilledZero,intZeroProcess,"");
	}
	
	/**
	 * @param value 数值
	 * @param scale 小数位数
	 * @param isSeparated 0-不对整数进行逗号分割,1-对整数进行逗号分割,2-不对整数进行逗号分割但必须有指定的小数位数,3-对整数进行逗号分割且必须有指定的小数位数
	 * @return
	 */
	public static String fmt(String value,int scale,int isSeparated){
		if(null == value || (value=value.trim()).length()==0)
			return "";
		try{
			if(Double.parseDouble(value)==0.5)
				value = "0.5000000000000001";//0.5时,四舍五入不精确,特此处理,如:0.5四舍五入后为1,但处理结果为0
			BigDecimal big = new BigDecimal(value);
			String format = isSeparated==1||isSeparated==3 ?",###":"###";
			if(-1 < big.doubleValue() && big.doubleValue() < 1)
				format += "0";
			String point = extendStr(scale,isSeparated==2||isSeparated==3?"0":"#");
			format += point.length()==0 ? "" : ("."+point);
			return new java.text.DecimalFormat(format).format(big);
		} catch (Exception e){
			return "";
		}
	}
	/**
	 * 将一个字符串扩展为number个字符串,然后返回扩展之后的字符串.如果beExtendStr为null则返回null;
	 * @param number 要扩展的数目
	 * @param beExtendStr 将要被扩展的字符串
	 * @author MaCi Hotesion<br>
	 * ex:<br>
	 * extendStr(5,null);	                //throws NullPointerException<br>
	 * extendStr(5,"") 		return ""; <br>
	 * extendStr(5,"#") 	return "#####";<br>
	 * extendStr(3,"234") 	return "234234234";<br>
	 */
	public static String extendStr(int number,String beExtendStr){
		if(number <= 0 || null == beExtendStr || beExtendStr.length()==0)
			return "";
		StringBuffer extendSB = new StringBuffer();
		for (int i = 0; i < number; i++)
			extendSB.append(beExtendStr);
		return extendSB.toString();
	}
	public static String NEVL(String value,String nullValue){
		return NEVL2(value,nullValue,value);
	}
	public static String NEVL2(String value,String nullValue,String notNullValue){
		return value==null || value.length()==0 ? nullValue:notNullValue;
	}
}
