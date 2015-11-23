package slightlyMoreComplexActors

import slightlyMoreComplexActors.BartenderActor.{EntryDenied, EntryApproved}
import akka.actor.{ActorRef, Props, Actor}

import scala.concurrent.Future

object BossActor {
  def props = Props[BossActor]

  case class ApproveEntry(age: Int, name: String)
  case class OpenBar(regulars: List[String])
}

class BossActor extends Actor {
  import BossActor._

  def receive = {
    case OpenBar(regulars) => sender ! context.actorOf(BartenderActor.props(regulars))
    case ApproveEntry(age: Int, name: String) =>
      sender() ! (if(age > 17) EntryApproved(name) else EntryDenied(name))
  }
}
