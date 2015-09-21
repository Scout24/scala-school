package component

import play.api.test.FakeRequest
import testutils._

class ApplicationControllerFunSuite extends FunSuiteWrapper {

  test("root path should return 200") {
    val Some(response) = route(FakeRequest(GET, "/"))
    assert(status(response) === OK)
    assert(contentAsString(response).contains("Your new application is ready."))
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