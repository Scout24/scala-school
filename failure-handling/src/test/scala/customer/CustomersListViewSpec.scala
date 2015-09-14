package customer

import org.scalatest.{MustMatchers, WordSpec}

class CustomersListViewSpec extends WordSpec with MustMatchers {
  val view = new CustomersListView(new CustomerService(new CustomerRepoImpl))
  val customerIds = 27 to 35

  val expected = List("John Smith", "Peter Muller", "Hans Dampf")

  "Listing names for existing customers only" should {

    "using listExistingCustomersLangTryBased" in {
      view.listExistingCustomerNamesLangTryBased(customerIds) must be(expected)
    }

    "using listExistingCustomersTryBased" in {
      view.listExistingCustomerNamesTryBased(customerIds) must be(expected)
    }

    "using listExistingCustomersOptionBased" in {
      view.listExistingCustomerNamesOptionBased(customerIds) must be(expected)
    }

    "using listExistingCustomersScalacticOrBased" in {
      view.listExistingCustomerNamesScalacticOrBased(customerIds) must be(expected)
    }
  }
}
