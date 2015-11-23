import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.scalatest._
import simpleActors.BartenderActor.{Hello, Order}
import simpleActors.CustomerActor.Drink
import simpleActors.{BartenderActor, CustomerActor}

import scala.concurrent.duration._

class SimpleActorSpec(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with FlatSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("SimpleActorTest"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  def timeout = FiniteDuration(5, MILLISECONDS)

  "BartenderActor" should "greet new customers" in {
    val bartenderRef = system.actorOf(BartenderActor.props)

    bartenderRef ! Hello("Susie")
    expectMsg(timeout, "Hello Susie, welcome to Bar Tatsu!")
  }

  "BartenderActor" should "respond to an order" in {
    val bartenderRef = system.actorOf(BartenderActor.props)

    bartenderRef ! Order("Caipirinha")
    expectMsgAllOf(timeout, "Here's your Caipirinha", Drink("Caipirinha"))
  }

  "CustomerActor" should "say thank you for the drink" in {
    val customerRef = system.actorOf(CustomerActor.props)

    customerRef ! Drink("Caipirinha")
    expectMsg(timeout, "Thanks for the Caipirinha!")
  }

  "Customer and bartender" should "interact" in {
    val dave = system.actorOf(BartenderActor.props, "dave")
    val julia = TestProbe()

    dave.tell(Hello("julia"), julia.ref)
    julia.expectMsg(timeout, "Hello julia, welcome to Bar Tatsu!")

    dave.tell(Order("white wine"), julia.ref)
    julia.expectMsg(timeout, "Here's your white wine")
    julia.expectMsg(timeout, Drink("white wine"))

  }
}
