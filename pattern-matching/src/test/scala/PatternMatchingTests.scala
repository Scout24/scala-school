import PatternMatching._
import org.scalatest._

class PatternMatchingTests extends FlatSpec with MustMatchers {
  "Personlized greeting" should "work with user case classes" in {
    val freeUser: User = new FreeUserCC("Tom", 500, 0.1d)
    val potentialPremiumUser: User = new FreeUserCC("John", 1000, 0.8d)
    val premiumUser: User = new PremiumUserCC("Brian", 3000)

    personalizedGreeting(freeUser) must === ("Hello Tom")
    personalizedGreeting(potentialPremiumUser) must === ("John, what can we do for you today?")
    personalizedGreeting(premiumUser) must === ("Welcome back, dear Brian")
  }

  "Personlized greeting" should "work with user non-case classes" in {
    val freeUser: User = new FreeUser("Tom", 500, 0.1d)
    val potentialPremiumUser: User = new FreeUser("John", 1000, 0.8d)
    val premiumUser: User = new PremiumUser("Brian", 3000)

    personalizedGreeting(freeUser) must === ("Hello Tom")
    personalizedGreeting(potentialPremiumUser) must === ("John, what can we do for you today?")
    personalizedGreeting(premiumUser) must === ("Welcome back, dear Brian")
  }
}
