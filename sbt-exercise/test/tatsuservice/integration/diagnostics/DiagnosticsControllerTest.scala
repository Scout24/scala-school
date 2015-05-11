package tatsuservice.integration.diagnostics

import org.scalatestplus.play.OneAppPerSuite
import tatsuservice.testutils.PlaySpecWrapper
import play.api.test.FakeRequest

class DiagnosticsControllerTest extends PlaySpecWrapper with OneAppPerSuite {

  "heartbeat check should return ok" in {
    val Some(response) = route(FakeRequest(GET, "/diagnostics/heartbeat"))

    status(response) mustEqual OK
    contentAsString(response) mustEqual "Ok"
  }

  // TODO: (cd) Add tests for model validation and dependencies checks.
}
