package models

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.libs.json.Json

@RunWith(classOf[JUnitRunner])
class VehicleSpec extends Specification {

  val testAddress = Address("Fraunhofer Str", 9, 81543)
  val testVehicle = new Vehicle(0, "Alfa", "Mito", testAddress)

  "Address" should {

    "be convertable to json" in {
      Json.toJson(testAddress).toString() mustEqual "{\"street\":\"Fraunhofer Str\",\"no\":9,\"zip\":81543}"
    }

    "be creatable from json" in {
      Json.parse("{\"street\":\"Fraunhofer Str\",\"no\":9,\"zip\":81543}")
        .as[Address] mustEqual testAddress
    }
  }

  "Vehicle" should {

    "be convertable to json" in {
      Json.toJson(testVehicle).toString() mustEqual "{\"id\":0,\"make\":\"Alfa\",\"model\":\"Mito\",\"registAdd\":{\"street\":\"Fraunhofer Str\",\"no\":9,\"zip\":81543}}"
    }

    "be creatable from json" in {
      Json.parse("{\"id\":0,\"make\":\"Alfa\",\"model\":\"Mito\",\"registAdd\":{\"street\":\"Fraunhofer Str\",\"no\":9,\"zip\":81543}}")
        .as[Vehicle].registAdd mustEqual testVehicle.registAdd
    }
  }
}