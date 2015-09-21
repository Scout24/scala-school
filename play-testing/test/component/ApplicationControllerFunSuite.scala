package component

import play.api.test.{FakeRequest, PlaySpecification}
import testutils._

class ApplicationControllerFunSuite extends FunSuiteWrapper {

  test("root path should return 200") {
    val Some(response) = route(FakeRequest(GET, "/"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("Your new application is ready."))
  }

  test("circumference should return the circumference of a circle with radius r") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference/$radius?language=de"))
    assert(status(response) === OK)
    assert(contentAsString(response).toDouble > 12.566)
    assert(contentAsString(response).toDouble < 12.567)
  }

  test("circumferencePage should return the circumference of a circle with radius r in different languages") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=de"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("Der Umfang eines Kreises mit Radius 2.0 ist 12.56"))
  }

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
class ApplicationControllerSpecs2 extends PlaySpecification