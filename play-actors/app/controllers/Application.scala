package controllers

import actors.GreeterActor
import akka.actor.ActorSystem
import com.google.inject.{Singleton, Inject}
import play.api.mvc._

//this needs to be a singleton as the greeter actor will be declared when the controller instantiated
@Singleton
class Application @Inject() (system: ActorSystem) extends Controller {

  val greeter = system.actorOf(GreeterActor.props, "greeter-actor")
}
