package models

import play.api.Logger

import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

object FutureExamples {

  // TODO Exercise 1 - Use a future to make the logger log immediately after loading has started
  def loadWebContent(logger: Logger) = {
    val str = Source.fromURL("http://example.com").mkString
    logger.info("started loading...")
    str
  }
}
