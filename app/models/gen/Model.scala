package models.gen

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Model extends {
  val profile = slick.jdbc.MySQLProfile
} with Model

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Model {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = FlywaySchemaHistory.schema ++ Todo.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table FlywaySchemaHistory
   *  @param installedRank Database column installed_rank SqlType(INT), PrimaryKey
   *  @param version Database column version SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param description Database column description SqlType(VARCHAR), Length(200,true)
   *  @param `type` Database column type SqlType(VARCHAR), Length(20,true)
   *  @param script Database column script SqlType(VARCHAR), Length(1000,true)
   *  @param checksum Database column checksum SqlType(INT), Default(None)
   *  @param installedBy Database column installed_by SqlType(VARCHAR), Length(100,true)
   *  @param installedOn Database column installed_on SqlType(TIMESTAMP)
   *  @param executionTime Database column execution_time SqlType(INT)
   *  @param success Database column success SqlType(BIT) */
  case class FlywaySchemaHistoryRow(installedRank: Int, version: Option[String] = None, description: String, `type`: String, script: String, checksum: Option[Int] = None, installedBy: String, installedOn: String, executionTime: Int, success: Boolean)
  /** GetResult implicit for fetching FlywaySchemaHistoryRow objects using plain SQL queries */
  implicit def GetResultFlywaySchemaHistoryRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String], e3: GR[Option[Int]], e4: GR[Boolean]): GR[FlywaySchemaHistoryRow] = GR{
    prs => import prs._
    FlywaySchemaHistoryRow.tupled((<<[Int], <<?[String], <<[String], <<[String], <<[String], <<?[Int], <<[String], <<[String], <<[Int], <<[Boolean]))
  }
  /** Table description of table flyway_schema_history. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class FlywaySchemaHistory(_tableTag: Tag) extends profile.api.Table[FlywaySchemaHistoryRow](_tableTag, Some("play_api_sample_db"), "flyway_schema_history") {
    def * = (installedRank, version, description, `type`, script, checksum, installedBy, installedOn, executionTime, success) <> (FlywaySchemaHistoryRow.tupled, FlywaySchemaHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(installedRank), version, Rep.Some(description), Rep.Some(`type`), Rep.Some(script), checksum, Rep.Some(installedBy), Rep.Some(installedOn), Rep.Some(executionTime), Rep.Some(success))).shaped.<>({r=>import r._; _1.map(_=> FlywaySchemaHistoryRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column installed_rank SqlType(INT), PrimaryKey */
    val installedRank: Rep[Int] = column[Int]("installed_rank", O.PrimaryKey)
    /** Database column version SqlType(VARCHAR), Length(50,true), Default(None) */
    val version: Rep[Option[String]] = column[Option[String]]("version", O.Length(50,varying=true), O.Default(None))
    /** Database column description SqlType(VARCHAR), Length(200,true) */
    val description: Rep[String] = column[String]("description", O.Length(200,varying=true))
    /** Database column type SqlType(VARCHAR), Length(20,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("type", O.Length(20,varying=true))
    /** Database column script SqlType(VARCHAR), Length(1000,true) */
    val script: Rep[String] = column[String]("script", O.Length(1000,varying=true))
    /** Database column checksum SqlType(INT), Default(None) */
    val checksum: Rep[Option[Int]] = column[Option[Int]]("checksum", O.Default(None))
    /** Database column installed_by SqlType(VARCHAR), Length(100,true) */
    val installedBy: Rep[String] = column[String]("installed_by", O.Length(100,varying=true))
    /** Database column installed_on SqlType(TIMESTAMP) */
    val installedOn: Rep[String] = column[String]("installed_on")
    /** Database column execution_time SqlType(INT) */
    val executionTime: Rep[Int] = column[Int]("execution_time")
    /** Database column success SqlType(BIT) */
    val success: Rep[Boolean] = column[Boolean]("success")

    /** Index over (success) (database name flyway_schema_history_s_idx) */
    val index1 = index("flyway_schema_history_s_idx", success)
  }
  /** Collection-like TableQuery object for table FlywaySchemaHistory */
  lazy val FlywaySchemaHistory = new TableQuery(tag => new FlywaySchemaHistory(tag))

  /** Entity class storing rows of table Todo
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param todo Database column todo SqlType(VARCHAR), Length(256,true), Default(None)
   *  @param status Database column status SqlType(INT), Default(None) */
  case class TodoRow(id: Int, todo: Option[String] = None, status: Option[Int] = None)
  /** GetResult implicit for fetching TodoRow objects using plain SQL queries */
  implicit def GetResultTodoRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[TodoRow] = GR{
    prs => import prs._
    TodoRow.tupled((<<[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table todo. Objects of this class serve as prototypes for rows in queries. */
  class Todo(_tableTag: Tag) extends profile.api.Table[TodoRow](_tableTag, Some("play_api_sample_db"), "todo") {
    def * = (id, todo, status) <> (TodoRow.tupled, TodoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), todo, status)).shaped.<>({r=>import r._; _1.map(_=> TodoRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column todo SqlType(VARCHAR), Length(256,true), Default(None) */
    val todo: Rep[Option[String]] = column[Option[String]]("todo", O.Length(256,varying=true), O.Default(None))
    /** Database column status SqlType(INT), Default(None) */
    val status: Rep[Option[Int]] = column[Option[Int]]("status", O.Default(None))
  }
  /** Collection-like TableQuery object for table Todo */
  lazy val Todo = new TableQuery(tag => new Todo(tag))
}
