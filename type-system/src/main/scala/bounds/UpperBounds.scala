package bounds

trait Similar {
  def isSimilar(x: Any): Boolean
}
case class MyInt(x: Int) extends Similar {
  def isSimilar(m: Any): Boolean =
    m.isInstanceOf[MyInt] &&
      m.asInstanceOf[MyInt].x == x
}
object UpperBoundTest extends App {
  import scala.collection.mutable._
  def findSimilar(e: Similar, xs: MutableList[Similar]): Boolean =
  //def findSimilar[T <: Similar](e: T, xs: MutableList[T]): Boolean =
    if (xs.isEmpty) false
    else if (e.isSimilar(xs.head)) true
    //else findSimilar[T](e, xs.tail)
    else findSimilar(e, xs.tail)
  val list: MutableList[MyInt] = MutableList(MyInt(1), MyInt(2), MyInt(3))
  
  // TODO Exercise 2 - make this work using an upper bound
  println(findSimilar(MyInt(4), list))
  println(findSimilar(MyInt(2), list))
}