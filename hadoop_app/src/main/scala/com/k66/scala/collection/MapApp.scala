package com.k66.scala.collection

object MapApp {
  def main(args: Array[String]): Unit = {
    val m1 = Map(1 -> "a" , 2 -> "b" , "c" -> 3)
    println(m1)
    for((k , v) <- m1){
      println(s"${k} = ${v}")
    }

    for(k <- m1.keys){
      println(s"${k} = ${m1(k)} , Option: ${m1.get(k)}")
    }

    for (v <- m1.values) {
      println(s"${v}")
    }

    println(m1.getOrElse(4, "There is no this key"))
  }
}
