### java程序是如何运行的

#### java和jdk

- JDK（Java Development Kit）Java 开发工具包 :编译器、Java 运行环境（JRE，Java Runtime Environment）、JVM（Java 虚拟机）监控和诊断工具等，而 Java 则表示一种开发语言。 

#### Java 程序是怎么执行的？

- 把 Java 代码编译成字节码，也就是把 .java 类型的文件编译成 .class 类型的文件。 Java 源代码 -> 词法分析器 -> 语法分析器 -> 语义分析器 -> 字符码生成器 -> 最终生成字节码 。
- 把 class 文件放置到 Java 虚拟机，这个虚拟机通常指的是 Oracle 官方自带的 Hotspot JVM。
- java 虚拟机使用类加载器（Class Loader）装载 class 文件 。
- 类加载完成之后，会进行字节码效验，字节码效验通过之后 JVM 解释器会把字节码翻译成机器码交由操作系统执行。但不是所有代码都是解释执行的，JVM 对此做了优化，比如，以 Hotspot 虚拟机来说，它本身提供了 JIT（Just In Time）也就是我们通常所说的动态编译器，它能够在运行时将热点代码编译为机器码，这个时候字节码就变成了编译执行。

#### Java 虚拟机是如何判定热点代码的？

- 基于采样的热点判定
- 基于计数器的热点判定

#### Java 中如何跳出多重嵌套循环

- break+标号

```
breakFor:for(int i=0;i<10;i++) {
	System.out.println("【外层循环】"+i);
	for(int j=0;j<5;j++) {
		System.out.println("【内层循环】"+j);
		if(j==4) break breakFor;
	}
}
```

- 变量

```
boolean flag = true;
for(int i=0;i<10 && flag;i++) {
	System.out.println("【外层循环】"+i);
	for(int j=0;j<5;j++) {
		System.out.println("【内层循环】"+j);
		if(j==4) {
			flag = false;
			break;
		}
	}
}
```

#### 如何打印数组内容

```
Arrays.toString(a.getBytes())
```

#### Java 中会存在内存泄漏吗？请简单描述一下

Java 中的内存泄漏的常见情景如下：

- 长生命周期对象持有短生命的引用，比如，缓存系统，我们加载了一个对象放在缓存中，然后一直不使用这个缓存，由于缓存的对象一直被缓存引用得不到释放，就造成了内存泄漏；
- 各种连接未调用关闭方法，比如，数据库 Connection 连接，未显性地关闭，就会造成内存泄漏；
- 内部类持有外部类，如果一个外部类的实例对象的方法返回了一个内部类的实例对象，这个内部类对象被长期引用了，即使那个外部类实例对象不再被使用，但由于内部类持有外部类的实例对象，这个外部类对象将不会被垃圾回收，这也会造成内存泄露；
- 改变哈希值，当一个对象被存储进 HashSet 集合中以后，就不能修改这个对象中的那些参与计算哈希值的字段了，否则对象修改后的哈希值与最初存储进 HashSet 集合中时的哈希值就不同了，在这种情况下，即使在 contains 方法使用该对象的当前引用作为的参数去 HashSet 集合中检索对象，也将返回找不到对象的结果，这也会导致无法从 HashSet 集合中单独删除当前对象，造成内存泄露。

### 基础数据类型和包装类 

- 布尔型：boolean
- 整数型：byte、short、int、long
- 浮点型：float、double
- 字符型：char

#### 包装类型的特点

- 功能丰富
- 可定义泛型类型参数
- 序列化
- 类型转换
- 高频区间的数据缓存
    - Float 和 Double 不会有缓存，其他包装类都有缓存。 
    - Integer 是唯一一个可以修改缓存范围的包装类，在 VM optons 加入参数： 
      - -XX:AutoBoxCacheMax=666 即修改缓存最大值为 `666` 

