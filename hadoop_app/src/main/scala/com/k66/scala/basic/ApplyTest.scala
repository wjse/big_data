package com.k66.scala.basic

object ApplyTest {
  def apply() = {
    println("ApplyTest object apply")
    new ApplyTest()
  }

  def main(args: Array[String]): Unit = {
    val a = new ApplyTest()
    a.test()

    println("===========================")

    val b = ApplyTest()
    b.test()

    println("===========================")

    val c = new ApplyTest()
    println(c)
    println(c())

    println("===========================")

    println(ApplyTest().test())
  }
}

class ApplyTest{
  def test():Unit = {
    println("ApplyTest class test")
  }

  def apply():Unit = {
    println("class apply method")
  }
}
