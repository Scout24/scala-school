package com.autoscout24

import scalaz.Monoid

object SuperAdder {

  def add(items: List[Int]): Int = ???

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = ???

}

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val monoid: Monoid[Order] = ???
}
