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


}
