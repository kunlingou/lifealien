package com.kunlinr.lifealien.context.store;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeReference<E> {
	
	protected final Type _type;
	
	protected TypeReference() {
		Type superClass = getClass().getGenericSuperclass();
		superClass.getClass().getGenericSuperclass();
		if(superClass instanceof Class<?>) {
			throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
		}else {
			_type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
		}
	}
	
	protected TypeReference(Type type) {
		this._type = type;
	}
	
	public Type getType() {
		return _type;
	}
}
