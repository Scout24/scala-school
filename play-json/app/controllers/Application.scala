package controllers

import models.{Vehicle, Calculator}
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

  def nextVehicle = Action {
    Ok(Json.toJson(Vehicle.testVehicles(scala.util.Random.nextInt(3))))
  }
}
