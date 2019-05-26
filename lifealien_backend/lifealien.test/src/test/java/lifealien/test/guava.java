package lifealien.test;

import org.junit.Test;

import com.google.common.base.CharMatcher;

public class guava {
	@Test
	public void guava1() {
		boolean matchesAllOf = CharMatcher.is('a').matchesAllOf("a");
		System.out.println(matchesAllOf);
	}
}
