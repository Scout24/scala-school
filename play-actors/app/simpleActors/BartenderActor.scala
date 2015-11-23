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

  def receive = ???
}
