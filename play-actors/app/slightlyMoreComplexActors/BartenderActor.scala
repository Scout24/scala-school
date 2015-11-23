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

  def servingDrinks(approved: List[String]): Receive = {
    case Hello(name, age) => ???
    case Order(drink) => ???
  }

  def awaitingApproval(approved: List[String], customer: ActorRef): Receive = {
    case EntryApproved(name) => ???
    case EntryDenied(name) => ???
  }

}
