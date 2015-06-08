object Mocks {
  implicit def anyToRichString(str: Any): Object {def toUpper: String} =  new { def toUpper = "NOPE :(" }
  implicit def richStringToNull(rs: Any): String = null.asInstanceOf[String]
}
