package com.k66.scala.fun

object SortFunApp {
  /**
   * 排序
   * sorted：字符串按字典序，数值类型按升序
   * sortBy：自定义排序
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val l = List("c","b","a")
    println(l.sorted)//"a","b","c"

    val l1 = List(30,50,70,60,10,20)
    println(l1.sortBy(x => x))//List(10, 20, 30, 50, 60, 70) 升序
    println(l1.sortBy(x => -x))//List(70, 60, 50, 30, 20, 10) 降序
    println(l1.sortBy(x => x)(Ordering.Int.reverse))//List(70, 60, 50, 30, 20, 10) 降序
    l1.sortWith((x , y) => x < y)//List(10, 20, 30, 50, 60, 70)
    println(l1.sortWith(_<_))
    l1.sortWith((x, y) => x > y)//List(70, 60, 50, 30, 20, 10)
    println(l1.sortWith(_ > _))

    val l2 = List("hello" , "world" , "hello" , "aaa" , "b" , "zzzz")
    println(l2.sortBy(_.length))//List(b, aaa, zzzz, hello, world, hello)
    println(l2.sortBy(-_.length))//List(hello, world, hello, zzzz, aaa, b)

    //先按长度排序，再按默认顺序排
    l2.sortBy(x => (x.length , x))//List(b, aaa, zzzz, hello, hello, world)
    l2.sortBy(x => (-x.length , x))//List(hello, hello, world, zzzz, aaa, b)

    //对象排序
    class Person(val age:Int , val name:String){
      override def toString: String = s"Person($age , $name)"
    }

    val persons = List(new Person(10 , "A"),new Person(30 , "D"),new Person(10 , "B"),new Person(8 , "C"))
    persons.sortBy(x => (x.age , x.name))//List(Person(8 , C), Person(10 , A), Person(10 , B), Person(30 , D))
    persons.sortBy(x => (x.age , x.name))(Ordering.Tuple2(Ordering.Int , Ordering.String).reverse)//List(Person(30 , D), Person(10 , B), Person(10 , A), Person(8 , C))
  }
}
