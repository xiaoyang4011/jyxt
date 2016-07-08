package crds.pub.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import crds.pub.util.bean.BeanDBUtil;
import crds.pub.util.bean.BeanIDB;

/**
 * @specification :JdbcTemplate扩展
 * @version : 1.0
 * @auther : yangy
 * @date : Jul 30, 2008 4:01:13 PM
 * @email : qilaug@gmail.com
 */
public class JdbcTemplateExtend extends JdbcTemplate implements BeanIDB {
	/** 日志对象 */
	private static Logger logger = Logger.getLogger(JdbcTemplateExtend.class);
	
	private DataSource dataSource;

	/**
	 * 默认构造器，调用此方法初始化，需要调用setDataSource设置数据源
	 */
	public JdbcTemplateExtend() {
	}

	/**
	 * 初始构造器
	 * 
	 * @param dataSource
	 *            数据源
	 */
	public JdbcTemplateExtend(DataSource dataSource) {
		this.dataSource = dataSource;
		super.setDataSource(dataSource);
	}

	/**
	 * 普通分页查询<br>
	 * <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
	 * 
	 * @see #setFetchSize(int)
	 * @see #setMaxRows(int)
	 * @param sql
	 *            查询的sql语句
	 * @param startRow
	 *            起始行
	 * @param rowsCount
	 *            获取的行数
	 * @return
	 * @throws DataAccessException
	 */

	@SuppressWarnings("unchecked")
	public List queryForListPage(String sql, int startRow, int rowsCount)
			throws DataAccessException {
		return queryForListPage(sql, startRow, rowsCount,
				getColumnMapRowMapper());
	}
	
	/**
	 * 普通分页查询<br>
	 * <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
	 * 
	 * @see #setFetchSize(int)
	 * @see #setMaxRows(int)
	 * @param sql
	 *            查询的sql语句
	 * @param startRow
	 *            起始行
	 * @param rowsCount
	 *            获取的行数
	 * @return
	 * @throws DataAccessException
	 */

	@SuppressWarnings("unchecked")
	public List queryForListPage(String sql,Object[] args,int startRow, int rowsCount)
			throws DataAccessException {		
		return queryForListPage(sql,args,startRow, rowsCount,
				getColumnMapRowMapper());
	}

	/**
	 * 自定义行包装器查询<br>
	 * <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
	 * 
	 * @see #setFetchSize(int)
	 * @see #setMaxRows(int)
	 * @param sql
	 *            查询的sql语句
	 * @param startRow
	 *            起始行
	 * @param rowsCount
	 *            获取的行数
	 * @param rowMapper
	 *            行包装器
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List queryForListPage(String sql, int startRow, int rowsCount,
			RowMapper rowMapper) throws DataAccessException {
		return (List) query(sql, new SplitPageResultSetExtractor(rowMapper,
				startRow, rowsCount));
	}
	
	/**
	 * 自定义行包装器查询<br>
	 * <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
	 * 
	 * @see #setFetchSize(int)
	 * @see #setMaxRows(int)
	 * @param sql
	 *            查询的sql语句
	 * @param args
	 *            查询sql语句的参数
	 * @param startRow
	 *            起始行
	 * @param rowsCount
	 *            获取的行数
	 * @param rowMapper
	 *            行包装器
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List queryForListPage(String sql,Object[] args, int startRow, int rowsCount,
			RowMapper rowMapper) throws DataAccessException {
		return (List) this.query(sql, args,new SplitPageResultSetExtractor(rowMapper,
				startRow, rowsCount));
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		super.setDataSource(dataSource);
	}

	/**
	 * 行包装器
	 * 
	 * @param entityType
	 *            行包装器实体类型
	 * @return RowMapper 行包装器
	 */
    @SuppressWarnings("unchecked")
	protected RowMapper getJdbcTemplateRowMapper(Class entityType){
        return new JdbcTemplateRowMapper(entityType);
    }

	/**
	 * 普通查询<br>
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @param args[]
	 *            参数数组
	 * @param entityType
	 *            行包装器实体类型
	 * @return List
	 * @throws DataAccessException
	 */    
    @SuppressWarnings("unchecked")
	public List queryForList(String sql, Class entityType)throws DataAccessException {
        return query(sql, getJdbcTemplateRowMapper(entityType));
    }
    
	/**
	 * 普通查询<br>
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @param args[]
	 *            参数数组
	 * @param entityType
	 *            行包装器实体类型
	 * @return List
	 * @throws DataAccessException
	 */    
    @SuppressWarnings("unchecked")
	public List queryForList(String sql, Object args[], Class entityType)throws DataAccessException {
        return query(sql, args, getJdbcTemplateRowMapper(entityType));
    }
    
	/**
	 * 普通分页查询<br>
	 * <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
	 * 
	 * @see #setFetchSize(int)
	 * @see #setMaxRows(int)
	 * @param sql
	 *            查询的sql语句
	 * @param startRow
	 *            起始行
	 * @param rowsCount
	 *            获取的行数
	 * @param entityType
	 *            行包装器实体类型
	 * @return List
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List queryForListPage(String sql, int startRow, int rowsCount,
			Class entityType) throws DataAccessException {
		return queryForListPage(sql,startRow,rowsCount,getJdbcTemplateRowMapper(entityType));
	}
	
	/**
	 * 普通分页查询<br>
	 * <b>如果结果结合比较大应该调用setFetchsize() 和setMaxRow两个方法来控制一下，否则会内存溢出</b>
	 * 
	 * @see #setFetchSize(int)
	 * @see #setMaxRows(int)
	 * @param sql
	 *            查询的sql语句
	 * @param args[]
	 *            参数数组
	 * @param startRow
	 *            起始行
	 * @param rowsCount
	 *            获取的行数
	 * @param entityType
	 *            行包装器实体类型
	 * @return List
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List queryForListPage(String sql, Object args[], int startRow, int rowsCount,
			Class entityType) throws DataAccessException {
		return queryForListPage(sql,args,startRow,rowsCount,getJdbcTemplateRowMapper(entityType));
	}
	
	public Map queryForMap(int exceptionRetFlag,String sql){
		try {
			return queryForMap(sql);
		} catch (Exception e) {
			return exceptionRetFlag==1?null:new HashMap();
		}
	}
	
	public Map queryForMap(int exceptionRetFlag,String sql,Object[] arg){
		try {
			return queryForMap(sql,arg);
		} catch (Exception e) {
			return exceptionRetFlag==1?null:new HashMap();
		}
	}

	@SuppressWarnings("unchecked")
	public int insertToDB(Map<String, Object> beanMapInfo) {
		if(null == beanMapInfo)
			return 0;
		String sql = (String)beanMapInfo.get(BeanDBUtil.INSERT_SQL);
		List<Object> paramList = (List<Object>) beanMapInfo.get(BeanDBUtil.SQL_PARAM_LIST);
		return insertToDB(sql,paramList.toArray());
	}

	public int insertToDB(String sql, Object[] param) {
		if(null == sql || null == param)
			return 0;
		logger.info(sql);
		return update(sql, param);
	}

	public int insertToDB(String sql) {
		logger.info(sql);
		return update(sql);
	}
}
