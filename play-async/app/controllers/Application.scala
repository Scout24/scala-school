package controllers

import javax.inject.Inject

import models.{LocalModelsCounterMoto, LocalModelsCounterCars}
import play.api.libs.ws.{WSResponse, WSClient}
import play.api.mvc._
import views.html.{index}
import play.api.libs.json._

import scala.concurrent.Future

class Application @Inject()(ws: WSClient) extends Controller {

  // TODO Exercise 2 - switch to remote counter source
  def root = Action {
    val counter = LocalModelsCounterCars.counter + LocalModelsCounterMoto.counter
    Ok(index(counter))
  }

  case class CounterResponse(counter: Int)
  implicit val crf = Json.format[CounterResponse]

  def rootremote = Action.async {
    import scala.concurrent.ExecutionContext.Implicits.global
    val url = "http://localhost:9000"
    val carsCounter = ws.url(url + "/carscounter").get
    val motoCounter = ws.url(url + "/motocounter").get
    for {
      cc <- carsCounter.map(_.json.validate[CounterResponse].get.counter)
      mc <- motoCounter.map(_.json.validate[CounterResponse].get.counter)
    } yield Ok(index(cc + mc))
  }
}
