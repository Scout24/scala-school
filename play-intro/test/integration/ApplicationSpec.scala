package integration

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.mvc.AnyContentAsEmpty
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "not calculate a circumference without a radius" in new WithApplication {
      val circ = route(FakeRequest(GET, "/circumference")).get
      status(circ) must equalTo(BAD_REQUEST)
    }

    "calculate the circumference" in new WithApplication {
      val circ = route(FakeRequest(GET, "/circumference/2.1")).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must contain("13.194689145078002")
    }

    "calculate the circumference" in new WithApplication {
      val circ = route(FakeRequest(GET, "/circumference/4")).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must contain("25.13274122872")
    }

    "render a result page with the calculated circumference" in new WithApplication {
      val headers = FakeHeaders(Seq("Accept-Language" -> "en"))
      val circ = route(FakeRequest(GET, "/circumference/2.1", headers, AnyContentAsEmpty)).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must be matching resultMessageEn("2.1", "13.194689145078002")
    }

    "render a german result page with the calculated circumference" in new WithApplication {
      val headers = FakeHeaders(Seq("Accept-Language" -> "de"))
      val circ = route(FakeRequest(GET, "/circumference/2.1", headers, AnyContentAsEmpty)).get

      status(circ) must equalTo(OK)
      contentType(circ) must beSome.which(_ == "text/html")
      contentAsString(circ) must be matching resultMessageDe("2,1", "13,194689145078002")
    }
  }

  def resultMessageEn(radius: String, result: String) =
    s"""(?s)(.*)<h2>(.*)The circumference of a circle with radius $radius is $result(.*)</h2>(.*)""".r

  def resultMessageDe(radius: String, result: String) =
    s"""(?s)(.*)<h2>(.*)Der Umfang eines Kreises mit Radius $radius ist $result(.*)</h2>(.*)""".r
}
