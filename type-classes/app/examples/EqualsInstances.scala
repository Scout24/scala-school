package examples

import data.{HexNumeral, RomanNumeral}

/**
  * Created by matlloyd on 29/02/2016.
  */
object EqualsInstancesv1 {
  import Equalsv1._

  implicit val intRomanNumeralEquals: Equals[Int, RomanNumeral] =
    Equals.makeInstance((n: Int, rn: RomanNumeral) => n == rn.value)

  implicit val intHexNumeralEquals: Equals[Int, HexNumeral] =
    Equals.makeInstance((n: Int, rn: HexNumeral) => n == rn.value)

}

object EqualsInstancesv2 {
  import Equalsv2._
  import EqualsOps._

  implicit val (intRomanNumeralEquals: (Equals[Int, RomanNumeral]),
                romanNumeralIntEquals: (Equals[RomanNumeral, Int])) =
    EqHelper.both((n: Int, rn: RomanNumeral) => n == rn.value)

  implicit val (intHexNumeralEquals: (Equals[Int, HexNumeral]),
                hexNumeralIntEquals: (Equals[HexNumeral, Int])) =
    EqHelper.both((n: Int, rn: HexNumeral) => n == rn.value)

  implicit def OptionEquals[A, B](implicit tc: Equals[A, B]): Equals[Option[A], Option[B]] = EqHelper.single({
    case (None, None) => true
    case (Some(a), Some(b)) => a === b
    case _ => false
  })

  implicit def ListEquals[A, B](implicit tc: Equals[A, B]): Equals[List[A], List[B]] = EqHelper.single({
    case (Nil, Nil) => true
    case (as, bs) if as.length == bs.length => (as zip bs).forall { case (a, b) => a === b }
    case _ => false
  })

}
