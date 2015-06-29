package integration

import org.specs2.matcher.Matcher
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.mvc.Headers

import play.api.test._
import play.api.test.Helpers._

import scala.util.matching.Regex

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/boum")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Play Json!")
    }

    "return a vehicle json" in new WithApplication {
      val vehicle = route(FakeRequest(GET, "/vehicle")).get

      status(vehicle) must equalTo(OK)
      contentType(vehicle) must beSome.which(_ == "application/json")
      contentAsString(vehicle) must startWith("{\"id\":")
    }

    "accept a vehicle json" in new WithApplication {
      val response = route(FakeRequest(POST, "/vehicle", Headers(("Content-Type","application/json")),
        "{\"id\":0,\"make\":\"Alfa\",\"model\":\"Mito\",\"registAdd\":{\"street\":\"Fraunhofer Str\",\"no\":9,\"zip\":81543}}"
      )).get

      status(response) must equalTo(OK)
    }
  }
}
