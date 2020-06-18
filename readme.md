###                             		  java基础面试题

###### 1.为什么String要用final修饰?

a.因为它可以缓存结果，在传参时不需要考虑去改变它的值；如果是可变类的话，则可能需要拷贝出来一个新值进行传参，在性能上会有一些损失。（高效）

b.当你在调用其它方法时，比如会调用一些系统级操作指令之前，可能会有一系列的校验，如果是可变类的花话，可能在你校验之后，它的内部的值改变了，这样会引发严重的系统崩溃问题，这是迫使String类设计成不可变类的一个重要原因。（安全）

###### 2.==和equals的区别？

==对于基本数据类型来说，是用于比较"值"是否相等的；对于引用数据类型来说，是用于比较引用地址是否相同的。

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

查看Object类的源码，我们可以知道Object类中的equals()方法其实就是==，而String类重写了equals()将其变成了比较两个字符串的值是否相等。

```java
public boolean equals(Object anObject) {
    // 对象引用相同直接返回 true
    if (this == anObject) {
        return true;
    }
    // 判断需要对比的值是否为 String 类型，如果不是则直接返回 false
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            // 把两个字符串都转换为 char 数组对比
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            // 循环比对两个字符串的每一个字符
            while (n-- != 0) {
                // 如果其中有一个字符不相等就 true false，否则继续对比
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```

###### 3.String和StringBuilder、StringBuffer的区别？

因为String类型是不可变的，所以在字符串拼接的时候如果使用String的话性能会很低，因此我们就需要另一个数据类型StringBuffer，它提供了append和insert方法来拼接字符串，查看源码发现，它使用Synchronized来保证线程安全，源码如下所示：

```java
@Override
public synchronized StringBuffer append(Object obj) {
    toStringCache = null;
    super.append(String.valueOf(obj));
    return this;
}

@Override
public synchronized StringBuffer append(String str) {
    toStringCache = null;
    super.append(str);
    return this;
}
```

因为它用了Synchronized来保证线程安全，所以性能不是很高，于是在JDK1.5就有了StringBuilder，它同样提供了append和insert方法，但它没有使用Synchonized修饰，因此性能要优于StringBuffer，可以在非并发的操作环境下使用StringBuilder来拼接字符串。

###### 4.String和JVM

String创建的方式有两种，new String()和直接赋值的方式，直接赋值的会先去字符串常量池中查找是否已经存在此值，如果存在则把引用地址直接指向此值，不存在则先在常量池中创建，然后把引用指向它。而new String()的方式一定会先在堆上创建一个字符串对象，然后再去常量池中查询此字符串的值是否已经存在，如果不存在会先在常量池中创建字符串然后把引用的值指向此字符串，代码如下：

```java
String s1 = new String("Java");
String s2 = s1.intern();
String s3 = "Java";
System.out.println(s1 == s2); // false
System.out.println(s2 == s3); // true
```

它们在JVM中存储的位置，如图：

![image-20200618184916835](/Users/guanghui/Library/Application Support/typora-user-images/image-20200618184916835.png)

Tip：JDK1.7之后把永生代换成元空间，把字符串常量池从方法区移到了java堆上。# diary
