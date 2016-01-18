package com.autoscout24

final case class PhoneNumber(countryCode: String, number: Int)

sealed trait Contact {
  val id: String
  val phoneNumbers: Seq[PhoneNumber]
}

final case class PrivateSeller(id: String, phoneNumbers: Seq[PhoneNumber]) extends Contact
final case class Dealer(id: String, phoneNumbers: Seq[PhoneNumber]) extends Contact

object Types extends App {
  val contact: Contact = Dealer("00X", Seq.empty)

  contact match {
    case PrivateSeller(_, _) =>
    // case Dealer(_, _) =>
  }
}
