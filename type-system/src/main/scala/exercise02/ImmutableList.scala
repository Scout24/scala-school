package exercise02

import fruits.Orange
import org.scalatest.{MustMatchers, FlatSpec}

/* --------------- Exercise 02 -------------
 * TODO make ImmutableList.findSimilar also accept subtypes of ImmutableList[Similar]
 * Hint: introduce type parameter with upper bound to findSimilar.
 */

trait ImmutableList[A] {
  def prepend(el: A): ImmutableListItem[A] = ImmutableListItem(el, this)

  def contains(el: A): Boolean = this match {
    case ImmutableListEnd() => false
    case ImmutableListItem(value, tail) => if(value == el) true else tail.contains(el)
  }
}

case class ImmutableListEnd[A]() extends ImmutableList[A]

case class ImmutableListItem[A](value: A, tail: ImmutableList[A]) extends ImmutableList[A]

object ImmutableList {
  def findSimilar(el: Similar, list: ImmutableList[Similar]): Boolean = list match {
    case ImmutableListEnd() => false
    case ImmutableListItem(value, tail) => if (el.isSimilar(value)) true else findSimilar(el, tail)
  }
}

trait Similar {
  def isSimilar(x: Any): Boolean
}

case class MyInt(x: Int) extends Similar {
  def isSimilar(m: Any): Boolean = m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
}

// -------------- Tests ----------------------

class ImmutableListTest extends FlatSpec with MustMatchers {

  "Immutable List" should "be generic" in {
    val orange = new Orange
    val lst: ImmutableList[Orange] = ImmutableListEnd().prepend(orange)

    lst.contains(orange) must be true
  }

  "Find Similar" should "allow to use ImmutableList[Similar]" in {
    val list: ImmutableList[Similar] = ImmutableListEnd[Similar]().prepend(MyInt(1)).prepend(MyInt(2))

    ImmutableList.findSimilar(MyInt(4), list) must be false
    ImmutableList.findSimilar(MyInt(2), list) must be true
  }

  "Find Similar" should "allow to use subtypes of ImmutableList[Similar]" in {
    val list: ImmutableList[MyInt] = ImmutableListEnd().prepend(MyInt(1)).prepend(MyInt(2))

    ImmutableList.findSimilar(MyInt(4), list) must be false
    ImmutableList.findSimilar(MyInt(2), list) must be true
  }
}