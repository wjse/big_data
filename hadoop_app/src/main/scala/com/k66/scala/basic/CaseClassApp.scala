package com.k66.scala.basic

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

//必须要有参数列表
case class Dog(name:String)

//必须无参数列表
case object Cat

class Person(name:String , age:Int)
