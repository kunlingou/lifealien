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
