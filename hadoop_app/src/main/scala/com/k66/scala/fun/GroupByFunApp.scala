package com.k66.scala.fun

object GroupByFunApp {
  def main(args: Array[String]): Unit = {
    val list = List(30,5,7,60,1,20)
    list.groupBy(x => if(x%2 == 0) "偶数" else "奇数")// Map(偶数 -> List(30, 60, 20), 奇数 -> List(5, 7, 1))

    val arr = Array(("a" , 100),("b" , 10),("a" , 190),("d" , 10))
    arr.groupBy(x => x._1)//Map(a -> Array((a,100), (a,190)), b -> Array((b,10)), d -> Array((d,10)))
    arr.groupBy(_._1)

    arr.groupBy(x => x._2)//Map(100 -> Array((a,100)), 10 -> Array((b,10), (d,10)), 190 -> Array((a,190)))
    arr.groupBy(_._2)

    /**
     * 统计每个单词出现的次数
     * 1）flatMap
     * 2）groupBy
     */
    val words = List("hello A" , "hello B" , "hello world")

    // Map(hello -> List(hello, hello, hello), A -> List(A), B -> List(B), world -> List(world))
    val group: Map[String , List[String]] = words.flatMap(_.split(" ")).groupBy(x=>x)//x=>x 自己=>自己

    println(group.map(x => (x._1 , x._2.size)))//Map(hello -> 3, A -> 1, B -> 1, world -> 1)

    /**
     * mapValues一定是使用在kv的数据结构上
     */
    println(words.flatMap(_.split(" ")).groupBy(x => x).mapValues(_.size))//Map(hello -> 3, A -> 1, B -> 1, world -> 1)
  }
}
