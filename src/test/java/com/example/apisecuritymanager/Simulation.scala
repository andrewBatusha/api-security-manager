package com.example.apisecuritymanager

import com.example.apisecuritymanager.Request.{getPass, signIn, signUp}
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder

object Simulation {
  def userExpScen: ScenarioBuilder = scenario("user experience scenario")
    .exec(signUp())
    .exec(signIn())
    .exec(getPass())
}
