package data

/**
  * Created by matlloyd on 29/02/2016.
  *
  * Only used to get the Syntax Highlighting to validate.
  */

case class RomanNumeral(str: String) {
  lazy val value = str match {
    case "VI" => 6
    case "CCXXVII" => 227
  }
}
