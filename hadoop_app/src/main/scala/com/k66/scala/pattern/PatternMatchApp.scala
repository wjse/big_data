package com.k66.scala.pattern

import scala.io.StdIn
import scala.util.Random

/**
 * 模式匹配
 */
object PatternMatchApp{
  def main(args: Array[String]): Unit = {
//    test01()
//    test02()
//    test03()
//    test04()
//    test05()
//    test06()
//    test07()
//    test08()
//    test09()
    test10()
  }

  private def test01(): Unit = {
    val a = 9
    val b = 3
    val in = StdIn.readLine("请输入运算符:")
//    in match {
//      case "+" => println(a + b)
//      case "-" => println(a - b)
//      case "*" => println(a * b)
//      case "/" => println(a / b)
//      case _ => println("请正确输入")
//    }
    val v = in match {
      case "+" => a + b
      case "-" => a - b
      case "*" => a * b
      case "/" => a / b
      case _ => "请正确输入"
    }

    println(v)
  }

  private def test02(): Unit = {
    val array = Array("A" , "B" , "C" , "D" , "E" , "F" , "G")
    1.to(10).foreach(i => {
      val s = array(Random.nextInt(array.length))
      Thread.sleep(1000)
      val index = s match {
        case "A" => 0
        case "B" => 1
        case "C" => 2
        case "D" => 3
        case "E" => 4
        case "F" => 5
        case "G" => 6
        case _ => -1
      }
      println(s"Current teacher index is $index")
    })
  }

  /**
   * 匹配时添加if作守卫判断
   */
  private def test03() : Unit = {
    def judgeGrade(grade:String , name:String): Unit = {
      grade match {
        case "A" => println("优")
        case "B" => println("良")
        case "C" => println("中")
        case "D" => println("差")
        case _ if(name == "krr") => println("还行")
        case _ => println("不及格")
      }
    }

    judgeGrade("A" , "krr")
    judgeGrade("E" , "krr")
  }

  private def test04(): Unit = {
    def matchType(obj:Any): Unit = {
      obj match {
        case x:Int => println("Int")
        case x:Long => println("Long")
        case x:String => println("String")
        case x:Map[_,_] => println("Map")
        case x:List[_] => println("List")
        case _ => println("Unsupported Type")
      }
    }

    matchType(1)
    matchType(1L)
    matchType("krr")
    matchType(Set(0,1))
    matchType(List(0,1))
    matchType(Map("name" -> "krr"))

    val array = Array(1 , 20 , 3 , "krr" , true)
    1.to(5).foreach(x => {
      array(Random.nextInt(array.length)) match {
        case a: Int if a < 10 => println("TinyInt")
        case a: Int if a > 10 => println("BigInt")
        case a: String if a.equals("krr") => println(s"${a}K66")
        case a: Boolean if a => println("It's True")
      }
    })
  }

  /**
   * 数组模式匹配@_*的用法
   */
  private def test05(): Unit = {
    def greeting(array:Array[String]): Unit = {
      array match {
        case Array("A") => println("A")
        case Array(x , y) => println(s"Hi ${x} , ${y}")
        case Array("A") => println("A")
        case Array("A" , _*) => println(s"A and others")
        case _ => println("Nothing")
      }
    }

    greeting(Array("A"))
    greeting(Array("A" , "B"))
    greeting(Array("A" , "B" , "C"))
    greeting(Array("B" , "C"))
    greeting(Array("B" , "C" , "D"))

    val array = Array(1 , 2 , 3, 4)
    array match {
      case Array(1 ,2,other@_*) => println(other.toList)
    }
  }

  /**
   * Tuple匹配
   */
  private def test06() : Unit = {
    val infos = ("krr" , 37)
    infos match {
      case (name , age) => println(s"$name = $age")
    }

    infos match {
      case (name:String , _) => println(name)//只取要用的其他用_代替
    }
  }

  /**
   * List匹配
   */
  private def test07(): Unit = {
    def greeting(list: List[String]): Unit = {
      list match {
        case "A"::Nil => println("A")
        case x::y::Nil => println(s"Hi ${x} , ${y}")
        case List("A") => println("A")
        case "A"::_ => println(s"A and others")
        case List("C",other@_*) => println(s"C and ${other.mkString(",")}")
        case _ => println("Nothing")
      }
    }

    greeting(List("A"))
    greeting(List("A", "B"))
    greeting(List("A", "B", "C"))
    greeting(List("B", "C"))
    greeting(List("B", "C", "D"))
    greeting(List("C", "B", "A"))

    List(1 , 2 , 3 , 4) match {
      case a::b::c =>
        println(a) //1
        println(b) //2
        println(c) //List(3 , 4)
    }
  }

  /**
   * 对class匹配
   * 一般不用，较麻烦，使用cass class匹配
   */
  private def test08(): Unit = {
    class Person(val name:String , val age:Int)
    //Person伴生对象
    object Person{
      def unapply(person: Person) : Option[(String , Int)] = {
        if(null != person){
          Some(person.name , person.age)
        }else{
          None
        }
      }
    }
    val krr = new Person("krr" , 37)

    krr match {
      //此处调用的是Person的伴生对象中的unapply方法
      case Person(name , age) => println(s"$name , $age")
      case _ =>
    }
  }

  /**
   * cass class匹配
   */
  private def test09(): Unit={
    case class User(name:String , age:Int)
    val user = User("krr" , 37)

    user match {
      case User(name , age) => println(s"$name , $age")
    }
  }

  /**
   * Spark中模式匹配例子
   */
  private def test10(): Unit = {
    case class SubmitTask(id:String , name:String)
    case class Heartbeat(time:Long)
    case object CheckTimeOutTask

    val inputs = Array(CheckTimeOutTask , Heartbeat(1000) , SubmitTask("001" , "Task001"))

    1.to(10).foreach(i => {
      inputs(Random.nextInt(inputs.length)) match {
        case CheckTimeOutTask => println("CheckTimeOutTask")
        case Heartbeat(time) => println(s"Heartbeat: $time")
        case SubmitTask(id , name) => println(s"SubmitTask: $id , $name")
      }
    })
  }
}