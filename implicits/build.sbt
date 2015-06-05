name := "scala-implicits"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

//scalacOptions += "-Xprint:typer"
