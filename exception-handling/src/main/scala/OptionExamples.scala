object OptionExamples {

  val incrementWrapListFunction = { a : Int => List(a + 1) }
  val incrementWrapOptionFunction = { a : Int => Some(a + 1) }
  val filterFunction = { a : Int => a > 2 }

  def firstListFunction(aList: List[Int]): List[List[Int]] = {
    aList.map(incrementWrapListFunction)
  }
  
  def secondListFunction(aList: List[Int]): List[Int] = {
    aList.flatMap(incrementWrapListFunction)
  }
  
  def thirdListFunction(aList: List[Int]): List[Int] = {
    aList.filter(filterFunction)
  }

  def firstOptionFunction(option: Option[Int]): Option[Option[Int]] = {
    option.map(incrementWrapOptionFunction)
  }

  def secondOptionFunction(option: Option[Int]): Option[Int] = {
    option.flatMap(incrementWrapOptionFunction)
  }

  def thirdOptionFunction(option: Option[Int]): Option[Int] = {
    option.filter(filterFunction)
  }
}
