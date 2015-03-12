
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

  class Point(x: Int, y: Int)
  trait CharSequence {
    def charAt(index: Int): Char
    def length: Int
    def subSequence(start: Int, end: Int): CharSequence
    def toString(): String
  }
  trait Ordered[T] {
    def compare(that: T): Int

    def <(that: T): Boolean = (this compare that) < 0
    def >(that: T): Boolean = (this compare that) > 0
    def <=(that: T): Boolean = (this compare that) <= 0
    def >=(that: T): Boolean = (this compare that) >= 0
  }

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
  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x + 1) }
  }
  trait Doubling extends IntQueue {
    abstract override def put(x: Int) { super.put(2 * x) }
  }

  def main(args: Array[String]) {
    (new Frog).philosophize()

    // Multiple inheritance thought experiment
    val q = new BasicIntQueue with Incrementing with Doubling
    q.put(42)  // which put would be called?
    println("q [" + q + "]")
  }

}
