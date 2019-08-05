package com.kunlinr.lifealien.context.store;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储抽象类
 * @author kunlingou
 *
 */
public abstract class AbstractStore implements Store{
	
	private final Map<Key<?>, Object> values = new ConcurrentHashMap<>();
	
	public <T> void put(String key, T value, Class<T> valueType) {
		put(new Key<T>(key, new TypeReference<T>(valueType) {}), value);
	}
	
	public <T> void put(String key, T value, TypeReference<T> valueTypeRef) {
		put(new Key<T>(key, valueTypeRef), value);
	}
	
	public <T> T get(String key, Class<T> valueType) {
		return get(new Key<T>(key, new TypeReference<T>(valueType) {}));
	}
	
	public <T> T get(String key, TypeReference<T> valueTypeRef) {
		return get(new Key<T>(key,valueTypeRef));
	}
	
	public <T> Object getObject(String key, Class<T> valueType) {
		return getObject(new Key<T>(key, new TypeReference<T>(valueType) {}));
	}
	
	public <T> Object getObject(String key, TypeReference<T> valueTypeRef) {
		return getObject(new Key<T>(key,valueTypeRef));
	}
	
	@SuppressWarnings("unchecked")
	private <T> void put(Key<T> key, T value){
		Type type;
		if((type = key.typeRef.getType()) instanceof Class) {
			values.put(key, ((Class<T>)type).cast(value));
		}else if(type instanceof ParameterizedType) {
			values.put(key, ((Class<T>)((ParameterizedType)type).getRawType()).cast(value));
		}else {
			values.put(key, value);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T get(Key<T> key) {
		Type type;
		Object obj = values.get(key);
		try {
			if((type = key.typeRef.getType()) instanceof Class) 
				return ((Class<T>)type).cast(obj);
			else 
				return (T) obj;
		}catch(Exception e) {
			return null;
		}
	}
	
	private <T> Object getObject(Key<T> key) {
		return values.get(key);
	}
	
	public abstract <T> int KeyHashCode(Key<T> key);
	
	public abstract <T> boolean KeyEquals(Key<T> key, Object obj);
	
	public class Key<T>{
		public final String identifier;
		public final TypeReference<T> typeRef;
		
		public Key(String identifier,TypeReference<T> typeRef) {
			this.identifier = identifier;
			this.typeRef = typeRef;
		}
		
		@Override
		public int hashCode() {
			return KeyHashCode(this);
		};
		@Override
		public boolean equals(Object obj) {
			return KeyEquals(this, obj);
		}
	}
}
