package lifealien.test.lib;

public class AutoClosed {
	public static void main(String[] args) {
		try (Message message = new Message()){
			message.send("12345678");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
class Message implements AutoCloseable{
	public void send(String content) {
		System.out.println("【send】"+content);
	}
	@Override
	public void close() throws Exception {
		System.out.println("【close】");
	}
}
