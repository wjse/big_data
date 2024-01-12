package com.k66.scala.oo

object TypeApp {
  def main(args: Array[String]): Unit = {
//    val pig = new Pig
//    println(pig.isInstanceOf[Pig])
//    println(pig.isInstanceOf[Animal])
//    println(pig.isInstanceOf[Object])
//
//    val animal = new Animal
//    println(animal.isInstanceOf[Pig])
//    println(animal.isInstanceOf[Animal])
//    println(animal.isInstanceOf[Object])
//    println(animal.isInstanceOf[AnyRef])
//    println(animal.isInstanceOf[Any])
    //别名
    type S = String
    val name:S = "k66"
    println(name)
  }
}

class Animal {
  override def toString: String = "这是一个动物..."
}

class Pig extends Animal {
  override def toString: String = "这是一只猪..."
}