package variance

//trait ImmutableList[+T] {
//  def prepend[E >: T](el: E) = ImmutableListItem[E](el, this)
//}

// --------------- TODO Exercise X - make it covariant ------------

trait ImmutableList[T] {
  def prepend(el: T): ImmutableListItem[T] = ImmutableListItem(el, this)
}

case class ImmutableListEnd[T]() extends ImmutableList[T]

case class ImmutableListItem[T](value: T, tail: ImmutableList[T]) extends ImmutableList[T]

object ImmutableList {
  def contains[T, E <: T](el: E, lst: ImmutableList[T]): Boolean = lst match {
    case ImmutableListEnd => false
    case ImmutableListItem(value, tail) => if(value == el) true else contains(el, tail)
  }
}
