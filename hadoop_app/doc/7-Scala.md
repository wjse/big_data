## Scala

> Scala是一门多范式（面向对象、函数式编程等）的，类似于Java的，运行在JVM之上的编程语言

https://scala-lang.org/

https://docs.scala-lang.org/

## 1、Scala基础

- 标识符
- 数据类型
- 值和变量
- 数据类型转换
- 字符串操作
- 从控制台读取数据
- 运算符
- 条件分支
- 循环分支
- 方法定义
- 默认参数
- 命名参数
- 变长参数



### 1.1、标识符

```scala
object IdentifierApp {
  def main(args: Array[String]): Unit = {

    /**
     * 字母/下划线开头,后面可以是数字，字母，下划线
     */
    val name = "krr"
    val _name = "krr"
    val _1 = "krr"
    val __ = "krr"
    println(name , _name , _1 , __)

    /**
     * 特别注意的
     */
    val int = "int"
    val _ = "_"
    val `try` = "try"
    val +-*/ = "+-*/"
    println(int , _ , `try`) //_打印报错
  }
}
```



**值和变量**

- val 常量
- var 变量

优先使用val



### 1.2、数据类型

![https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg](https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg)

| Data Type | Possible Values                                              |
| --------- | ------------------------------------------------------------ |
| Boolean   | `true` or `false`                                            |
| Byte      | 8-bit signed two’s complement integer (-2^7 to 2^7-1, inclusive) -128 to 127 |
| Short     | 16-bit signed two’s complement integer (-2^15 to 2^15-1, inclusive) -32,768 to 32,767 |
| Int       | 32-bit two’s complement integer (-2^31 to 2^31-1, inclusive) -2,147,483,648 to 2,147,483,647 |
| Long      | 64-bit two’s complement integer (-2^63 to 2^63-1, inclusive) (-2^63 to 2^63-1, inclusive) |
| Float     | 32-bit IEEE 754 single-precision float 1.40129846432481707e-45 to 3.40282346638528860e+38 |
| Double    | 64-bit IEEE 754 double-precision float 4.94065645841246544e-324d to 1.79769313486231570e+308d |
| Char      | 16-bit unsigned Unicode character (0 to 2^16-1, inclusive) 0 to 65,535 |
| String    | a sequence of `Char`                                         |

```scala
val l:Long = 8543423408545 //依然为Int类型
var l = 8543423408545L //真正的Long型
```

浮点型默认Double



### 1.3、数据类型转换

