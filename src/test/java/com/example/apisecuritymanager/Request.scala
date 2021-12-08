package com.example.apisecuritymanager

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.util.UUID

object Request {

  val csvFeeder = csv("uuids.csv").circular

  def signUp(): ChainBuilder = {
    feed(csvFeeder)
      .exec(
        http("signUp")
          .post("/api/auth/sign-up")
          .body(StringBody(
            """
               |{
               |    "email": "${uuid}@gmail.com",
               |    "password": "password"
               |}""".stripMargin
          ))
          .header("content-type", "application/json")
          .check(status.is(200))
      )
  }

  def signIn(): ChainBuilder = {
    feed(csvFeeder)
      .exec(
        http("signIn")
          .post("/api/auth/sign-in")
          .body(StringBody(
            """
               |{
               |    "email": "${uuid}@gmail.com",
               |    "password": "password"
               |}""".stripMargin
          ))
          .header("content-type", "application/json")
          .check(status.is(200))
          .check(jsonPath("$.jwt").saveAs("jwt"))
      )
  }

  def getPass(): ChainBuilder = {
    exec(
      http("pass")
        .get("/api/auth/pass")
        .header("Authorization", "Bearer ${jwt}")
        .check(status.is(200))
    )
  }
}
