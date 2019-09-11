package org.lifealien.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;

import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class TypeUtil {
	public static String getString(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toHexString((bytes[i] & 0x000000FF) | 0xFFFFFF00).substring(6));
		}
		return sb.toString().toUpperCase();
	}

//	public static UUID getUUID(Object o) {
//		try {
//			if(o instanceof byte[]) {
//				String str = getString(o);
//				return UUIDType.toUUID(str);
//			}else if(o instanceof String) {
//				return UUIDType.toUUID((String)o);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	public static GUID getGUID(UUID o) {
//		String str = o.toString().replaceAll("-", "").toUpperCase();
//		if(str.length() ==32) {
//			return GUID.valueOf(str);
//		}
//		return null;
//	}

//	public static Map<String,String> changeToFormDataType(Map<String,Object> map){
//		Map<String,String> formData = new HashMap<>();
//		if(map != null) {
//			for(Entry<String,Object> e:map.entrySet()) {
//				formData.put(e.getKey(), getString(e.getValue()));
//			}
//		}
//		return formData;
//	}

//	public static String getString(Object obj) {
//		if(obj ==null) return "";
//		if(obj instanceof byte[]) {
//			return getString((byte[])obj);
//		}else if(obj instanceof NCLOB) {
//			try {
//				return ((NCLOB)obj).getSubString(1, (int) ((NCLOB)obj).length());
//			} catch (SQLException e) {
//				return String.valueOf(obj);
//			}
//		}
//		return String.valueOf(obj);
//	}

	/**
	 * @see StringUtils#arrayToDelimitedString(Object[], String)
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		return arrayToDelimitedString(arr, delim, null);
	}

	/**
	 * @see StringUtils#arrayToDelimitedString(Object[], String)
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim, String format) {
		if (ObjectUtils.isEmpty(arr)) {
			return "";
		}
		if (arr.length == 1) {
			return String.format(format, ObjectUtils.nullSafeToString(arr[0]));
		}
		if (ObjectUtils.isEmpty(format)) {
			return StringUtils.arrayToDelimitedString(arr, delim);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(String.format(format, arr[i]));
		}
		return sb.toString();
	}

	/**
	 * JsonNode转Properties
	 * 
	 * @param body 传入的jsonNode对象
	 * @param mode 转化模式：deep:深度模式；shallow:浅度模式。
	 * @return
	 */
	public static Properties getProperties(JsonNode body, String mode) {
		Properties properties = new Properties();
		if (body == null)
			return properties;
		if (body.isValueNode()) {
			ValueNode content = (ValueNode) body;
			// TODO
		} else if (body.isObject()) {
			ObjectNode content = (ObjectNode) body;
			Iterator<String> fieldNames = content.fieldNames();
			String fieldName;
			while (fieldNames.hasNext() && (fieldName = fieldNames.next()) != null) {
				if ("shallow".equals(mode)) {
					properties.setProperty(fieldName, content.get(fieldName).asText());
				}
			}
		}
		return properties;
	}

	public static <E> String getString(Collection<E> e) {
		return e.toString();
	}

//	public static Object toJSON(Object e) {
//		return JSON.toJSON(e);
//	}
//	
//	public static Object toJSONString(Object e,SerializerFeature... features) {
//		return JSON.toJSONString(e,features);
//	}
//	
//	public static JSONObject toJSON(KObject object) {
//		if(object == null) return new JSONObject();
//		KClass clazz = object.getType();
//		List<Attribute> attrs = clazz.getAttributes();
//		JSONObject result = new JSONObject();
//		for (Attribute a : attrs) {
//			if (a.getType().isArray()) {
//				continue;
//			}
//			if (a.getType().isMap()) {				
//					result.put(a.getName(), toJSON(object.get(a.getName())));			
//			} else {
//				Object value = object.getObject(a.getName());
//				result.put(a.getName(), value == null ? JSONObject.NULL : value);
//			}
//		}
//		return result;
//	}

	/**
	 * 16进制转换成为string类型字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String hexStringToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "UTF-8");
			new String();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * hex字符串转byte数组
	 * 
	 * @param inHex 待转换的Hex字符串
	 * @return 转换后的byte数组结果
	 */
	public static byte[] hexToByteArray(String inHex) {
		int hexlen = inHex.length();
		byte[] result;
		if (hexlen % 2 == 1) {
			// 奇数
			hexlen++;
			result = new byte[(hexlen / 2)];
			inHex = "0" + inHex;
		} else {
			// 偶数
			result = new byte[(hexlen / 2)];
		}
		int j = 0;
		for (int i = 0; i < hexlen; i += 2) {
			result[j] = hexToByte(inHex.substring(i, i + 2));
			j++;
		}
		return result;
	}

	/**
	 * Hex字符串转byte
	 * 
	 * @param inHex 待转换的Hex字符串
	 * @return 转换后的byte
	 */
	public static byte hexToByte(String inHex) {
		return (byte) Integer.parseInt(inHex, 16);
	}

	/**
	 * 是否是Hex（16进制）字符串
	 * 
	 * @return
	 */
	public static boolean isHexString(char... chars) {
		if (chars.length == 0) {
			return false;
		}
		for (char ch : chars) {
			if ((ch < '0' || ch > '9') && (ch < 'A' || ch > 'F') && (ch < 'a' || ch > 'f')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			return (obj == null || "".equals(obj));
		} else {
			return Objects.isNull(obj);
		}
	}

	/**
	 * 二进制转字符串
	 * 
	 * @param str
	 * @return
	 */
	private String binaryStrToStr(String str) {
		return binaryArrayToStr(str.split(" "));
	}

	/**
	 * 二进制转字符串
	 * 
	 * @param str
	 * @return
	 */
	private static String binaryArrayToStr(String[] str) {
		String[] list = str.clone();
		ByteBuffer bytes = ByteBuffer.allocate(list.length);
		for (int i = 0; i < list.length; i++) {
			bytes.put(Long.valueOf(list[i], 2).byteValue());
		}
		bytes.flip();
		CharBuffer decode = Charset.defaultCharset().decode(bytes);
		return decode.toString();
	}

	/**
	 * 字符串转二进制字符串
	 * 
	 * @param str
	 * @return
	 */
	private static String[] strToBinaryArray(String str) {
		byte[] bytes = strToByteArray(str);
		String[] binarys = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			binarys[i] = Long.toString(bytes[i] & 0xff, 2);
		}
		return binarys;
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @param str
	 * @return
	 */
	private static byte[] strToByteArray(String str) {
		return Charset.defaultCharset().encode(str).array();
	}

	public static void main(String[] args) {
		String[] binarys = strToBinaryArray("用户名：2341");
		System.out.println(Arrays.deepToString(binarys));
//		String binaryArrayToStr = binaryArrayToStr(binarys);
//		System.out.println(binaryArrayToStr);
	}
}
