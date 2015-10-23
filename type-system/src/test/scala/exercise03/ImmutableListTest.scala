package exercise03

import fruits.{OrganicOrange, Fruit, Orange}
import org.scalatest.{MustMatchers, FlatSpec}

class ImmutableListTest  extends FlatSpec with MustMatchers {

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

  "ImmutableList.min" should "allow a more generic comparator" in {
    val order: Ordering[Fruit] = new Ordering[Fruit] {
      def compare(x: Fruit, y: Fruit) = x.name.compareTo(y.name)
    }

    val orange = new Orange
    val organicOrange = new OrganicOrange
    val lst: ImmutableList[Orange] = ImmutableListEnd().prepend(orange).prepend(organicOrange)

    lst.min(order) must be(orange)
  }
}
