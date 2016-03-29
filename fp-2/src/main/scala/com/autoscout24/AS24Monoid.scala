package com.autoscout24

import cats.Monoid
import cats.std.string._
import cats.std.int._
import cats.syntax.semigroup._

object AS24MonoidTest extends App {

  val helloWorld = "Hello " |+| "world" |+| Monoid[String].empty

  val three = 1 |+| 2 |+| Monoid[Int].empty
}
