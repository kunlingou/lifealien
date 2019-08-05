package com.kunlinr.lifealien.context.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractStore implements Store{
	
	protected final Map<Key<?>, Object> values = new ConcurrentHashMap<>();
	
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
	
	protected abstract <T> void put(Key<T> key, T value);
	
	protected abstract <T> T get(Key<T> key);
	
	protected class Key<T>{
		final String identifier;
		final TypeReference<T> typeRef;
		
		public Key(String identifier,TypeReference<T> typeRef) {
			this.identifier = identifier;
			this.typeRef = typeRef;
		}
	}
}
