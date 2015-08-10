import org.scalatest.{MustMatchers, FlatSpec}

class MappingTest extends FlatSpec with MustMatchers {

  "getPopulationOf some place that exists" should "return the population" in {
    Mapping.getPopulationOf("Hamburg") must be(1.734)
  }

  "getPopulationOf some place that doesn't exist" should "return None" in {
    Mapping.getPopulationOf("Gotham") must be(None)
  }

  "addPopulations of cities in Germany" should "return the population" in {
    Mapping.addPopulationsOf("Munich", "Hamburg", "Berlin") must be(6.497)
  }

  "addPopulations of cities in Germany" should "deal with missing cities" in {
    Mapping.addPopulationsOf("Munich", "Hamburg", "Leipzig") must be("Sorry, can't find Leipzig")
  }

}
