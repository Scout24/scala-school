package com.autoscout24

import cats.Monad
import cats.syntax.functor._
import cats.syntax.flatMap._

import com.autoscout24.ResultExt._
import com.autoscout24.DefaultResultMonad._
import org.specs2.Specification

class ResultFunctorSpec extends Specification { def is = s2"""

  This is a specification to check Result Monad

  flatMap function should
    collect msg if applied to succ and warn $e1
    collect results and msgs if applied to warnings $e2
    collect failure msgs if applied to failures $e3
  """

  def e1 = warn(10, "blah") flatMap {a => succ(a + 1)} must_== warn(11, "blah")

  def e2 = warn(10, "blah") flatMap {a => warn(a * 2, "double")} must_== warn(20, "blah double")

  def e3 = warn(10, "blah") flatMap {a => fail[Int]("failure")} must_== fail("blah failure")

}
