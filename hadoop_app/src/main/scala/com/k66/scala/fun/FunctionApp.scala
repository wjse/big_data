package com.k66.scala.fun


object FunctionApp {
  def main(args: Array[String]): Unit = {
//    test01()
//    test02()
//    test03()
//    test04()
//    test05()
    test06()
  }

  /**
   * Scala中函数的定义方式一
   * val/var 函数名称 = (参数列表) => {函数体}
   */
  def test01(): Unit = {
    //$Lambda$1/2075203460@26aa12dd
    val f1 = (a:Int , b:Int) => a + b
    println(f1)
    println(f1(1 ,2 ))
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
    //$Lambda$7/1908143486@7f13d6e
    println(sayHelloM)
    sayHelloM("Krr")

    val sayHellF : (String) => Unit = sayHelloM
    sayHellF("Fun")

    //把函数当入参，参数是函数的函数叫做高阶函数
    def foo(op:(String) => Unit) = {
      op("God")
    }

    foo(sayHelloM)

    def foo2(op:() => Unit) = op()
    def p = println("Hello")

    //_是方法和函数的转换桥梁
    foo2(p _)
  }

  private def test04(): Unit = {
    val f = () => {}

    def foo(f:() => Unit) = f()
    foo(() => println("匿名函数调用"))
    foo(f)

    def foo2(add:(Int , Int) => Int) = println(add(10 , 20))
    foo2((a , b) => a + b)
    //第一个_表示第一个参数，第二个_表示第二个参数，每个参数只能使用一次
    foo2(_ + _)
    foo2(_ * _)

    def cal(a:Int , b:Int , operator:(Int , Int) => Int):Int = operator(a , b)
    def add(a:Int , b:Int) = a + b
    def mul(a:Int , b:Int) = a * b

    println(cal(2 ,3 , add))
    println(cal(2 ,3 , mul))
    println(cal(2 ,3 , (a , b) => a - b))//匿名函数
    println(cal(6 ,3 , _/_))//简化版
  }

   private def test05(): Unit ={
    //常规方法定义
    def sum(a:Int , b:Int) = a + b
    println(sum(3 , 4))

    //科里化定义
    def sumC(a:Int)(b:Int) = a + b
    println(sumC(3)(4))

    def eq(x:String)(y:String) = x.toLowerCase == y.toLowerCase //Scala中可直接使用==来比较
    println(eq("Krr")("KRR"))
  }

  def test06(): Unit = {
    val l = List(1,2,3,4,5,6,7,8)
    //自定义一个foreach函数
    def foreach(l:List[Int] , f : Int => Unit) = {
      for(e <- l)
        f(e)
    }

//    foreach(l , x => println(x))
    foreach(l , println)
    println("========================")
    def filter(l:List[Int] , f : Int => Boolean) = {
      for(e <- l if f(e))
        yield e
    }
//    filter(l , x => x % 2 == 0).foreach(println)
    filter(l , _% 2 == 0).foreach(println)
    println("========================")

    filter(l , _ > 6).foreach(println)

    def map(l:List[Int] , f : Int => Int) = {
      for(e <- l)
        yield f(e)
    }

    println("========================")
    map(l , _ * 2).foreach(println)

    def reduce(l:List[Int] , f : (Int , Int) => Int) = {
      var first = l(0)
      for(i <- 1 until(l.length)){
        first = f(first , l(i))
      }
      first
    }

    println("========================")
    println(reduce(l , _ + _))

    println("========================")
    println(reduce(l , (x, y) => {
      println(s"$x === $y")
      x + y
    }))
  }
}
