package controllers

import models.{LocalModelsCounterCars, LocalModelsCounterMoto}
import play.api.libs.json.{JsNumber, JsValue, JsObject, Json}
import play.api.mvc._
import views.html.index

class RemoteCounter extends Controller {

  def carscounter = Action {
    Ok(JsObject(Seq("counter" -> JsNumber(0))))
  }

  def motocounter = Action {
    Ok(JsObject(Seq("counter" -> JsNumber(0))))
  }
}
