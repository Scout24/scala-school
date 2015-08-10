object Mapping {

  val populationInMillions = Map(
    "Munich" -> 1.388,
    "Hamburg" -> 1.734,
    "Berlin" -> 3.375,
    "London" -> 8.63,
    "Hong Kong" -> 7.188,
    "New York" -> 8.406,
    "Bangkok" -> 6.355,
    "New Delhi" -> 9.879,
    "Beijing" -> 11.51,
    "Tokyo" -> 13.35,
    "Luxembourg" -> 0.543)

  //implement this first
  def readMap(string: String): Option[Double] = ???

  def getPopulationOf(place: String): Double = ???

  def addPopulationsOf(places: String*): Double = ???

}
