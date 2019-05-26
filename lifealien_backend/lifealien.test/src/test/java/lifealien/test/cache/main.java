package lifealien.test.cache;

import java.util.HashMap;
import java.util.Map;

public class main {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		String put = map.put("2", "3");
		System.out.println(put);
	}
}
