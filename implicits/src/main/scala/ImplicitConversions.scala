object ImplicitsConversions {

  case class RichString(str: String) {
    def toUpper: String = str.toUpperCase
  }

  //TODO: Exercise 1 - provide implicit conversions from String to RichString anc vice versa
  implicit def stringToRichString = ???

}
