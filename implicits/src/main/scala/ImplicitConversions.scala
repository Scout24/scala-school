object ImplicitsConversions extends App {


  val HELLO = "hello".toAllUpper





  class RichString(val str: String) {
    def toAllUpper = str.toUpperCase
  }

  implicit def string2RichString(str: String): RichString = new RichString(str)


  

  def takeRichString(rs: RichString) { rs.length }

  takeRichString("hello")



  implicit def richString2String(rs: RichString): String = rs.str


}
