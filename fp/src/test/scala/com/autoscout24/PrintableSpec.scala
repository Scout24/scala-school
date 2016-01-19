package com.autoscout24

import org.scalatest.{MustMatchers, WordSpec}

class PrintableSpec extends WordSpec with MustMatchers {

  "Print.format" should {

    "print an integer" in {
      Print.format(356) must be("356")
    }

    "print a string" in {
      Print.format("Hello") must be("Hello")
    }

    "print a cat" in {
      val freddie = new Cat("Freddie", 5, "ginger")
      Print.format(freddie) must be("Freddie is a 5 year-old ginger cat.")
    }
  }

  "PrintSyntax" should {
    import PrintSyntax._

    "add an implicit print method to integer" in {
      1983.format must be("1983")
    }

    "add an implicit print method to string" in {
      "This is a printable string".format must be("This is a printable string")
    }

    "add an implicit print method to cat" in {
      val mittens = new Cat("Mittens", 10, "black and white")
      mittens.format must be("Mittens is a 10 year-old black and white cat")
    }

  }

}
