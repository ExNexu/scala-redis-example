name := "Scala Redis Example"

organization := "us.bleibinha"

version := "0.0.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test"
)

initialCommands := "import us.bleibinha.scalaredisexample._"

