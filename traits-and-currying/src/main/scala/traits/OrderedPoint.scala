package traits

/* Trait defining an ordering between two Object of Generic Type T */

trait OrderedPoint[T] {

    /* this is the thin interface */
    def compare(that: T): Int

    /* provide a richer interface by implementing these in terms of compare */
    def <(that: T): Boolean = (this compare that) < 0
    def >(that: T): Boolean = ???
    def <=(that: T): Boolean = ???
    def >=(that: T): Boolean = ???

}
