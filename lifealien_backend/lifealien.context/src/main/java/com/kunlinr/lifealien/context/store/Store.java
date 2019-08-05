package com.kunlinr.lifealien.context.store;

/**
 * 存储对象
 * @author goukunlin
 *
 */
public interface Store {
	
	<T> void put(String key, T value, Class<T> valueType);
	
	<T> void put(String key, T value, TypeReference<T> valueTypeRef);
	
	<T> T get(String key, TypeReference<T> valueTypeRef);
	
	<T> T get(String key, Class<T> valueType);
		
}
