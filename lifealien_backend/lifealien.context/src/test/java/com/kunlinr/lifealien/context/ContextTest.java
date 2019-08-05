package com.kunlinr.lifealien.context;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import org.junit.jupiter.api.Test;

import com.kunlinr.lifealien.context.store.Store;
import com.kunlinr.lifealien.context.store.TypeReference;
import com.kunlinr.lifealien.context.store.Store.Key;

public class ContextTest {
	@Test
	public void Test1() {
		Runnable a = new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable");
			}
		};
		Store context = new Store();
//		context.put("a", a, Runnable.class);
//		context.get("a", Runnable.class);
	}
	
	@Test
	public void Test2() {
		Runnable a = new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable");
			}
		};
		Runnable a1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable1");
			}
		};
		Executor b = new Executor() {
			@Override
			public void execute(Runnable command) {
				System.out.println(command.getClass());
			}
		};
		Store context = new Store();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("1231");
		List<String> arrayList2 = new LinkedList<String>();
		arrayList2.add("12311");
//		Key<Runnable> key = context.new Key<Runnable>("a",Runnable.class);
//		Key<Executor> key2 = context.new Key<Executor>("a",Executor.class);
		context.put("a", a, new TypeReference<Runnable>() {});
		context.put("a", a1, Runnable.class);
		context.put("a", arrayList2, new TypeReference<List<String>>() {});
		context.put("a", arrayList, new TypeReference<ArrayList<String>>() {});
		context.put("a", b, Executor.class);
		new Thread(context.get("a", new TypeReference<Runnable>() {})).start();
		List<String> list = context.get("a", new TypeReference<ArrayList<String>>() {});
		System.out.println(list);
//		List<Runnable> list = new ArrayList<Runnable>();
//		context.put("a", list, new TypeReference<List<Runnable>>() {});
//		List<Runnable> list2 = context.get("a", );
		
//		System.out.println(context.get("a", Runnable.class));
//		System.out.println(context.get("a", List.class));
//		Key<Runnable> key3 = context.new Key<Runnable>("a",Runnable.class);
//		System.out.println(context.get(key3));
	}
}
