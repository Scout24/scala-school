package playIntro.integration.diagnostics

import org.scalatestplus.play.OneAppPerSuite
import playIntro.testutils.PlaySpecWrapper
import play.api.test.FakeRequest

class DiagnosticsControllerSpec extends PlaySpecWrapper with OneAppPerSuite {

  "heartbeat check should return ok" in {
    val Some(response) = route(FakeRequest(GET, "/diagnostics/heartbeat"))

    status(response) mustEqual OK
    contentAsString(response) mustEqual "Ok"
  }
}
