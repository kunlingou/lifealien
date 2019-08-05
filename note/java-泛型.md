### 积累

#### map管理多种类型的value

- value为多个类型

```
public class Context {
	private final Map<String, Object> values = new HashMap<>();
	
	public <T> void put(String key, T value, Class<T> valueType){
		values.put(key, valueType.cast(value));
	}
	
	public <T> T get(String key, Class<T> valueType) {
		return valueType.cast(values.get(key));
	}
}
```

##### 类型安全的异构容器

- 类型作为key值

```
public class Context {
	private final Map<Class<?>, Object> values = new HashMap<>();
	
	public <T> void put(T value, Class<T> valueType){
		values.put(valueType, valueType.cast(value));
	}
	
	public <T> T get(Class<T> valueType) {
		return valueType.cast(values.get(valueType));
	}
}
```

##### 多条同类型容器条目

- 类型和key值相同时，value唯一。
- 解决不了集合的范型问题

```
public class Context {
	private final Map<Key<?>, Object> values = new ConcurrentHashMap<>();
	
	public <T> void put(String key, T value, Class<T> valueType) {
		put(new Key<T>(key, valueType), value);
	}
	
	public <T> T get(String key, Class<T> valueType) {
		return get(new Key<T>(key, valueType));
	}
	
	public <T> void put(Key<T> key, T value){
		values.put(key, key.type.cast(value));
	}
	
	public <T> T get(Key<T> key) {
		return key.type.cast(values.get(key));
	}
	
	public class Key<T>{
		final String identifier;
		final Class<T> type;
		
		public Key(String identifier, Class<T> type) {
			this.identifier = identifier;
			this.type = type;
		}
		@Override
		public int hashCode() {
			return Objects.hashCode(identifier) ^ Objects.hashCode(type);
		};
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Key) {
				Key<?> p = this.getClass().cast(obj);
				String v = type.getName(),pv = p.type.getName();
				return this.identifier.equals(p.identifier)
						&& (v == pv || (pv != null && pv.equals(v)));
			}
		    return super.equals(obj);
		}
	}
}
```

- 最终版

```

```

