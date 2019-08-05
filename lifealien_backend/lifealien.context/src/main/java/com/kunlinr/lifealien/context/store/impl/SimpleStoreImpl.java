package com.kunlinr.lifealien.context.store.impl;

import java.util.Objects;

import com.kunlinr.lifealien.context.store.AbstractStore;

/**
 * 实现简单的数据存储，不按类型隔离。
 * 一个id对应一个对象
 * @author goukunlin
 *
 */
public class SimpleStoreImpl extends AbstractStore{

	@Override
	public <T> int KeyHashCode(Key<T> key) {
		return Objects.hashCode(key.identifier);
	}

	@Override
	public <T> boolean KeyEquals(Key<T> key, Object obj) {
		if(obj instanceof Key) {
			Key<?> p = key.getClass().cast(obj);
			return key.identifier.equals(p.identifier);
		}
	    return (obj != null && obj.equals(key));
	}
}
