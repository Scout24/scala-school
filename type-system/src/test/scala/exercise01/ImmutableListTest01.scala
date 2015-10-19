package exercise01

import org.scalatest.{MustMatchers, FlatSpec}

import fruits._

class ImmutableListTest01 extends FlatSpec with MustMatchers {

  "ImmutableList" should "be generic" in {
    val orange = new Orange
    val lst: ImmutableList[Orange] = ImmutableListEnd().prepend(orange)

    lst.contains(orange) must be true
  }
}