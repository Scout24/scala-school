import org.scalatest._
import Mocks._

class ImplicitsTests extends FlatSpec with MustMatchers {
  "Implicit conversions" should "be used" in {
    import ImplicitsConversions._
    "abcdefg".toAllUpper must be("ABCDEFG")
  }

  "Implicit conversions" should "also be used" in {
    import ImplicitsConversions._
    val rs = RichString("abcdefg")
    rs.length() must be(7)
  }

  "Implicit parameters" should "be used" in {
    import MathConstants._
    ImplicitsParameters.calculateCircumference(2.0) must be(12.5663604976)
  }

}
