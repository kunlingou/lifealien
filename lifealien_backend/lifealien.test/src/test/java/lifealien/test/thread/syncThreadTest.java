package lifealien.test.thread;

public class syncThreadTest {
	
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		new Thread(myThread,"[A]").start();
		new Thread(myThread,"[B]").start();
		new Thread(myThread,"[C]").start();
	}
}

class MyThread implements Runnable{
	private int ticket = 10;
	@Override
	public void run() {
		while(true) {
			synchronized (this) {
				if(this.ticket>0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"卖票.ticket = "+ this.ticket --);
				}else {
					System.out.println("票已经卖光了");
					break;
				}
			}
		}
	}
}