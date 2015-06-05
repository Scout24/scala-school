object ImplicitsConversions {

  case class RichString(str: String) {
    def toAllUpper: String = str.toUpperCase
  }

  //TODO: Exercise 2: provide implicit conversions between String and RichString

}
