package playIntro.unit

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import playIntro.calculation.Calculator

@RunWith(classOf[JUnitRunner])
class CalculatorSpec extends Specification {

  "Calculator" should {

    "should compute the addition of two numbers" in {
      Calculator.add(1,3) mustEqual 4
    }

    "compute the circumference with the given radius" in {
      Calculator.circumference(2.0) mustEqual 12.56637061436
    }
  }
}
