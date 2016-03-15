package examples

import data.{HexNumeral, RomanNumeral}
import examples.Equalsv2.Equals

import scala.language.implicitConversions

/**
  * Created by matlloyd on 29/02/2016.
  */
object App extends App {

  val one = {
    import Wrappedv1._
    import WrappedInt._

    227.eql(RomanNumeral("CCXXVII"))
    // is the same as
    WrappedInt.IntToWrappedInt(6).eql(RomanNumeral("VI"))
  }

  println(s"Using simple wrapped ints: $one")

  val two = {

    import Wrappedv2._
    import WrappedInt._
    import EqualsInstancesv1.intRomanNumeralEquals

    227.eql(RomanNumeral("CCXXVII"))
    // is the same as
    WrappedInt.IntToWrappedInt(227).eql(RomanNumeral("CCXXVII"))
    // is the same as
    WrappedInt.IntToWrappedInt(227)(intRomanNumeralEquals).eql(RomanNumeral("CCXXVII"))
  }

  println(s"Using more generic wrapped ints: $two")

  val three = {

    import Equalsv1._
    import EqualsOps._
    import EqualsInstancesv1.intRomanNumeralEquals

    227.eql(RomanNumeral("CCXXVII"))
    // is the same as
    EqualsOps.aToEquals(227).eql(RomanNumeral("CCXXVII"))
    // is the same as
    EqualsOps.aToEquals(227)(intRomanNumeralEquals).eql(RomanNumeral("CCXXVII"))
  }

  println(s"Using fully generic operations: $three")

  val four = {

    import Equalsv1._
    import EqualsOps._
    import EqualsInstancesv1.intHexNumeralEquals

    // is the same as
    227.eql(HexNumeral("0xE3"))

    // is the same as
    EqualsOps.aToEquals(227).eql(HexNumeral("0xE3"))
    // is the same as
    EqualsOps.aToEquals(227)(intHexNumeralEquals).eql(HexNumeral("0xE3"))
  }

  println(s"Using fully generic operations we can work with other types: $four")

  val five = {
    import Equalsv2._
    import EqualsInstancesv2.{OptionEquals, intHexNumeralEquals}
    import HigherKindedEqualsOps._

    Option(227) === Option(HexNumeral("0xE3"))

    // is the same as

    Option(227).eql(Option(HexNumeral("0xE3")))

    // is the same as
    HigherKindedEqualsOps.aToEquals(Option(227)).eql(Option(HexNumeral("0xE3")))
    // is the same as
    HigherKindedEqualsOps.aToEquals(Option(227))(OptionEquals).eql(Option(HexNumeral("0xE3")))
    // is the same as
    HigherKindedEqualsOps.aToEquals(Option(227))(OptionEquals(intHexNumeralEquals)).eql(Option(HexNumeral("0xE3")))
  }

  println(s"Using higher kinded operations: $five")

  val six = {
    import Equalsv2._
    import EqualsInstancesv2.{ListEquals, intHexNumeralEquals}
    import HigherKindedEqualsOps._

    List(227) === List(HexNumeral("0xE3"))

    // is the same as

    List(227).eql(List(HexNumeral("0xE3")))


    // is the same as
    HigherKindedEqualsOps.aToEquals(List(227)).eql(List(HexNumeral("0xE3")))
    // is the same as
    HigherKindedEqualsOps.aToEquals(List(227))(ListEquals).eql(List(HexNumeral("0xE3")))
    // is the same as
    HigherKindedEqualsOps.aToEquals(List(227))(ListEquals(intHexNumeralEquals)).eql(List(HexNumeral("0xE3")))
  }

  println(s"Using polymorphic higher kinded operations: $six")

  val seven = {
    import Equalsv2._
    import EqualsInstancesv2.{ListEquals, hexNumeralIntEquals, intHexNumeralEquals}
    import HigherKindedEqualsOps._

    List(42) =/= List(HexNumeral("0xE3"))

    List(HexNumeral("0xE3")) =/= List(42)
  }

  println(s"Using higher kinded derived infix operators: $seven")


}
