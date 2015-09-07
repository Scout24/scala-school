import org.scalactic.Or

import scala.util.Try

class Consumer(repo: CustomerRepo) {

  def errorMessageNotFound(customerId: Int): ErrorMessage = s"Could not find a customer with id $customerId."
  def errorMessageInactive(customerId: Int): ErrorMessage = s"Customer with id $customerId is inactive."

  type ErrorMessage = String
  type CustomerName = String

  /** Use scala language try catch, what problems do you see here? */
  def customerName(id: Int): CustomerName = ???

  def customerNameOption(id: Int): Option[CustomerName] = ???
  def customerNameTry(id: Int): Try[CustomerName] = ???
  def customerNameOr(id: Int): String Or ErrorMessage = ???


}
