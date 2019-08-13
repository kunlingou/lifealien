### 线程常用操作方法

#### 休眠

- sleep

```
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
```

#### 中断

- interrupt

```
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
```

#### 强制执行

- join

```
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
```

#### 线程礼让

- yield
- 礼让一次

```
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
```

#### 线程优先级

- 设置优先级：setPriority
- 获取优先级：getPriority
- 优先级高有可能先执行
- 主方法的优先级为普通

```
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
```

### 线程同步与死锁

#### 同步

- synchronized
- 同步方法，相当于锁定this

- 现象

```
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

[A]卖票.ticket = 10
[B]卖票.ticket = 9
[C]卖票.ticket = 10
[A]卖票.ticket = 8
[C]卖票.ticket = 7
[B]卖票.ticket = 6
[A]卖票.ticket = 5
[C]卖票.ticket = 5
[B]卖票.ticket = 5
[C]卖票.ticket = 4
[A]卖票.ticket = 4
[B]卖票.ticket = 4
[A]卖票.ticket = 3
[B]卖票.ticket = 2
[C]卖票.ticket = 2
[B]卖票.ticket = 0
票已经卖光了
[C]卖票.ticket = 0
票已经卖光了
[A]卖票.ticket = 1
票已经卖光了
```

- synchronized关键字
- 性能降低，系统中多采用同步方法。

```
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
```

#### 死锁

- 线程同步可能会导致死锁

```
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
```

### 生产者消费者模型

- 生产一个消费一个，不断循环

#### 相关问题

- 同步问题
- 重复操作问题

#### 简单的生产者消费者模型

```
public class Test {
	public static void main(String[] args) {
		Message message = new Message();
		Producer producer = new Producer();
		producer.setMessage(message);
		Consumer consumer = new Consumer();
		consumer.setMessage(message);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}

/**
 * 生产者
 * @author kunlingou
 */
class Producer implements Runnable{
	Message message;
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message produce() {
		for(int i=0;i<100;i++) {
			if(i%2 == 0) {
				message.setKey("key1");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				message.setContent("content1");
			}else {
				message.setKey("key2");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				message.setContent("content2");
			}
			System.out.println("【生产者】"+message.getKey()+"  -  "+message.getContent());
		}
		return message;
	}

	@Override
	public void run() {
		produce();
	}
}


/**
 * 消费者
 * @author kunlingou
 */
class Consumer implements Runnable{
	Message message;
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message consum() {
		for(int i=0;i<100;i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("【消费者】:"+message.getKey()+"  -  "+message.getContent());
		}
		return message;
	}

	@Override
	public void run() {
		consum();
	}
}

/**
 * 消息（产品）
 * @author kunlingou
 */
class Message {
	String key;
	Object content;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
}

【生产者】key1  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【消费者】:key2  -  content1
【生产者】key2  -  content2
【消费者】:key1  -  content2
【消费者】:key1  -  content2
【消费者】:key1  -  content2
【消费者】:key1  -  content2
【消费者】:key1  -  content2
【消费者】:key1  -  content2
【消费者】:key1  -  content2
【生产者】key1  -  content1
【生产者】key2  -  content2
```

#### 解决同步问题

- 将生产、消费操作放到Message中，作为原子，用synchronized保证同步。

