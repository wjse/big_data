package com.k66.scala.fun


object FunctionApp {
  def main(args: Array[String]): Unit = {
//    test01()
//    test02()
//    test03()
//    test04()
//    test05()
//    test06()
  }

  /**
   * Scala中函数的定义方式一
   * val/var 函数名称 = (参数列表) => {函数体}
   */
  def test01(): Unit = {
    val f1 = (a:Int , b:Int) => a + b

    println(f1) //com.k66.scala.fun.FunctionApp$$$Lambda$1/431687835@26aa12dd
    println(f1(1 ,2 )) //3
  }

  /**
   * Scala中函数的定义方式二
   * val/var 函数名称 : (入参类型) => 返回值类型 = (入参变量) => {函数体}
   */
  def test02(): Unit = {
    val f2 : (Int , Int) => Int = (a , b) => a + b
    println(f2)
    println(f2(1, 2))
  }

  def test03(): Unit = {
    def sayHello(str:String):Unit = {
      println(s"Hello $str")
    }

    //将方法赋值给一个函数、变量：方法名后面+空格_
    val sayHelloM = sayHello _

    println(sayHelloM) //com.k66.scala.fun.FunctionApp$$$Lambda$1/1975358023@1f554b06
    sayHelloM("Krr") //Hello Krr

    //定义一个函数，函数体为sayHelloM
    val sayHellF : (String) => Unit = sayHelloM
    println(sayHellF) //com.k66.scala.fun.FunctionApp$$$Lambda$1/1975358023@1f554b06
    sayHellF("Fun") //Hello Fun

    //把函数当入参，参数是函数的函数叫做高阶函数
    def foo(fun:(String) => Unit) = fun("God")
    foo(sayHelloM) //Hello God

    def foo2(fun:() => Unit) = fun()
    def p = println("Hello")

    //_是方法和函数的转换桥梁
    foo2(p _)
  }

  private def test04(): Unit = {
    def foo(f:() => Unit) = f()
    foo(() => println("匿名函数调用"))

    val f = () => {println("It's f()")}
    foo(f)

    def foo2(add:(Int , Int) => Int) = println(add(10 , 20))
    foo2((a , b) => a + b) //30
    //第一个_表示第一个参数，第二个_表示第二个参数，每个参数只能使用一次
    foo2(_+_) //30
    foo2(_*_) //200

    def cal(a:Int , b:Int , operator:(Int , Int) => Int):Int = operator(a , b)
    def add(a:Int , b:Int) = a + b
    def mul(a:Int , b:Int) = a * b

    println(cal(2 ,3 , add)) //5
    println(cal(2 ,3 , mul)) //6
    println(cal(2 ,3 , (a , b) => a - b))//匿名函数 //1
    println(cal(6 ,3 , _/_))//简化版 //2
  }

   private def test05(): Unit ={
    //常规方法定义
    def sum(a:Int , b:Int) = a + b
    println(sum(3 , 4)) //7

    //科里化定义
    def sumC(a:Int)(b:Int) = a + b
    println(sumC(3)(4)) //7

    def eq(x:String)(y:String) = x.toLowerCase == y.toLowerCase //Scala中可直接使用==来比较
    println(eq("Krr")("KRR")) //true
    println(eq("Krr")_) //com.k66.scala.fun.FunctionApp$$$Lambda$5/1632492873@1f554b06
  }

  private def test06(): Unit = {
    val l = List(1,2,3,4,5,6,7,8)
    //自定义foreach函数
    def foreach(l:List[Int] , fun : Int => Unit) = {
      for(e <- l)
        fun(e)
    }

    foreach(l , println)//1,2,3,4,5,6,7,8
    println("========================")

    //自定义filter函数
    def filter(l:List[Int] , fun : Int => Boolean) = {
      for(e <- l if fun(e)) //守护遍历
        yield e
    }
//    filter(l , x => x % 2 == 0).foreach(println)
    filter(l , _% 2 == 0).foreach(println)//2,4,6,8
    println("========================")
    filter(l , _> 6).foreach(println)//7,8
    println("========================")

    //自定义map函数
    def map(l:List[Int] , fun : Int => Int) = {
      for(e <- l)
        yield fun(e)
    }
    map(l , _* 2).foreach(println)//2,4,6,8,10,12,14,16
    println("========================")

    //自定义reduce函数
    def reduce(l:List[Int] , fun : (Int , Int) => Int) = {
      var first = l(0)
      for(i <- 1 until(l.length)){
        first = fun(first , l(i))
      }
      first
    }

    println(reduce(l , _+_))//36
    println("========================")

    reduce(l, (x, y) => {
      val z = x + y
      println(s"x:$x + y:$y = z:$z") //reduce 过程展示
      z
    })
  }
}
