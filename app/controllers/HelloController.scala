package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.libs.json._

import slick.jdbc.MySQLProfile.api._

//import models.gen.Model.{Todo, TodoRow}
import models.gen.Model._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class HelloController @Inject() (val controllerComponents: ControllerComponents) extends BaseController {
  val todoTable = TableQuery[Todo]
  val db = Database.forConfig(path="db")

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

    val setup = DBIO.seq(
      todoTable += TodoRow(0, todo, status)
    )
    val setupFuture = db.run(setup)
    Await.result(setupFuture, Duration.Inf)

    Ok("Got: " + res.toString())
  }

  def getTodo: Action[AnyContent] = Action {
    case class TodoWriter(id: Int, todo: Option[String], status: Option[Int])
    object TodoWriter {
      implicit val todoWrites: Writes[TodoWriter] = Json.writes[TodoWriter]
    }
//    case class SeqTodoRow(todos: Seq[TodoRow])
//    object SeqTodoRow {
//      implicit val seqTodoWrites: Writes[SeqTodoRow] = Json.writes[SeqTodoRow]
//    }

    val tmpTodoAction = db.run(todoTable.result)
    val todoAction = Await.result(tmpTodoAction, Duration.Inf)
//    val seqTodos = SeqTodoRow(
//      todos = todoAction.map(action =>
//        TodoRow(
//          id = action.id,
//          todo = action.todo,
//          status = action.status
//        )
//      )
//    )

    val todo = todoAction.map(action =>
      TodoWriter(
        id = action.id,
        todo = action.todo,
        status = action.status
      )
    ).head

    val todoJson = Json.toJson(todo)
    Ok(todoJson)
  }
}