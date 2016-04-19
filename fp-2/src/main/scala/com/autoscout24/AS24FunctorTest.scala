package com.autoscout24

import cats.Functor
import cats.std.function._
import cats.std.option._
import cats.syntax.functor._

object AS24FunctorTest extends App {
  val triple = (x: Int) => x * 3
  val double = (y: Int) => y * 2
  val doubleAndTriple = triple map double

  doubleAndTriple(1)

  // lift a function A => B into function F[A] => F[B]
  val increment = (x: Int) => x + 1
  val lifted = Functor[Option].lift(increment)

  lifted(Some(1))
}
