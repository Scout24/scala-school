// Example code based on Programming in Scala, Second Edition by Martin Odersky, Lex Spoon, Bill Venners http://booksites.artima.com/programming_in_scala_2ed

object TraitExamples {

  trait Philosophical {
    def philosophize() {
      println("I consume memory, therefore I am!")
    }
  }

  class Animal

  class Frog extends Animal with Philosophical {
    override def toString = "green"
    override def philosophize() {
      println("It ain't easy being "+ toString +"!")
    }
  }

  class Point(val x: Int, val y: Int)
  trait CharSequence {
    def charAt(index: Int): Char
    def length: Int
    def subSequence(start: Int, end: Int): CharSequence
    def toString(): String
  }
  trait Ordered[T] {
    def compare(that: T): Int

    def <(that: T): Boolean = (this compare that) < 0
    def >(that: T): Boolean = ???
    def <=(that: T): Boolean = ???
    def >=(that: T): Boolean = ???
  }

  val orderedPointSmall: Point with Ordered[Point] = ???
//    new Point(1,1) with Ordered[Point] {
//    def compare(that: Point) = (this.x - that.y) + (this.y - that.y)
//  }

//  class MyPoint(x: Int, y: Int) extends Point(x,y) with Ordered[Point] {
//    def compare(that: Point) = (this.x - that.y) + (this.y - that.y)
//  }
  val orderedPointBig: Point with Ordered[Point] = ??? //new MyPoint(10,10)

  // -------------- Stackable Modifications ----------------

  import scala.collection.mutable.ArrayBuffer

  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) { buf += x }
  }

  // abstract override
  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x + 1) }
  }

  // non-abstract override
  trait Doubling extends BasicIntQueue {
    override def put(x: Int) { super.put(2 * x) }
  }

  // self reference with nominal type
  trait Tripling { self: IntQueue =>
    def triplePut(x: Int) { this.put(3 * x) }
  }

  // self reference with structural type
  trait Quadrupling { self: { def put(x: Int) } =>
    def quadruplePut(x: Int) { this.put(4 * x) }
  }


  val queue1 = new BasicIntQueue with Incrementing with Doubling

  def main(args: Array[String]) {

    (new Frog).philosophize()

    val d = new BasicIntQueue with Incrementing with Doubling
    d.put(42)  // which put would be called?
    println("d [" + d + "]")

    val t = new BasicIntQueue with Tripling // mixin at instantiation
    t.triplePut(42)
    println("t [" + t + "]")

    class QuadruplingIntQueue extends BasicIntQueue with Quadrupling // mixin at class definition
    val q = new QuadruplingIntQueue
    q.quadruplePut(42)
    println("q [" + q + "]")
  }

}
