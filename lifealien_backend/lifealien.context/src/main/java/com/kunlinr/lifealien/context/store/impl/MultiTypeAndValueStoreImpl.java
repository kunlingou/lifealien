package com.kunlinr.lifealien.context.store.impl;

import java.util.Objects;

import com.kunlinr.lifealien.context.store.AbstractStore;

/**
 * 类型安全的异构容器
 * 一个类和一个id对应一个对象
 * @author goukunlin
 */
public class MultiTypeAndValueStoreImpl extends AbstractStore{

	@Override
	public <T> int KeyHashCode(com.kunlinr.lifealien.context.store.AbstractStore.Key<T> key) {
		return Objects.hashCode(key.identifier) ^ Objects.hashCode(key.typeRef.getType());
	}

	@Override
	public <T> boolean KeyEquals(com.kunlinr.lifealien.context.store.AbstractStore.Key<T> key, Object obj) {
		if(obj instanceof Key) {
			Key<?> p = key.getClass().cast(obj);
			String v = key.typeRef.getType().getTypeName(),pv = p.typeRef.getType().getTypeName();
			return key.identifier.equals(p.identifier)
					&& (v == pv || (pv != null && pv.equals(v)));
		}
	    return super.equals(obj);
	}
}
