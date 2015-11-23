package actors

import actors.CustomerActor.Drink
import akka.actor.{Actor, Props}

object BartenderActor {
  def props = Props[BartenderActor]

  case class Welcome(name: String, age: Int)
  case class Order(drink: String)
}

class BartenderActor extends Actor {
  import BartenderActor._
  import BossActor._

  def receive = servingDrinks(Nil)

  def servingDrinks(approved: List[String]) : Receive = {
    case Welcome(name: String, age: Int) =>
      if(approved contains name) sender() ! s"Hello $name, welcome to Bar Tatsu!"
      else {
        context.parent ! ApproveSale(age, name)
        context.become(awaitingApproval(approved))
      }
    case Order(drink: String) =>
      sender() ! s"Here's your $drink"
      sender() ! Drink(drink)
  }

  def awaitingApproval(approved: List[String]) : Receive = {
    case Yes(name) => context.become(servingDrinks(name :: approved))
    case No(name) => context.become(servingDrinks(approved))
  }

}
