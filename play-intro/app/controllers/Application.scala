package controllers

import models.Calculator
import play.api._
import play.api.mvc._
import play.twirl.api.Html
import views.html.resultPage

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def invalid = Action {
    Status(BAD_REQUEST)
  }

  def circumference(radius: Double) = Action { implicit request =>
    val language = request.getQueryString("language").getOrElse("")
    Ok(resultPage(radius, language))
  }
}
