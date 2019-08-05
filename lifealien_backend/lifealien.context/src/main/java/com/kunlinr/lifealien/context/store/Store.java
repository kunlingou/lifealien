package com.kunlinr.lifealien.context.store;

/**
 * 存储对象
 * @author goukunlin
 *
 */
public interface Store {
	
	/**
	 * 支持简单的java对象类型，如String、Object或自定义对象
	 * @param key 唯一标识，identifier
	 * @param value key值对应的对象
	 * @param valueType 对象类型：String.class、Object.class
	 */
	<T> void put(String key, T value, Class<T> valueType);
	 
	/**
	 * 支持java集合对象，如{@code List<String>、Map<String,Object>}或自定义集合对象，同时支持简单的java对象类型。
	 * @param key 唯一标识，identifier
	 * @param value key值对应的对象
	 * @param valueTypeRef 对象类型：{@code new TypeReference<List<String>>(){} 
	 */
	<T> void put(String key, T value, TypeReference<T> valueTypeRef);
	
	/**
	 * @see Store#put(String, Object, Class)
	 * @param key 唯一标识，identifier
	 * @param valueType 对象类型：String.class、Object.class
	 * @return 返回存储的对象
	 */
	<T> T get(String key, Class<T> valueType);
		
	/**
	 * @see #put(String, Object, TypeReference)
	 * @param key 唯一标识，identifier
	 * @param valueTypeRef 对象类型：{@code new TypeReference<List<String>>(){} 
	 * @return 返回存储的对象
	 */
	<T> T get(String key, TypeReference<T> valueTypeRef);
	
	/**
	 * @see #get(String, Class)
	 * 区别是如果对象和实际情况不同，上面方法返回null，该方法返回Object
	 * @param key 唯一标识，identifier
	 * @param valueType 
	 * @return 返回存储的对象
	 */
	<T> Object getObject(String key, Class<T> valueType);
	
	/**
	 * @see #get(String, TypeReference)
	 * 区别是如果对象和实际情况不同，上面方法返回null，该方法返回Object
	 * @param key 唯一标识，identifier
	 * @param valueTypeRef 对象类型：{@code new TypeReference<List<String>>(){} 
	 * @return 返回存储的对象
	 */
	<T> Object getObject(String key, TypeReference<T> valueTypeRef);
}
