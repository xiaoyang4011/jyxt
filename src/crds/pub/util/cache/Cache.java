package crds.pub.util.cache;
/**
 * 缓存处理对象
 * @author Maci Hotesion
 *
 * @param <E>
 */
public interface Cache<E> {
	/**
	 * 从缓存中取值,如果不存在则中CacheManager中查询出来取值,并缓存
	 * 如果是getIntence()直接取到的Cache 则直接返回null
	 * @param key
	 * @return
	 */
	public E get(String key);
	
	/**
	 * 设值
	 * @param key
	 * @param obj
	 */
	public void set(String key ,Object obj);
	/**
	 * 删除值
	 * @param key
	 */
	public void delete(String key);
	
	/**
	 * 更新值
	 * @param key
	 * @param obj
	 */
	public void update(String key, Object obj);
}
