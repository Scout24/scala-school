import org.scalatest.{MustMatchers, WordSpec}

class ActiveCustomersListViewSpec extends WordSpec with MustMatchers {
  val view = new ActiveCustomersListView(new CustomerService(new CustomerRepoImpl))
  val customerIds = 27 to 35

  val expected = List("John Smith", "Peter Muller")

  "Listing names for active and existing customers only" should {

    "using listActiveCustomersLangTryBased" in {
      view.listActiveCustomersLangTryBased(customerIds) must be(expected)
    }

    "using listActiveCustomersTryBased" in {
      view.listActiveCustomersTryBased(customerIds) must be(expected)
    }

    "using listActiveCustomersOptionBased" in {
      view.listActiveCustomersOptionBased(customerIds) must be(expected)
    }

    "using listActiveCustomersScalacticOrBased" in {
      view.listActiveCustomersScalacticOrBased(customerIds) must be(expected)
    }
  }
}
