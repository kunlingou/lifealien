package lifealien.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class HashMapTest {
	/**
	 * HashMap的key和value都能为null么?如果key能为null,那么它是怎么样查找值的？
	 * key值可以为null
	 */
	@Test
	public void Test1() {
		Map<String,Object> map = new HashMap<>();
		map.put(null, "123");
		map.put(null, "234");
		System.out.println(Objects.toString(map.get(null)));
		System.out.println(Objects.toString(null == null));
	}
}
