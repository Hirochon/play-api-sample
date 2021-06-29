package controllers

import play.api.libs.json.{JsValue, Json}

import javax.inject.Inject
import play.api.mvc._
import slick.jdbc.MySQLProfile.api._
import models.gen.Model._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class HelloController @Inject() (val controllerComponents: ControllerComponents) extends BaseController {
  def fetchAll: Action[AnyContent] = Action {
    Ok
  }

  def postNone: Action[AnyContent] = Action {
    Ok(Json.obj())
  }

  def postString: Action[JsValue] = Action(parse.json) { request: Request[JsValue] =>
    val res = request.body
    val todo = (res \ "todo").asOpt[String]
    val status = (res \ "status").asOpt[Int]

    val todoTable = TableQuery[Todo]
    val db = Database.forConfig(path="db")

    val setup = DBIO.seq(
      todoTable += TodoRow(0, todo, status)
    )
    val setupFuture = db.run(setup)
    Await.result(setupFuture, Duration.Inf)

    Ok("Got: " + res.toString())
  }

}
