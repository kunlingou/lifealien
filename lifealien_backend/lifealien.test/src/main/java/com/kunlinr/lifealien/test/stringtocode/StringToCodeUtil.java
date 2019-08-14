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
