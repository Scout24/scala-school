
organization := "com.autoscout24"

name := "tatsu-service"

val tatsuServiceVersion = Option(System.getenv("GO_PIPELINE_LABEL")).getOrElse("1.0-SNAPSHOT")

version := tatsuServiceVersion
scalaVersion in ThisBuild := "2.11.6"
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.0.0-beta4",
  "org.scalatestplus" %% "play" % "1.2.0" % "test",
  ws
)

lazy val tatsuService: Project = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
    cleanFiles <+= baseDirectory { base => base / "logs" }
  )