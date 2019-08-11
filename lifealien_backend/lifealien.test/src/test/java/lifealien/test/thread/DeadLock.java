package lifealien.test.thread;
class A{
	public synchronized void say(B xq) {
		System.out.println("A:先给钱，再过路");
		xq.get();
	}
	
	public synchronized void get() {
		System.out.println("A:可以买饭吃了");
	}
}
class B{
	public synchronized void say(A xq) {
		System.out.println("B:先过路，再给钱");
		xq.get();
	}
	public synchronized void get() {
		System.out.println("B:过去后可以买吃的了");
	}
}
public class DeadLock implements Runnable{
	private A a = new A();
	private B b = new B();
	@Override
	public void run() {
		a.say(b);
	}
	
	public DeadLock() {
		new Thread(this).start();
		b.say(a);
	}
	
	public static void main(String[] args) {
		new DeadLock();
	}
}
