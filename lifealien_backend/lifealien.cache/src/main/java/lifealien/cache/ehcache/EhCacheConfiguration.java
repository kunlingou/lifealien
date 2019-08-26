package lifealien.cache.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EhCacheConfiguration {
	
	@Autowired
	CacheManager cacheManager;
	
	@Bean
	public CacheManager getCacheManager() {
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
		.withCache("preconfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,Object.class,ResourcePoolsBuilder.heap(10)))
		.build();
		cacheManager.init();
		return cacheManager;
	}
	
	@Bean
	public Cache<String, Object> getCache(){
		return cacheManager.createCache("myCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,Object.class,ResourcePoolsBuilder.heap(10)));
	}
}
