package actors

import akka.actor.{Actor, Props}

object GreeterActor {
  def props = Props[GreeterActor]

  case class Greet(name: String)
}

class GreeterActor extends Actor {
  import GreeterActor._

  def receive = {
    case Greet(name: String) =>
      sender() ! "Hello, " + name + ", welcome to Bar Tatsu!"
  }
}
