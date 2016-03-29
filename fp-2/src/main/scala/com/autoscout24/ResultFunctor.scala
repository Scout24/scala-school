package com.autoscout24

sealed trait Result[+A]

final case class Success[A](value: A) extends Result[A]

final case class Warning[A](value: A, message: String) extends Result[A]

final case class Failure(message: String) extends Result[Nothing]

class ResultFunctor {

}
