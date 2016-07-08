package crds.pub.util;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ColumnMapRowMapper;

import crds.pub.util.bean.BeanUtil;

/**
 * @specification :JDBC返回对象封装�?
 * @version : 1.0
 * @author : banlibo
 * @date : Nov 5, 2008 9:55:47 AM
 * @email : banlibo@beyondsoft.com
 */
public class JdbcTemplateRowMapper extends ColumnMapRowMapper{
	private static Logger logger = Logger.getLogger(JdbcTemplateRowMapper.class);
	
	@SuppressWarnings("unchecked")
	private Class entityType; 
	
	@SuppressWarnings("unchecked")
	public Class getEntityType() {
		return entityType;
	}

	@SuppressWarnings("unchecked")
	public void setEntityType(Class entityType) {
		this.entityType = entityType;
	}

	public JdbcTemplateRowMapper(){
		
	}
	
	@SuppressWarnings("unchecked")
	public JdbcTemplateRowMapper(Class entityType){
		setEntityType(entityType);
	}
	
	@SuppressWarnings("unchecked")
	public Object mapRow(ResultSet rs,int rowNum)throws SQLException{
		Class entityType = getEntityType();
		Object entity = null;
		try{
			if(entityType == null){
				return super.mapRow(rs, rowNum);
			}
			entity = entityType.newInstance();
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();
	        for(int i = 1; i <= columnCount; i++){
	            String key = getColumnKey(rsmd.getColumnName(i));
	            Object obj = getColumnValue(rs, i);
	            if(obj == null) continue;
	            String value = obj.toString();
	            String notFindMethodMessage = "";
	            try{
		            String methodName = BeanUtil.beanMethod("set", key,null);//setMethod_Name, key在Oracle中返回的全是大写,其它数据库返回的不一定全是大写
		            notFindMethodMessage = methodName;
		            Method method = entityType.getMethod(methodName, String.class);
		            method.invoke(entity, new Object[]{value});
	            }catch(Exception e){
	            	try{
		            	String methodName = BeanUtil.beanMethod("set", key,true);//setMethod_name
		            	notFindMethodMessage += " " + methodName;
		            	Method method = entityType.getMethod(methodName, String.class);
		            	method.invoke(entity, new Object[]{value});
	            	} catch (NoSuchMethodException nsme) {
	            		try{
			            	String methodName = BeanUtil.beanMethod("set", key,false);//setMETHOD_NAME
			            	notFindMethodMessage += " " + methodName;
			            	Method method = entityType.getMethod(methodName, String.class);
			            	method.invoke(entity, new Object[]{value});
		            	} catch (NoSuchMethodException nsme1) {
		            		logger.info("invoked not find Method["+notFindMethodMessage+"] in the: " + entityType.getName());
		            	}
		            	continue;
	            	}
	            }
	        }
		}catch(Exception e){
			logger.error("JdbcTemplateRowMapper.mapRow():"+e.getMessage());
		}
		return entity;
	}

}
