package integration

import org.specs2.matcher.Matcher
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import scala.util.matching.Regex

class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/boum")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the index page using the local counters" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("84 vehicles found")
    }

    "render the index page using the remote counters" in new WithServer(port = 9000) {
      val home = route(FakeRequest(GET, "/remote")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("84 vehicles found")
    }
  }
}
