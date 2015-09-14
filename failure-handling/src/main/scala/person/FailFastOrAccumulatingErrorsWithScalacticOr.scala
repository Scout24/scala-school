package person

object FailFastOrAccumulatingErrorsWithScalacticOr {

  import org.scalactic._
  import Accumulation._

  case class Person(name: String, age: Int)

  def parseName(input: String): String Or One[ErrorMessage] = {
    val trimmed = input.trim
    if (!trimmed.isEmpty) Good(trimmed) else Bad(One(s""""$input" is not a valid name"""))
  }

  def parseAge(input: String): Int Or One[ErrorMessage] = {
    try {
      val age = input.trim.toInt
      if (age >= 0) Good(age) else Bad(One(s""""$age" is not a valid age"""))
    }
    catch {
      case _: NumberFormatException => Bad(One(s""""$input" is not a valid integer"""))
    }
  }

  /**
   * Implement by returning a Person if both name and age are correct (use parseName and parseAge from above), or an error message on the first encountered error
   *
   * Hint: Use for comprehension over the parseAge and parseName method
   */
  def parsePersonFailFast(inputName: String, inputAge: String): Person Or One[ErrorMessage] = ???

  /**
   * Implement by returning a Person if both name and age are correct, or a Bad(One(...)) or Bad(Many(...) in case of error(s)
   *
   * Hint: You can't use a for comprehension to accumulate error messages, since for comprehension is fail fast.
   * Think about using Scalactic's Accumulation.withGood using the outputs from parseName and parseAge.
   */
  def parsePersonAccumulateErrors(inputName: String, inputAge: String): Person Or Every[ErrorMessage] = ???
}
