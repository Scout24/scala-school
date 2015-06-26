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
  def process(input: JsValue) : JsValue = {
    input match {
      case JsObject(value) => process(value)
      case JsNumber(value) => Json.toJson(value * value)
      case _ => input
    }
  }
}
