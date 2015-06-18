package controllers

import models.Calculator
import play.api._
import play.api.mvc._
import play.twirl.api.Html
import views.html.resultPage

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  //TODO add your controller methods here
}
