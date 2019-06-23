package com.kunlinr.lifealien.collection.hashmap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.log.Log;

/**
 * - HashMap、和Hashtable、SynchronizedMap区别：
    - HashMap 线程不安全，可以有null的key值或value值。
    - hashtable 线程安全，不能有null的key值或value值。
    - ConcurrentHashMap 线程安全，不能有null的key值或value值。删除操作比较费时。
    - SynchronizedMap 线程安全，可以有null的key值或value值。
        -  可以通过```Collections.synchronizedMap(new HashMap<String, Object>());```方式创建
    - 性能:HashMap>ConcurrentHashMap>SynchronizedMap>Hashtable
 * @author kunlingou
 */
public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		
		Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<String, Object>());
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String,Object>();
//		hashMap.put(null, "1");
//		hashMap.put(null, null);
//		
//		
//		hashtable.put("1", "1");
////		hashtable.put("1", null);
//		
//		synchronizedMap.put(null, "1");
//		synchronizedMap.put(null, null);
		
//		concurrentHashMap.put(null, "1");
//		concurrentHashMap.put(null, null);
		
//		syncMapPerformance(hashtable);
//		syncMapPerformance(synchronizedMap);
//		syncMapPerformance(concurrentHashMap);
//		syncMapPerformance(hashMap);
		
//		float v = 0.0f/0.0f;
//		System.out.println(Float.isNaN(v));
//		System.out.println(v != v);
		
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>(1 << 4);
		
		for(int i=0;i<13;i++) {
			if(i==12) {
				System.out.println();
			}
			hashMap2.put(String.valueOf(i), 0);
		}
		
		int tableSizeFor = tableSizeFor(65);
		System.out.println(tableSizeFor);
	}
	
	/**
	* 获取不小于当前数的最小的2^k的值.
	* 例如：31->32,65->128
	*/
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n+1;
    }
	
	/**
	 * 测试多线程下各种map的性能
	 * 性能:HashMap(线程不安全)>ConcurrentHashMap>SynchronizedMap>Hashtable
	 * @param map
	 * @throws InterruptedException
	 * @see HashMap
	 * @see ConcurrentHashMap
	 * @see Collections.SynchronizedMap
	 * @see Hashtable
	 */
	public static void syncMapPerformance(Map<String,Object> map) throws InterruptedException {
		System.out.println("start for : "+map.getClass());
		long start = System.nanoTime();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++) {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<100000;i++) {
						String value = String.valueOf(Math.round(Math.random()*10000));
						map.get(value);
						map.put(value, value);
					}
				}
			});
		}
		newFixedThreadPool.shutdown();
		newFixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		long end = System.nanoTime()-start;
		System.out.println("cost for : "+end);
	}
}