```
public static Integer valueOf(int i) {
	if (i >= IntegerCache.low && i <= IntegerCache.high)
		return IntegerCache.cache[i + (-IntegerCache.low)];
	return new Integer(i);
}
```

- Integer 和 int 比较时，会自动拆箱为 int 相当于两个 int 比较 

#### short

```
Set<Short> set = new HashSet<>();
for (short i = 0; i < 5; i++) {
    set.add(i);
    set.remove(i - 1);
}
System.out.println(set.size());//5
```

- Short 类型 -1 之后转换成了 Int 类型，remove() 的时候在集合中找不到 Int 类型的数据，所以就没有删除任何元素，执行的结果就是 5 。

```
short s=2;s=s+1; 会报错吗？short s=2;s+=1; 会报错吗？
```

- s=s+1 会报错，s+=1 不会报错，因为 s=s+1 会导致 short 类型升级为 int 类型，所以会报错，而 s+=1 还是原来的 short 类型，所以不会报错。 

#### `float f=3.4;` 会报错吗？为什么？

- 会报错，因为值 3.4 是 double 类型，float 类型级别小于 double 类型，所以会报错。

#### 为什么需要包装类？

- ① Java 的设计思想是万物既对象，包装类体现了面向对象的设计理念；
- ② 包装类包含了很多属性和方法，比基础数据类型功能多，比如提供的获取哈希值（hashCode）或获取类（getClass）的方法等。 

#### 泛型可以为基础类型吗？为什么？

- 泛型不能使用基础数据类型。泛型在 JVM（Java虚拟机）编译的时候会类型檫除，比如代码 `List<Integer> list` 在 JVM 编译的时候会转换为 `List list` ，因为泛型是在 JDK 5 时提供的，而 JVM 的类型檫除是为了兼容以前代码的一个折中方案，类型檫除之后就变成了 Object，而 Object 不能存储基础数据类型，但可以使用基础数据类型对应的包装类，所以像 `List<int> list` 这样的代码是不被允许的，编译器阶段会检查报错，而 `List<Integer> list` 是被允许的。 

#### 选择包装类还是基础类的原则有哪些？

- 所有 POJO 类属性必须使用包装类；
- RPC 方法返回值和参数必须使用包装类；
- 所有局部变量推荐使用基础数据类型。

#### 基础数据类型在 JVM 中一定存储在栈中吗？为什么？

- 当基础数据类型为局部变量的时候，比如在方法中声明的变量，则存放在方法栈中的，当方法结束系统会释放方法栈，在该方法中的变量也会随着栈的销毁而结束，这也是局部变量只能在方法中使用的原因；
- 当基础数据类型为全局变量的时候，比如类中的声明的变量，则存储在堆上，因为全局变量不会随着某个方法的执行结束而销毁。

#### 3*0.1==0.3 返回值是多少？

- 返回值为：false。因为有些浮点数不能完全精确的表示出来。0.30000000000000004 

 ### 深入理解字符串 

- 不可变类(immutable)，对它的任何改动，其实就是创建了一个新对象，再把引用指向该对象； 
- String 对象赋值之后就会在常量池中缓存，如果下次创建会判定常量池是否已经有缓存对象，如果有的话直接返回该引用给创建者。

#### 字符串创建

- String str = "gkl";
- String str = new String("gkl"); 

```
String s1 = "laowang";
String s2 = s1;
String s3 = new String(s1);
System.out.println(s1 == s2);//true
System.out.println(s1 == s3);//false
```

- s3 使用 new String 时一定会在堆中重新创建一个内存区域，而 s2 则会直接使用了 s1 的引用，所以得到的结果也完全不同。 

#### 字符串拼加

- String str = "lao" + "wang";
- String str = "lao"; str += "wang";
- String str = "lao"; String str2 = str + "wang";
- 但 JVM 也会对 String 进行特殊处理，以此来提供程序的运行效率 

```
String str = "hi," + "lao" + "wang";
String str2 = "hi,laowang";
System.out.println(str == str2);//true
```

#### 字符串截取

