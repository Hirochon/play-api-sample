//package models
//
//import models.gen.Model.TodoRow
//import play.api.libs.json.{Json, Reads, Writes}
//
//case class SeqTodoRow(todos: Seq[TodoRow])
//object SeqTodoRow {
//  implicit val todoWrites: Writes[SeqTodoRow] = Json.writes[SeqTodoRow]
//  implicit val todoReads: Reads[SeqTodoRow] = Json.reads[SeqTodoRow]
//}
