package com.k66.scala.oo

object TraitApp {
  def main(args: Array[String]): Unit = {
    val sMem = new StaticMemoryManager
    println(sMem.maxOnHeapStorageMemory)

    val uMem = new UnifiedMemoryManager
    println(uMem.maxOnHeapStorageMemory)

    val sMem2 = new StaticMemoryManager2
    sMem2.log(s"${sMem2.name}加载就绪")
    println(sMem2.maxOnHeapStorageMemory)
//    throw sMem2.exception
    println(sMem2.exception)

    new StudentService().register(new Student("k66" , 20))
  }
}

trait MemoryManager{

  println("---------------MemoryManager--------------")

  val name:String

  def maxOnHeapStorageMemory:Long
}

class StaticMemoryManager extends MemoryManager{

  println("---------------StaticMemoryManager--------------")

  override val name: String = "静态内存管理"

  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存")
    100L
  }
}

class UnifiedMemoryManager extends MemoryManager{

  println("---------------UnifiedMemoryManager--------------")

  override val name: String = "统一的内存管理"

  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存")
    200L
  }
}

class MemoryLogger{
  println("---------------MemoryLog--------------")

  def log(msg:String): Unit = {
    println(s"开始打印日志: [$msg]")
  }
}

trait MemoryException{
  println("---------------MemoryException--------------")
  def exception:Exception
}

/**
 * 静态混入
 * 按 extends ... with ... with ...顺序
 *
 * 动态混入 new StaticMemoryManager with xxx with xxx with xxx
 * 可匿名实现
 */
class StaticMemoryManager2 extends MemoryLogger  with MemoryManager with MemoryException {

  println("---------------StaticMemoryManager2--------------")

  override val name: String = "静态内存管理2"

  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存")
    0L
  }

  override def exception: Exception = new RuntimeException(s"$name Full Memory")
}

class Student(val name : String , val age : Int)

trait DAO{
  def save(s : Student): Unit = {
    println(s"保存${s.name}到数据库")
  }
}



trait Service{
//  self:DAO =>
  _:DAO =>
  def register(s : Student): Unit = {
    println(s"${s.name}注册成功")
    this.save(s)
  }
}

class StudentService extends Service with DAO