```
String str = "abcdef";
// 结果：cdef（从下标为2的开始截取到最后，包含开始下标）
System.out.println(str.substring(2));
// 结果：cd（从下标为2的开始截取到下标为4的，包含开始下标不包含结束下标）
System.out.println(str.substring(2,4));
```

#### 字符串格式化

```
String str = String.format("我叫%s，今年%d岁，喜欢%s", "老王", 30, "读书");
```

| **转换符** | **说明**             |
| ---------- | -------------------- |
| %s         | 字符串类型           |
| %d         | 整数类型（十进制）   |
| %c         | 字符类型             |
| %b         | 布尔类型             |
| %x         | 整数类型（十六进制） |
| %o         | 整数类型（八进制）   |
| %f         | 浮点类型             |
| %a         | 浮点类型（十六进制） |
| %e         | 指数类型             |
| %%         | 百分比类型           |
| %n         | 换行符               |

#### 字符对比

- equals
- equalsIgnoreCase 

#### String、StringBuffer、StringBuilder

- StringBuffer  线程安全

```
StringBuffer sf = new StringBuffer("lao");
// 添加字符串到尾部
sf.append("wang"); // 执行结果：laowang
// 插入字符串到到当前字符串下标的位置
sf.insert(0,"hi,"); // 执行结果：hi,laowang
// 修改字符中某个下标的值
sf.setCharAt(0,'H'); // 执行结果：Hi,laowang
```

#### "==" 和 equals 的区别

- ==
  - 基本类型：比较的是值是否相同；
  - 引用类型：比较的是引用是否相同。
- equals 本质上就是 `==`，只不过 String 和 Integer 等重写了 equals 方法，把它变成了值比较。 

#### 以下代码输出的结果是

```
String str = "laowang";
str.substring(0,1);
System.out.println(str);
```

- laowang因为 String 的 substring() 方法不会修改原字符串内容，所以结果还是 laowang。 

```
String str = "abcdef";
System.out.println(str.substring(3, 3));//""(空)。
```

#### 判定字符串是否为空，有几种方式？

- str.equals("")
- str.length()==0

#### String 对象的 intern() 有什么作用？

- intern() 方法用于查找常量池中是否存在该字符值，如果常量池中不存在则现在常量池中创建，如果已经存在则直接返回。 

```
String s = "laowang";
String s2 = s.intern();
System.out.println(s == s2); // 返回 true
```

#### String s=new String("laowang") 创建了几个对象？

- 总共创建了两个对象，一个是字符串 “laowang”，另一个是指向字符串的变量 s。 

#### 什么是字符串常量池？

- 字符串常量池是存储在 Java 堆内存中的字符串池，是为防止每次新建字符串带的时间和空间消耗的一种解决方案。在创建字符串时 JVM 会首先检查字符串常量池，如果字符串已经存在池中，就返回池中的实例引用，如果字符串不在池中，就会实例化一个字符串放到池中并把当前引用指向该字符串。 

#### String 不可变性都有哪些好处？

- 只有当字符串是不可变的，字符串常量池才能实现，字符串池的实现可以在运行时节约很多堆空间，因为不同的字符串变量都指向池中的同一个字符串；
- 可以避免一些安全漏洞，比如在 Socket 编程中，主机名和端口都是以字符串的形式传入，因为字符串是不可变的，所以它的值是不可改变的，否则黑客们可以钻到空子，改变字符串指向的对象的值，造成安全漏洞；
- 多线程安全，因为字符串是不可变的，所以同一个字符串实例可以被多个线程共享，保证了多线程的安全性；
- 适合做缓存的 key，因为字符串是不可变的，所以在它创建的时候哈希值就被缓存了，不需要重新计算速度更快，所以字符串很适合作缓存的中的 key。

#### String 是否可以被继承？为什么？

- String 不能被继承。因为 String 被声明为 final（最终类），所以不能被继承 。

### Java 中的运算符和流程控制 

