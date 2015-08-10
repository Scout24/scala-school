class CityNameConstants(cities: List[String]) {
  val citiesByFirst = cities.map(c => c.head -> c).toMap[Char, String]

  def uppercaseGerman(str: String) = if (str.contains('ß')) None else Some(str.toUpperCase())

  def getUsingMatch(c: Char): Option[String] = {
    val city = citiesByFirst.get(c)
    val uppercased = city match {
      case Some(str) => uppercaseGerman(str)
      case None => None
    }
    uppercased match {
      case Some(str) => Some(str.replace(' ', '_'))
      case None => None
    }
  }

  def getUsingMap(c: Char): Option[String] = {
    val city = citiesByFirst.get(c)
    val uppercased = city.flatMap(uppercaseGerman)
    uppercased.map(_.replace(' ', '_'))
  }

  def getUsingFor(c: Char): Option[String] = {
    for {
      city <- citiesByFirst.get(c)
      uppercased <- uppercaseGerman(city)
    } yield uppercased.replace(' ', '_')
  }
}