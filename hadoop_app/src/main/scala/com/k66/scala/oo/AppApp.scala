package com.k66.scala.oo

object AppApp extends App {
  util.Properties.setProp("scala.time" , "true")
  println("hello k66")

  def foo() : Unit = {
    println("foo")
  }

  foo()
}
