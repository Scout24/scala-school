package com.autoscout24

import org.specs2.Specification

import cats.syntax.functor._
import com.autoscout24.Result._
import com.autoscout24.DefaultResultFunctor._

class ResultFunctorSpec extends Specification { def is = s2"""

  This is a specification to check Result Functor

  map function should
    apply function to Success value $e1
    apply function to Warning value $e2
    not apply function to Failure $e3
  """

  def e1 = succ(10) map (_ + 1) must_== succ(11)

  def e2 = warn(10, "blah") map (_ * 2) must_== warn(20, "blah")

  def e3 = fail[Int]("blahblah") map (_ / 0) must_== fail("blahblah")

}
