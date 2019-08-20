## java-注解

### 元注解(meta-annotation)

- java5.0+
- java.lang.annotation

#### @Target

- 作用：用于描述注解的使用范围。
- 取值：**ElementType** 

|       值        |             使用范围             |    备注    |
| :-------------: | :------------------------------: | :--------: |
|      TYPE       | 类、接口(包括注释类型)或枚举声明 |            |
|      FIELD      |      字段声明(包括枚举常量)      |            |
|     METHOD      |             方法声明             |            |
|    PARAMETER    |          形式化参数声明          |            |
|   CONSTRUCTOR   |           构造函数声明           |            |
| LOCAL_VARIABLE  |           局部变量声明           |            |
| ANNOTATION_TYPE |           注释类型声明           |            |
|     PACKAGE     |             包装声明             |            |
| TYPE_PARAMETER  |           类型参数声明           | @since 1.8 |
|    TYPE_USE     |             使用类型             | @since 1.8 |

#### @Retention

- 作用：用于描述注解的生命周期。（该Annotaion被保留的时间长短）
- 取值：**RetentionPolicy**

|   值    |                           使用范围                           |  备注   |
| :-----: | :----------------------------------------------------------: | :-----: |
| SOURCE  |                     注释将被编译器丢弃。                     |         |
|  CLASS  |     注释由编译器记录在类文件中但不需要由VM在运行时保留。     | DEFAULT |
| RUNTIME | 注释将被编译器记录在类文件中在运行时被VM保留，因此可以反射性地读取它们。 |         |

#### **@Documented**

- 作用：**@**Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。
- 取值：Documented是一个标记注解，没有成员。 

#### **@Inherited**

- 作用：@Inherited阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。 
- 取值：Inherited是一个标记注解，没有成员。 
- 备注：
  - 类继承关系中，子类会继承父类使用的注解中被@Inherited修饰的注解 ；
  - 接口继承关系中，子接口不会继承父接口中的任何注解；
  - 类实现接口时不会继承任何接口中定义的注解 。

#### @Repeatable

- @since 1.8

- 作用：在需要对同一种注解多次使用时，往往需要借助@Repeatable。 
- 实例：一个人拥有多个身份。

```java
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)
public @interface Persons {
	Person[] value();
}

@Repeatable(Persons.class)
public @interface Person{
	String role() default "";
}

@Person(role="CEO")
@Person(role="husband")
@Person(role="father")
@Person(role="son")
public class Man {
	String name="";
}

//获取注解内容
if(Man.class.isAnnotationPresent(Persons.class)) {
	Persons p2=Man.class.getAnnotation(Persons.class);
	for(Person t:p2.value()){
		System.out.println(t.role());
	}
}
```

#### @Native

- 作用：@Native 作用在域上，用来表示域中的常量可能来自于本地代码。 

### 常用内置注解

#### @Override

- 作用：父类的方法删除或修改后，编译器会提示错误信息。生命周期：源码阶段。

#### @Deprecated

- 作用：如果使用了弃用的方法，编译器将会发生警告。生命周期：运行时。

#### @SuppressWarnings

- 作用：告知编译器忽略产生的特殊警告。

### 自定义注解

- 使用@interface创建自定义注解。
- 自动继承java.lang.annotation.Annotation接口。
- 注解中的方法，实际是声明了一个配置参数，参数名称就是方法名，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum、Annotation以及前面各类型的数组）。可以通过default来声明参数默认值。
- 注解中的方法，不允许使用protect、private修饰符，也无需加上public等修饰符，保持默认即可。这一点和接口类似。
- 自定义注解上需要打上前面的元注解，来描述注解的使用方式以及范围。
- 实例：

```java
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
	String author() default "";
	String date();
	int revision() default 1;
	String comments();
}
```

```java
//获取注解
try {
	for (Method method : MethodInfoParsing.class.getClassLoader()
		.loadClass("reflection.annotations.methodinfo.MethodInfoExample")
		.getMethods()) {
		if (method.isAnnotationPresent(MethodInfo.class)) {
			try {
				for (Annotation anno : method.getDeclaredAnnotations()) {
					System.out.println("Annotation in method " + method + " : " + anno);
				}
				MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
				if (methodInfo.revision() == 1) {
					System.out.println("Method with revision 1 = " + method);
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
	}
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
```

### 注释内容

```
@implSpec
This class is immutable and thread-safe.

@since 1.8
```

