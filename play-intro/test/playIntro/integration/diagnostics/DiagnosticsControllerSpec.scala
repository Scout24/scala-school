package playIntro.integration.diagnostics

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class DiagnosticsControllerSpec extends Specification {

  "Diagnostics" should {

    "return ok on heartbeat check" in new WithApplication{
      val heartbeat = route(FakeRequest(GET, "/diagnostics/heartbeat")).get

      status(heartbeat) must equalTo(OK)
      contentAsString(heartbeat) must equalTo("Ok")
    }
  }
}
