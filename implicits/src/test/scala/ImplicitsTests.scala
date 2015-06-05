import org.scalatest._
import ImplicitsParameters._

class ImplicitsTests extends FlatSpec with MustMatchers {
  "Implicits" should "be applied" in {
    import MathConstants._
    calculateCircumference(2.0) must be(12.5663604976)
  }
}
