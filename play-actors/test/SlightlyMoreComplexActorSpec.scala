import akka.util.Timeout
import slightlyMoreComplexActors.BartenderActor.{EntryDenied, EntryApproved, Hello, Order}
import slightlyMoreComplexActors.BossActor.{ApproveEntry, OpenBar}
import slightlyMoreComplexActors.CustomerActor.{Sorry, Welcome, Drink}
import slightlyMoreComplexActors.{BossActor, BartenderActor, CustomerActor}
import akka.actor.{ActorRef, ActorSystem}
import akka.testkit.{TestActorRef, TestProbe, ImplicitSender, TestKit}
import org.scalatest._
import akka.pattern.ask
import scala.language.postfixOps
import scala.concurrent.duration._

import scala.concurrent.Await

class SlightlyMoreComplexActorSpec(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with FlatSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("SlightlyMoreComplexActorTest"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "CustomerActor" should "say hello to the bartender" in {
    val bartender = TestProbe()
    val customerRef = system.actorOf(CustomerActor.props("Arif", 31, "Mojito", bartender.ref))

    bartender.expectMsg(Hello("Arif", 31))
  }

  "BartenderActor" should "greet and serve regulars" in {
    val bartenderRef = system.actorOf(BartenderActor.props(List("Susie", "Julia")))

    bartenderRef ! Hello("Susie", 36)
    expectMsg(Welcome)

    bartenderRef ! Order("Mojito")
    expectMsg(Drink("Mojito"))
  }

  "BartendActor" should "ask for approval for new customers" in {
    val boss = TestProbe()
    val bartenderRef = TestActorRef(BartenderActor.props(List("Susie", "Julia")), boss.ref)

    bartenderRef ! Hello("Arif", 31)
    boss.expectMsg(ApproveEntry("Arif", 31))
  }

  "BossActor" should "approve customers over 18" in {
    val bossRef = system.actorOf(BossActor.props)

    bossRef ! ApproveEntry("Arif", 31)
    expectMsg(EntryApproved("Arif"))
  }

  "BossActor" should "reject customers under 18" in {
    val bossRef = system.actorOf(BossActor.props)

    bossRef ! ApproveEntry("David", 16)
    expectMsg(EntryDenied("David"))
  }

  "CustomerActor" should "start drinking when entrance is granted" in {
    val bartender = TestProbe()
    val customerRef = system.actorOf(CustomerActor.props("Matt", 29, "Espresso Martini", bartender.ref))

    bartender.expectMsg(Hello("Matt", 29))
    customerRef ! Welcome
    bartender.expectMsg(Order("Espresso Martini"))
  }

  "CustomerActor" should "stop when entrance is denied" in {
    val watcher = TestProbe()
    val bartenderRef = TestProbe().ref
    val customerRef = system.actorOf(CustomerActor.props("David", 16, "Vodka Red Bull", bartenderRef))
    watcher.watch(customerRef)
    customerRef ! Sorry

    watcher.expectTerminated(customerRef, 1 second)
  }

  "BartenderActor" should "not serve new underage customers" in {
    val bossRef = system.actorOf(BossActor.props)
    implicit val timeout: Timeout = 100 milliseconds
    val future = bossRef ? OpenBar(List("Susie", "Julia"))
    val bartenderRef = Await.result(future, 100 milliseconds).asInstanceOf[ActorRef]

    bartenderRef ! Hello("David", 16)
    expectMsg(Sorry)
  }
}
