package com.kunlinr.lifealien.context.store;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MultiTypeAndValueStoreImpl {
private final Map<Key<?>, Object> values = new ConcurrentHashMap<>();
	
	public <T> void put(String key, T value, Class<T> valueType) {
		put(new Key<T>(key, new TypeReference<T>(valueType) {}), value);
	}
	
	public <T> void put(String key, T value, TypeReference<T> valueTypeRef) {
		put(new Key<T>(key, valueTypeRef), value);
	}
	
	public <T> T get(String key, TypeReference<T> valueTypeRef) {
		return get(new Key<T>(key,valueTypeRef));
	}
	
	public <T> T get(String key, Class<T> valueType) {
		return get(new Key<T>(key, new TypeReference<T>(valueType) {}));
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
		if((type = key.typeRef.getType()) instanceof Class) {
			return ((Class<T>)type).cast(values.get(key));
		}else {
			return (T)values.get(key);
		}
	}
	
	protected class Key<T>{
		final String identifier;
		final TypeReference<T> typeRef;
		
		public Key(String identifier,TypeReference<T> typeRef) {
			this.identifier = identifier;
			this.typeRef = typeRef;
		}
		
		@Override
		public int hashCode() {
			return Objects.hashCode(identifier) ^ Objects.hashCode(typeRef.getType());
		};
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Key) {
				Key<?> p = this.getClass().cast(obj);
				String v = typeRef.getType().getTypeName(),pv = p.typeRef.getType().getTypeName();
				return this.identifier.equals(p.identifier)
						&& (v == pv || (pv != null && pv.equals(v)));
			}
		    return super.equals(obj);
		}
	}
}
