import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

import scala.util.{Failure, Success}

class TryTests extends FlatSpec with MustMatchers {
  "Try println" should "be a success" in {
    (TryExamples.aTry match {
      case Success(v) => true
      case Failure(ex) => false
    }) must be(true)
  }
}
