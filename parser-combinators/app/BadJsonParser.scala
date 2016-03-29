import scala.language.postfixOps
import scala.util.parsing.combinator.RegexParsers

/**
  * Created by matlloyd on 15/02/2016.
  */
class BadJsonParser extends RegexParsers {

  type JsonValue = Any
  def jsonBool: Parser[Boolean] = ("true" ^^^ true) | ("false" ^^^ false)
  def jsonNum: Parser[Int] = "[0-9]+" ^^ { _.toInt }
  def jsonString: Parser[String] = "\"" ~> "[^\"]+".r <~ "\""
  def jsonArray: Parser[List[JsonValue]] = "[" ~> ((jsonValue <~ ",")*) <~ "]"
  def jsonObjEntry: Parser[(String, JsonValue)] = ("[^\"]+".r <~ ":") ~ jsonValue ^^
    { case key ~ value => (key, value) }
  def jsonObj: Parser[Map[String, JsonValue]] = "{" ~> (jsonObjEntry*) <~ "}" ^^ { _.toMap }
  def jsonValue: Parser[JsonValue] = jsonBool | jsonNum | jsonString | jsonArray | jsonObj

}
