import org.scalatest.{MustMatchers, FlatSpec}

class OptionTests extends FlatSpec with MustMatchers {
  val aList = List(1,2,3,4,5)
  val anOption = Some(3)

  "firstListFunction" should "return a list of list of A" in {
    OptionExamples.incrementList(aList) must be (List(List(2), List(3), List(4), List(5), List(6)))
  }

  "secondListFunction" should "return a list of A" in {
    OptionExamples.incrementAndFlattenList(aList) must be (List(2,3,4,5,6))
  }

  "thirdListFunction" should "return a list of A" in {
    OptionExamples.filterList(aList) must be (List(3,4,5))
  }

  "firstOptionFunction" should "return a some of some of A" in {
    OptionExamples.incrementOption(anOption) must be (Some(Some(4)))
  }

  "secondOptionFunction" should "return a some of A" in {
    OptionExamples.incrementAndFlattenOption(anOption) must be (Some(4))
  }

  "firstOptionFunction" should "work with None" in {
    OptionExamples.incrementOption(None) must be (None)
  }

  "secondOptionFunction" should "also work with None" in {
    OptionExamples.incrementAndFlattenOption(None) must be (None)
  }

  "thirdOptionFunction" should "filter" in {
    OptionExamples.filterOption(Some(3)) must be (Some(3))
    OptionExamples.filterOption(Some(2)) must be (None)
    OptionExamples.filterOption(None) must be (None)
  }


}