```
package lifealien.test.thread.producerandconsumer;

public class Test {
	public static void main(String[] args) {
		Message message = new Message();
		Producer producer = new Producer();
		producer.setMessage(message);
		Consumer consumer = new Consumer();
		consumer.setMessage(message);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}

/**
 * 生产者
 * @author kunlingou
 */
class Producer implements Runnable{
	Message message;
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message produce() {
		for(int i=0;i<100;i++) {
			if(i%2 == 0) {
				message.set("key1", "content1");
			}else {
				message.set("key2", "content2");
			}
		}
		return message;
	}

	@Override
	public void run() {
		produce();
	}
}


/**
 * 消费者
 * @author kunlingou
 */
class Consumer implements Runnable{
	Message message;
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message consum() {
		for(int i=0;i<100;i++) {
			message.get();
		}
		return message;
	}

	@Override
	public void run() {
		consum();
	}
}

/**
 * 消息（产品）
 * @author kunlingou
 */
class Message {
	String key;
	Object content;
	
	/**
	 * 生产
	 * @param key
	 * @param content
	 */
	public synchronized void set(String key, Object content) {
		this.key = key;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.content =content;
		System.out.println("【生产者】:"+this.key+"  -  "+this.content);
	}
	
	/**
	 * 消费
	 * @return
	 */
	public synchronized Message get() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("【消费者】:"+this.key+"  -  "+this.content);
		return this;
	}
}


【消费者】:null  -  null
【消费者】:null  -  null
【消费者】:null  -  null
【生产者】:key1  -  content1
【生产者】:key2  -  content2
【生产者】:key1  -  content1
【生产者】:key2  -  content2
【生产者】:key1  -  content1
```

#### 解决重复操作问题

```
package lifealien.test.thread.producerandconsumer;

public class Test {
	public static void main(String[] args) {
		Message message = new Message();
		Producer producer = new Producer();
		producer.setMessage(message);
		Consumer consumer = new Consumer();
		consumer.setMessage(message);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}

/**
 * 生产者
 * @author kunlingou
 */
class Producer implements Runnable{
	Message message;
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message produce() {
		for(int i=0;i<100;i++) {
			if(i%2 == 0) {
				message.set("key1", "content1");
			}else {
				message.set("key2", "content2");
			}
		}
		return message;
	}

	@Override
	public void run() {
		produce();
	}
}


/**
 * 消费者
 * @author kunlingou
 */
class Consumer implements Runnable{
	Message message;
	public void setMessage(Message message) {
		this.message = message;
	}
	public Message consum() {
		for(int i=0;i<100;i++) {
			message.get();
		}
		return message;
	}

	@Override
	public void run() {
		consum();
	}
}

/**
 * 消息（产品）
 * @author kunlingou
 */
class Message {
	String key;
	Object content;
	/**
	 * 是否生产完成
	 */
	boolean isProduced;
	/**
	 * 生产
	 * @param key
	 * @param content
	 */
	public synchronized void set(String key, Object content) {
		if(isProduced) {
			try {
				wait();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.key = key;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.content =content;
		System.out.println("【生产者】:"+this.key+"  -  "+this.content);
		this.isProduced = true;
		notify();
	}
	
	/**
	 * 消费
	 * @return
	 */
	public synchronized Message get() {
		if(!isProduced) {
			try {
				wait();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("【消费者】:"+this.key+"  -  "+this.content);
		this.isProduced = false;
		notify();
		return this;
	}
}


【生产者】:key1  -  content1
【消费者】:key1  -  content1
【生产者】:key2  -  content2
【消费者】:key2  -  content2
【生产者】:key1  -  content1
【消费者】:key1  -  content1
【生产者】:key2  -  content2
【消费者】:key2  -  content2
【生产者】:key1  -  content1
【消费者】:key1  -  content1
```

### 多线程深入话题

#### 优雅地停止线程

```
public static boolean flag = true;
	public static void main(String[] args) throws InterruptedException {
		Runnable run = () ->{
			for(int x =0; x<10; x++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(flag) {
					System.out.println(Thread.currentThread().getName()+",x="+x);
				}
			}
		};
		Thread threadA = new Thread(run, "子线程A");
		threadA.start();
		Thread.sleep(200);
		flag = false;
	}
```

#### 后台守护线程

- GC是最大的后台守护线程

```
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
	

用户线程,x=0
守护线程,x=0
守护线程,x=1
用户线程,x=1
用户线程,x=2
守护线程,x=2
守护线程,x=3
用户线程,x=3
守护线程,x=4
用户线程,x=4
```

#### volatile关键字

- 进程变量操作时，拷贝副本，修改数据，同步数据。增加volatile时，直接内存操作。

### 综合实例

#### 数字加减

- 两个线程做加法，两个线程做减法。

```
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
```

#### 生产电脑

```

```

