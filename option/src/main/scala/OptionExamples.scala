object OptionExamples {

  val ListFunction = { a : Int => List(a + 1) }
  val OptionFunction = { a : Int => Some(a + 1) }
  val filterFunction = { a : Int => a > 2 }

  def incrementList(aList: List[Int]): List[List[Int]] = ???
  
  def incrementAndFlattenList(aList: List[Int]): List[Int] = ???
  
  def filterList(aList: List[Int]): List[Int] = ???

  def incrementOption(option: Option[Int]): Option[Option[Int]] = ???

  def incrementAndFlattenOption(option: Option[Int]): Option[Int] = ???

  def filterOption(option: Option[Int]): Option[Int] = ???
}
