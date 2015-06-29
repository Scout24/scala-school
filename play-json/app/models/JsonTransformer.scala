package models

import play.api.libs.json._

/**
 * Conversion utility class for converting one Json to another. Transformation rules:
 *
 * JsObject :
        JsString  - reverse it
        JsNumber  - sequare it
        JsBoolean - flip it
        JsNull    - do nothing to it
        JsArray   - recursively apply the transformation to elements in this JsArray
        JSObject  - recursively apply the transformation to elements in this JsObject
 */


class JsonTransformer {

  def transform(json: JsValue): JsValue = {
    json match {
      case JsObject(jsPairs) => JsObject(jsPairs.map(element => (element._1, transform(element._2))))
      case JsArray(jsList) => JsArray(jsList.map(element => transform(Json.toJson(element))))
      case JsNumber(number) => JsNumber(number * number)
      case JsString(str) => JsString(str.reverse)
      case JsBoolean(bool) => JsBoolean(!bool)
      case JsNull => JsNull
    }
  }
}

