object OptionExamples {

  val incrementWrapListFunction = { a : Int => List(a + 1) }
  val incrementWrapOptionFunction = { a : Int => Some(a + 1) }
  val filterFunction = { a : Int => a > 2 }

  def incrementListFunction(aList: List[Int]): List[List[Int]] = ???
  
  def incrementAndFlattenListFunction(aList: List[Int]): List[Int] = ???
  
  def filterListFunction(aList: List[Int]): List[Int] = ???

  def incrementOptionFunction(option: Option[Int]): Option[Option[Int]] = ???

  def incrementAndFlattenOptionFunction(option: Option[Int]): Option[Int] = ???

  def filterOptionFunction(option: Option[Int]): Option[Int] = ???
}
