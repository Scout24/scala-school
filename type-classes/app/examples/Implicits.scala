package examples

/**
  * Created by matlloyd on 29/02/2016.
  */
object Implicits extends App {

  {
    implicit val intX: Int = 5

    def implicitExample(n: Int)(implicit x: Int) = {
      n + x
    }

    implicitExample(5) // 10
  }

  {
    import scala.language.implicitConversions

    implicit def intToString(n: Int): String = n.toString

    def needsString (s: String) = "I see: " + s

    needsString(6)
  }

}
