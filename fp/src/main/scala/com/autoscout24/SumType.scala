package com.autoscout24

sealed trait Vehicle {
  val serialNumber: String
}

final case class Truck(serialNumber: String) extends Vehicle
final case class Bus(serialNumber: String) extends Vehicle

object SumType extends App {

  val vehicle: Vehicle = Bus("00X")
  vehicle match {
    case Bus(_) =>
    case Truck(_) =>
  }

}
