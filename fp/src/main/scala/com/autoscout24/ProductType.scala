package com.autoscout24

final case class ClutchPedal(serialNumber: String)
final case class GasPedal(serialNumber: String)

final case class Car(clutchPedal: ClutchPedal, gasPedal: GasPedal)

object ProductType {

  Car(
    ClutchPedal("000"),
    GasPedal("001")
  )

}
