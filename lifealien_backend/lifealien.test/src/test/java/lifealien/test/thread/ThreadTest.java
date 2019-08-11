package lifealien.test.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {
	@Test
	public void ThreadSleepTest1(){
		Runnable run = () ->{
			for(int x =0; x<10; x++) {
				System.out.println(Thread.currentThread().getName()+",x="+x);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		for(int num=0 ;num<5; num++) {
			new Thread(run, "线程对象 ="+num).start();;
		}
	}
	
	public static void main1(String[] args) {
		Runnable run = () ->{
			for(int x =0; x<10; x++) {
				System.out.println(Thread.currentThread().getName()+",x="+x);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		for(int num=0 ;num<5; num++) {
			new Thread(run, "线程对象 ="+num).start();;
		}
	}
	
	public static void main2(String[] args) throws InterruptedException {
		Runnable run = () ->{
			System.out.println("睡觉10s");
			try {
				Thread.sleep(10000);
				System.out.println("睡觉完成");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Thread thread = new Thread(run);
		thread.start();
		Thread.sleep(1000);
		if(!thread.isInterrupted()) {
			System.out.println("打断");
			thread.interrupt();
		}
//		for(int num=0 ;num<5; num++) {
//			new Thread(run, "线程对象 ="+num).start();;
//		}
	}
	
	public static void main3(String[] args) {
		Thread main = Thread.currentThread();
		Runnable run = () ->{
			for(int x =0; x<10; x++) {
				System.out.println(Thread.currentThread().getName()+",x="+x);
				if(x == 3) {
					try {
						main.join();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(run, "子线程");
		thread.start();
		for(int num=0 ;num<50; num++) {
			System.out.println("【主线程】"+",num="+num);
		}
	}
	
	public static void main4(String[] args) throws InterruptedException {
		Thread main = Thread.currentThread();
		Runnable run = () ->{
			for(int x =0; x<10; x++) {
				if(x==3) {
					Thread.yield();
					System.out.println("【线程礼让】");
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+",x="+x);
			}
		};
		Thread thread = new Thread(run, "子线程");
		thread.start();
		for(int num=0 ;num<50; num++) {
			Thread.sleep(100);
			System.out.println("【主线程】"+",num="+num);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread main = Thread.currentThread();
		Runnable run = () ->{
			for(int x =0; x<10; x++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+",x="+x);
			}
		};
		Thread threadA = new Thread(run, "子线程A");
		Thread threadB = new Thread(run, "子线程B");
		Thread threadC = new Thread(run, "子线程C");
		threadA.setPriority(Thread.MIN_PRIORITY);
		threadB.setPriority(Thread.MIN_PRIORITY);
		threadC.setPriority(Thread.MAX_PRIORITY);
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
