package slightlyMoreComplexActors

import slightlyMoreComplexActors.CustomerActor.Drink
import akka.actor.{ActorRef, Actor, Props}

object BartenderActor {
  def props = Props[BartenderActor]

  case class Hello(name: String, age: Int)  
  case class Order(drink: String)
  case class EntryApproved(name: String)
  case class EntryDenied(name: String)
}

class BartenderActor extends Actor {
  import BartenderActor._
  import BossActor._

  def receive = servingDrinks(Nil)

  def servingDrinks(approved: List[String]) : Receive = {
    case Hello(name: String, age: Int) =>
      if(approved contains name) sender() ! s"Hello $name, welcome to Bar Tatsu!"
      else {
        context.parent ! ApproveEntry(age, name)
        context.become(awaitingApproval(approved, sender))
      }
    case Order(drink: String) =>
      sender() ! s"Here's your $drink"
      sender() ! Drink(drink)
  }

  def awaitingApproval(approved: List[String], customer: ActorRef) : Receive = {
    case EntryApproved(name) => context.become(servingDrinks(name :: approved))
    case EntryDenied(name) => {
      customer !
      context.become(servingDrinks(approved))
    }
  }

}
