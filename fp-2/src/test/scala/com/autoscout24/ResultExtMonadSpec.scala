package com.autoscout24

import cats.syntax.flatMap._
import cats.syntax.functor._
import com.autoscout24.DefaultResultMonad._
import com.autoscout24.ResultExt._
import org.specs2.Specification

class ResultExtMonadSpec extends Specification { def is = s2"""

  This is a specification to check Result Monad

  flatMap function should
    collect result and msg if applied to succ and warn $e2
    collect result and msgs if applied to warnings $e3
    collect failure msgs if applied to failures $e4
    colelct result and msgs $e5
  """

  def e1 = succ(10) map (_ + 1) must_== succ(11)

  def e2 = warn(10, "blah") flatMap {a => succ(a + 1)} must_== warn(11, "blah")

  def e3 = warn(10, "blah") flatMap {a => warn(a * 2, "double")} must_== warn(20, "blah double")

  def e4 = warn(10, "blah") flatMap {a => fail[Int]("failure")} must_== fail("blah failure")

  def e5 = {
    val actual = for {
      a <- warn(10, "10")
      b <- warn(11, "11")
      c <- warn(a + b, "sum")
    } yield c

    actual must_== warn(21, "10 11 sum")
  }

  def e6 = fail[Int]("blahblah") map (_ / 0) must_== fail("blahblah")

}
