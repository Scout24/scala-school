package exercise01

import fruits.Orange
import org.scalatest.{FlatSpec, MustMatchers}

class ImmutableListTest extends FlatSpec with MustMatchers {

  "ImmutableList" should "be generic" in {
    val orange = new Orange
    val lst: ImmutableList[Orange] = ImmutableListEnd().prepend(orange)

    lst.contains(orange) must be true
  }
}