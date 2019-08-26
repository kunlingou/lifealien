package org.lifealien.util.type;

import java.util.UUID;

import org.lifealien.util.SyntaxException;

public class UUIDType {
	/**
	 * 字符串转换成UUID，兼容32位和36位
	 * @param value
	 * @return
	 */
	public static UUID toUUID(String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		String[] components = value.split("-");
		if (components.length == 5) {
			for (int i = 0; i < 5; i++)
				components[i] = "0x" + components[i];

			long mostSigBits = Long.decode(components[0]).longValue();
			mostSigBits <<= 16;
			mostSigBits |= Long.decode(components[1]).longValue();
			mostSigBits <<= 16;
			mostSigBits |= Long.decode(components[2]).longValue();

			long leastSigBits = Long.decode(components[3]).longValue();
			leastSigBits <<= 48;
			leastSigBits |= Long.decode(components[4]).longValue();

			return new UUID(mostSigBits, leastSigBits);
		} else if (components.length == 1 && value.length() == 32) {
			try {
				long mostSigBits = hexToLong(value, 0);
				long leastSigBits = hexToLong(value, 16);
				return new UUID(mostSigBits, leastSigBits);
			} catch (Exception e) {
				throw new IllegalArgumentException("Invalid UUID string: " + value, e);
			}
		} else {
			throw new IllegalArgumentException("Invalid UUID string: " + value);
		}
	}
	
	private static long hexToLong(String str, int start) throws StringIndexOutOfBoundsException, SyntaxException {
		long temp = parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		temp = temp << 4 | parseChar(str, start++);
		return temp << 4 | parseChar(str, start++);
	}
	
	static int parseChar(String s, int offset) throws SyntaxException,StringIndexOutOfBoundsException {
		char c = s.charAt(offset);
		if (c < '0') {
		} else if (c <= '9') {
			return c - '0';
		} else if (c < 'A') {
		} else if (c <= 'F') {
			return c - h2b_A_10;
		} else if (c < 'a') {
		} else if (c <= 'f') {
			return c - h2b_a_10;
		}
		throw new SyntaxException("在偏移量" + offset + "处出现无效的十六进制字符'" + c
				+ "'");
	}
	
	final static int h2b_A_10 = 'A' - 10;
	final static int h2b_a_10 = 'a' - 10;
	
	
}
