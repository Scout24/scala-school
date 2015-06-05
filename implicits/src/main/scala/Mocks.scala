object Mocks {
  implicit def any2RichString(str: Any): Object {def toAllUpper: String} =  new { def toAllUpper = "NOPE" }
  implicit def richStringToNull(rs: Any): String =  null.asInstanceOf[String]
}
