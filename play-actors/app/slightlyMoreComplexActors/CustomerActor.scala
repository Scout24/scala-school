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
    bartender ! Hello(name, age)
  }

  def drinking: Receive = {
    case Drink(drink: String) => {
      sender() ! s"Thanks for the $drink!"
      context.system.scheduler.scheduleOnce(100 milliseconds, self, DrinkFinished)
    }
    case DrinkFinished() => {
      bartender ! Order(favouriteDrink)
    }
  }

  def entering: Receive = {
    case Welcome() => {
      bartender ! Order(favouriteDrink)
      context.become(drinking)
    }
    case Sorry() => context.stop(self)
  }

  def receive = entering
}

