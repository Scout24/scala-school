object CurryingExamples {

  type A = Int
  type B = Option[Int]
  type C = (A, B)

  def fun(a: A, b: B): C = (a,b)
  def funCurried(a: A): B => C = (b: B) => (a,b)
  def funCurried2(a: A)(b: B): C = (a,b)

  def funGen[T](a: T, b: Some[T]) = (a,b)
  def funGenCurried[T](a: T)(b: Some[T]) = (a,b)

  def main(args: Array[String]): Unit = {
    val x: (Any, Some[Any]) = funGen(1, Some("A")) //type inference cannot help here
    //val y = funGenCurried(1)(Some("A")) // correctly shows a compile error
  }
}
