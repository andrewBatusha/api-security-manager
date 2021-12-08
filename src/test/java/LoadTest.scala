import com.example.apisecuritymanager.Simulation.userExpScen
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class LoadTest extends Simulation {
  val httpConf = http.baseUrl("http://localhost:8086")
   .acceptHeader("application/json, */*")
   .acceptCharsetHeader("UTF-8")

  setUp(
    userExpScen.inject(
      atOnceUsers(2000)
  ).protocols(httpConf)
  )
}