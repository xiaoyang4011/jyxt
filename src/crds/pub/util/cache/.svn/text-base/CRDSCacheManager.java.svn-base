package crds.pub.util.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
* @author Maci Hotesion
*/
public final class CRDSCacheManager implements Serializable {
    /**  */
	private static final long serialVersionUID = -7617000395677475939L;
	private static Map<String, Object> cacheMap = new HashMap<String, Object>();

	private CRDSCacheManager() {}
    
    /**
     * 添加数据到内存
     * 仅当存储空间中不存在键相同的数据时才保存
     * @param key key值
     * @param value　要保存的数据
     * @return 
     */
    public static void set(String key, Object value) {
        cacheMap.put(key, value);
    }

    /**
     * 从内存中删除指定key的数据
     * @param key key值
     */
    public static void delete(String key) {
        cacheMap.remove(key);
    }

    /**
     * 从内存中取出指定key的数据
     * @param key   key值
     * @return　取出的数据　
     */
    public static Object get(String key) {
        return cacheMap.get(key);
    }

    /**
     * 替换内存中指定key的数据
     * 仅当存储空间中存在键相同的数据时才保存
     * @param key key值
     * @param value 要替换的数据
     * @return 是否替换成功,成功返回true,否则返回false
     */
    public static Object replace(String key, Object value) {
    	return cacheMap.put(key, value);
    }
}
