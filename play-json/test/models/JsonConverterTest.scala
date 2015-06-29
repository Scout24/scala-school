package models

import org.specs2.mutable._
import play.api.libs.json._

class JsonConverterTest extends Specification {

  "The Json Transformer" should {

    val jsonTransformer= new JsonTransformer()

    "square JsInts" in {
      jsonTransformer.transform(JsNumber(5)) === JsNumber(25)
    }

    "reverse JsStrings" in {
      jsonTransformer.transform(JsString("bottle")) === JsString("elttob")
    }

    "reverse JsStrings" in {
      jsonTransformer.transform(JsBoolean(true)) === JsBoolean(false)
    }

    "do nothing to a JsNull" in {
      jsonTransformer.transform(JsNull) === JsNull
    }

    "recursively walk throught a JsArray and performs transformations" in {
      jsonTransformer.transform(JsArray(Seq(JsString("apples"), JsString("berries"), JsNumber(12)))) ===
      JsArray(Seq(JsString("selppa"), JsString("seirreb"), JsNumber(144)))
    }

    "recursively walk throught a JsObject and apply transformation while preserving the key value structure" in {
      jsonTransformer.transform(JsObject(Seq(("fruits" , JsArray(Seq(JsString("apples"), JsString("berries"), JsNumber(12))))))) ===
        JsObject(Seq(("fruits" , JsArray(Seq(JsString("selppa"), JsString("seirreb"), JsNumber(144))))))
    }



  }

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
