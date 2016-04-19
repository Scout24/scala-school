package com.autoscout24

import cats.Monad

sealed trait ResultExt[+A]

final case class SuccessExt[A](value: A) extends ResultExt[A]

final case class WarningExt[A](value: A, message: String) extends ResultExt[A]

final case class FailureExt(message: String) extends ResultExt[Nothing]

object ResultExt {
  def succ[A](value: A): ResultExt[A] = SuccessExt(value)

  def warn[A](value: A, message: String): ResultExt[A] = WarningExt(value, message)

  def fail[A](message: String): ResultExt[A] = FailureExt(message)
}

object DefaultResultMonad {
  import ResultExt._

  implicit val resultMonad: Monad[ResultExt] = new Monad[ResultExt] {
    override def pure[A](x: A): ResultExt[A] = succ(x)

    override def flatMap[A, B](fa: ResultExt[A])(f: (A) => ResultExt[B]): ResultExt[B] = ???
  }
}