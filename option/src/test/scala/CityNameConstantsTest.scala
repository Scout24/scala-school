import org.scalatest.{FlatSpec, MustMatchers}

class CityNameConstantsTest extends FlatSpec with MustMatchers {

  val cities = List("München", "Straßburg", "New York", "London", "Hong Kong")
  val cityNameConstants = new CityNameConstants(cities)

  "CityNameConstants.getUsingMatch" should "return a constant identifier given a city name's first letter" in {
    cityNameConstants.getUsingMatch('L').get must be("LONDON")
    cityNameConstants.getUsingMatch('N').get must be("NEW_YORK")
  }

  "CityNameConstants.getUsingMatch" should "not return a constant based on a city name that contains an ß" in {
    cityNameConstants.getUsingMatch('S') must be(None)
  }

  "CityNameConstants.getUsingMap" should "return a constant identifier given a city name's first letter" in {
    cityNameConstants.getUsingMap('L').get must be("LONDON")
    cityNameConstants.getUsingMap('N').get must be("NEW_YORK")
  }

  "CityNameConstants.getUsingMap" should "not return a constant based on a city name that contains an ß" in {
    cityNameConstants.getUsingMap('S') must be(None)
  }

  "CityNameConstants.getUsingFor" should "return a constant identifier given a city name's first letter" in {
    cityNameConstants.getUsingFor('L').get must be("LONDON")
    cityNameConstants.getUsingFor('N').get must be("NEW_YORK")
  }

  "CityNameConstants.getUsingFor" should "not return a constant based on a city name that contains an ß" in {
    cityNameConstants.getUsingFor('S') must be(None)
  }

}
