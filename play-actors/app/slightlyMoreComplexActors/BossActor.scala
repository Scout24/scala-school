package slightlyMoreComplexActors

import slightlyMoreComplexActors.BartenderActor.{EntryDenied, EntryApproved}
import akka.actor.{Props, Actor}

object BossActor {
  def props = Props[BossActor]

  case class ApproveEntry(age: Int, name: String)
}

class BossActor extends Actor {
  import BossActor._

  def receive = {
    case ApproveEntry(age: Int, name: String) =>
      sender() ! (if(age > 17) EntryApproved(name) else EntryDenied(name))
  }
}
