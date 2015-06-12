package playIntro.unit

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import playIntro.calculation.Calculator

@RunWith(classOf[JUnitRunner])
class CalculatorSpec extends Specification {

  "Calculator" should {

    "should compute the addition of two numbers" in {
      val calculator = new Calculator()
          calculator.add(1,3) mustEqual 4
    }
  }
}
