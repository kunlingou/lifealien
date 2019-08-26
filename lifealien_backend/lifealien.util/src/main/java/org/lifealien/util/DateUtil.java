package org.lifealien.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateUtil {
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getTime() {
		Calendar instance = Calendar.getInstance();
//		instance.add(Calendar.DATE, 1);
		
//		Date date = new Date();
//        Instant instant = date.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
//        
//        LocalDateTime localDateTime = LocalDateTime.now();
//        ZonedDateTime zdt = localDateTime.atZone(zoneId);
//        Date date = Date.from(zdt.toInstant());
		return instance.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.getTime());
		boolean flag = true;
		for(int i=0;i<10 && flag;i++) {
			System.out.println("【外层循环】"+i);
			for(int j=0;j<5;j++) {
				System.out.println("【内层循环】"+j);
				if(j==4) {
					flag = false;
					break;
				}
			}
		}
		String a = "一";
		System.out.println(Arrays.toString(a.getBytes()));
		
		Set<Short> set = new HashSet<>();
		for (short i = 0; i < 5; i++) {
		    set.add(i);
		    set.remove(i-1);
		}
		System.out.println(set.size());
		
		String str1 = "123";
		String str2 = "456";
		String str4 = str1+str2;
		String str5 = "123456";
		String str3 = "123456";
		str1.length();
		System.out.println(str3.substring(3,3));
		
		StringBuffer sf = new StringBuffer("hi,");
		changeSf(sf);
		System.out.println(sf);
	}
	public static void changeSf(StringBuffer sf){
//	    sf.append("laowang");
		sf = new StringBuffer("laowang");
	}
}
