
object TraitExamples {

  abstract class Hello {
    def hello(): Unit
  }
  trait A extends Hello {
    override def hello() = println("here")
  }

  class MyHello extends Hello with A

  trait B {
    self: Hello =>
  }

  class MyHello2 extends Hello with B {
    def hello() = println("here")
  }


}
