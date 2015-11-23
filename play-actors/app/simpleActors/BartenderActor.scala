package simpleActors

import simpleActors.CustomerActor.Drink
import akka.actor.{Actor, Props}

object BartenderActor {
  def props = Props[BartenderActor]

  case class Hello(name: String)
  case class Order(drink: String)
}

class BartenderActor extends Actor {
  import BartenderActor._

  def receive = {
    case Hello(name: String) =>
      sender() ! s"Hello $name, welcome to Bar Tatsu!"
    case Order(drink: String) =>
      sender() ! s"Here's your $drink"
      sender() ! Drink(drink)
  }
}
