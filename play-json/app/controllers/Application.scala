package controllers

import models.Calculator
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import play.twirl.api.Html
import views.html.resultPage

import scala.collection.immutable.Range.Inclusive

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def invalid = Action {
    Status(BAD_REQUEST)
  }

  def circumference(r: Double) = Action { implicit request =>
    val language = request.getQueryString("language").getOrElse("")
    Ok(resultPage(r,language))
  }

  def circumferences(a: Int, b: Int) = Action {
    val rs: Inclusive = a to b
    val circs = rs.map(Calculator.circumference(_))
    Ok(Json.toJson(circs))
  }
}
