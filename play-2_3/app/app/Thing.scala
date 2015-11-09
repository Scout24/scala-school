package app

import play.api.Play
import play.api.Play.current

object Thing {

  val name = Play.configuration.underlying.getString("thing.name")

  def start(): Unit = {
    println(s"Start up $name")
  }

  def stop(): Unit = {
    println(s"Stop the $name")
  }
}
