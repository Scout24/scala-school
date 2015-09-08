package currying

object CurryingExamples {

  type A = Int
  type B = Option[Int]
  type C = (A, B)

  def fun(a: A, b: B): C = (a,b)
  def funCurried(a: A): B => C = (b: B) => (a,b)
  def funCurriedSugar(a: A)(b: B): C = (a,b)

  // partial application
  val partAppFun: B => C = funCurried(42)
  val result: C = partAppFun(Some(13))


  // enforcing of type inference order
  def funGen[T](a: T, b: Some[T]) = (a,b)
  def funGenCurried[T](a: T)(b: Some[T]) = (a,b)

  val x = funGen(1, Some("A")) //type inference cannot help here
  //val y = funGenCurried(1)(Some("A")) // correctly shows a compile error


  //TODO: Currying Exercise
  def modN(n: Int)(x: Int) = ((x % n) == 0)
  def filterByMod2(numbers: Seq[Int]): Seq[Int] = ???

  def modNnotCurried(n: Int, x: Int) = ((x % n) == 0)
  def filterByMod2notCurried(numbers: Seq[Int]): Seq[Int] = ???
}