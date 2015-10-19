package exercise01

// --------------- TODO Exercise 01 - make ImmutableList and subclasses generic ------------

trait ImmutableList {
  def prepend(el: String): ImmutableListItem = ImmutableListItem(el, this)

  def contains(el: String): Boolean = this match {
    case ImmutableListEnd() => false
    case ImmutableListItem(value, tail) => if(value == el) true else tail.contains(el)
  }
}

case class ImmutableListEnd() extends ImmutableList

case class ImmutableListItem(value: String, tail: ImmutableList) extends ImmutableList
