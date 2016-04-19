package com.autoscout24

import cats.Functor

sealed trait Result[+A]

final case class Success[A](value: A) extends Result[A]

final case class Warning[A](value: A, message: String) extends Result[A]

final case class Failure(message: String) extends Result[Nothing]

object Result {
  def succ[A](value: A): Result[A] = Success(value)

  def warn[A](value: A, message: String): Result[A] = Warning(value, message)

  def fail[A](message: String): Result[A] = Failure(message)
}

object DefaultResultFunctor {
  import Result._

  implicit val resultFunctor: Functor[Result] = new Functor[Result] {
    override def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = ???
  }
}
