import PatternMatching.{PremiumUser, FreeUser, User}
import org.scalatest._

class PatternMatchingTests extends FlatSpec with MustMatchers {
  "A personlized greeting" should "be returned for a user" in {
    val freeUser: User = new FreeUser("Tom", 500, 0.1d)
    val potentialPremiumUser: User = new FreeUser("John", 1000, 0.8d)
    val premiumUser: User = new PremiumUser("Brian", 3000)

    PatternMatching.personalizedGreeting(freeUser) must === ("Hello Tom")
    PatternMatching.personalizedGreeting(potentialPremiumUser) must === ("John, what can we do for you today?")
    PatternMatching.personalizedGreeting(premiumUser) must === ("Welcome back, dear Brian")
  }
}
