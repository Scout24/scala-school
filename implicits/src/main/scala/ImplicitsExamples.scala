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

  implicit def ordered[A <: Ordered[A]]: Ordering[A] = new Ordering[A] {
    def compare(x: A, y: A) = x.compare(y)
  }

  def printOrdered[A](input: A)(implicit ord: Ordered[A]) {
    println(ord.compare(input))
  }

  def printOrdered2[A : Ordered](input: A) {
  //def printOrdered2[A](input: A) {
    printOrdered() //gets the ordering implicitly
  }

  printOrdered2(1 :: 2 :: Nil)




}
