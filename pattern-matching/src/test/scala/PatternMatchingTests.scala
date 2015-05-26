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

  "Free premium for third user" should
    "return a win message for the third user if he/she is a free user with a low upgrade probability" in {
    val freeUser: User = new FreeUserCC("Tom", 500, 0.1d)
    val potentialPremiumUser: User = new FreeUserCC("John", 1000, 0.8d)
    val premiumUser: User = new PremiumUserCC("Brian", 3000)

    freePremiumForThirdUser(premiumUser :: potentialPremiumUser :: freeUser :: Nil) must
      === (Some("Congratulations, Tom, you won a free premium membership!"))

    freePremiumForThirdUser(Nil) must === (None)
    freePremiumForThirdUser(freeUser :: potentialPremiumUser :: premiumUser :: Nil) must === (None)
    freePremiumForThirdUser(freeUser :: premiumUser :: potentialPremiumUser :: Nil) must === (None)
  }
}
