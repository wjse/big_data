package com.k66.scala.fun

object FoldFunApp {
  /**
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    //第一个参数是初始值
    //第二个参数为高阶函数
    println(list.fold(0)(_+_))//36

    val add = (x:Int , y:Int) => {
      val z = x + y
      println(s"x:$x + y:$y = z:$z")
      z
    }

    /**
     * x:10 + y:1 = z:11
     * x:11 + y:2 = z:13
     * x:13 + y:3 = z:16
     * x:16 + y:4 = z:20
     * x:20 + y:5 = z:25
     * x:25 + y:6 = z:31
     * x:31 + y:7 = z:38
     * x:38 + y:8 = z:46
     */
    list.fold(10)(add)
  }
}
