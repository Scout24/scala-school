import actors.BartenderActor.{Hello, Order}
import actors.CustomerActor.Drink
import actors.{BartenderActor, CustomerActor}
import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestProbe, ImplicitSender, TestKit}
import org.scalatest._

class ActorSpec(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with FlatSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("GreeterActorTest"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "BartenderActor" should "greet new customers" in {
    val bartenderRef = system.actorOf(BartenderActor.props)

    bartenderRef ! Hello("Susie", 36)
    expectMsg("Hello Susie, welcome to Bar Tatsu!")
  }

  "BartenderActor" should "respond to an order" in {
    val bartenderRef = system.actorOf(BartenderActor.props)

    bartenderRef ! Order("Caipirinha")
    expectMsg("Here's your Caipirinha")
    expectMsg(Drink("Caipirinha"))
  }

  "CustomerActor" should "say thank you for the drink" in {
    val customerRef = system.actorOf(CustomerActor.props("Arif", 31, "Mojito", system.actorOf(BartenderActor.props)))

    customerRef ! Drink("Caipirinha")
    expectMsg("Thanks for the Caipirinha!")
  }

  "Customer and bartender" should "interact" in {
    val dave = system.actorOf(BartenderActor.props, "dave")
    val julia = TestProbe()
    val juliaActor = system.actorOf(Props(julia.getClass))

    dave.tell(Hello("julia", 22), juliaActor)
    julia.expectMsg("Hello julia, welcome to Bar Tatsu!")

    dave.tell(Order("white wine"), juliaActor)
    julia.expectMsg("Here's your Caipirinha")
    julia.expectMsg(Drink("Caipirinha"))
//    expectMsg("Thanks for the Caipirinha!")

  }
}
