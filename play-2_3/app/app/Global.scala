package app

import play.api.{Application, GlobalSettings}

object Global extends GlobalSettings {

  override def onStart(app: Application) = {
    Thing.start()
  }

  override def onStop(app: Application) = {
    Thing.stop()
  }

}
