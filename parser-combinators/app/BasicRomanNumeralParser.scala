import scala.util.parsing.combinator.RegexParsers

/**
  * Created by matlloyd on 15/02/2016.
  */
class BasicRomanNumeralParser extends RegexParsers {

  def I: Parser[Int] = "I" ^^^ 1
  def V: Parser[Int] = "V" ^^^ 5
  def X: Parser[Int] = "X" ^^^ 10
  def L: Parser[Int] = "L" ^^^ 50
  def C: Parser[Int] = "C" ^^^ 100
  def D: Parser[Int] = "D" ^^^ 500
  def M: Parser[Int] = "M" ^^^ 1000

  def numerals: Parser[Int] = I | V | X | L | C | D | M

  def doParse(input: String): Option[Int] = parse(numerals, input) match {
    case Success(n) => Some(n)
    case NoSuccess(err, next) => None
  }

}

