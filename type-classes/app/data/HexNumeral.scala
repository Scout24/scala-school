package data

/**
  * Created by matlloyd on 29/02/2016.
  */

case class HexNumeral(str: String) {
  lazy val value = str match {
    case "0xE3" => 227
  }
}
