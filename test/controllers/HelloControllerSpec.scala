package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Application
import play.api.test._
import play.api.test.Helpers._

class HelloControllerSpec extends PlaySpec with GuiceFakeApplicationFactory {

  val app: Application = fakeApplication()

  "HelloController GET" must {
    "「/hello」にGETメソッドでアクセスできる" in {
      val request = FakeRequest(GET, "/hello")
      val response = route(app, request).get

      status(response) mustBe OK
    }
  }
}
