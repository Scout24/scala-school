package traits

/* Trait defining an ordering between two Object of Generic Type T */

trait Ordered[T] {

  /** Result of comparing `this` with operand `that`.
    *
    * Implement this method to determine how instances of A will be sorted.
    *
    * Returns `x` where:
    *
    *   - `x < 0` when `this < that`
    *
    *   - `x == 0` when `this == that`
    *
    *   - `x > 0` when  `this > that`
    *
    */
    def compare(that: T): Int

    /* provide a richer interface by implementing these in terms of compare */
    def <(that: T): Boolean = (this compare that) < 0
    def >(that: T): Boolean = ???
    def <=(that: T): Boolean = ???
    def >=(that: T): Boolean = ???

}
