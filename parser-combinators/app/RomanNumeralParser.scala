import scala.language.postfixOps
import scala.util.parsing.combinator.RegexParsers

/**
  * Created by matlloyd on 15/02/2016.
  *
  * A full Roman Numeral Parser as a more full example of
  * how to use Parser Combinators.
  */
class RomanNumeralParser extends RegexParsers {

  /**
    * The parsers for the individual terms. They are simply the direct values.
    */
  protected def I: Parser[Int] = "I" ^^^ 1
  protected def V: Parser[Int] = "V" ^^^ 5
  protected def X: Parser[Int] = "X" ^^^ 10
  protected def L: Parser[Int] = "L" ^^^ 50
  protected def C: Parser[Int] = "C" ^^^ 100
  protected def D: Parser[Int] = "D" ^^^ 500
  protected def M: Parser[Int] = "M" ^^^ 1000

  /**
    * The parser for the valid subtraction terms.
    * We collect the terms and subtract the small from the large.
    * @param small The small term in the subtraction term
    * @param big The large term in the subtraction term
    * @return A parser that matches a small term followed by a large term and subtracts their values.
    */
  protected def subTerm(small: Parser[Int], big:Parser[Int]): Parser[Int] = small ~ big ^? {
    case smallValue ~ bigValue => bigValue - smallValue
  }
  protected def IVSub: Parser[Int] = subTerm(I, V)
  protected def IXSub: Parser[Int] = subTerm(I, X)
  protected def XLSub: Parser[Int] = subTerm(X, L)
  protected def XCSub: Parser[Int] = subTerm(X, C)
  protected def CDSub: Parser[Int] = subTerm(C, D)
  protected def CMSub: Parser[Int] = subTerm(C, M)

  /**
    * The parsers for the repeating terms.
    * We allow for either 3, 2 or 1 of these repeating elements.
    * We collect and sum them.
    * @param baseTerm The term that we want multiple of.
    * @return A parser that matches either 3, 2 or 1 of the base term and sums the result.
    */
  protected def RepTerm(baseTerm: Parser[Int]): Parser[Int] = (repN(3, baseTerm) | repN(2, baseTerm) | repN(1, baseTerm)) ^^ { _.sum }
  protected def IRep: Parser[Int] = RepTerm(I)
  protected def XRep: Parser[Int] = RepTerm(X)
  protected def CRep: Parser[Int] = RepTerm(C)
  protected def MRep: Parser[Int] = RepTerm(M)

  /**
    * Join the optional terms for the repeated and subtracted sub terms.
    * @return The sum of the terms.
    */
  protected def sumOptionalTerms: PartialFunction[Option[Int] ~ Option[Int], Int] = {
    case repeated ~ subtracted => repeated.getOrElse(0) + subtracted.getOrElse(0)
  }

  /**
    * The parsers for the compound terms.
    * A compound term is made up of the repeating terms and the subtraction terms.
    * Each sub-term is optional.
    */
  protected def MTerms: Parser[Int] = (MRep?) ~ (CMSub?) ^? sumOptionalTerms
  protected def DTerms: Parser[Int] = (D?) ~ (CDSub?) ^? sumOptionalTerms
  protected def CTerms: Parser[Int] = (CRep?) ~ (XCSub?) ^? sumOptionalTerms
  protected def LTerms: Parser[Int] = (L?) ~ (XLSub?) ^? sumOptionalTerms
  protected def XTerms: Parser[Int] = (XRep?) ~ (IXSub?) ^? sumOptionalTerms
  protected def VTerms: Parser[Int] = (V?) ~ (IVSub?) ^? sumOptionalTerms
  // Nothing can be subtracted from I
  protected def ITerms: Parser[Int] = (IRep?) ^^ { _.getOrElse(0) }

  /**
    * The final parser that brings the terms together and sums them in to a Roman Numeral.
    * Roman Numerals do not encode 0 or negative numbers, so it must be greater than 0.
    * @return a Parser that pulls together all of the possible terms in a Roman Numeral.
    */
  protected def RomanNumeralGrammar: Parser[Int] = (MTerms ~ DTerms ~ CTerms ~ LTerms ~ XTerms ~ VTerms ~ ITerms) ^? {
    // we use ^? here because the partial function may fail due to the if clause
    // in which case we want the Parser to return NoSuccess instead of throwing a match error
    case m ~ d ~ c ~ l ~ x ~ v ~ i if (m + d + c + l + x + v + i) > 0 => m + d + c + l + x + v + i
  }

  /**
    * Parse a Roman Numeral string into an int.
    *
    * I made this the apply method as it seems the most sensible for a parser.
    *
    * I've made the assumption that the Numeral string must be exact.
    * i.e. the string "MMIV was a good year" although starts with a numeral it isn't exact and will fail.
    *
    * @param s The String that we are parsing as Roman Numerals.
    * @return A fulfilled Option if the string is a valid Roman Numeral. An empty Option otherwise.
    */
  def apply(s: String): Option[Int] = parseAll(RomanNumeralGrammar, s) match {
    case Success(value, remainingInput) => Some(value)

    case NoSuccess(msg, _) => None
  }
}