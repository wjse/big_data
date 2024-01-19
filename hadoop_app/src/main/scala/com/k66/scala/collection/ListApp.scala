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
