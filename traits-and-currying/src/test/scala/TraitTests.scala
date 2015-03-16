import org.scalatest._
import TraitExamples._

class TraitTests extends FlatSpec with MustMatchers {
  "queue 1" should "turn a 3 into a 7" in {
    queue1.put(3)
    queue1.get must be === 7
  }

  "small ordered point" should "be smaller than big ordered point" in {
    (orderedPointSmall < orderedPointBig) must be(true)
  }
}