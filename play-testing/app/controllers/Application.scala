package controllers

import models.Calculator
import play.api.libs.json.Json
import play.api.mvc._
import views.html.resultPage

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def invalid = Action {
    Status(BAD_REQUEST)
  }

  def circumferencePage(r: Double) = Action { implicit request =>
    val language = request.getQueryString("language").getOrElse("")
    Ok(resultPage(r,language))
  }

  def circumference(r: Double) = Action { implicit request =>
    Ok(Json.toJson(Calculator.circumference(r)))
  }

  def sequence(i: Int) = Action { implicit request =>
    Ok(Json.toJson(Calculator.sequence(i)))
  }
}
