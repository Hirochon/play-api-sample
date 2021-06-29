addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.8")
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.11.0")

resolvers += "Flyway" at "https://flywaydb.org/repo"
addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "7.4.0")