name := """play-api-sample"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

val scalaTestPlusPlayVersion = "5.1.0"
val MysqlConnectorJavaVersion = "6.0.6"
val SlickVersion = "3.3.3"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusPlayVersion % Test,
  "mysql" % "mysql-connector-java" % MysqlConnectorJavaVersion,
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "com.typesafe.slick" %% "slick-codegen" % SlickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % SlickVersion,
)

enablePlugins(FlywayPlugin)
import com.typesafe.config.ConfigFactory
val DBConfig = ConfigFactory.parseFile(new File("conf/application.conf"))
flywayUser := DBConfig.getString("db.user")
flywayPassword := DBConfig.getString("db.password")
flywayUrl := DBConfig.getString("db.url")
flywayLocations := Seq(
  "filesystem:db/migrations"
)