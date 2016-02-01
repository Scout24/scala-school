package controllers

import actions._
import models.Calculator
import play.api.mvc._
import play.twirl.api.Html
import views.html.resultPage

class Application extends Controller {

  def veryLongAction =
    TimingAction {
      Action {
        val result = doSomethingComplicated()
        Ok(s"Got $result")
      }
    }

  def authorised1(userId: Int, pass: String) =
    LoginAction(userId, pass) { req =>
      Ok(s"Super Secret Page")
    }

  def authorised2(userId: Int, pass: String) =
    AdminLoginAction(userId, pass) { req =>
      Ok(s"Super Secret Page that only ${req.user.name} can see")
    }

  def authorised3(userId: Int, pass: String) =
    (TimingAction2() andThen AdminLoginAction(userId, pass)) { req =>
      Ok(s"Super Secret Page that only ${req.user.name} can see")
    }

  def doSomethingComplicated() = 42

  val echo = Action { request =>
    Ok("Got request [" + request + "]")
  }

}
