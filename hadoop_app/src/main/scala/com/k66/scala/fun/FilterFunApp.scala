package com.k66.scala.fun

object FilterFunApp {
  /**
   * filter对传入的元素进行过滤，返回结果为true的元素
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    println(list.map(_*2).filter(_>8))//10,12,14,16
    println(list.filter(_%2 != 0))//1,3,5,7

    println(list.filter(_>3).filter(_%2 ==0))//4,6,8
    list.filter(x => x > 3 && x % 2 == 0)//4,6,8

    //数值类型乘以2
    val l = List(30 , "Krr" , 50 , true , "A" , "20" , 10)
    println(l.filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int] * 2))

    //flatten:扁平化
    val l2 = List(List(1,2),List(3,4),List(5,6))
    println(l2.flatten)//List(1,2,3,4,5,6)

    val words = List("hello A" , "hello B" , "hello world")
    println(words.flatten)//List(h, e, l, l, o,  , A, h, e, l, l, o,  , B, h, e, l, l, o,  , w, o, r, l, d)

    //flatMap: flatten + map
    println(l2.flatten.map(_*2))//List(2, 4, 6, 8, 10, 12)
    println(l2.flatMap(_.map(_*2)))//List(2, 4, 6, 8, 10, 12)
    println(l2.map(_.map(_*2)))//List(List(2, 4), List(6, 8), List(10, 12))

    println(words.flatMap(_.split(" ")))//List(hello, A, hello, B, hello, world)
    println(words.map(_.split(" ")).flatten)//List(hello, A, hello, B, hello, world)
  }
}
