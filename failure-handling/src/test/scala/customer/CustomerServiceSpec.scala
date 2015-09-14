package customer

import org.scalactic.{Bad, Good}
import org.scalatest.{MustMatchers, WordSpec}

import scala.util.{Failure, Success}

class CustomerServiceSpec extends WordSpec with MustMatchers {
  val sut = new CustomerService(new CustomerRepoImpl)
  val customerIds = 27 to 35

  "get a customer name from the repo" should {

    "using try/catch and Option" in {
      sut.customerNameOption(27) must be(Some("John Smith"))
      sut.customerNameOption(30) must be(None)
    }

    "using the Try monad" in {
      sut.customerNameTry(27) must be(Success("John Smith"))
      sut.customerNameTry(30).isFailure must be(true)
    }

    "using Scalactic's Or" in {
      sut.customerNameOr(27) must be(Good("John Smith"))
      sut.customerNameOr(30).isBad must be(true)
    }
  }
}
