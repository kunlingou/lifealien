package org.lifealien.util;

public class Test {
	public static void main(String[] args) {
		//h\x98B\xDC \x00\x00\x01\x05zt\xC9,\xA9\xDB\xA7
		byte[] bytes = new byte[] {'9',};
		TypeUtil.getString(bytes);
	}
}
