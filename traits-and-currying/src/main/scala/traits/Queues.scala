package traits

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

// self referene with nominal type
trait Filtering extends IntQueue {
  self: IntQueue =>
  def putFiltered(x: Int) {
    if (x >= 0) this.put(x)
  }
}

// self reference with structural type
trait Quadrupling { self: { def put(x: Int) } =>
  def quadruplePut(x: Int) { this.put(4 * x) }
}