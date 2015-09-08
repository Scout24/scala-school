package traits

import org.scalatest.{MustMatchers, FlatSpec}

class QueueTests extends FlatSpec with MustMatchers {

  import Queues._

  // TODO: Traits Exercise 2
  "queue 1" should "turn a 3 into a 7" in {
    queue1.put(3)
    queue1.get must be === 7
  }

  "queue 2" should "turn a 3 into a 8" in {
    queue2.put(3)
    queue2.get must be === 8
  }

  "queue 3" should "must filter negative numbers and increment non-negative numbers" in {
    queue3.putFiltered(-1)
    queue3.putFiltered(3)
    queue3.putFiltered(13)
    queue3.get must be === 4
    queue3.get must be === 14
  }
}
