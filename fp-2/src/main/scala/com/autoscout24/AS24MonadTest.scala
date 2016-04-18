package com.autoscout24

import cats.Monad
import cats.std.option._
import cats.std.list._
import cats.syntax.flatMap._

object AS24MonadTest extends App {
  val opt1 = Monad[Option].pure(1)
  val opt2 = opt1 flatMap {a => Monad[Option].pure(a + 1)}

  val list1 = Monad[List].pure(1)
  val list2 = list1 flatMap {a => Monad[List].pure(a + 1)}
}
