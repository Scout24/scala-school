import play.libs.Json
import play.twirl.api.Xml

import scala.language.postfixOps
import scala.util.parsing.combinator.RegexParsers

/**
  * Created by matlloyd on 15/02/2016.
  */
class ParserExamples extends RegexParsers {

  // Required types for highlighting.
  type JsonValue = Any
  val JsonGrammar:Parser[Json] = ???
  val RomanNumeralGrammar:Parser[Int] = ???
  val XMLGrammar:Parser[Xml] = ???
  val jsonListEntry: Parser[JsonValue] = ???

  // Slide 2

  parse(JsonGrammar, "{ name: 'Lemmy Kilmister' }") // JSONObject
  parse(RomanNumeralGrammar, "VIII") // 8
  parse(XMLGrammar, "<cars><car>...</car><car>...</car></cars>") //[XMLObject]

  // Slide 3

  def I: Parser[Int] = "I" ^^^ 1
  def V: Parser[Int] = "V" ^^^ 5
  def X: Parser[Int] = "X" ^^^ 10

  parse(X, "X") match {
    case Success(n, next) => // n == 10
    case NoSuccess(err, next) =>
  }

  // Slide 4

  def numStr: Parser[String] = "[0-9]+".r

  parse(numStr, "3432") match {
    case Success(n, next) => n == "3432"
  }

  // Slide 5

  def numInt: Parser[Int] = "[0-9]+".r ^^ {
    numberStr => numberStr.toInt
  }

  parse(numInt, "3432") match {
    case Success(n, _) => n == 3432
  }

  // Slide 6

  def numerals: Parser[Int] = I | V | X

  parse(numerals, "V") match {
    case Success(n, _) => n == 5
  }

  parse(numerals, "I") match {
    case Success(n, _) => n == 1
  }

  // Slide 7

  def VI1: Parser[Int ~ Int] = V ~ I

  def VI2: Parser[~[Int, Int]] = V ~ I

  // Slide 8

  // "VI" -> 6
  def VI: Parser[Int] = V ~ I ^^ {
    case vValue ~ iValue => vValue + iValue
  }

  // "XXX" -> 30
  def XXX: Parser[Int] = X ~ X ~ X ^^ {
    case xValue1 ~ xValue2 ~ xValue3 => xValue1 + xValue2 + xValue3
  }

  // Slide 9

  def oneOrMoreXs: Parser[List[Int]] = X+
  def zeroOrMoreXs: Parser[List[Int]] = X*

  // Slide 10

  def manyXs: Parser[Int] = (X+) ^^ { list => list.sum }

  // Slide 11

  // "XXX" -> 30
  def threeXs: Parser[Int] = repN(3, X) ^^ { list => list.sum }

  // Slide 12

  val dumbjArray: Parser[List[JsonValue]] = "[" ~ (jsonListEntry*) ~ "]" ^^ {
    case bracket1 ~ jsonList ~ bracket2 => jsonList
  }

  val betterjArray: Parser[List[JsonValue]] = "[" ~> (jsonListEntry*) <~ "]"

  // Slide 13

  def numeral: Parser[Int] = I | V | X
  def str: Parser[String] = "I might add a numeral here:"

  def group: Parser[(String, Option[Int])] = (str ~ (numeral?)) ^^ {
    case str ~ num => (str, num)
  }


}
