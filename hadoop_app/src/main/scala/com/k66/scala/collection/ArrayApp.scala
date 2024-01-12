package com.k66.scala.collection

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
