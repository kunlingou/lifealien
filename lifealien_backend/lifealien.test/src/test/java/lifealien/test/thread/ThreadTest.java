package lifealien.test.thread;

public class ThreadTest {	
	public static void main(String[] args) throws InterruptedException {
		Runnable run = () ->{
			for(int x =0; x<5; x++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+",x="+x);
			}
		};
		Runnable run2 = () ->{
			for(int x =0; x<Integer.MAX_VALUE; x++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+",x="+x);
			}
		};
		Thread threadA = new Thread(run, "用户线程");
		Thread threadB = new Thread(run2, "守护线程");
		threadB.setDaemon(true);//设置为守护线程
		threadA.start();
		threadB.start();
	}
}
