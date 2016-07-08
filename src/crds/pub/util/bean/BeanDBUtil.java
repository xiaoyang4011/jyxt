/*
 * FileName:   BeanDBUtil.java
 * Founder:    MaciHotesion
 * Copyright:  Copyright 2007-2008 Hotesion.
 * Description: 
 * ********************** modify log **********************
 * Mender: 
 * Modify Date: 
 * Modify Range: 
 */
package crds.pub.util.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2006<br>
 *
 * @author MaCi Hotesion
 * @since JDK 1.6.0_03
 */
@SuppressWarnings("unchecked")
public class BeanDBUtil extends BeanUtil {
	static Logger logger = Logger.getLogger(BeanDBUtil.class);	
	public static final String SQL_PARAM_LIST = "sql_param_list";
	public static final String INSERT_SQL="insert_sql";
	
	private BeanDBUtil(){}

	/**
	 * 将一个bean对象生成一条插入的字符串SQL语句.此方法与{@link #insertSQLByParam(String, Object, boolean, String, Map)}不同,区别在于此方法生成的SQL语句直接是一条插入的SQL语句,此SQL语句中已经附带有值.这种方式针对表结构字段全为varchar2类型的字段特别有用.
	 * @param tableName 表名称
	 * @param bean bean对象
	 * @param getFlag 获取标识,是否获取父类的属性字段,true[获取],false[不获取]
	 * @param excludedFiledName 要排除的字段名称字符串,要排除多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将不会被返回
	 * @param warnProcessFiledNameMap 待处理字段,详情请参阅{@link BeanUtil#processObjectValue(Map, Map)}
	 * @return 返回的就是一条插入的SQL语句字符串，如:INSERT INTO tableName (field1,field2) values ('value1','value2')<br>
	 */
	public static String insertSQLString(String tableName, Object bean, boolean getFlag, String excludedFiledName, Map<String,BeanProc> warnProcessFiledNameMap) {
    	Map<String,Object> mapInfo = getFiledNamesAndValues(bean, getFlag, excludedFiledName, warnProcessFiledNameMap);
    	if(null != mapInfo){
    		tableName = null == tableName ? getBeanName(bean): tableName;
    		StringBuffer insertSQL = new StringBuffer("INSERT INTO " ).append(tableName);
    		StringBuffer insertFiledSQL = new StringBuffer();
    		StringBuffer insertValuesSQL = new StringBuffer();
    		Set<String> filedSet = mapInfo.keySet();
    		for (String fieldName : filedSet) {
    			Object value = mapInfo.get(fieldName);
    			if(null != value){
	    			insertFiledSQL.append(",").append(fieldName);
	    			insertValuesSQL.append(",'").append(valueProcess(value)).append("'");
    			}
    		}
    		//如果有值则从第1个字符串开始截取
    		if(insertFiledSQL.length()>0){
    			insertSQL.append("(").append(insertFiledSQL.substring(1)).append(") ");
    			insertSQL.append(" VALUES(").append(insertValuesSQL.substring(1)).append(") ");
    			return insertSQL.toString();
    		} else {
    			logger.info(bean.getClass().getName()+":No object property values, can not constitute insert SQL statement.");
    			return null;
    		}
    	}
    	return null;
    }

	/**
	 * 此方法相当于调用{@link #insertSQLString(tableName, bean, getFlag, excludedFiledName,null)},详情请参阅{@link #insertSQLString(String, Object, boolean, String, Map))};
	 * @return Map<String,Object>
	 */
	public static String insertSQLString(String tableName, Object bean,boolean getFlag, String excludedFiledName) {
    	return insertSQLString(tableName, bean, getFlag, excludedFiledName,null);
    }

	/**
	 * 此方法相当于调用{@link #insertSQLString(tableName, bean, getFlag, null, null)},详情请参阅{@link #insertSQLString(String, Object, boolean, String, Map))};
	 * @return Map<String,Object>
	 */
	public static String insertSQLString(String tableName, Object bean,boolean getFlag) {
    	return insertSQLString(tableName, bean, getFlag, null, null);
    }

	/**
	 * 此方法相当于调用{@link #insertSQLString(null, bean, getFlag,null, null)},详情请参阅{@link #insertSQLString(String, Object, boolean, String, Map))};
	 * @return Map<String,Object>
	 */
	public static String insertSQLString(Object bean,boolean getFlag) {
    	return insertSQLString(null, bean, getFlag,null, null);
    }
	
