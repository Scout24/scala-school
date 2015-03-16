import org.scalatest._

class TraitTests extends FlatSpec with MustMatchers {
  "Trait Test" should "have tests" in {
    TraitExamples.queue1.put(3)
    TraitExamples.queue1.get must be === 7
  }
}