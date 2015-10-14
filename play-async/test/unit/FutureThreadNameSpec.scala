package unit

import java.util.concurrent.Executor

import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration._
import scala.concurrent.{Promise, Await, ExecutionContext, Future}
import scala.language.postfixOps

object ThreadName {

  def getThreadNameMain() = Thread.currentThread().getName

  def getThreadNameAsync() = {

    implicit val executionContext = ExecutionContext.fromExecutor(new Executor {
      override def execute(command: Runnable): Unit = command.run()
    })

    // TODO Exercise 2 - use a different execution context to make the test green
    Future {
      getThreadNameMain()
    }
  }

}

class FutureThreadNameSpec extends WordSpec with Matchers {

  "The Future " should {

    "return a different thread name" in {

      import ThreadName._

      val threadNameMain = getThreadNameMain()

      val threadNameAsync = Await.result(getThreadNameAsync(), 1 seconds)

      threadNameAsync should not be (threadNameMain)

    }
  }

}
