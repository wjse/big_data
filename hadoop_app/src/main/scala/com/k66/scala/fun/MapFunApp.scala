package com.k66.scala.fun

object MapFunApp {


  /**
   * map对传入的每个元素都进行映射（一进一出）
   * @param args
   */
  def main(args: Array[String]): Unit = {

    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    list.map((x: Int) => x * 2)
    list.map(x => x * 2)
    val l = list.map(_ * 2) //_标识list中的每个元素
    println(l)

    val courses = List("Hadoop", "Spark", "Flink")
    def lower(course: String) = course.toLowerCase
    courses.map(x => lower(x))
    courses.map(lower(_))
    val lCourses = courses.map(_.toLowerCase)
    println(lCourses)

    val array = List(Array(("Krr", 37)), Array(("Baker", 43)))
    array.map(x => x.map(y => (y._1, y._2 + 2)))
  }
}
