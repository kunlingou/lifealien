### java基础类库

#### StringBuffer

- StringBuffer
- StringBuilder

#### CharSequence接口

- String
- StringBuffer
- StringBuilder

#### AutoCloseable接口

- 1.7+
- 实现自动关闭处理

```
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


【send】12345678
【close】
```

#### Runtime类

- 单例
- 内存
- 垃圾回收

#### System类

- arraycopy
- currentTimeMillis
- gc

#### Cleaner 

- 1.9+

- finialize
- 单独线程完成

#### 对象克隆

- Object.clone()
- 实现Cloneable接口

