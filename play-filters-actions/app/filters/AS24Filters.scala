package filters

import javax.inject.Inject

import play.api.http.HttpFilters

  class AS24Filters @Inject() (
    timingFilter: TimingFilter,
    authFilter: HTTPBasicAuthFilter
  ) extends HttpFilters {
    val filters = Seq(timingFilter, authFilter)
  }

