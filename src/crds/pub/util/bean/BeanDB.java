/*
 * FileName:   BeanUtilDB.java
 * Founder:    MaciHotesion
 * Copyright:  Copyright 2007-2008 Hotesion.
 * Description: 
 * ********************** modify log **********************
 * Mender: 
 * Modify Date: 
 * Modify Range: 
 */
package crds.pub.util.bean;

import java.util.List;
import java.util.Map;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2006<br>
 *
 * @author MaCi Hotesion
 * @since JDK 1.6.0_03
 */
@SuppressWarnings("unchecked")
public abstract class BeanDB implements BeanIDB {
	/**
	 * @param beanMapInfo 此参数必须是由{@link BeanDBUtil#insertSQLByParam(String, Object, boolean, String, Map)}返回的值
	 * @return int
	 */
	public int insertToDB(Map<String,Object> beanMapInfo){
		if(null == beanMapInfo)
			return 0;
		String sql = (String)beanMapInfo.get(BeanDBUtil.INSERT_SQL);
		List<Object> paramList = (List<Object>)beanMapInfo.get(BeanDBUtil.SQL_PARAM_LIST);
		return insertToDB(sql,paramList.toArray());
	}

	public abstract int insertToDB(String sql,Object[] param);

	public abstract int insertToDB(String sql);
}

