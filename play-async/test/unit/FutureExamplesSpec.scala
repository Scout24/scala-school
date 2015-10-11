package unit

import models.FutureExamples
import org.specs2.mock.Mockito
import org.specs2.mutable._
import play.api.Logger

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class FutureExamplesSpec extends Specification with Mockito {

  "FutureExamples" should {

    "loadWebContent should start logging immediately" in {
      val logmock = mock[Logger]
      val res = Future { FutureExamples.loadWebContent(logmock) }
      there was one(logmock).info("started loading...")
    }
  }
}
