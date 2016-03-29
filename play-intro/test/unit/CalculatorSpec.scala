package unit

import models.Calculator
import org.specs2.mutable.Specification

class CalculatorSpec extends Specification {

  "Calculator" should {

    "compute the addition of two numbers" in {
      Calculator.add(1,3) mustEqual 4
    }

    "compute the circumference with the given radius" in {
      Calculator.circumference(2.0) mustEqual 12.56637061436
    }
  }
}
