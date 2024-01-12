package com.k66.scala.collection

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
