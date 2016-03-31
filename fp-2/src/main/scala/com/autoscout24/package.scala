package com

import cats.Monoid
import cats.syntax.semigroup._

package object autoscout24 {
  case class Order(cost: Double, quantity: Int)

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.fold(monoid.empty)(_ |+| _)

  implicit val monoidOrder: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = Order(x.cost + y.cost, x.quantity + y.quantity)
  }

}

