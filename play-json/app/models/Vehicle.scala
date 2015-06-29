package models

import com.fasterxml.jackson.annotation.JsonValue
import play.api.libs.json._

case class Address(street: String, no: Int, zip: Int)
object Address {
  implicit val addressFormat: Format[Address] = Json.format[Address]
}

class Vehicle(val id: Int, val make: String, val model: String, val registAdd: Address)

object Vehicle {
  var testVehicles = List[Vehicle](
    new Vehicle(0, "Alfa", "Mito", Address("Fraunhofer Str", 9, 81543)),
    new Vehicle(1, "Fiat", "Panda", Address("Gradestr", 20, 12347)),
    new Vehicle(2, "Audi", "A4", Address("Dingolfinger Str", 9, 81673))
  )

  implicit val vehicleWrites = new Writes[Vehicle] {
    import Address._
    def writes(v: Vehicle) = JsObject(Seq(
      ("id", JsNumber(v.id)),
      ("make", JsString(v.make)),
      ("model", JsString(v.model)),
      ("registAdd", Json.toJson(v.registAdd))
    ))
  }

  implicit val vehicleReads = new Reads[Vehicle] {
    import Address._
    def reads(json: JsValue) = JsSuccess(new Vehicle(
      (json \ "id").as[Int],
      (json \ "make").as[String],
      (json \ "model").as[String],
      (json \ "registAdd").as[Address]
    ))
  }
}