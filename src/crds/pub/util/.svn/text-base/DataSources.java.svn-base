package crds.pub.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

/**
 * 
 * @specification :数据源失效控制
 * @version : 1.0
 * @author : maogf
 * @date : Jul 27, 2009 5:17:02 PM
 * @email : maogenfeng@gmail.com
 */

public class DataSources extends AbstractRoutingDataSource {

	static Logger logger = Logger.getLogger(DataSources.class);

	private Object[] targetDataSourcesKeys = null;

	private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

	public static void setCurrentLookupKeyIndex(Integer currentLookupKeyIndex) {
		Assert.notNull(currentLookupKeyIndex,
				"current LookupKey cannot be null");
		contextHolder.set(currentLookupKeyIndex);
	}

	public static Integer getCurrentLookupKeyIndex() {

		Integer i = (Integer) contextHolder.get();
		if (i == null) {
			setCurrentLookupKeyIndex(0);
			return new Integer(0);
		} else {
			return i;
		}
	}

	public static void clearCurrentLookupKeyIndex() {
		contextHolder.remove();
	}

	/*
	 * (重载方法)返回当前dataSource key
	 * 
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return targetDataSourcesKeys[getCurrentLookupKeyIndex().intValue()];
	}

	/*
	 * (重载方法)获取数据库连接
	 * 
	 */
	@Override
	public Connection getConnection() throws SQLException {
		int i = 0;
		// do {
		// try {
		// if (logger.isDebugEnabled())
		// logger.debug("Datasource connect ...["
		// + determineCurrentLookupKey() + "]");
		// // 尝试获取
		// return determineTargetDataSource().getConnection();
		// } catch (SQLException e) {
		// logger.error("数据源"+targetDataSourcesKeys[getCurrentLookupKeyIndex().intValue()]+"连接异常。");
		// }
		//
		// // 获取连接失败，切换数据库
		// i++;
		// if (i < targetDataSourcesKeys.length)
		// setCurrentLookupKeyIndex(i);
		// else
		// return determineTargetDataSource().getConnection();
		// } while (i < targetDataSourcesKeys.length);
		// return determineTargetDataSource().getConnection();
		Connection connection = null;
		while (i < targetDataSourcesKeys.length) {
			try {
				connection = determineTargetDataSource().getConnection();
				break;
			} catch (Exception e) {
				logger
						.error("数据源"
								+ targetDataSourcesKeys[i]
								+ ":"
								+ ((DriverManagerDataSource) determineTargetDataSource())
										.getUrl() + "连接异常。"+e.getMessage());
				continue;
			} finally {
				i++;
				if (i < targetDataSourcesKeys.length) {
					setCurrentLookupKeyIndex(i);
				}
			}
		}
		clearCurrentLookupKeyIndex();
		return connection;
	}

	/*
	 * (重载方法)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setTargetDataSources(Map targetDataSources) {
		this.targetDataSourcesKeys = targetDataSources.keySet().toArray();// 将map的值放入本地
		setCurrentLookupKeyIndex(0);
		super.setTargetDataSources(targetDataSources);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}