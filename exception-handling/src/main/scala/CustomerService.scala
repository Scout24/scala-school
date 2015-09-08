import org.scalactic.{Bad, Good, Or}

import scala.util.{Failure, Success, Try}

class CustomerService(repo: CustomerRepo) {

  def errorMessageNotFound(customerId: Int): String = s"Could not find a customer with id $customerId."
  def errorMessageInactive(customerId: Int): String = s"Customer with id $customerId is inactive."

  sealed trait CustomerServiceError { def msg: String }
  case class CustomerNotFound(msg: String) extends CustomerServiceError
  case class CustomerInactive(msg: String) extends CustomerServiceError

  type CustomerName = String

  /** Just simply call customer repo here and return the name. What problems do you see here with our scala wrapper method? */
  // TODO ???
  def customerName(id: Int): CustomerName = repo.getCustomerName(id)

    /**
     * Use scala language try/catch to catch exceptions from underlying repo and translate to Option.
     * But only return None for i.e. Customer not found & inactive
     */
  // TODO ???
  def customerNameOption(id: Int): Option[CustomerName] = {
    try {
      Some(repo.getCustomerName(id))
    } catch {
      case e: CustomerNotFoundException => None
      case e: CustomerInactiveException => None
    }
  }

  /**
   * Use Try from Scala library (one liner)
   */
  // TODO ???
  def customerNameTry(id: Int): Try[CustomerName] = Try(repo.getCustomerName(id))

  /**
   * Use Scala Library Try for dealing with the exceptions thrown by the customer repo and then translate them to Good or Bad.
   * Rethrow all other exceptions.
   *
   * Do you see the benefits in the method signature compared to the above solutions?
   */
  // TODO ???
  def customerNameOr(id: Int): String Or CustomerServiceError = {
    Try(repo.getCustomerName(id)) match {
      case Success(name) => Good(name)
      case Failure(e: CustomerNotFoundException) => Bad(CustomerNotFound(errorMessageNotFound(id)))
      case Failure(e: CustomerInactiveException) => Bad(CustomerInactive(errorMessageInactive(id)))
      case Failure(other) => throw other
    }
  }
}
