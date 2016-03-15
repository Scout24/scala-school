organization := "com.autoscout24"
name := "typeclasses"
version := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.6"
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  ws,
  specs2 % Test
)

lazy val playFiltersActions: Project = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
    cleanFiles <+= baseDirectory { base => base / "logs" }
  )

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator