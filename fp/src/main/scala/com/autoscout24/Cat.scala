package com.autoscout24

case class Cat(name: String, age: Int, color: String) {}

object Cat {
  import scalaz.Equal
  implicit val catEqual = Equal.equal[Cat] {
    (cat1, cat2) => ???
  }
}
