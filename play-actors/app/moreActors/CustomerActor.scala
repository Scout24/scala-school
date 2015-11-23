package moreActors

import akka.actor.{Actor, Props}

object CustomerActor {
  def props = Props[CustomerActor]

  case class Drink(drink: String)
}

class CustomerActor extends Actor {
  import CustomerActor._

  def receive = {
    case Drink(drink: String) =>
      sender() ! s"Thanks for the $drink!"
  }
}

