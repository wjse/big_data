package com.k66.scala.pattern

/**
 * Scala中异常处理
 */
object ExceptionApp {
  def main(args: Array[String]): Unit = {
    try {
      val i = 10 / 0
    }catch {
      case e: ArithmeticException => println("发生了算术异常" + e.getMessage)
      case e: NullPointerException => println("数据异常")
      case _ => println("未知异常")
    }finally {
      println("finally")
    }
  }
}
