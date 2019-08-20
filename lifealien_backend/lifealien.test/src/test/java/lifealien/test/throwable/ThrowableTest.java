package lifealien.test.throwable;

import java.awt.List;

import org.junit.Test;

public class ThrowableTest {
	@Test
	public void Test1() {
		List array = null;
		try {
			array.toString();
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception");
		}
	}
}
