package com.k66.scala.fun

import scala.io.Source

/**
 * 使用scala算子完成wc统计
 * 只取排名靠前的2个
 */
object WordCountFunApp {
  def main(args: Array[String]): Unit = {
    val list = Source.fromFile("data/wc/wc.data" , "utf-8").getLines().toList
    println(list)//List(pk,pk,pk, hello,hello, world,world,world,world, krr,krr,krr,krr,krr)
    list.flatMap(_.split(","))//1按,拆分打散，List(pk,pk,pk,hello,hello,world,world,world,world,krr,krr,krr,krr,krr)
      .groupBy(x => x)//2按字符本身分组Map(pk->List(pk,pk,pk) , hello->List(hello,hello),...)
      .mapValues(_.size)//3转换map值，用value的size作为值Map(pk->3 , hello->2,...)
      .toList//4转list
      .sortBy(-_._2)//5按tuple第二个元素降序排序
      .take(2)//6取前两个
      .foreach(println)
  }
}
