package models

import play.api.libs.json.{Json, Writes}

trait TodoSerializer {
  case class TodoWriter(id: Int, todo: Option[String], status: Option[Int])
  case class SeqTodoRow(todos: Seq[TodoWriter])

  implicit val todoWrites: Writes[TodoWriter] = Json.writes[TodoWriter]
  implicit val seqTodoWrites: Writes[SeqTodoRow] = Json.writes[SeqTodoRow]
}