class CityNameConstants(cities: List[String]) {
  val citiesByFirst = cities.map(c => c.head -> c).toMap[Char, String]

  def uppercaseGerman(str: String) = if (str.contains('ß')) None else Some(str.toUpperCase())

  def getUsingMatch(c: Char): Option[String] = {
    val city = citiesByFirst.get(c)
    val uppercased = city match {
      case Some(str) => ???
      case None => None
    }
    ?? // hint: return Some(...replace(' ', '_'))
  }

  def getUsingMap(c: Char): Option[String] = {
    val city = citiesByFirst.get(c)
    val uppercased = city.flatMap(???)
    uppercased.map(???)
  }

  def getUsingFor(c: Char): Option[String] = {
    for {
      city <- ??
      uppercased <- ??
    } yield ???
  }

  // helper to make the exercise compile
  def ??(): Option[Nothing] = throw new NotImplementedError()
}