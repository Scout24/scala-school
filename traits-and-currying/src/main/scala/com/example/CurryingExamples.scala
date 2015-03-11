package com.example

object CurryingExamples {

  def fun(a: Int) = (b: Int) => (a,b)
  def funGen[A,B](a: A) = (b: B) => (a,b)
  def fun2(a: Int)(b: Int) = (a,b)
  def fun2Gen[A,B](a: A)(b: B) = (a,b)
  def fun3Gen[A](a: A)(b: Some[A]) = (a,b)
  def fun4Gen[A](a: A, b: Some[A]) = (a,b)

  def main(args: Array[String]): Unit = {
    println(fun(1)(2))
    println(fun2(1)(2))
    //val y = fun3Gen(1)(Some("A")) // correctly shows a compile error
    val x: (Any, Some[Any]) = fun4Gen(1, Some("A")) //type inference cannot work here
  }
}
