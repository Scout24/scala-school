package component

import play.api.test.{FakeRequest, PlaySpecification, WithApplication}
import testutils._
import org.scalatest.Tag

class ApplicationControllerFunSuite extends FunSuiteWrapper {

  //TODO: Fix this test
  test("root path should return 200") (pendingUntilFixed( {
    val Some(response) = route(FakeRequest(GET, "/"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("Your new application is not ready."))
  }))

  test("circumference should return the circumference of a circle with radius r", Tag("#circumference")) {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference/$radius?language=de"))
    assert(status(response) === OK)
    assert(contentAsString(response).toDouble > 12.566)
    assert(contentAsString(response).toDouble < 12.567)
  }

  test("circumferencePage should be in English by default", Tag("#circumference")) {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("<title>Your circumference is...</title>"))
  }

  test("circumferencePage should return the circumference of a circle with radius r in English by default") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumfeence-page/$radius"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("The circumference of a circle with radius 2.0 is 12.56"))
  }

  test("circumferencePage should be translated into German") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumfernce-page/$radius?language=de"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("<title>Ihr Umfang ist...</title>"))
  }

  test("circumferencePage should return the circumference of a circle with radius r in German") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumfernce-page/$radius?language=de"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("<title>Ihr Umfang ist...</title>"))
    assert(contentAsString(response).contains("Der Umfang eines Kreises mit Radius 2.0 ist 12.56"))
  }

  test("sequence should return first five values in the multiplication table of startValue") {
    val startValue = 3
    val Some(response) = route(FakeRequest(GET, s"/sequence/$startValue"))
    assert(status(response) === OK)
    val result: Seq[Int] = contentAsJson(response).as[Seq[Int]]
    assert(result.contains(3))
    assert(result.contains(6))
    assert(result.contains(12))
    assert(result.contains(15))
    assert(!result.contains(18))
  }

  //TODO: Write another test
  test("some test that hasn't been written yet") (pending)
}

//TODO: Re-write this test using the FlatSpec style
class ApplicationControllerFlatSpec extends FlatSpecWrapper

//TODO: Re-write this test using the WordSpec style
class ApplicationControllerWordSpec extends WordSpecWrapper

//TODO: Re-write this test using the FunSpec style
class ApplicationControllerFunSpec extends FunSpecWrapper

//TODO: Re-write this test using the PropSpec style
class ApplicationControllerPropSpec extends PropSpecWrapper

//TODO: Re-write this test using the FeatureSpec style
class ApplicationControllerFeatureSpec extends FeatureSpecWrapper

//TODO: Re-write this test using the Specs2 style
class ApplicationControllerSpecs2AcceptanceStyle extends PlaySpecification {

  def between(i: Int, j: Int) = be_>=(i) and be_<=(j)

  override def is = s2"""
  The circumference endpoint
    should respond with a value that is close to the precise circumference  $e1
    """

  def e1 = new WithApplication() {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference/$radius"))

    status(response) mustEqual OK
    contentAsString(response).toDouble must be between(12.566, 12.567)
  }

}

class ApplicationControllerSpecs2 extends PlaySpecification {

  "The circumference-page endpoint" >> {
    "when called with German language query parameter" >> {
      "should respond with a page with a German title" >> new WithApplication() {
        val radius = 2.0
        val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=de"))

        status(response) must be equalTo OK
        contentAsString(response) must contain("<title>Ihr Umfang ist...</title>")
      }

      "should respond with a page with a German result message containing the correct value" >> new WithApplication() {
        val radius = 2.0
        val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=de"))

        status(response) must be_==(OK)
        contentAsString(response) must contain("Der Umfang eines Kreises mit Radius 2.0 ist 12.56")
      }
    }

    "when called with (default) English language query parameter" >> {
      "should respond with a page with an English title" >> new WithApplication() {
        val radius = 2.0
        val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=en"))

        status(response) mustEqual OK
        contentAsString(response) must contain("<title>Your circumference is...</title>")
      }

      "should respond with a page with an English result message containing the correct value" >> new WithApplication() {
        val radius = 2.0
        val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=en"))

        status(response) mustEqual OK
        contentAsString(response) must contain("The circumference of a circle with radius 2.0 is 12.56")
      }
    }
  }

  "The sequence endpoint" should {
    "when called with a valid number" should {
      "respond with the first five values of this numbers multiplication table" in new WithApplication() {
        val startValue = 3
        val Some(response) = route(FakeRequest(GET, s"/sequence/$startValue"))
        status(response) mustEqual OK
        val result: Seq[Int] = contentAsJson(response).as[Seq[Int]]

        result must contain(exactly(3, 6, 9, 12, 15))
      }
      "respond with five zeros when 0 is the start value" in new WithApplication() {
        val startValue = 0
        val Some(response) = route(FakeRequest(GET, s"/sequence/$startValue"))
        status(response) mustEqual OK
        val result: Seq[Int] = contentAsJson(response).as[Seq[Int]]

        result must contain(atLeast(0))
        result must have length 5
      }
    }
    "called with string instead of a number" should {
      "respond with an error code" in new WithApplication() {
        val Some(response) = route(FakeRequest(GET, s"/sequence/abc"))
        status(response) mustEqual BAD_REQUEST
      }
    }
  }
}