package tatsuservice.app

import org.joda.time.DateTimeZone
import play.api.mvc.{RequestHeader, Result, Results}
import play.api.{Application, GlobalSettings}

import scala.concurrent.Future
import scala.io.Source

object Global extends Global

class Global extends GlobalSettings {

  val version = Source.fromInputStream(getClass.getResourceAsStream("/build.txt")).mkString.trim
  System.getProperties.setProperty("version", version) // needed by the logging framework
  DateTimeZone.setDefault(DateTimeZone.forID("Europe/Berlin")) // AS24 DB Times

  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
    Future.successful(Results.InternalServerError(s"Sorry, something went wrong"))
  }
}
