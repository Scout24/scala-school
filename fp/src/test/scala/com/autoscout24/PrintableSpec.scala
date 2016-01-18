package com.autoscout24

import org.scalatest.{MustMatchers, WordSpec}
import PrintDefaults._

class PrintableSpec extends WordSpec with MustMatchers {

  "Print.format" should {

    "print an integer" in {
      Print.format(356) must be("356")
    }

    "print a string" in {
      Print.format("Hello") must be("Hello")
    }
  }

}
