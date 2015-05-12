
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

/* Exercise 1-a: Create a new SettingKey and SettingValue */

//lazy val helloSetting: SettingKey[String] = ???

//helloSetting := ???

/* Exercise 1-b: Create a new TaskKey and TaskValue */

//lazy val helloTask: TaskKey[Unit] = ???

//helloTask := ???

/* Exercise 2-a: Create a new Test configuration that runs only the Unit tests */

lazy val UTest = config("unit") extend (Test)

/* Exercise 2-b: Create filters that detect Unit tests and Integration test files */

def itFilter(name: String): Boolean = ???
def unitFilter(name: String): Boolean = ???

/* Exercise 2-c: Add configuration to project and apply filters to the testOptions */

lazy val tatsuService: Project = project
  .in(file("."))
  .configs(UTest)
  .enablePlugins(PlayScala)
  .settings(inConfig(UTest)(Defaults.testTasks): _*)
  .settings(
    cleanFiles <+= baseDirectory { base => base / "logs" },
    testOptions in UTest := Seq(Tests.Filter(???))
  )