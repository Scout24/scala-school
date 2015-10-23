package exercise01

import fruits.Orange
import org.scalatest.{MustMatchers, FlatSpec}

/* --------------- Exercise 01 -------------
 * TODO make ImmutableList and subclasses generic
 * Hint: introduce type parameter A to class, replace String by type parameter.
 */


trait ImmutableList {
  def prepend(el: String): ImmutableListItem = ImmutableListItem(el, this)

  def contains(el: String): Boolean = this match {
    case ImmutableListEnd() => false
    case ImmutableListItem(value, tail) => if(value == el) true else tail.contains(el)
  }
}

case class ImmutableListEnd() extends ImmutableList

case class ImmutableListItem(value: String, tail: ImmutableList) extends ImmutableList

// -------------- Tests ----------------------
class ImmutableListTest extends FlatSpec with MustMatchers {

  "ImmutableList" should "be generic" in {
    val orange = new Orange
    val lst: ImmutableList[Orange] = ImmutableListEnd().prepend(orange)

    lst.contains(orange) must be true
  }
}