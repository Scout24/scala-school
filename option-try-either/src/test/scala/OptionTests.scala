import org.scalatest.{MustMatchers, FlatSpec}

class OptionTests extends FlatSpec with MustMatchers {
  val aList = List(1,2,3,4,5)
  val anOption = Some(3)

  "firstListFunction" should "return a list of list of A" in {
    OptionExamples.firstListFunction(aList) must be (List(List(2), List(3), List(4), List(5), List(6)))
  }

  "secondListFunction" should "return a list of A" in {
    OptionExamples.secondListFunction(aList) must be (List(2,3,4,5,6))
  }

  "firstOptionFunction" should "return a some of some of A" in {
    OptionExamples.firstOptionFunction(anOption) must be (Some(Some(4)))
  }

  "secondOptionFunction" should "return a some of A" in {
    OptionExamples.secondOptionFunction(anOption) must be (Some(4))
  }

  "firstOptionFunction" should "work with None" in {
    OptionExamples.firstOptionFunction(None) must be (Some(None))
  }

  "secondOptionFunction" should "also work with None" in {
    OptionExamples.secondOptionFunction(None) must be (None)
  }

}
