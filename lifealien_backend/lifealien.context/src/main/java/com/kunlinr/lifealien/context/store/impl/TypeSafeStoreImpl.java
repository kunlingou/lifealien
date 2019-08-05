package com.kunlinr.lifealien.context.store.impl;

import java.util.Objects;

import com.kunlinr.lifealien.context.store.AbstractStore;

/**
 * 类型安全的异构容器
 * 一个类对应一个对象
 * @author goukunlin
 *
 */
public class TypeSafeStoreImpl extends AbstractStore{

	@Override
	public <T> int KeyHashCode(Key<T> key) {
		return Objects.hashCode(key.typeRef.getType());
	}

	@Override
	public <T> boolean KeyEquals(Key<T> key, Object obj) {
		if(obj instanceof Key) {
			Key<?> p = key.getClass().cast(obj);
			String v = key.typeRef.getType().getTypeName(),pv = p.typeRef.getType().getTypeName();
			return v == pv || (pv != null && pv.equals(v));
		}
	    return (obj == key || (obj != null && obj.equals(key)));
	}
}
