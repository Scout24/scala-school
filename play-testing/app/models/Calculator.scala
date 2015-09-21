package models

object Calculator {
  private val PI = 3.14159265359
  def add(a: Double, b: Double) = a + b
  def circumference(radius: Double) = 2.0 * PI * radius

  def sequence(i: Int) = {
    Seq(i, 2*i, 3*i, 4*i, 5*i)
  }
}
