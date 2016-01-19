package com.autoscout24

import org.scalatest.{MustMatchers, WordSpec}

class MonoidsSpec extends WordSpec with MustMatchers {

  "SuperAdder.add" should {

    "add together a list of Ints" in {
      SuperAdder.add(List(8,12,32)) must be(52)
    }

    "add together a list of Option[Int]" in {
      SuperAdder.add(List(Some(8),None,Some(4))) must be(Some(12))
    }

    "add together a list of Orders" in {
      SuperAdder.add(List(Order(12,2), Order(5.5, 4))) must be(Order(17.5,6))
    }
  }

}
