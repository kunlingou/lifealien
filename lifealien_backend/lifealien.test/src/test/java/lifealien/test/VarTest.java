package lifealien.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.junit.Test;

public class VarTest {
	
	public static void main(String[] args) {
		String S = "acd";
		int K = 1;
		S.length();
		char[] ch = S.toCharArray();
		for(int i=0;i<ch.length;i++) {
			Integer.valueOf(ch[i]);
//			ch
		}
//		Integer.MAX_VALUE
		List<Integer> list0 = new ArrayList<>();
	}
	
//	public List<List<Integer>> levelOrder(Node root) {
//        
//    }
	
//	@Test
//	public void testInOrOutFor() {
//		List<Student> stus = new ArrayList<Student>();
//		for(int i=0;i<10000;i++) {
//			stus.add(new Student("123","1111","12312312"));
//		}
//		int size = stus.size();
//		long t1,t2;
//		t1 = System.nanoTime();
//		Student stus1;
//		for(int i=0;i<size;i++) {
//			stus1 = stus.get(i);
//		}
//		t2 = System.nanoTime() - t1;
//		System.out.println("定义在for循环外："+t2);
//		
//		t1 = System.nanoTime();
//		for(int i=0;i<size;i++) {
//			Student stus2 = stus.get(i);
//		}
//		t2 = System.nanoTime() - t1;
//		System.out.println("定义在for循环内："+t2);
//	}
	
	@Test
	public void testInOrOutWhile() {
		Set<Student> newKeySet = ConcurrentHashMap.newKeySet();
		Set<Student> stus = new HashSet<Student>();
		for(int i=0;i<100000;i++) {
			stus.add(new Student("123","1111","12312312"));
		}
		int size = stus.size();
		long t1,t2;
		Iterator<Student> iterator;
		iterator = stus.iterator();
		t1 = System.nanoTime();
		Student stus1;
		while(iterator.hasNext()) {
			stus1 = iterator.next();
		}
		t2 = System.nanoTime() - t1;
		System.out.println("定义在while循环外："+t2);
		
		iterator = stus.iterator();
		t1 = System.nanoTime();
		while(iterator.hasNext()) {
			Student stus2 = iterator.next();
		}
		t2 = System.nanoTime() - t1;
		System.out.println("定义在while循环内："+t2);
	}
}

class Student{
	String id;
	String name;
	String title;
	public Student(String id, String name, String title) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
	}
}
