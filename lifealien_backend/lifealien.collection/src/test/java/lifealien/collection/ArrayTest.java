package lifealien.collection;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ArrayTest {
	
	/**
	 * 数组被引用更新是否会改变原值？
	 * 会！
	 */
	@Test
	public void Test1() {
		String[] array = new String[] {"name1","name2"};
		updateTest1(array);
		System.out.println(Arrays.toString(array));
	}

	private void updateTest1(String[] array) {
		array[0] = "name2";
	}
}
