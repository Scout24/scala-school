import org.scalatest._

class CurryingTests extends FlatSpec with MustMatchers {
  "Test" should "have tests" in {
    CurryingExamples.filterByMod2(1 to 10) must be === 2 :: 4 :: 6 :: 8 :: 10 :: Nil
  }
}
