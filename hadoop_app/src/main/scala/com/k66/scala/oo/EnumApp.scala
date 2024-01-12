package com.k66.scala.oo

object EnumApp {
  def main(args: Array[String]): Unit = {
    println(WeekDay.isWorkingDay(WeekDay.Fri))
  }
}

object WeekDay extends Enumeration {
  private type WeekDay = Value
  val Mon , Tue , Wed , Thu , Fri , Sat , Sun = Value

  def isWorkingDay(d: WeekDay) = !(d == Sat || d == Sun)
}