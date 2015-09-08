import org.scalactic.{Many, Bad, One, Good}
import org.scalatest.{MustMatchers, WordSpec}

class FailFastOrAccumulatingErrorsWithScalacticOrSpec extends WordSpec with MustMatchers {

  import FailFastOrAccumulatingErrorsWithScalacticOr._

  "Fail fast at first error with Scalactic Or" should {
    "parsePerson success" in {
      parsePersonFailFast("Bridget Jones", "29") must be(Good(Person("Bridget Jones", 29)))
    }

    "parsePerson age is no number" in {
      parsePersonFailFast("Bridget Jones", "") must be(Bad(One(""""" is not a valid integer""")))
    }

    "parsePerson age is not a valid age" in {
      parsePersonFailFast("Bridget Jones", "-29") must be(Bad(One(""""-29" is not a valid age""")))
    }

    "parse person with both invalid returns first error" in {
      parsePersonFailFast("", "") must be(Bad(One(""""" is not a valid name""")))
    }
  }

  "Accumulate errors with Scalactic Or" should {

    "parsePerson success" in {
      parsePersonAccumulateErrors("Bridget Jones", "29") must be(Good(Person("Bridget Jones", 29)))
    }

    "parsePerson age is no number" in {
      parsePersonAccumulateErrors("Bridget Jones", "") must be(Bad(One(""""" is not a valid integer""")))
    }

    "parsePerson age is not a valid age" in {
      parsePersonAccumulateErrors("Bridget Jones", "-29") must be(Bad(One(""""-29" is not a valid age""")))
    }

    "parse person with both invalid" in {
      parsePersonAccumulateErrors("", "") must be(Bad(Many(""""" is not a valid name""", """"" is not a valid integer""")))
    }

  }
}
