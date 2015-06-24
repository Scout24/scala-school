package integration

import org.specs2.matcher.Matcher
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import scala.util.matching.Regex

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }

    "not calculate a circumference without a radius" in new WithApplication{
      val circ = route(FakeRequest(GET, "/circumference")).get
      status(circ) must equalTo(BAD_REQUEST)
    }

    "calculate the circumference" in new WithApplication{
      val circ = route(FakeRequest(GET, "/circumference/2")).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must contain("12.56637061436")
    }


    "render a result page with the calculated circumference" in new WithApplication{
      val circ = route(FakeRequest(GET, "/circumference/2")).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must be matching(resultMessageEn(2.0, 12.56637061436))
    }

    "render a german result page with the calculated circumference" in new WithApplication{
      val circ = route(FakeRequest(GET, "/circumference/2?language=de")).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must be matching(resultMessageDe(2.0, 12.56637061436))
    }
  }

  def resultMessageEn(radius: Double, result: Double) =
    s"""(?s)(.*)<h2>(.*)The circumference of a circle with radius $radius is $result(.*)</h2>(.*)""".r

  def resultMessageDe(radius: Double, result: Double) =
    s"""(?s)(.*)<h2>(.*)Der Umfang eines Kreises mit Radius $radius ist $result(.*)</h2>(.*)""".r
}
