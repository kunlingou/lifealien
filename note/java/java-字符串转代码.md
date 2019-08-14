- 依赖

```
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-jexl3</artifactId>
    <version>3.1</version>
</dependency>
```

- 工具类

```
package com.kunlinr.lifealien.test.stringtocode;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;

public class StringToCodeUtil {
	private static JexlEngine jexlEngine = new Engine();
	
	public static Object executeExpression(String jexlExpression, Map<String, Object> map) {
	    JexlExpression expression = jexlEngine.createExpression(jexlExpression);
	    JexlContext context = new MapContext();
	    if (!Objects.isNull(map) && map.size()>0) {
	        map.forEach(context::set);
	    }
	    return expression.evaluate(context);
	}
}
```

- 测试

```
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


//coding every day
```

