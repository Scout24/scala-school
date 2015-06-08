object ImplicitsConversions {

  case class RichString(val str: String) {
    def toUpper: String = str.toUpperCase
  }

  //TODO - Exercise 2: provide implicit conversions from String to RichString anc vice versa
  implicit def stringToRichString = ???

}
