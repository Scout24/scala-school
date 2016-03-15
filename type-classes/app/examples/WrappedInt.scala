package examples

import data.RomanNumeral

import scala.language.implicitConversions

/**
  * Created by matlloyd on 29/02/2016.
  */

object Wrappedv1 {

  trait WrappedInt {
    def eql(other: RomanNumeral): Boolean
  }

  object WrappedInt {
    implicit def IntToWrappedInt(v: Int): WrappedInt = new WrappedInt {
      def eql(other: RomanNumeral): Boolean = other.value == v
    }
  }

}

object Wrappedv2 {

  import Equalsv1.Equals

  trait WrappedInt {
    def eql(other: RomanNumeral): Boolean
  }

  object WrappedInt {
    implicit def IntToWrappedInt(v: Int)(implicit tc: Equals[Int, RomanNumeral]): WrappedInt = new WrappedInt {
      def eql(other: RomanNumeral): Boolean = tc.equal(v, other)
    }
  }

}

