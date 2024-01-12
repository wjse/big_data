package com.k66.scala.basic

import scala.io.StdIn
import scala.util.control.Breaks._

/**
 * 标识符
 */
object IdentifierApp {
  def main(args: Array[String]): Unit = {
    breakable{
      break()
    }

    /**
     * 字母/下划线开头,后面可以是数字，字母，下划线
     */
    val name = "krr"
    val _name = "krr"
    val _1 = "krr"
    val __ = "krr"
    println(name , _name , _1 , __)

    /**
     * 特别注意的
     */
    val int = "int"
    val _ = "_"
    val `try` = "try"
    val +-*/ = "+-*/"
//    println(int , _ , `try`) //_打印报错
    """
      |
      |""".stripMargin
  }
}
