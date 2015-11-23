package slightlyMoreComplexActors

import slightlyMoreComplexActors.CustomerActor.{Sorry, Welcome, Drink}
import akka.actor.{ActorRef, Actor, Props}

object BartenderActor {
  def props(regulars: List[String]) = Props(classOf[BartenderActor], regulars)

  case class Hello(name: String, age: Int)  
  case class Order(drink: String)
  case class EntryApproved(name: String)
  case class EntryDenied(name: String)
}

class BartenderActor(regulars: List[String]) extends Actor {
  import BartenderActor._
  import BossActor._

  def receive = servingDrinks(regulars)

  def servingDrinks(approved: List[String]) : Receive = {
    case Hello(name: String, age: Int) =>
      if(approved contains name) sender() ! Welcome
      else {
        context.parent ! ApproveEntry(name, age)
        context.become(awaitingApproval(approved, sender))
      }
    case Order(drink: String) =>
      sender() ! Drink(drink)
  }

  def awaitingApproval(approved: List[String], customer: ActorRef) : Receive = {
    case EntryApproved(name) => {
      customer ! Welcome
      context.become(servingDrinks(name :: approved))
    }
    case EntryDenied(name) => {
      customer ! Sorry
      context.become(servingDrinks(approved))
    }
  }

}
