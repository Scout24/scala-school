package actors

import actors.GreeterActor.Greet
import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest._

class GreeterActorTest(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with FlatSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("GreeterActorTest"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "GreeterActor" should "greet new customers" in {
    val greeterRef = system.actorOf(GreeterActor.props)

    greeterRef ! Greet("Susie")
    expectMsg("Hello, Susie, welcome to Bar Tatsu!")
  }
}
