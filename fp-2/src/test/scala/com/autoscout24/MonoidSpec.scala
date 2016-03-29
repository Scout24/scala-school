package com.autoscout24

import org.specs2._
import cats.Monoid
import cats.std.int._
import cats.std.option._

class MonoidSpec extends Specification  { def is = s2"""

  This is a specification to check add function

  add function should
    sum ints                                    $e1
    sum options of int                          $e2
    sum orders                                  $e3
  """

  def e1 = add(List(1, 2, 3)) must_== 6
  def e2 = add(List(Some(1), Some(2), Some(3), None)) must_== Some(6)
  def e3 = add(List(Order(1.1, 2), Order(1.2, 3))) must_== Order(2.3, 5)

}
