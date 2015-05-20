import org.scalatest._

class PatternMatchingTests extends FlatSpec with Matchers {
  "Pattern matching" should "be tested" in {
    true should === (true)
  }
}
