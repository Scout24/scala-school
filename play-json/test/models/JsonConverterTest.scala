package models

import org.specs2.mutable._
import play.api.libs.json._

class JsonConverterTest extends Specification {

  "The Json Transformer" should {

    val jsonTransformer= new JsonTransformer()

    "square JsInts" in {
      jsonTransformer.process(number) === numberSquared
    }

  }

  val number: JsValue = Json.parse("""
      {
        "age" : 50
      }
                                   """)

  val numberSquared: JsValue = Json.parse("""
      {
        "age" : 250
      }
                                   """)

  val input: JsValue = Json.parse("""
      {
        "name" : "Watership Down",
        "location" : {
          "lat" : 51.235685,
          "long" : -1.309197
        },
        "residents" : [ {
          "name" : "Fiver",
          "age" : 4,
          "role" : null
        }, {
          "name" : "Bigwig",
          "age" : 6,
          "role" : "Owsla"
        } ]
      }
  """)
}
