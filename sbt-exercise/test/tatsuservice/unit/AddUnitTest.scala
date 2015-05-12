package tatsuservice.unit

import tatsuservice.diagnostics.Calculator
import tatsuservice.testutils.PlaySpecWrapper


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class AddUnitTest extends PlaySpecWrapper {

  "Add should compute the addition of two numbers" in {
    val calculator = new Calculator()
    calculator.add(1,3) mustEqual 4
  }

}