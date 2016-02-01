package filters

import play.api.mvc._

import scala.concurrent.Future

  class TimingFilter extends Filter {

    def apply(nextFilter: RequestHeader => Future[Result])
             (requestHeader: RequestHeader): Future[Result] = {

      val startTime = System.currentTimeMillis

      nextFilter(requestHeader).map { result =>

        val endTime = System.currentTimeMillis
        val requestTime = endTime - startTime

        result.withHeaders("Request-Time" -> requestTime.toString)
      }
    }
  }

