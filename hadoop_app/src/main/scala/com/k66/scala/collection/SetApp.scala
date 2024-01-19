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
