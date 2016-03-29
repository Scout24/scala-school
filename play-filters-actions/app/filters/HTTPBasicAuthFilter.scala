package filters

import data.User
import play.api.mvc._

import scala.concurrent.Future

  class HTTPBasicAuthFilter extends Filter {

    def decodeBasicAuth(auth: String): Option[(String, String)] = ???

    def apply(nextFilter: (RequestHeader) => Future[Result])
             (requestHeader: RequestHeader): Future[Result] = {

      requestHeader.headers.get("authorization").map { basicAuth =>
        decodeBasicAuth(basicAuth) match {
          case Some((user, pass)) if User.authenticate(user, pass) =>
            nextFilter(requestHeader)
          case _ =>
            Future.successful(Results.Unauthorized)
        }
      }.getOrElse(Future.successful(Results.Unauthorized))

    }
  }

