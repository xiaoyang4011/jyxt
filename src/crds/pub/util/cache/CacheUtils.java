package crds.pub.util.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

public class CacheUtils {
	private JdbcTemplate jdbcTemplate = null;
	private static JdbcTemplate jdbc = null;
	/** 默认带管理类的Key前缀 */
	private static final String DEFAULTMAPREFIX = "CRDSM";
	/** 默认前缀 */
	private static final String DEFAULTPREFIX = "CRDS";
	private static final String SystemDoURL = "SystemDoURL";
	private static CacheUtils cacheUtils = null;

	/**
	 * 缓存对象工厂
	 */
	private CacheUtils() {
		cacheUtils = this;
	}

	/**
	 * 得到一个默认的缓存处理对象
	 * @param cache
	 * @return
	 */
	public static Cache<?> getInstance(CacheManager cache) {
		return cacheUtils.new CacheImpl<Object>(cache, DEFAULTMAPREFIX);
	}

	/**
	 * 得到一个默认的缓存处理对象
	 * @param cache
	 * @return
	 */
	public static Cache<?> getInstance(CacheManager cache, String prefix) {
		return cacheUtils.new CacheImpl<Object>(cache, prefix);
	}

	/**
	 * 得到一个默认的取值对象
	 * @return
	 */
	public static Cache<?> getInstance(String prefix) {
		return cacheUtils.new CacheImpl<Object>(null, prefix);
	}
	/**
	 * 得到一个默认的取值对象
	 * @return
	 */
	public static Cache<?> getInstance() {
		return cacheUtils.new CacheImpl<Object>(null, DEFAULTPREFIX);
	}

	private class CacheImpl<E> implements Cache<Object> {
		private CacheManager cache;
		private String prefix;
		private static final String P = "_";

		public CacheImpl(CacheManager cache, String prefix) {
			this.cache = cache;
			this.prefix = prefix;
		}

		public Object get(String key) {
			StringBuffer buffer = new StringBuffer(P);
			buffer.append(prefix).append(key);
			Object obj = CRDSCacheManager.get(buffer.toString());
			if (null != obj) {
				// 直接取出来
				return obj;
			} else if (null != cache) {
				// 先查询出来后放到缓存里再取出来
				obj = cache.get(key);
				if (null != obj) {
					CRDSCacheManager.set(buffer.toString(), obj);
				}
			}
			return obj;
		}

		public void set(String key, Object obj) {
			StringBuffer buffer = new StringBuffer(P);
			buffer.append(prefix).append(key);
			CRDSCacheManager.set(buffer.toString(), obj);
		}

		public void delete(String key) {
			StringBuffer buffer = new StringBuffer(P);
			buffer.append(prefix).append(key);
			CRDSCacheManager.delete(buffer.toString());
		}

		public void update(String key, Object obj) {
			StringBuffer buffer = new StringBuffer(P);
			buffer.append(prefix).append(key);
			CRDSCacheManager.replace(buffer.toString(), obj);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String isPosCompanyCode(String doURL){
		Map<String, String> map = (Map<String, String>)cacheUtils.new CacheImpl<Object>(new CacheManager(){
			public Object get(String key) {
				Map<String, String> doURLMap = new HashMap<String, String>();
				List<Map<String,String>> doURLList = jdbc.queryForList("select LINK, IS_POS_COMPANY_CODE from system_operation_info t where link is not null");
				if(null != doURLList){
					for (Map<String, String> map : doURLList) {
						doURLMap.put(map.get("LINK"), map.get("IS_POS_COMPANY_CODE"));
					}
				}
				return doURLMap;
			}
		},SystemDoURL).get(SystemDoURL);
		String posFlag = map.get(doURL);
		if(posFlag == null){//*.do?param=value
			Set<String> set = map.keySet();
			for (String keyStr : set) {
				if(keyStr.indexOf(doURL)>=0){
					posFlag = map.get(keyStr);
					break;
				}
			}
		}
		return posFlag;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbc = this.jdbcTemplate;
	}
	
	public static void main(String[] args) {
		CacheUtils.getInstance().set("ABCD", new Date());
	}
}
