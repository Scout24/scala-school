package controllers

import javax.inject.Inject

import play.api.libs.ws.{WSResponse, WSClient}
import play.api.mvc._
import views.html.index
import play.api.libs.json._

import scala.concurrent.Future

class Application @Inject()(ws: WSClient) extends Controller {

  def root = Action {
    import models.{LocalModelsCounterMoto, LocalModelsCounterCars}
    val counter = LocalModelsCounterCars.counter + LocalModelsCounterMoto.counter
    Ok(index(counter))
  }

  case class CounterResponse(counter: Int)
  implicit val crf = Json.format[CounterResponse]
  def counterFromJsonResponse(response: WSResponse): Int = response.json.validate[CounterResponse].get.counter

  // TODO Exercise 5 - implement same behaviour as above with remote counters, e.g., by using a for comprehension
  def rootremote = Action.async {
    import scala.concurrent.ExecutionContext.Implicits.global
    val carsCounterResponse: Future[WSResponse] = ws.url("http://localhost:9000/carscounter").get
    val motoCounterResponse: Future[WSResponse] = ws.url("http://localhost:9000/motocounter").get

    val res: Future[Result] = ???
    res
  }
}
