package actions

import data.User
import play.api.mvc._

import scala.concurrent.Future

case class TimingAction[A](action: Action[A]) extends Action[A] {

  def apply(request: Request[A]): Future[Result] = {
    val startTime = System.currentTimeMillis

    action(request).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }

  override def parser: BodyParser[A] = action.parser
}

case class TimingAction2() extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A],
                     block: (Request[A]) => Future[Result]): Future[Result] = {
    val startTime = System.currentTimeMillis

    block(request).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}

case class LoginAction(userId: Int, pass: String) extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A],
                     block: (Request[A]) => Future[Result]): Future[Result] = {
    User.get(userId) match {
      case Some(userAccount) if pass == userAccount.pass => block(request)
      case _ => Future(Results.Forbidden)
    }
  }
}


case class UserWrappedRequest[A](user: User,
                                 request: Request[A]) extends WrappedRequest[A](request)


case class AdminLoginAction(userId: Int, pass: String)
  extends ActionBuilder[UserWrappedRequest] {

  def invokeBlock[A](request: Request[A],
                     block: (UserWrappedRequest[A]) => Future[Result]): Future[Result] = {
    User.get(userId) match {
      case Some(userAccount) if userAccount.pass == pass =>
        block(UserWrappedRequest(userAccount, request))
      case _ =>
        Future(Results.Forbidden)
    }
  }

}