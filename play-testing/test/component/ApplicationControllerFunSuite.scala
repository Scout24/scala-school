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

  test("circumferencePage should be in English by default") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("<title>Your circumference is...</title>"))
  }

  test("circumferencePage should return the circumference of a circle with radius r in English by default") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("The circumference of a circle with radius 2.0 is 12.56"))
  }

  test("circumferencePage should be translated into German") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=de"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("<title>Ihr Umfang ist...</title>"))
  }

  test("circumferencePage should return the circumference of a circle with radius r in German") {
    val radius = 2.0
    val Some(response) = route(FakeRequest(GET, s"/circumference-page/$radius?language=de"))
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