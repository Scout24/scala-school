package unit

import models.FutureExamples
import org.scalatest.WordSpec
import org.scalatest.mock.MockitoSugar
import play.api.Logger
import org.mockito.Mockito._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source


object ContentLoader {
  
  // TODO Exercise 1 - Use a future to make the logger log immediately after loading has started
  def loadWebContent(logger: Logger) = {
    val str = Source.fromURL("http://example.com").mkString
    logger.info("started loading...")
    str
  }
}

class ContentLoaderSpec extends WordSpec with MockitoSugar {

  "The ContentLoader" should {

    "loadWebContent with immediately logging" in {
      val logger = mock[Logger]
      val res = Future {
        ContentLoader.loadWebContent(logger)
      }
      verify(logger).info("started loading...")
    }
  }
}
