package com.example

object CurryingExamples {

  def fun(a: Int) = (b: Int) => (a,b)
  def fun2(a: Int)(b: Int) = (a,b)

  def main(args: Array[String]): Unit = {
    println(fun(1)(2))
    println(fun2(1)(2))
  }
}
