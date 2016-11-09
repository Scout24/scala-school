package forcomp

import org.scalatest.{MustMatchers, WordSpec}

class ConferenceSpec extends WordSpec with MustMatchers {

  import Conference._

  "Track.talksOn" should {
    "be all talks on the given theme in their order" in {
      aws.talksOn("Platforms") mustBe Seq(aws.talks(1), aws.talks(3))
    }

    "yield not talks if none exists" in {
      js.talksOn("Platforms") mustBe Seq.empty
    }
  }

  "Track.talkAt" should {
    "be the talk at the given time" in {
      aws.talkAt(slot1) mustBe Some(aws.talks.head)
    }

    "be none if no talk in this slot exists" in {
      js.talkAt(slot4) mustBe None
    }
  }

  "Track.themes" should {
    "contain all track themes" in {
      aws.themes mustBe Set("Cloud", "Platforms", "Automation", "Scala", "JS")
    }
  }

  "Schedule.talks" should {
    "yield all talks in the schedule" in {
      schedule.talks mustBe (aws.talks ++ scala.talks ++ js.talks).to[Set]
    }

    "yield only all talks in the schedule" in {
      Schedule(Set(aws)).talks mustBe aws.talks.to[Set]
    }
  }

  "Schedule.talksOn" should {
    "return all talks on the given time and theme" in {
      schedule.talksOn(slot2, "Libraries") mustBe Set(scala.talks(1))
    }
  }
}
