# java积累

## 访问修饰符

|           | 同一个类 | 同一个包 | 不同包的子类 | 不同包的非子类 |
| :-------: | :------: | :------: | :----------: | :------------: |
|  Private  |    √     |          |              |                |
|  Default  |    √     |    √     |              |                |
| Protected |    √     |    √     |      √       |                |
|  Public   |    √     |    √     |      √       |       √        |

### 外观类

### 接口

- 编译前

```
public interface Parent {
	String get();
	String name = "paretn";
}
```

- 编译后

```
public abstract interface Parent
{
  public static final String name = "paretn";
  
  public abstract String get();
}
```

### 英文

| id   | identifier | 识别 |
| ---- | ---------- | ---- |
|      |            |      |
|      |            |      |
|      |            |      |

### 其他

- Optional
- AutoCloseable

### Java 8 新特性

- Lambda 表达式
- 方法引用
- 默认方法
- 新工具
- Stream API
- Date Time API
- Optional 类
- Nashorn，JavaScript 引擎