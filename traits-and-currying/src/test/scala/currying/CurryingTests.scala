package currying

import org.scalatest._

class CurryingTests extends FlatSpec with MustMatchers {

  "filterByMod2" should "should filter by modulo 2 using a curried modN function" in {
    CurryingExamples.filterByMod2(1 to 10) must be === 2 :: 4 :: 6 :: 8 :: 10 :: Nil
  }

  "filterByMod2notCurried" should "should filter by modulo 2 using a non-curried modN function" in {
    CurryingExamples.filterByMod2Uncurried(1 to 10) must be === 2 :: 4 :: 6 :: 8 :: 10 :: Nil
  }
}
