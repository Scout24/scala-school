package testutils

import org.scalatest._
import org.scalatestplus.play.{OneAppPerSuite, OneAppPerTest}
import play.api.http.{Status, HeaderNames}
import play.api.test._

class FunSuiteWrapper extends FunSuite
with Writeables
with RouteInvokers
with PlayRunners
with ResultExtractors
with HeaderNames
with Status
with DefaultAwaitTimeout
with OneAppPerTest

class FlatSpecWrapper extends FlatSpec
with Writeables
with RouteInvokers
with PlayRunners
with ResultExtractors
with HeaderNames
with Status
with DefaultAwaitTimeout
with OneAppPerTest

class WordSpecWrapper extends WordSpec
with MustMatchers
with Writeables
with RouteInvokers
with PlayRunners
with ResultExtractors
with HeaderNames
with Status
with DefaultAwaitTimeout
with OneAppPerSuite

class FunSpecWrapper extends FunSpec
with Writeables
with RouteInvokers
with PlayRunners
with ResultExtractors
with HeaderNames
with Status
with DefaultAwaitTimeout
with OneAppPerTest

class PropSpecWrapper extends PropSpec
with Writeables
with RouteInvokers
with PlayRunners
with ResultExtractors
with HeaderNames
with Status
with DefaultAwaitTimeout
with OneAppPerTest

class FeatureSpecWrapper extends FeatureSpec
with Writeables
with RouteInvokers
with PlayRunners
with ResultExtractors
with HeaderNames
with Status
with DefaultAwaitTimeout
with OneAppPerTest