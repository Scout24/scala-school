package variance

object RX {

  trait Observable {

    def map(f: String => String): Observable

    def subscribe(onNext: String => Unit)
  }

  trait Subscriber { def onNext(item: String) }

  class ObservableImpl(val onSubscribe: Subscriber => Unit) extends Observable {

    def subscribe(onNextFn: String => Unit) = {
      val subscriber = new Subscriber {
        def onNext(item: String): Unit = onNextFn(item)
      }
      onSubscribe(subscriber)
    }

    def map(f: String => String): Observable = {
      val mapItems = (sub: Subscriber) => {
        subscribe(item => sub.onNext(f(item)))
      }
      new ObservableImpl(mapItems)
    }
  }

  object Observable {
    def just(items: String*): Observable = {
      val sendItems = (sub : Subscriber) => {
        items.foreach(item => sub.onNext(item))
      }
      new ObservableImpl(sendItems)
    }
  }
}
