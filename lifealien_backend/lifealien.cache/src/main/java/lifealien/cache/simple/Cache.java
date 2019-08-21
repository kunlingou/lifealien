package lifealien.cache.simple;

public class Cache{
	
	/**缓存id*/
	private String key;
	
	/**缓存数据*/
	private Object value;
	
	/**缓存失效时间*/
	private long timeOut;
	
	/**缓存是否终止*/
	private boolean expired;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
