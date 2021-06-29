package models

import com.typesafe.config.{Config, ConfigFactory}
import slick.codegen.SourceCodeGenerator
import slick.jdbc.MySQLProfile
import slick.model.Model
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object TableCodeGenerator extends App {
  class CustomSourceCodeGenerator(model: Model) extends SourceCodeGenerator(model) {
    override def Table = new Table(_) {
      override def Column = new Column(_) {
        override def rawType: String = model.tpe match {
          case "java.sql.Timestamp" => "String"
          case _ => super.rawType
        }
      }
    }
  }

  val config: Config = ConfigFactory.load
  val profile = "slick.jdbc.MySQLProfile"
  val outputFolder = "./app"
  val pkg = "models.gen"
  val container = "Model"
  val fileName = "Model.scala"
  val db = Database.forConfig(path = "db")

  val model = Await.result(db.run(MySQLProfile.createModel(None, ignoreInvalidDefaults = false)(ExecutionContext.global).withPinnedSession), Duration.Inf)

  new CustomSourceCodeGenerator(model).writeToFile(
    profile, outputFolder, pkg, container, fileName
  )
}
