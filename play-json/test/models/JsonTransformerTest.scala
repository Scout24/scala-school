package models

import org.specs2.mutable._
import play.api.libs.json._

class JsonTransformerTest extends Specification {

  "The Json Transformer" should {

    val jsonTransformer= new JsonTransformer()

    "square JsNumbers" in {
      jsonTransformer.transform(JsNumber(5)) === JsNumber(25)
    }

    "reverse JsStrings" in {
      jsonTransformer.transform(JsString("bottle")) === JsString("elttob")
    }

    "flip JsBooleans" in {
      jsonTransformer.transform(JsBoolean(true)) === JsBoolean(false)
    }

    "do nothing to a JsNull" in {
      jsonTransformer.transform(JsNull) === JsNull
    }

    "recursively walk through a JsArray and perform all transformations" in {
      jsonTransformer.transform(JsArray(Seq(JsString("apples"), JsString("berries"), JsNumber(12)))) ===
      JsArray(Seq(JsString("selppa"), JsString("seirreb"), JsNumber(144)))
    }

    "recursively walk through a JsObject and apply the transformation while preserving the key value structure - 1" in {
      val input: JsObject = JsObject(
        Seq(("fruits", JsArray(Seq(JsString("apples"), JsString("berries"), JsNumber(12))
            )
          )
        )
      )

      val expectedTransformation: JsObject = JsObject(Seq(("fruits", JsArray(Seq(JsString("selppa"), JsString("seirreb"), JsNumber(144))))))

      jsonTransformer.transform(input) === expectedTransformation
    }

    "recursively walk through a JsObject and apply the transformation while preserving the key value structure - 2" in {
      val input = """{
                    |  "name" : "Watership Down",
                    |  "location" : {
                    |    "lat" : 51.235685,
                    |    "long" : -1.309197
                    |  },
                    |  "residents" : [ {
                    |    "name" : "Fiver",
                    |    "age" : 4,
                    |    "role" : null
                    |  }, {
                    |    "name" : "Bigwig",
                    |    "age" : 6,
                    |    "role" : "Owsla"
                    |  } ]
                    |}""".stripMargin

      val expectedOutput =
                  """
                    |{
                    |   "name":"nwoD pihsretaW",
                    |   "location":{
                    |      "lat":2625.095417419225,
                    |      "long":1.713996784809
                    |       },
                    |   "residents": [ {
                    |     "name":"reviF",
                    |     "age":16,
                    |     "role":null
                    |   },{
                    |     "name":"giwgiB",
                    |     "age":36,
                    |     "role":"alswO"
                    |   }]
                    |}""".stripMargin

      jsonTransformer.transform(Json.parse(input)) === Json.parse(expectedOutput)
    }

  }

}
