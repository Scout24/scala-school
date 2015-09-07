class ConsumerView(consumer: Consumer) {

  /**
   *
   * @return A list of all active customers
   */
  def listActiveCustomers(): Seq[String] = ???

  /**
   *
   * @param ids
   * @return customer names or error message separated by newlines
   */
  def displayCustomers(ids: Seq[Int]): String = ???
}
