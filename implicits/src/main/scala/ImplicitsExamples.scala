object ImplicitsExamples {
  def main(args: Array[String]): Unit = {
    println("Implicit tests")
  }

  "hello".toAllUpper

  class RichString(str: String) {
    def toAllUpper = str.toUpperCase
  }
  implicit def string2RichString(str: String): RichString = new RichString(str)
  //implicit def string2RichString(str: String): Object = new RichString(str)

  string2RichString("hello").toAllUpper

  //---------- Context bounds --------

  trait Comparator[A] { def isOrdered(a1: A, a2: A): Boolean }
  implicit object IntComparator extends Comparator[Int] {
    override def isOrdered(a1: Int, a2: Int): Boolean = if(a1 < a2) true else false
  }

  //def orderPairList[A](list: List[(A,A)])(implicit ord: Comparator[A]) = {
  def orderPairList[A : Comparator](list: List[(A,A)]) = {
    for { pair <- list } yield orderPair(pair)
  }

  def orderPair[A](pair: (A, A))(implicit ord: Comparator[A]) = {
    if(ord.isOrdered(pair._1, pair._2)) pair else pair.swap
  }
//  def orderPair[A : Comparator](pair: (A, A)) = {
//    if(implicitly[Comparator[A]].isOrdered(pair._1, pair._2)) pair else pair.swap
//  }

  println(orderPairList((1,2) :: (2,1) :: Nil))



}
