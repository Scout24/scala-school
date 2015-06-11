package playIntro.testutils

import org.scalatest.OneInstancePerTest
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.http.{HeaderNames, HttpProtocol, Status}
import play.api.test._

class PlaySpecWrapper
  extends PlaySpec
  with PlayRunners
  with HeaderNames
  with Status
  with HttpProtocol
  with DefaultAwaitTimeout
  with ResultExtractors
  with Writeables
  with EssentialActionCaller
  with RouteInvokers
  with FutureAwaits
  with OneInstancePerTest
  with MockitoSugar
