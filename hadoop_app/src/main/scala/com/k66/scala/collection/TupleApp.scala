package com.k66.scala.collection

/**
 * 元祖
 * 对偶元祖，只有2个元素
 */
object TupleApp {
  def main(args: Array[String]): Unit = {
    //()包起来的一系列的值,最多只能有22个元素
    val a = (1,2,3,4,5)
    println(a)

    println(a._1)
    println(a._2)
    println(a._3)
    println(a._4)
    println(a._5)
    println("===============================")
    for(i <- 0 until(a.productArity)){
      println(a.productElement(i))
    }

    //scala定义了1-22个长度的Tuple
    val b3 = Tuple3(1 ,2 ,3)
    val b4 = Tuple4(1 ,2 ,3 , 4)
    //...

    //
    val t0 = 0  -> "zero"
    val t1 = 1  -> "one"
    val t2 = 2  -> "two"
    val t3 = 3  -> "three" -> "third"

    println(t3._1._2)

    //对偶元祖
    val tt = ("a" , "b")
    println(tt.swap)//b ,a

    val array = Array[Int](1,2,3,4,5,6,7,8,9,10)

    /**
     * 根据输入的Int数组 ==> 最大值  最小值  平均值
     * @param array
     */
    def test(array: Array[Int]) = {
      (array.max , array.min , array.sum / array.size)
    }

    val res = test(array)
    val (max , min , avg) = test(array)
    println(max , min , avg)
  }
}
