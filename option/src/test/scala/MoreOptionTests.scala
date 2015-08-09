import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

import scala.util.{Failure, Success}

class MoreOptionTests extends FlatSpec with MustMatchers {

  "Reading a valid url using pattern matching" should "be a success" in {
    OptionMatch.getURLContent("http://example.com").get.next() must be("<!doctype html>")
  }

  "Reading a valid url using map" should "be a success" in {
    OptionMap.getURLContent("http://example.com").get.next() must be("<!doctype html>")
  }

  "Reading a wrong url using map" should "be a success" in {
    OptionMap.getURLContent("garbage").isDefined must be(true)
  }

  "Reading a valid url using flatMap" should "be a success" in {
    OptionFlatMap.getURLContent("http://example.com").get.next() must be("<!doctype html>")
  }

  "Reading a valid url using for" should "be a success" in {
    OptionFor.getURLContent("http://example.com").get.next() must be("<!doctype html>")
  }

  "Reading a wrong url using for" should "be a success with an error message" in {
    OptionFor.getURLContent("garbage").get.next() must be("Please make sure to enter a valid URL")
  }
}
