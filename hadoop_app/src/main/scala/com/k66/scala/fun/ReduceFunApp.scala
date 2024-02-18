package com.k66.scala.fun

object ReduceFunApp {
  /**
   * reduce两两操作
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    println(list.reduce(_+_))//36

    val sub = (x:Int , y:Int) => {
      val z = x - y
      println(s"x:$x - y:$y = z:$z")
      z
    }

    //从左开始依次与上次结果集进行计算
    list.reduceLeft(sub)

    //从右开始依次与上次结果集进行计算
    list.reduceRight(sub)
  }
}
