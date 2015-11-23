package actors

import actors.CustomerActor.Drink
import akka.actor.{Props, Actor}

object BossActor {
  def props = Props[BossActor]

  case class ApproveSale(age: Int, name: String)
  case class Yes(name: String)
  case class No(name: String)
}

class BossActor extends Actor {
  import BossActor._

  def receive = {
    case ApproveSale(age: Int, name: String) =>
      sender() ! (if(age > 17) Yes(name) else No(name))
  }
}
