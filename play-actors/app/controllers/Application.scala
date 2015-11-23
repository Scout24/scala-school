package controllers

import com.google.inject.Singleton
import play.api.mvc._

//this needs to be a singleton as the greeter actor will be declared when the controller instantiated
@Singleton
class Application extends Controller {

  def index = Action {
    Ok("Your new application is ready.")
  }

}
