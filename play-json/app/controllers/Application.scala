package controllers

import models.Vehicle
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

  def nextVehicle = Action {
    Ok(Json.toJson(Vehicle.testVehicles(scala.util.Random.nextInt(Vehicle.testVehicles.length))))
  }

  def addVehicle = Action(parse.json) { request =>
    val v: Vehicle = request.body.as[Vehicle]
    Vehicle.testVehicles = v :: Vehicle.testVehicles
    Ok
  }
}
