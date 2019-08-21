### java缓存

#### 缓存分类

- 文件缓存：通过序列化把数据存储在磁盘上。
- 内存缓存：实现一个静态Map进行管理。
- 应用内缓存：OSCache(页面缓存)、EHCache
- 独立进程缓存：memcache

#### 实现一个简单的内存缓存

- cache

```
package lifealien.cache.simple;

public class Cache{
	
	/**缓存id*/
	private String key;
	
	/**缓存数据*/
	private Object value;
	
	/**缓存失效时间*/
	private long timeOut;
	
	/**缓存是否终止*/
	private boolean expired;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
```

- cacheManager

```
package lifealien.cache.simple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class CacheManager {

	private HashMap<String, Object> cacheMap;
	
	private static CacheManager cacheManager;
	
	private CacheManager() {
		cacheMap = new HashMap<>();
	}
	
	public static CacheManager getInstance() {
		if(cacheManager == null) {
			cacheManager = new CacheManager();
		}
		return cacheManager;
	}
	
	/**
	 * 设置缓存
	 */
	public synchronized void putCache(String key, Cache obj) { 
		cacheMap.put(key, obj); 
	}
	
	/**
	 * 清除指定的缓存
	 */
	public synchronized void clearOnly(String key) { 
		cacheMap.remove(key); 
	}
	
	/**
	 * 清除所有缓存
	 */
	public synchronized void clearAll() { 
		cacheMap.clear(); 
	}
	
	/**
	 * 清除指定类型(头部)的缓存
	 */
	public synchronized void clearAll(String type) {
		_typeFilter(type,k->clearOnly(k));
	}
	
	public Cache getCacheInfo(String key) {
		if(hasCache(key)) {
			Cache cache;
			if(cacheExpired(cache = getCache(key, Cache.class))) {
				cache.setExpired(true);
			}
			return cache;
		}else {
			return null;
		}
	}
	
	public void putCacheInfo(String key,Object obj) {
		putCacheInfo(key, obj, -1, false);
	}
	
	public void putCacheInfo(String key,Object obj,long dt) {
		putCacheInfo(key, obj, dt, false);
	}
	
	public synchronized void putCacheInfo(String key,Object obj,long dt, boolean expired) {
		Cache cache = new Cache();
		cache.setKey(key);
		cache.setValue(obj);
		cache.setTimeOut(dt + System.currentTimeMillis());
		cache.setExpired(expired);
		cacheMap.put(key, cache);
	}
	
	public Set<String> getCacheAllkey() { 
		return cacheMap.keySet();
	}
	
	public Set<String> getCacheAllkey(String type) {
		Set<String> set = new HashSet<>();
		_typeFilter(type,k->set.add(k));
		return set;
	}
	
	public int getCacheSize(){
		return cacheMap.size();
	}
	
	public synchronized int getCacheSize(String type){
		AtomicInteger size = new AtomicInteger(0);
		_typeFilter(type,k->size.addAndGet(1));
		return size.get();
	}
	
	/**
	 * 判断cache是否终止
	 * @param cache
	 * @return
	 */
	public boolean cacheExpired(Cache cache) {
		if(cache == null) {
			return false;
		}
		long CacheDt = cache.getTimeOut();
		if(CacheDt<0 || CacheDt>System.currentTimeMillis()) {
			return false;
		}else {
			return true;
		}
	}
	
	public synchronized Boolean getSimpleFlag(String key) {
		try {
			return (Boolean) cacheMap.get(key);
		}catch(Exception e) {
			return null;
		}
	}
	
	public synchronized boolean hasCache(String key) { 
		return cacheMap.containsKey(key); 
	}
	
	/**
	 * 类型过滤器
	 * @param type
	 * @param action
	 */
	private void _typeFilter(String type, Consumer<String> action) {
		Iterator<String> iterator = cacheMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key;
			if((key = iterator.next()).startsWith(type)) {
				action.accept(key);
			}
		}
	}
	
	/**
	 * 获取指定数据类型的缓存
	 * @return
	 */
	public synchronized <T> T getCache(String key,Class<T> clazz) { 
		try {
			return clazz.cast(cacheMap.get(key));
		}catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取指定的缓存
	 * @return
	 */
	public synchronized Object getCache(String key) { 
		return cacheMap.get(key);
	}
}
```

#### EhCache

- <https://www.jianshu.com/p/5a0669d6305e> 
- <https://blog.csdn.net/qq_28988969/article/details/78210873> 

