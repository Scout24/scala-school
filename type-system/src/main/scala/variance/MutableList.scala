package variance

trait MutableList[T] {
  private var lst = List[T]()
  def prepend(el: T) { lst = el :: lst }
  def length = lst.size
}
