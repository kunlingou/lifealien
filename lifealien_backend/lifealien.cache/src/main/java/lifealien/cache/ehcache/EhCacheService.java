package lifealien.cache.ehcache;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EhCacheService {
	
	@Autowired
	Cache<String, Object> cache;
	
	public Cache getCache() {
		return null;
	}
}
