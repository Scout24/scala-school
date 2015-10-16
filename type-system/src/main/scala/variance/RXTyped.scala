package variance

object RXTyped {

  trait Observable[E] {

    def map[T](f: E => T): Observable[T]

    def subscribe(onNext: E => Unit)
  }

  trait Subscriber[E] { def onNext(item: E) }

  class ObservableImpl[E](val onSubscribe: Subscriber[E] => Unit) extends Observable[E] {

    def subscribe(onNextFn: E => Unit) = {
      val subscriber = new Subscriber[E] {
        def onNext(item: E): Unit = onNextFn(item)
      }
      onSubscribe(subscriber)
    }

    def map[T](f: E => T): Observable[T] = {
      val mapItems = (sub: Subscriber[T]) => {
        subscribe(item => sub.onNext(f(item)))
      }
      new ObservableImpl[T](mapItems)
    }
  }

  object Observable {
    def just[E](items: E*): Observable[E] = {
      val sendItems = (sub : Subscriber[E]) => {
        items.foreach(item => sub.onNext(item))
      }
      new ObservableImpl[E](sendItems)
    }
  }
}
