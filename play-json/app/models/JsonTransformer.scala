package models

import play.api.libs.json._

/**
 * Conversion utility class for converting one Json to another. Transformation rules:
 *
 * JsObject :
        JsString  - reverse it
        JsNumber  - square it
        JsBoolean - flip it
        JsNull    - do nothing to it
        JsArray   - recursively apply the transformation to elements in this JsArray
        JSObject  - recursively apply the transformation to elements in this JsObject
 */


class JsonTransformer {

  def transform(json: JsValue): JsValue = {
    json match {
      case JsNumber(number) => ???
      case JsString(str) => ???
      case JsBoolean(bool) => ???
      case JsNull => ???
      case JsArray(jsList) => ???
      case JsObject(jsPairs) => ???
    }
  }
}

