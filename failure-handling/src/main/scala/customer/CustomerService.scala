package customer

import org.scalactic.{Bad, Good, Or}

import scala.util.{Failure, Success, Try}

class CustomerService(repo: CustomerRepo) {

  def errorMessageNotFound(customerId: Int): String = s"Could not find a customer with id $customerId."

  case class CustomerNotFound(msg: String)

  type CustomerName = String

  /** Simple wrapper to demonstrate that Scala treats Java's checked exceptions silently.
    * What problems do you see here with our scala wrapper method? */
  def customerName(id: Int): CustomerName = repo.getCustomerName(id)

    /**
     * Use scala language try/catch to catch exceptions from underlying repo and translate to Option.
     * But only return None for i.e. Customer not found
     */
  // TODO ???
  def customerNameOption(id: Int): Option[CustomerName] = {
    try {
      Some(repo.getCustomerName(id))
    } catch {
      case e: CustomerNotFoundException => None
    }
  }

  /**
   * Use Try from Scala library (one liner).
   */
  // TODO ???
  def customerNameTry(id: Int): Try[CustomerName] = Try(repo.getCustomerName(id))

  /**
   * Use Scala Library Try for dealing with the exceptions thrown by the customer repo and then translate them to Scalactics Good or Bad.
   * Rethrow all other exceptions.
   *
   * Do you see the benefits in the method signature compared to the above solutions?
   */
  // TODO ???
  def customerNameOr(id: Int): String Or CustomerNotFound = {
    Try(repo.getCustomerName(id)) match {
      case Success(name) => Good(name)
      case Failure(e: CustomerNotFoundException) => Bad(CustomerNotFound(errorMessageNotFound(id)))
      case Failure(other) => throw other
    }
  }
}
