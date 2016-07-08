package crds.pub.util.bean;

import java.util.Map;

public interface BeanIDB {

	/**
	 * @param Map<String,Object>,入库的SQL语句和参数值,此参数必须由方法{@link BeanDBUtil#insertSQLByParam(String, Object, boolean, String, Map)}获取,或者由此方法的其它重载方法获取.
	 * @return 
	 */
	int insertToDB(Map<String,Object> beanMapInfo);
	
	int insertToDB(String sql,Object[] param);
	
	int insertToDB(String sql);
}
