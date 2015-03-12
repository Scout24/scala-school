object CurryingExamples {

  type A = Int
  type B = Option[Int]
  type C = (Int, Option[Int])

  type ABtoC = (A,B) => C
  type AtoBtoC = A => (B => C)

  def fun(a: A, b: B) = (a,b)
  def funCurried(a: A) = (b: B) => (a,b)
  def funCurried2(a: A)(b: B) = (a,b)

  def funGen[T](a: T, b: Some[T]) = (a,b)
  def funGenCurried[T](a: T)(b: Some[T]) = (a,b)

  def modN(n: Int)(x: Int) = ((x % n) == 0)
  def modNnc(n: Int, x: Int) = ((x % n) == 0)
  def filterByMod2(l:Seq[Int]) = l.filter(modN(2))
  def filterByMod2nc(l:Seq[Int]) = l.filter(modNnc(2,_))

  def main(args: Array[String]): Unit = {
    val x: (Any, Some[Any]) = funGen(1, Some("A")) //type inference cannot help here
    //val y = funGenCurried(1)(Some("A")) // correctly shows a compile error
  }
}
