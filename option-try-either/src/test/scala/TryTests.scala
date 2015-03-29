import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

import scala.util.{Failure, Success}

class TryTests extends FlatSpec with MustMatchers {
  "Try println" should "be a success" in {
    (TryFor.aTry match {
      case Success(v) => true
      case Failure(ex) => false
    }) must be(true)
  }

  "Reading a valid url" should "be a success" in {
    TryMap.content1 must be("<!doctype html>")
  }

  "Reading a bad url" should "be a success with an error message" in {
    TryFor.content2.get.next must be("Please make sure to enter a valid URL")
  }
}
