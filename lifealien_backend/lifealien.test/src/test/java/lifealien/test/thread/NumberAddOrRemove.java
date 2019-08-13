package lifealien.test.thread;

public class NumberAddOrRemove {
	public static void main(String[] args) {
		Resource resource = new Resource(0);
		Add add = new Add(resource);
		Remove remove = new Remove(resource);
		new Thread(add,"【加法A】").start();
		new Thread(add,"【加法B】").start();
		new Thread(remove,"【减法A】").start();
		new Thread(remove,"【减法B】").start();
	}
}

/**
 * 资源
 * @author kunlingou
 */
class Resource{
	private int num = 0;
	private boolean isAdded = false;
	public Resource(int num) {
		this.num = num;
	}
	public synchronized void add(int i) {
		while(isAdded) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num = num+i;
		this.isAdded = true;
		System.out.println(Thread.currentThread().getName()+num);
		this.notifyAll();
	}
	public synchronized void remove(int i) {
		while(!isAdded) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num = num-i;
		System.out.println(Thread.currentThread().getName()+num);
		this.isAdded = false;
		this.notifyAll();
	}
}

/**
 * 线程（增加）
 * @author kunlingou
 *
 */
class Add implements Runnable{
	Resource resource;
	public Add(Resource resource) {
		this.resource = resource;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			resource.add(1);
		}
	}
}

/**
 * 线程（减少）
 * @author kunlingou
 *
 */
class Remove implements Runnable{
	Resource resource;
	public Remove(Resource resource) {
		this.resource = resource;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			resource.remove(1);
		}
	}
}