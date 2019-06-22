package com.kunlinr.lifealien.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ThreadTest {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Math.random()*100);
				}
			});
		}
		threadPool.shutdown();
		while (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
		    Logger.getLogger("123").warning("线程还在执行");
		}
		long end = System.currentTimeMillis()-start;
		Logger.getLogger("123").warning("共消耗时间"+end);
	}
}