	/**
	 * 将一个bean对象生成一条插入的字符串SQL语句,和一个值列表List<{@link Object}>,即插入SQL语句所对应的值
	 * @param tableName 表名称
	 * @param bean bean对象
	 * @param getFlag 获取标识,是否获取父类的属性字段,true[获取],false[不获取]
	 * @param excludedFiledName 要排除的字段名称字符串,要排除多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将不会被返回
	 * @param warnProcessFiledNameMap 待处理字段,详情请参阅{@link BeanUtil#processObjectValue(Map, Map)}
	 * @return Map<String,Object> 主要是有两个值,一个是SQL语句,另外一个是SQL语句所对应的List<{@link Object}>值对象列表.<br>
	 * 要从此返回值中获取这两个值,必须是:<br>
	 * map.get({@link BeanDBUtil#INSERT_SQL});//获取SQL语句, INSERT INTO tableName (field1,field2) values (?,?)<br>
	 * map.get({@link BeanDBUtil#SQL_PARAM_LIST});//获取参数值列表
	 */
	public static Map<String,Object> insertSQLByParam(String tableName, Object bean, boolean getFlag, String excludedFiledName,Map<String,BeanProc> warnProcessFiledNameMap) {
    	Map<String,Object> mapInfo = getFiledNamesAndValues(bean, getFlag, excludedFiledName, warnProcessFiledNameMap);
    	if(null != mapInfo){
    		List<Object> valueList = new ArrayList<Object>();
    		tableName = null == tableName ? getBeanName(bean): tableName;
    		StringBuffer insertSQL = new StringBuffer("INSERT INTO " ).append(tableName);
    		StringBuffer insertFiledSQL = new StringBuffer();
    		StringBuffer insertValuesSQL = new StringBuffer();
    		Set<String> filedSet = mapInfo.keySet();
    		for (String fieldName : filedSet) {
    			insertFiledSQL.append(",").append(fieldName);
    			insertValuesSQL.append(",?");
    			valueList.add(mapInfo.get(fieldName));
            }
    		//如果有值则从第1个字符串开始截取
    		if(!valueList.isEmpty()){
    			insertSQL.append("(").append(insertFiledSQL.substring(1)).append(") ");
        		insertSQL.append(" VALUES(").append(insertValuesSQL.substring(1)).append(") ");
        		Map sqlInfoMap = new HashMap();
    			sqlInfoMap.put(INSERT_SQL, insertSQL.toString());
    			sqlInfoMap.put(SQL_PARAM_LIST, valueList);
    			return sqlInfoMap;
    		} else {
    			logger.info(bean.getClass().getName()+":No object property values, can not constitute insert SQL statement.");
    			return null;
    		}
    	}
    	return null;
    }

	/**
	 * 此方法相当于调用{@link #insertSQLByParam(tableName, bean, getFlag, excludedFiledName,null)},详情请参阅{@link #insertSQLByParam(String, Object, boolean, String, Map)};
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> insertSQLByParam(String tableName, Object bean, boolean getFlag, String excludedFiledName){
    	return insertSQLByParam(tableName, bean, getFlag, excludedFiledName,null);
    }

	/**
	 * 此方法相当于调用{@link #insertSQLByParam(tableName, bean, getFlag, null ,null)},详情请参阅{@link #insertSQLByParam(String, Object, boolean, String, Map)};
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> insertSQLByParam(String tableName, Object bean, boolean getFlag){
    	return insertSQLByParam(tableName, bean, getFlag, null ,null);
    }

	/**
	 * 此方法相当于调用{@link #insertSQLByParam(tableName, bean, false, null, null)},详情请参阅{@link #insertSQLByParam(String, Object, boolean, String, Map)};
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> insertSQLByParam(String tableName, Object bean){
    	return insertSQLByParam(tableName, bean, false, null, null);
    }

	/**
	 * 此方法相当于调用{@link #insertSQLByParam(null, bean, getFlag, null, null)},详情请参阅{@link #insertSQLByParam(String, Object, boolean, String, Map)};
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> insertSQLByParam(Object bean,boolean getFlag){
    	return insertSQLByParam(null, bean, getFlag, null, null);
    }

	/**
	 * 此方法相当于调用{@link #insertSQLByParam(null, bean, false, null, null)},详情请参阅{@link #insertSQLByParam(String, Object, boolean, String, Map)};
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> insertSQLByParam(Object bean){
    	return insertSQLByParam(null, bean, false, null, null);
    }
	
	//获取某一个对象的具体名称,排除包的路径名称.内部调用此方法前需要判空.
    private static String getBeanName(Object bean){
    	String beanName = bean.getClass().getName();
    	return beanName.substring(beanName.lastIndexOf(".")+1);
    }

    //将特殊字符过滤为’
	private static String valueProcess(Object objValue){
    	String value = objValue.toString();
    	return value.replaceAll("'", "’");
    }

}

