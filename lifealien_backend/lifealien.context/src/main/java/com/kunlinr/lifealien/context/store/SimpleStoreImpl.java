package com.kunlinr.lifealien.context.store;

import java.util.Objects;

/**
 * 实现简单的数据存储，不按类型隔离。
 * @author goukunlin
 *
 */
public class SimpleStoreImpl extends AbstractStore{

	@Override
	protected <T> void put(Key<T> key, T value) {
		values.put(key, (T)value);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected <T> T get(Key<T> key) {
		return (T) values.get(key);
	}
	
	protected class SimpleKey<T> extends AbstractStore.Key<T>{
		public SimpleKey(String identifier, TypeReference<T> typeRef) {
			super(identifier, typeRef);
		}
		@Override
		public int hashCode() {
			return Objects.hashCode(identifier);
		};
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Key) {
				Key<?> p = this.getClass().cast(obj);
				return this.identifier.equals(p.identifier);
			}
		    return super.equals(obj);
		}
	}
}
