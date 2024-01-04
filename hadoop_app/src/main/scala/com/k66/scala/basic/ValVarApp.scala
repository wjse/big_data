package com.k66.scala.basic

/**
 * 不可变变量 val 名称[:类型] = xxx
 * 可变变量   var 名称[:类型] = xxx
 */
object ValVarApp {
  def main(args: Array[String]): Unit = {
    val money:Int = 100

    var count = 1
    count = 2

    val info =
      """
        |呵呵
        |呵呵
        |""".stripMargin
//    print(info)

//    for (i <- 1 until  10) {
//      println(i)
//    }
//
//    for (i <- 1 to 10 if i % 2 == 1) {
//      println(i)
//    }
//
//    val str = "abcd"
//    str.map(s => s.toUpper).foreach(print)

    println(max(19 , 20))
    println(test(20, 10))
    def max(x:Int , y:Int):Int = if(x > y) x else y
    def add(x:Int , y:Int) = x + y
    def test(x:Int , y:Int) = if(x == 10) "X" else if(y == 10) y + x
    def log(msg:String , level:String = "INFO") = println(s"$level : $msg")
    log("hhhhhh" , "DEBUG")
    log(level="DEBUG" , msg="Hello")

    def sum(nums: Int*) = {
      var res = 0;
      for (n <- nums) res += n
      res
    }

    println(sum(1 to 100: _*))

    val p = new Person {
      override def speak = println("Hello Person")

      override val name: String = "YL"
    }
    p.speak
    println(p.name)
  }

  abstract class Person{
    def speak
    val name:String
  }
}
