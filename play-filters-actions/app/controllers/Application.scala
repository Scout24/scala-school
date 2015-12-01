package controllers

import models.Calculator
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

  def circumference(r: Double) = Action { implicit request =>
    //Ok(Html(""+Calculator.circumference(r)))
    val language = request.getQueryString("language").getOrElse("")
    Ok(resultPage(r,language))
  }
}
