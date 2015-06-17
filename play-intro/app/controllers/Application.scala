package controllers

import play.api._
import play.api.mvc._
import play.twirl.api.Html
import playIntro.calculation.Calculator

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
