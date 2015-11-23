import akka.util.Timeout
import slightlyMoreComplexActors.BartenderActor.{Hello, Order}
import slightlyMoreComplexActors.BossActor.{OpenBar}
import slightlyMoreComplexActors.CustomerActor.{Sorry, Welcome, Drink}
import slightlyMoreComplexActors.{BossActor, BartenderActor, CustomerActor}
import akka.actor.{ActorRef, Props, ActorSystem}
import akka.testkit.{TestActorRef, TestProbe, ImplicitSender, TestKit}
import org.scalatest._
import akka.pattern.ask
import scala.language.postfixOps
import scala.concurrent.duration._


import scala.concurrent.{Await, Future}

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

  "BartenderActor" should "greet and serve regulars" in {
    val bartenderRef = system.actorOf(BartenderActor.props(List("Susie", "Julia")))

    bartenderRef ! Hello("Susie", 36)
    expectMsg(Welcome)

    bartenderRef ! Order("Mojito")
    expectMsg(Drink("Mojito"))
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
