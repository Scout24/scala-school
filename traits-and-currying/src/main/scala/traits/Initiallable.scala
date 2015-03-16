package traits

/**
 * Initiallable is a trait for translating a sequence of parts
 * into various combinations of initials and expanded words.
 *
 * E.g. "John Fitzgerald Kennedy" could be initialled as either
 * "J.F.K." (initials), "John F. Kennedy" (middle initials) or
 * "J.F. Kennedy" (first initials).
 */
trait Initiallable {

  def initials = toString.toUpperCase()

  def combine = ???

  def firstInitials = ???

  def middleInitials = ???

}
