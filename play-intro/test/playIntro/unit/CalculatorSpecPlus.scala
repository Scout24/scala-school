package playIntro.unit

import org.scalatestplus.play._
import playIntro.calculation.Calculator

class CalculatorSpecPlus extends PlaySpec {

  "Calculator" must {

    "compute the addition of two numbers" in {
      Calculator.add(1,3) mustBe 4
    }

    "compute the circumference with the given radius" in {
      Calculator.circumference(2.0) mustBe 12.56637061436
    }
  }
}
