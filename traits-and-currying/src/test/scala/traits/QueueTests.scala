package traits

import org.scalatest.{MustMatchers, FlatSpec}

class QueueTests extends FlatSpec with MustMatchers {
  // Exercise - Part 2
  "queue 1" should "turn a 3 into a 7" in {
    val queue1 : IntQueue = ???
    queue1.put(3)
    queue1.get must be === 7
  }

  "queue 2" should "turn a 3 into a 8" in {
    val queue2 : IntQueue = ???
    queue2.put(3)
    queue2.get must be === 8
  }

  "queue 3" should "must filter negative numbers and increment non-negative numbers" in {
    val queue3 : Filtering = ???
    queue3.putFiltered(-3)
    queue3.putFiltered(3)
    queue3.putFiltered(13)
    queue3.get must be === 4
    queue3.get must be === 14
  }
}
