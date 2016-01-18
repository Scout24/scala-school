package com.autoscout24
import java.util.Date

trait Json {
  val map: Map[String, String]
}

// 1. type class
trait JsonWriter[A] {
  def write(value: A): Json
}

object DefaultJsonWriters {
  // 2. type class instances
  implicit val stringJsonWriter = new JsonWriter[String] {
    override def write(value: String): Json = new Json{
      val map = Map("str" -> value)
    }
  }

  implicit val dateJsonWriter = new JsonWriter[Date] {
    override def write(value: Date): Json = new Json{
      val map = Map("date" -> value.toString)
    }
  }

  // 3. type class instance from our domain
  implicit val carJsonWriter = new JsonWriter[Contact] {
    override def write(value: Contact): Json = new Json {
      val map = Map("id" -> value.id, "phoneNumbers" -> value.phoneNumbers.mkString(",/n"))
    }
  }
}

// 3. interfaces

// 3.1 Interface Objects

object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]): Json = {
    writer.write(value)
  }
}

// 3.2 Interface Syntax
// using type enrichment

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit writer: JsonWriter[A]): Json = {
      writer.write(value)
    }
  }
}

object TypeClassesTest extends App {
  import DefaultJsonWriters._
  import JsonSyntax._

  val strJson = "blah".toJson
  assert(strJson.map.get("str") == Some("blah"))

  val contact: Contact = Dealer("00X", Seq.empty)
  val contactJson = contact.toJson
  assert(contactJson.map.get("id") == Some("00X"))
  assert(contactJson.map.get("phoneNumbers") == Some(""))
}
