package lifealien.test.stringtocode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kunlinr.lifealien.test.stringtocode.StringToCodeUtil;

public class StringToCodeTest {
	@Test
	public void executeExpressionTest() {
		Map<String, Object> map = new HashMap<>();
	    map.put("alive", "coding every day");
	    map.put("out", System.out);
	    String expression = "out.print(alive)";
	    StringToCodeUtil.executeExpression(expression, map);
	}
}
