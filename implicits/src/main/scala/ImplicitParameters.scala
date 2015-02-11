object MathConstants {
  implicit val PI = 3.1415901244
}

object ImplicitsParameters extends App {

  import MathConstants.PI

  def calculateCircumference(radius: Double)(implicit PI: Double) = 2*PI*radius

  val c = calculateCircumference(2.0)

  println(c)

}
