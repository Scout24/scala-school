import org.scalatest.{MustMatchers, WordSpec}

class ConsumerViewTest extends WordSpec with MustMatchers {

  object Repo extends CustomerRepo {
    val customers = Map(
      27 -> "John Smith",
      28 -> "Peter Muller",
      29 -> "Inactive"
    )
    override def getCustomerName(id: Int): String = customers.get(id) match {
      case Some("Inactive") => throw new CustomerInactiveException
      case Some(name) => name
      case None => throw new CustomerNotFoundException
    }
  }

  val view = new ConsumerView(new Consumer(Repo))

  "All active customers should be listed" in {
    view.listActiveCustomers() must be(List("John Smith", "Peter Muller"))
  }

  "Customer names and error messages should be displayed in" in {
    view.displayCustomers(List(27,29,31)) must be(
      """John Smith
         Customer with id 29 is inactive.
         Could not find a customer with id 31.""")
  }
}
