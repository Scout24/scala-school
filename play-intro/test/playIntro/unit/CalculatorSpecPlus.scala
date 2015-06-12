package playIntro.unit

import org.scalatestplus.play._
import playIntro.calculation.Calculator

class CalculatorSpecPlus extends PlaySpec {

  "Calculator" must {

    "compute the addition of two numbers" in {
      val calculator = new Calculator()
      calculator.add(1,3) mustBe 4
    }
  }
}
