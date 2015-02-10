object ImplicitsParameters extends App {

  trait MoneyExchange { def exchange(d: Double): Double }

  class Euro2DollarExchange extends MoneyExchange {
    override def exchange(eur: Double) = eur * 1.1
  }

  def euro2localCurrency(eur: Double, localMex: MoneyExchange) = {
    localMex.exchange(eur)
  }

  val dollar = euro2localCurrency(2.0, new Euro2DollarExchange())


  val euro2DollarExchange: MoneyExchange = new Euro2DollarExchange

}
