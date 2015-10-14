package unit

import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

class FutureCombinatorsSpec extends WordSpec with Matchers {

  def fileBy(name: String) = Future {
    Source.fromFile(name)
  }

  def asyncToUpperCase(s : String) : Future[String] = Future {
    s.toUpperCase
  }

  def getHelloWordFile() = fileBy("test/HelloWorld.txt")

  // TODO Exercise 2 - use combinator (instance) methods flatMap, map, and recover provided by Future
  def getHelloWorld() :  Future[String] = ???

  def getHelloWorldUpperCase() : Future[String] = ???

  def getUndefinedFile() : Future[String] = ??? // fileBy("test/undefined.txt")



  "The Future" should {

    "return 'Hello Word!'" in {

      val helloWorldFuture  = getHelloWorld()

      Await.result(helloWorldFuture, 1 second) should be("Hello World!")

    }

    "return 'HELLO WORLD!'" in {

      val helloWorldFuture = getHelloWorldUpperCase()

      Await.result(helloWorldFuture, 1 second) should be("HELLO WORLD!")

    }

    "return 'Hello Word! in failure case'" in {

      val undefinedFile = getUndefinedFile()

      Await.result(undefinedFile, 1 second) should be("Hello World!")

    }
  }
}
