object OptionExamples {

  val incrementWrapListFunction = { a : Int => List(a + 1) }
  val incrementWrapOptionFunction = { a : Int => Some(a + 1) }

  def firstListFunction(aList: List[Int]): List[List[Int]] = ???
  
  def secondListFunction(aList: List[Int]): List[Int] = ???

  def firstOptionFunction(option: Option[Int]): Option[Option[Int]] = ???

  def secondOptionFunction(option: Option[Int]): Option[Int] = ???
}
