package com.autoscout24

trait Printable[A] {

  def format(value: A): String

}

object PrintDefaults {

  implicit val printableString = new Printable[String] {
    def format(value: String): String = ???
  }

  implicit val printableInt = new Printable[Int] {
    def format(value: Int): String = ???
  }

}

object Print {

  def format[A](value: A)(implicit printable: Printable[A]): String = ???

  def print[A](value: A)(implicit printable: Printable[A]): Unit = ???

}