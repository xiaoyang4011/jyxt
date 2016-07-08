package crds.pub.util;

import java.util.Arrays;
/**
 * 自定义函数
 */
public class CustomMethod {
	
	// 是否为空
	public static String nvl(String value, String emptyDefault,
			String notEmptyDefault) {
		notEmptyDefault = notEmptyDefault == null ? value : notEmptyDefault;
		return value == null ? emptyDefault : notEmptyDefault;
	}

	/**
	 * 比较函数 等于
	 * @param 
	 * basic_num：被比较数 compare_num：比较数 true_return_val：结果为true时返回数 false_return_val：结果为false时返回数
	 * @return true_return_val or false_return_val
	 */
	public static double eq(double basic_num,double compare_num,double true_return_val,double false_return_val){
		return basic_num==compare_num?true_return_val:false_return_val;
	}
	/**
	 * 比较函数 大于
	 * @param 
	 * basic_num：被比较数 compare_num：比较数 true_return_val：结果为true时返回数 false_return_val：结果为false时返回数
	 * @return true_return_val or false_return_val
	 */
	public static double gt(double basic_num,double compare_num,double true_return_val,double false_return_val){
		return basic_num>compare_num?true_return_val:false_return_val;
	}
	/**
	 * 比较函数 小于
	 * @param 
	 * basic_num：被比较数 compare_num：比较数 true_return_val：结果为true时返回数 false_return_val：结果为false时返回数
	 * @return true_return_val or false_return_val
	 */
	public static double lt(double basic_num,double compare_num,double true_return_val,double false_return_val){
		return basic_num<compare_num?true_return_val:false_return_val;
	}
	/**
	 * 比较函数 大于等于
	 * @param 
	 * basic_num：被比较数 compare_num：比较数 true_return_val：结果为true时返回数 false_return_val：结果为false时返回数
	 * @return true_return_val or false_return_val
	 */
	public static double gte(double basic_num,double compare_num,double true_return_val,double false_return_val){
		return basic_num>compare_num?true_return_val:false_return_val;
	}
	/**
	 * 比较函数 小于等于
	 * @param 
	 * basic_num：被比较数 compare_num：比较数 true_return_val：结果为true时返回数 false_return_val：结果为false时返回数
	 * @return true_return_val or false_return_val
	 */
	public static double lte(double basic_num,double compare_num,double true_return_val,double false_return_val){
		return basic_num>compare_num?true_return_val:false_return_val;
	}
	//取最小数
	public static double least(double...args){
		Arrays.sort(args);
		return args[0];
	}
	//取最大数
	public static double greatest(double...args){
		Arrays.sort(args);
		return args[args.length-1];
	}


}