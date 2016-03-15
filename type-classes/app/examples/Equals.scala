package examples

import scala.language.implicitConversions

/**
  * Created by matlloyd on 03/03/2016.
  */

object Equalsv1 {

  trait Equals[A, B] {
    def equal(a: A, b: B): Boolean
  }

  object Equals {
    def makeInstance[A, B](f: (A, B) => Boolean): Equals[A, B] = new Equals[A, B] {
      def equal(a: A, b: B): Boolean = f(a, b)
    }
  }

  trait EqualsOps[A, B] {
    def self: A
    implicit def tc: Equals[A, B]
    final def eql(other: B): Boolean = tc.equal(self, other)
  }

  object EqualsOps {
    implicit def aToEquals[A, B](v: A)(implicit instance: Equals[A, B]) = new EqualsOps[A, B] {
      def self = v
      implicit def tc = instance
    }
  }

}

object Equalsv2 {


  trait Equals[A, B] {
    def equal(a: A, b: B): Boolean = !notEqual(a, b)
    def notEqual(a: A, b: B): Boolean = !equal(a, b)
  }

  object EqHelper {
    def both[A, B](f: (A, B) => Boolean): (Equals[A, B], Equals[B, A]) =
      (single(f), single { (a, b) => f(b, a) })

    def single[A, B](f: (A, B) => Boolean): Equals[A, B] = new Equals[A, B] {
      override def equal(a: A, b: B): Boolean = f(a, b)
    }
  }

  trait EqualsOps[A, B] {
    def self: A
    implicit def tc: Equals[A, B]
    final def eql(other: B): Boolean = tc.equal(self, other)
    final def neql(other: B): Boolean = tc.notEqual(self, other)

    final def ===(other: B): Boolean = eql(other)
    final def =/=(other: B): Boolean = neql(other)
  }

  object EqualsOps {
    implicit def aToEquals[A, B](v: A)(implicit instance: A Equals B) = new EqualsOps[A, B] {
      def self = v
      implicit def tc = instance
    }
  }

  import scala.language.higherKinds

  trait HigherKindedEqualsOps[A, B, M[_]] {
    def self: M[A]
    implicit def tc: Equals[M[A], M[B]]
    final def eql(other: M[B]): Boolean = tc.equal(self, other)
    final def neql(other: M[B]): Boolean = tc.notEqual(self, other)

    final def ===(other: M[B]): Boolean = eql(other)
    final def =/=(other: M[B]): Boolean = neql(other)
  }

  object HigherKindedEqualsOps {
    implicit def aToEquals[A, B, M[_]](v: M[A])(implicit instance: Equals[M[A], M[B]]) =
      new HigherKindedEqualsOps[A, B, M] {
      def self = v
      implicit def tc = instance
    }
  }

}
