package com.autoscout24

import org.scalatest.{MustMatchers, WordSpec}

class ScalazSpec extends WordSpec with MustMatchers {

  "Scalaz equals method" should {
    import scalaz.syntax.equal._

    val cat1 = Cat("Garfield", 35, "orange and black")
    val cat2 = Cat("Heathcliff", 30, "orange and black")

    "compare cats" in {
      cat1 =/= cat2 must be(true)
    }

    "compare options of cats" in {
      import scalaz.std.option._

      val optionCat1: Option[Cat] = Some(cat1)
      val optionCat2: Option[Cat] = Some(cat2)
      val noneCat: Option[Cat] = None

      optionCat1 =/= optionCat2 must be(true)
      optionCat1 =/= noneCat must be(true)
    }
  }

}
