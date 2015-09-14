package customer

import org.scalactic.Good

import scala.util.{Success, Try}

class CustomersListView(customerService: CustomerService) {

  /**
   * Use [[customerService.customerName(id)]] for implementing a Seq of existing customer names for the given customer ids.
   * I.e. filter out all ids for that the Repo throws customer not found exceptions.
   *
   * You may freely use try or Try or another method for handling the exceptions of the underlying repo method.
   *
   * Hint: If you use Try() have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq in conjunction with pattern matching.
   * Remember with pattern matching you may define a partial function!
   *
   * See gist for a collect() demo: https://gist.github.com/breadfan/0a422f009ad17e2c8283
   *
   * Do you see the burden that is put on the client when using Exceptions for "business return values"?
   */
  def listExistingCustomerNamesLangTryBased(ids: Seq[Int]): Seq[String] = ???

  /**
   * Same as above but now use already "Try wrapped" [[customerService.customerNameTry(id)]].
   *
   * Hint: Have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq in conjunction with pattern matching.
   * Remember with pattern matching you may define a partial function!
   *
   * See gist for a collect() demo: https://gist.github.com/breadfan/0a422f009ad17e2c8283
   */
  def listExistingCustomerNamesTryBased(ids: Seq[Int]): Seq[String] = ???

  /**
   * Same as above but now use [[customerService.customerNameOption(id)]].
   *
   * Hint: Have a look at flatten() on Seq/List to solve this problem very concisely.
   */
  def listExistingCustomerNamesOptionBased(ids: Seq[Int]): Seq[String] = ???

  /**
   * Same as above but now use [[customerService.customerNameOr(id)]].
   *
   * Hint: Have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq in conjunction with pattern matching.
   * Remember with pattern matching you may define a partial function!
   *
   * See gist for a collect() demo: https://gist.github.com/breadfan/0a422f009ad17e2c8283
   */
  def listExistingCustomerNamesScalacticOrBased(ids: Seq[Int]): Seq[String] = ???
}
