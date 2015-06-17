package controllers

import global.Global
import play.api.mvc.{Action, Controller}

class Diagnostics extends Controller {

  def heartbeatCheck = Action(Ok("Ok"))

  def exceptionCheck = Action {
    throw new RuntimeException("Simulated RuntimeException")
    Ok("This code is not reachable but necessary to compile")
  }

  def version = Action {
    Ok(Global.version)
  }
}