![https://docs.scala-lang.org/resources/images/tour/type-casting-diagram.svg](https://docs.scala-lang.org/resources/images/tour/type-casting-diagram.svg)

```scala
val a = 3 + 4.0 //Int + Double = Double
val b = 2.6.toInt //2
val c = 10 * 3.5.toInt + 6 * 3.5.toInt //48
val d = (10 * 3.5 + 6 * 3.5).toInt //56

10.isInstanceOf[Int] //true
10.asInstanceOf[Long] //Long 10
```



### 1.4、字符串操作

字符串插值

```scala
val name = "Tom"
val age = 20
val info = s"name = $name , age = $age"
val info1 = s"name = $name , age = ${age - 1}" //{}进行运算

//多行文本
val sql = """
      	  |select *
      	  |  from t_table
      	  | where id = 1
      	  |""".stripMargin
```



### 1.5、从控制台读取数据

```scala
println("What's your name")
val name = StdIn.readLine()

println("How old are you")
val age = StdIn.readInt()

println("Show your money")
val money = StdIn.readDouble()

println(s"name = $name , age = $age , money = $money")
```



### 1.6、运算符

```scala
val s1 = "abc"
val s2 = new String("abc")
println(s1 == s2) //true
println(s1.eq(s2)) //false
//和java相反
//scala没有i++这种运算
```



### 1.7、条件分支

if返回值

```scala
    val a = 10
    val b = if(a > 20) 1 else 2
    println(b)
```



### 1.8、循环分支

scala中退出循环不像java中直接使用break关键字，而是需要scala.util.control.Breaks包的内容

```scala
import scala.util.control.Breaks._
val count = 0
while(true){
  count += 1
  println(count)
  if(count == 5){
    break()
  }
}
println("结束循环")
//此时的break()会直接停掉整个进程,它抛出的是一个BreakControl的exception来停止
//所以需要添加breakable{}块来捕获该异常

val count = 0
breakable{
  while(true){
    count += 1
    println(count)
    if(count == 5){
      break()
    }
  }
}
println("结束循环")

//breakable{}块就是break()异常的捕获，在哪捕获对执行逻辑会有影响

```



for循环

```scala
//to [a , b] 包含ab
for(i <- 1 to 10){
  println(i)
}

//和上面相同
for(i <- 1.to(10)){
  println(i)
}

for(i <- 1.to(10 , 2)){ //第二个参数为步长，间隔 ==> 1 to 10 by 2
  println(i)
}

for(i <- 1 to 10 reverse){ //反转， 10 to 1 by -1
  println(i)
}

//until [a,b)包含a
for(i <- 1 until 10){
  println(i)
}

//和上面相同
for(i <- 1.until(10)){
  println(i)
}

for(i <- 1.until(10 , 2)){//第二个参数为步长，间隔 ==> 1 until 10 by 2
  println(i)
}

for(i <- 1 until 10 reverse){ //反转， 10 until 1 by -1
  println(i)
}

```

步长可正可负，但不能为0



循环守卫

```scala
for(i <- 1 to 10 if i % 2 == 1){
  println(i)
}
```



循环返回值

```scala
//使用yield关键字
var result = for(i <- 1 to 10) yield i * i
println(result)
//Vector(1 , 4 , 9 , 16 , 25 , 36 , 49 ,64 , 81 , 100)
```



循环字符串

```scala
val str = "abcd"
var result = for(s <- str) yield s.toUpper
println(result)
//ABCD

//==>
str.map(s => s.toUpper).foreach(print)
//ABCD
```



### 1.9、方法定义

```scala
def max(x:Int , y:Int):Int = {
  if(x > y) {
    x
  }else{
    y
  }
}
//返回值无需return关键字，默认方法体最后一行就是返回值
//返回值定义可不写，scala可自动推导出来
//显示定义无返回值定义返回值类型为Unit
//调用无参方法可不用写括号
def add(x:Int , y:Int) = x + y
```



默认参数值

```scala
def log(msg:String , level:String = "INFO") = println(s"$level : $msg")

//命名参数,调用时不按方法定义的参数顺序，通过参数名来定义传参顺序
log(level="DEBUG" , msg="Hello")

//可变（变长）参数,只能是参数列表的最后一个，顶层就是java的...
def sum(nums:Int*) = {
  var res = 0;
  for(n <- nums) res += n
  res
}
sum(1 , 2 , 3)
sum(1 , 2 , 3 , 4)
sum(1 to 100 : _*)//将Range类型转换为方法定义参数类型
```



- Unit  类似java的 void ， 只有一个实例值()
- Null  只有一个实例值null，可以给AnyRef赋值，不能给AnyVal赋值
- Nothing 数据类型层级的最低端，可以返回任何数据类型的子类型



## 2、Scala面向对象编程

### 2.1 getter/setter

val为常量，scala默认只会生成对应变量名的get方法

var为变量，scala除了生产对应变量名的get方法，还会隐式生成"变量名_$eq"作为set方法。可通过@BeanProperty注解生成标准get/set方法

private声明的变量对应生成的getter也是private的



### 2.2 _占位符

"_"在scala中有一个功能为占位符，必须指定数据类型，如

```scala
var name:String = _ //null
var age:Int = _ //0
var money:Double = _ //0.0
```

占位符不能用val常量来表示，因为会涉及改动。占位符默认值就是java中各数据类型默认值，引用类型为null



### 2.3 构造器

```scala
class Person(val name:String , val age:Int){//主构造器
  
  val school = "Hongxing"
  var gender:String = _
  
  def this(name:String , age:Int , gender:String){//附属构造器，每个附属构造器首行必须调用已经存在的主构造器或者其他附属构造器
    this(name , age)
    this.gender = gender
  }
}
```



### 2.4 继承

```scala
/**
** 继承 ， 首先调用父类构造器
**/
class Student(name:String , age:Int , val major:String) extends Person(name , age){
  //子类重写父类属性
  override val school = "Shiyan"
  
  //重写toString
  override def toString: String = s"$name,$age,$major,$school"
}
```



### 2.5 抽象类

```scala
abstract class Person{
  def speak//抽象方法
  val name:String//抽象属性
}

class Student extends Person{
  override def speak = println("Student speak")
  override val name:String = "YL"
}

new Person(){
  def speak = println("匿名子类")
  val name:String = "匿名"
}
```



### 2.6 object/class

```scala
object Timer{//静态单例类
  var cnt = 0
  def inr() = {
    cnt += 1
    cnt
  }
}

def main(args: Array[String]): Unit = {
    println(Timer.inr)//1
    println(Timer.inr)//2
    println(Timer.inr)//3
}
```



伴生类，同名一个是object，一个是class，那么两个就互为伴生类和伴生对象

```scala
object ApplyTest{
  var cnt = 0
  def static:Unit = {
    println("static method")
  }
  
  def inr() = {
    cnt += 1
    cnt
  }
  
  def apply() = {
    println("object apply method")
    new ApplyTest
  }
}

class ApplyTest{
   def test():Unit = {
     println("test method")
   }
  
  def apply() = {
    println("class apply method")
  }
}

def main(args: Array[String]): Unit = {
    println(ApplyTest.inr)//1
    println(ApplyTest.inr)//2
    println(ApplyTest.inr)//3
  
    val a = new ApplyTest()
    a.test//test method
  
  	val b = ApplyTest()
    b.test()//可调用class的test方法，object() ==> object apply() 
            //类似于单例的getInstance()，比如Array("a" , "b" , "c" , ...)
  
  	val c = new ApplyTest
    println(c) //toString
    println(c()) //class的apply()
}
```



### 2.7 case class

样例类

使用场景：

1.模式匹配

2.在Spark SQL中大量使用

```scala
object CaseClassApp {
  def main(args: Array[String]): Unit = {
    println(Dog("旺财").name)
  }
}

case class Dog(name:String)
```



case class VS class

```scala
object CaseClassApp {
  def main(args: Array[String]): Unit = {
    val p1 = new Person("k66" , 35)
    val p2 = new Person("k66" , 35)
    println(p1 == p2) //false

    //case class 不用new
    //默认实现了实例化接口
    val d1 = Dog("旺财")
    val d2 = Dog("旺财")
    println(d1 == d2) //true,底层重写了hashcode 和 equals
  }
}

case class Dog(name:String)

class Person(name:String , age:Int)
```



case class VS cass object

```scala
//必须要有参数列表
case class Dog(name:String)

//必须无参数列表
case object Cat
```



### 2.8 trait

特质，某种特征，类似于Java中的接口，可以有抽象的属性或方法，也可以有具体的属性或方法，

一个类可以混入多个trait（mixin）

```scala
object TraitApp {
  def main(args: Array[String]): Unit = {
    val sMem = new StaticMemoryManager
    println(sMem.maxOnHeapStorageMemory)

    val uMem = new UnifiedMemoryManager
    println(uMem.maxOnHeapStorageMemory)

    val sMem2 = new StaticMemoryManager2
    sMem2.log(s"${sMem2.name}加载就绪")
    println(sMem2.maxOnHeapStorageMemory)
    throw sMem2.exception
  }
}

trait MemoryManager{

  println("---------------MemoryManager--------------")

  val name:String

  def maxOnHeapStorageMemory:Long
}

class StaticMemoryManager extends MemoryManager{

  println("---------------StaticMemoryManager--------------")

  override val name: String = "静态内存管理"

  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存")
    100L
  }
}

class UnifiedMemoryManager extends MemoryManager{

  println("---------------UnifiedMemoryManager--------------")

  override val name: String = "统一的内存管理"

  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存")
    200L
  }
}

class MemoryLogger{
  println("---------------MemoryLog--------------")

  def log(msg:String): Unit = {
    println(s"开始打印日志: [$msg]")
  }
}

trait MemoryException{
  println("---------------MemoryException--------------")
  def exception:Exception
}

/**
 * 静态混入
 * 按 extends ... with ... with ...顺序
 *
 * 动态混入 new StaticMemoryManager with xxx with xxx with xxx
 * 可匿名实现
 */
class StaticMemoryManager2 extends MemoryLogger  with MemoryManager with MemoryException {

  println("---------------StaticMemoryManager2--------------")

  override val name: String = "静态内存管理2"

  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存")
    0L
  }

  override def exception: Exception = new RuntimeException(s"$name Full Memory")
}
```



Self-types 自身类型,在一个trait里面用另外一个trait

```scala
class Student(val name : String , val age : Int)

trait DAO{
  def save(s : Student): Unit = {
    println(s"保存${s.name}到数据库")
  }
}

trait Service{
//  self:DAO => 可用self变量名或this关键字
  _:DAO => //只能用this关键字
  def register(s : Student): Unit = {
    println(s"${s.name}注册成功")
    this.save(s)
  }
}

//实现
class StudentService extends Service with DAO
```



### 2.9 隐式转换

scala提供大量与java互相转换的类

```scala
val list = new ArrayList[String] //java list
for(s <- list){//报错
  println(s)
}

//转换
import scala.collection.JavaConverters
for(s <- list.asScala){
  println(s)
}
```



### 2.10 包对象

在对应包下所有的class或object都可访问

```scala
package object oo{
  def methodA():Unit = {
    println("包全局可用方法")
  }
}

package oo

class TestA{
  def main(args: Array[String]): Unit = {
    methodA
  }
}
```



## 3、Scala集合

- 集合架构
- Array
- Set
- List
- Map
- Tuple



### 3.1 集合架构

mutable : 可变，对于某个集合可以直接对原对象进行修改，不会返回新的对象。scala.collection.mutable

immutable : 不可变，对某个集合对象做了修改，返回的是一个新的对象，而不会对原对象进行修改。scala.collection.immutable



scala.collection

High level abstract class or trait

![General collection hierarchy](https://docs.scala-lang.org/resources/images/tour/collections-diagram-213.svg)

scala.collection.immutable

![Immutable collection hierarchy](https://docs.scala-lang.org/resources/images/tour/collections-immutable-diagram-213.svg)



scala.collection.mutable

![Mutable collection hierarchy](https://docs.scala-lang.org/resources/images/tour/collections-mutable-diagram-213.svg)



![Graph legend](https://docs.scala-lang.org/resources/images/tour/collections-legend-diagram.svg)

- 虚线是通过隐式转换
- 粗实线是默认的实现
- 细实线是实现



### 3.2 Array

```scala
object ArrayApp {
  def main(args: Array[String]): Unit = {
    //不可变
    val a = new Array[String](5)
    a(0) = "k66"
    a(1) = "k77"
    a(2) = "k88"
    a(3) = "k99"
    println(a(3))

    //不写类型，默认Nothing类型
    val b = new Array(3)
    println(b)

    //底层调用apply方法
    val c = Array("k66" , "b77")
    println(c)

    val d = Array(1,2,3,4)
    val d1 = d :+ 100 //数组拷贝并在末尾添加100这个元素，返回新数组
    println(d1(4))
    val d2 = 100 +: d //数组拷贝并在首位添加100这个元素，返回新数组
    println(d2(0))

    val dd = Array(5,6)
    val ddd = d ++ dd //两个数组进行连接，返回新数组，注意++语义不同于变量++
    for(i <- ddd) print(i)
    println()
    ddd.foreach(print)//函数式foreach
    println()
    for(i <- ddd.length to 0 by -1) print(i) //倒序输出

    println()
    println(ddd.max)
    println(ddd.min)
    println(ddd.sum)

    println(ddd.mkString(","))
    println(ddd.mkString("[", "," , "]"))
  }
}
```



**ArrayBuffer 可变数组** ***

```scala
import scala.collection.mutable.ArrayBuffer

object ArrayBufferApp {
  def main(args: Array[String]): Unit = {
    val ab = ArrayBuffer(1, 2, 3)
    println(ab.mkString("[", ",", "]"))//[1 , 2, 3]

    ab += 1
    println(ab.mkString("[", ",", "]"))//[1,2,3,1]

    ab += (7 , 8 , 9)
    println(ab.mkString("[", ",", "]"))//[1,2,3,1,7,8,9]

    ab.insert(0 , 0)
    println(ab.mkString("[", ",", "]"))//[0,1,2,3,1,7,8,9]

    ab.remove(1)
    println(ab.mkString("[", ",", "]"))//[0,2,3,1,7,8,9]

    ab.remove(2 , 4)//从2位置开始删4个元素
    println(ab.mkString("[", ",", "]"))//[0,2,9]

    ab.trimEnd(1)//从末尾开始删1个
    println(ab.mkString("[", ",", "]"))//[0,2]

    10 +=: ab
    println(ab.mkString("[", ",", "]"))//[10 , 0,2]

    ab -= 10
    println(ab.mkString("[", ",", "]"))//[0,2]

    val b1 = ArrayBuffer(10 , 5 , 6)
    val b2 = ArrayBuffer(1 , 2 , 30 , 40)

    val b21 = 99 +: b2 :+ 10
    println(b2.mkString("[", ",", "]")) //[1,2,30,40]
    println(b21.mkString("[", ",", "]"))//[99,1,2,30,40,10]

    b1 ++= b2 //等价于 b1 = b1 + b2
    println(b1.mkString("[", ",", "]"))//[10,5,6,1,2,30,40]
    //关键看=，是否赋值

    b1 ++=: b2 // ++=:
    println(b1.mkString("[", ",", "]"))//b1 不变，元素加到b2
    println(b2.mkString("[", ",", "]"))//[10,5,6,1,2,30,40,1,2,30,40]
  }
}
```



### 3.3 Set

```scala
package com.k66.scala.collection

object SetApp {
  def main(args: Array[String]): Unit = {
    val set = Set(1 , 2 , 3 , 3)
    println(set.mkString("[" , "," , "]")) //[1 , 2 , 3]

    val arr = Array(1 , 1 , 2 , 2 , 3 , 3)
    val a2s = arr.toSet
    println(a2s.mkString("[" , "," , "]")) //[1 , 2 , 3]

    //可变的Set
    val mSet = scala.collection.mutable.Set(1 , 2 , 3 , 3)
    mSet.add(4) //可变的有CRUD方法
    println(mSet.mkString("[" , "," , "]")) //[1 , 2 , 3 , 4]

    val s1 = Set(10,20,60,70,50,30)
    val s2 = Set(5,10,70,2,7,30)

    //并集(3种写法)
    println(s1 ++ s2)
    println(s1 union s2)
    println(s1 | s2)

    //交集
    println(s1.intersect(s2))
    println(s1 & s2)

    //差集
    println(s1 -- s2)
    println(s1 diff s2)
    println(s1 &~ s2)
  }
}

```



### 3.4 LIst

```scala
package com.k66.scala.collection

import scala.::

object ListApp {
  def main(args: Array[String]): Unit = {
    println(Nil == List())

    val l = List(1,2,3,4,5)
    println(l)
    println(l.head)
    println(l.tail)//除了第一个元素的后面

    val ll = List(1,"a",2,"b")//Any
    println(ll)

    val l2 = l :+ 6
    println(l2)

    val l3 = 0 +: l
    println(l3)

    val l4 = 1 :: 2 :: 3 :: 4 :: 5 :: Nil // 不用List声明
    println(l4)

    val l5 = l4 ::: 6 :: 7 :: 8 :: Nil //这种形式的两个List拼接
    println(l5)

    //可变的
    val l6 = scala.collection.mutable.ListBuffer(1 ,2 , 3)
    println(l6)

    l6 += 4
    println(l6)

    l6 += (5,6,7)
    println(l6)

    l6 ++= List(8 , 9 , 10)//拼接不可变的用++=
    println(l6)

    println(l6.toList)//可变转换成不可变的

    l6.drop(1)//删除传入的index
  }
}

```



### 3.5 元祖

```scala
package com.k66.scala.collection

/**
 * 元祖
 * 对偶元祖，只有2个元素
 */
object TupleApp {
  def main(args: Array[String]): Unit = {
    //()包起来的一系列的值,最多只能有22个元素
    val a = (1,2,3,4,5)
    println(a)

    println(a._1)
    println(a._2)
    println(a._3)
    println(a._4)
    println(a._5)
    println("===============================")
    for(i <- 0 until(a.productArity)){
      println(a.productElement(i))
    }

    //scala定义了1-22个长度的Tuple
    val b3 = Tuple3(1 ,2 ,3)
    val b4 = Tuple4(1 ,2 ,3 , 4)
    //...

    //
    val t0 = 0  -> "zero"
    val t1 = 1  -> "one"
    val t2 = 2  -> "two"
    val t3 = 3  -> "three" -> "third"

    println(t3._1._2)

    //对偶元祖
    val tt = ("a" , "b")
    println(tt.swap)//b ,a

    val array = Array[Int](1,2,3,4,5,6,7,8,9,10)

    /**
     * 根据输入的Int数组 ==> 最大值  最小值  平均值
     * @param array
     */
    def test(array: Array[Int]) = {
      (array.max , array.min , array.sum / array.size)
    }

    val res = test(array)
    val (max , min , avg) = test(array)
    println(max , min , avg)
  }
}

```



### 3.6 Map

```scala
package com.k66.scala.collection

object MapApp {
  def main(args: Array[String]): Unit = {
    val m1 = Map(1 -> "a" , 2 -> "b" , "c" -> 3)
    println(m1)
    for((k , v) <- m1){
      println(s"${k} = ${v}")
    }

    for(k <- m1.keys){
      println(s"${k} = ${m1(k)} , Option: ${m1.get(k)}")
    }

    for (v <- m1.values) {
      println(s"${v}")
    }

    //一般使用getOrElse，不然容易报错NoSuchElementException
    println(m1.getOrElse(4, "There is no this key"))
  }
}

```



## 4、Scala模式匹配



### 4.1、隐式转换

在Spark源码里，是非常多使用的，

例如：java.io.File类中是没有直接read方法的，要求你提供一个read方法

Java:动态代理

Scala:隐式转换，偷偷的（隐式）对现有功能进行增强（转换）



### 4.2、 模式匹配

```scala
//类似于java switch
val num
num match {
  case 1 => //do something
  case 2 => //do something
  case 3 => //do something
  //...
  case _ => //un match any
}
```



样例：

```scala
/**
 * 模式匹配
 */
object PatternMatchApp{
  private def test01(): Unit = {
    val a = 9
    val b = 3
    val in = StdIn.readLine("请输入运算符:")
//    in match {
//      case "+" => println(a + b)
//      case "-" => println(a - b)
//      case "*" => println(a * b)
//      case "/" => println(a / b)
//      case _ => println("请正确输入")
//    }
    val v = in match {
      case "+" => a + b
      case "-" => a - b
      case "*" => a * b
      case "/" => a / b
      case _ => "请正确输入" //like java switch default
    }

    println(v)
  }

  private def test02(): Unit = {
    val array = Array("A" , "B" , "C" , "D" , "E" , "F" , "G")
    1.to(10).foreach(i => {
      val s = array(Random.nextInt(array.length))
      Thread.sleep(1000)
      val index = s match {
        case "A" => 0
        case "B" => 1
        case "C" => 2
        case "D" => 3
        case "E" => 4
        case "F" => 5
        case "G" => 6
        case _ => -1
      }
      println(s"Current teacher index is $index")
    })
  }

  /**
   * 匹配时添加if作守卫判断
   */
  private def test03() : Unit = {
    def judgeGrade(grade:String , name:String): Unit = {
      grade match {
        case "A" => println("优")
        case "B" => println("良")
        case "C" => println("中")
        case "D" => println("差")
        case _ if(name == "krr") => println("还行")
        case _ => println("不及格")
      }
    }

    judgeGrade("A" , "krr")
    judgeGrade("E" , "krr")
  }

  private def test04(): Unit = {
    def matchType(obj:Any): Unit = {
      obj match {
        case x:Int => println("Int")
        case x:Long => println("Long")
        case x:String => println("String")
        case x:Map[_,_] => println("Map") //_占位
        case x:List[_] => println("List") //_占位
        case _ => println("Unsupported Type")
      }
    }

    matchType(1)
    matchType(1L)
    matchType("krr")
    matchType(Set(0,1))
    matchType(List(0,1))
    matchType(Map("name" -> "krr"))
		
    //守护模式匹配
    val array = Array(1 , 20 , 3 , "krr" , true)
    1.to(5).foreach(x => {
      array(Random.nextInt(array.length)) match {
        case a: Int if a < 10 => println("TinyInt")
        case a: Int if a > 10 => println("BigInt")
        case a: String if a.equals("krr") => println(s"${a}K66")
        case a: Boolean if a => println("It's True")
      }
    })
  }

  /**
   * 数组模式匹配@_*的用法
   */
  private def test05(): Unit = {
    def greeting(array:Array[String]): Unit = {
      array match {
        case Array("A") => println("A")
        case Array(x , y) => println(s"Hi ${x} , ${y}")
        case Array("A") => println("A")
        case Array("A" , _*) => println(s"A and others")
        case _ => println("Nothing")
      }
    }

    greeting(Array("A"))
    greeting(Array("A" , "B"))
    greeting(Array("A" , "B" , "C"))
    greeting(Array("B" , "C"))
    greeting(Array("B" , "C" , "D"))

    val array = Array(1 , 2 , 3, 4)
    array match {
      case Array(1 ,2,other@_*) => println(other.toList)
    }
  }

  /**
   * Tuple匹配
   */
  private def test06() : Unit = {
    val infos = ("krr" , 37)
    infos match {
      case (name , age) => println(s"$name = $age")
    }

    infos match {
      case (name:String , _) => println(name)//只取要用的其他用_代替
    }
  }

  /**
   * List匹配
   */
  private def test07(): Unit = {
    def greeting(list: List[String]): Unit = {
      list match {
        case "A"::Nil => println("A")
        case x::y::Nil => println(s"Hi ${x} , ${y}")
        case List("A") => println("A")
        case "A"::_ => println(s"A and others")
        case List("C",other@_*) => println(s"C and ${other.mkString(",")}")
        case _ => println("Nothing")
      }
    }

    greeting(List("A"))
    greeting(List("A", "B"))
    greeting(List("A", "B", "C"))
    greeting(List("B", "C"))
    greeting(List("B", "C", "D"))
    greeting(List("C", "B", "A"))

    List(1 , 2 , 3 , 4) match {
      case a::b::c =>
        println(a) //1
        println(b) //2
        println(c) //List(3 , 4)
    }
  }

  /**
   * 对class匹配
   * 一般不用，较麻烦，使用cass class匹配
   */
  private def test08(): Unit = {
    class Person(val name:String , val age:Int)
    //Person伴生对象
    object Person{
      def unapply(person: Person) : Option[(String , Int)] = {
        if(null != person){
          Some(person.name , person.age)
        }else{
          None
        }
      }
    }
    val krr = new Person("krr" , 37)

    krr match {
      //此处调用的是Person的伴生对象中的unapply方法
      case Person(name , age) => println(s"$name , $age")
      case _ =>
    }
  }

  /**
   * cass class匹配
   */
  private def test09(): Unit={
    case class User(name:String , age:Int)
    val user = User("krr" , 37)

    user match {
      case User(name , age) => println(s"$name , $age")
    }
  }

  /**
   * Spark中模式匹配例子
   */
  private def test10(): Unit = {
    case class SubmitTask(id:String , name:String)
    case class Heartbeat(time:Long)
    case object CheckTimeOutTask

    val inputs = Array(CheckTimeOutTask , Heartbeat(1000) , SubmitTask("001" , "Task001"))

    1.to(10).foreach(i => {
      inputs(Random.nextInt(inputs.length)) match {
        case CheckTimeOutTask => println("CheckTimeOutTask")
        case Heartbeat(time) => println(s"Heartbeat: $time")
        case SubmitTask(id , name) => println(s"SubmitTask: $id , $name")
      }
    })
  }
}
```



