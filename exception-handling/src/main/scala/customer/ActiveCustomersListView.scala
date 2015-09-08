package customer

import org.scalactic.Good

import scala.util.{Success, Try}

class ActiveCustomersListView(customerService: CustomerService) {

  /**
   * Use [[customerService.customerName(id)]] for implementing a Seq of only active & existing customer names for the given customer ids.
   *
   * You may freely use try or Try or another method for handling the exceptions of the underlying repo method.
   *
   * Hint: If you use Try() have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq
   * TODO gist for collect
   *
   * Do you see the burden that is put on the client when using Exceptions for "business return values"?
   */
  // TODO ???
  def listActiveCustomersLangTryBased(ids: Seq[Int]): Seq[String] = {
    val possibleCustomerNames = ids.map { id => Try(customerService.customerName(id)) }
    possibleCustomerNames.collect {
      case Success(name) => name
    }
  }

  /**
   * Same as above but now use [[customerService.customerNameTry(id)]].
   *
   * Hint: Have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq
   * TODO gist for collect
   */
  // TODO ???
  def listActiveCustomersTryBased(ids: Seq[Int]): Seq[String] = {
    ids.map(customerService.customerNameTry).collect {
      case Success(name) => name
    }
  }

  /**
   * Same as above but now use [[customerService.customerNameOption(id)]].
   *
   * Hint: Have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq
   * TODO gist for collect
   */
  // TODO ???
  def listActiveCustomersOptionBased(ids: Seq[Int]): Seq[String] = ids.flatMap(customerService.customerNameOption)

  /**
   * Same as above but now use [[customerService.customerNameOr(id)]].
   *
   * Hint: Have a look at collect method in http://www.scala-lang.org/api/current/#scala.collection.Seq
   * TODO gist for collect
   */
  // TODO ???
  def listActiveCustomersScalacticOrBased(ids: Seq[Int]): Seq[String] = {
    ids.map(customerService.customerNameOr).collect {
      case Good(name) => name
    }
  }
}
