package com.kunlinr.lifealien.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<String>{
	@Override
	public String call() throws Exception {
		for(int x =0;x<10;x++) {
			Thread.sleep(1000);
			System.out.println("线程执行，x="+x);
		}
		return "线程执行完毕";
	}
}
public class ThreadDemo{
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<>(new MyThread());
		new Thread(task).start();
		System.out.println("线程返回数据:"+task.get());
	}
}