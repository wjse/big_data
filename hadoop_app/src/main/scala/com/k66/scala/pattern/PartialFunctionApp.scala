package com.k66.scala.pattern

/**
 * 偏函数
 * trait PartialFunction[-A, +B]
 * 被抱在花括号内的没有match的一组case语句
 */
object PartialFunctionApp {
  def main(args: Array[String]): Unit = {
    test01()
    test02()
    test03()
  }

  private def test01(): Unit = {
    def sayChinese(en: String): Unit = en match {
      case "A" => println("优")
      case "B" => println("良")
      case "C" => println("中")
      case "D" => println("差")
      case _ => println("不及格")
    }

    sayChinese("A")
  }

  private def test02(): Unit = {
    def sayChinese: PartialFunction[String , String] = {
      case "A" => "诶"
      case "B" => "逼"
      case "C" => "色"
      case "D" => "底"
      case _ => "？"
    }

    println(sayChinese("A"))
  }

  /**
   * List(1,2,3,4,"abc") 数值类型 +10,其他类型抛弃
   * 分析：
   * 1）判断是否是数值类型
   * 2）Any类型要转成数值类型
   * 3）数值类型的值 + 10
   */
  private def test03(): Unit = {
    val list = List(1,2,3,4,"abc")

    val trans = new PartialFunction[Any , Int] {
      //过滤
      override def isDefinedAt(x: Any): Boolean = {
        if(x.isInstanceOf[Int]) true else false
      }

      //过滤值处理
      override def apply(v1: Any): Int = v1.asInstanceOf[Int] + 10
    }

    list.collect(trans).foreach(println)
    println("===================================")

    //简化
    def f : PartialFunction[Any , Int] = {
      case i:Int => i + 2
    }
    list.collect(f).foreach(println)
    println("===================================")

    //再简化
    list.collect({case i:Int => i + 7}).foreach(println)
    println("===================================")

    //再再简化
    list.collect{case i:Int => i + 3}.foreach(println)
  }
}
