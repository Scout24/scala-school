package slightlyMoreComplexActors

import akka.actor.{ActorRef, Actor, Props}

import scala.language.postfixOps
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits._

object CustomerActor {
  def props(name: String, age: Int, favouriteDrink: String, bartender: ActorRef) =
    Props(classOf[CustomerActor], name, age, favouriteDrink, bartender)

  case class Drink(drink: String)
  case class Welcome()
  case class Sorry()
  case class DrinkFinished()
}

class CustomerActor(name: String, age: Int, favouriteDrink: String, bartender: ActorRef) extends Actor {
  import CustomerActor._
  import BartenderActor._

  override def preStart {
    ???
  }

  def drinking: Receive = {
    case Drink(drink: String) => ???
    case DrinkFinished() => ???
  }

  def entering: Receive = {
    case _ => ???
  }

  def receive = entering
}

