name := "Scala Redis Example"

organization := "us.bleibinha"

version := "0.0.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scala-lang" %% "scala-pickling" % "0.8.0",
  "com.etaty.rediscala" %% "rediscala" % "1.3.1",
  "org.scalatest" %% "scalatest" % "2.2.0" % "test"
)

initialCommands := "import us.bleibinha.scalaredisexample._"

