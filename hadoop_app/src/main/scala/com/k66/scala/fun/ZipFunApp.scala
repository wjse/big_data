package com.k66.scala.fun

object ZipFunApp {
  /**
   * zip合并/拉链
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val a = List(1,2,3,4)
    val b = List("a","b","c","d")
    println(a.zip(b))//List[(Int, String)] = List((1,a), (2,b), (3,c), (4,d))
    val c = List("a","b","c","d","e")
    //合并相同个数对应的元素，多余的丢弃
    println(a.zip(c))//List[(Int, String)] = List((1,a), (2,b), (3,c), (4,d))

    //zipAll全合并，没找到的用默认值填充
    println(a.zipAll(c , "-" , "~"))

    //加索引
    println(b.zipWithIndex)
  }
}
