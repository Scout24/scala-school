package currying

import org.scalatest._

class CurryingTests extends FlatSpec with MustMatchers {

  "filterByMod2" should "should filter by modulo 2 using the curried modN function" in {
    def modN(n: Int)(x: Int) = ((x % n) == 0)
    def filterByMod2(numbers: Seq[Int]): Seq[Int] = ???
    filterByMod2(1 to 10) must be === 2 :: 4 :: 6 :: 8 :: 10 :: Nil
  }

  "filterByMod2notCurried" should "should filter by modulo 2 using the non-curried modN function" in {
    def modNnotCurried(n: Int, x: Int) = ((x % n) == 0)
    def filterByMod2notCurried(numbers: Seq[Int]): Seq[Int] = ???
    filterByMod2notCurried(1 to 10) must be === 2 :: 4 :: 6 :: 8 :: 10 :: Nil
  }
}
