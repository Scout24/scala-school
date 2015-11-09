name := """play-2_3"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-3"
)

lazy val root = (project in file(".")).addPlugins(PlayScala)

scalaVersion := "2.11.7"