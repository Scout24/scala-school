package traits

import org.scalatest.{FlatSpec, MustMatchers}


/* Alternative Exercise - Traits Part 1 */
class NameTests extends FlatSpec with MustMatchers {

  val odersky = new Name("Martin Odersky")
  val kennedy = new Name("John Fitzgerald Kennedy")
  val tolkien = new Name("John Ronald Reuel Tolkien")

  "toString" should "print the full name" in {
    assert(tolkien.toString === tolkien.name)
  }

  "name" should "print the full name" in {
    assert(odersky.name === "Martin Odersky")
    assert(tolkien.name === "John Ronald Reuel Tolkien")
  }

  "initials" should "initial all names" in {
    assert(odersky.initials === "M.O.")
    assert(kennedy.initials === "J.F.K.")
    assert(tolkien.initials === "J.R.R.T.")
  }

  "firstInitials" should "initial all but the last name" in {
    assert(odersky.firstInitials === "M. Odersky")
    assert(kennedy.firstInitials === "J.F. Kennedy")
    assert(tolkien.firstInitials === "J.R.R. Tolkien")
  }

  "middleInitials" should "initial only middle names" in {
    assert(odersky.middleInitials === "Martin Odersky")
    assert(kennedy.middleInitials === "John F. Kennedy")
    assert(tolkien.middleInitials === "John R.R. Tolkien")
  }

}