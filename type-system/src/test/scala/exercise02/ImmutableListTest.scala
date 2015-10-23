package exercise02

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
