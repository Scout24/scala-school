import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.scalatest._
import simpleActors.BartenderActor.{Hello, Order}
import simpleActors.CustomerActor.Drink
import simpleActors.{BartenderActor, CustomerActor}

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

  "BartenderActor" should "greet new customers" in {
    val bartenderRef = system.actorOf(BartenderActor.props)

    bartenderRef ! Hello("Susie")
    expectMsg("Hello Susie, welcome to Bar Tatsu!")
  }

  "BartenderActor" should "respond to an order" in {
    val bartenderRef = system.actorOf(BartenderActor.props)

    bartenderRef ! Order("Caipirinha")
    expectMsgAllOf("Here's your Caipirinha", Drink("Caipirinha"))
//    expectMsg("Here's your Caipirinha")
//    expectMsg(Drink("Caipirinha"))
  }

  "CustomerActor" should "say thank you for the drink" in {
    val customerRef = system.actorOf(CustomerActor.props)

    customerRef ! Drink("Caipirinha")
    expectMsg("Thanks for the Caipirinha!")
  }

  "Customer and bartender" should "interact" in {
    val dave = system.actorOf(BartenderActor.props, "dave")
    val julia = TestProbe()
    val juliaActor = system.actorOf(Props(julia.getClass))

    dave.tell(Hello("julia"), juliaActor)
    julia.expectMsg("Hello julia, welcome to Bar Tatsu!")

    dave.tell(Order("white wine"), juliaActor)
    julia.expectMsg("Here's your Caipirinha")
    julia.expectMsg(Drink("Caipirinha"))

  }
}
