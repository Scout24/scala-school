## Exercise Part I: Migrating to Play 2.4
####1a. Pass the implicit messages parameter into the view (app/views/index.scala.html)
```
@()(implicit messages: Messages)
```

####2b. Inject the messagesApi into the Application controller and extend I18nSupport
```
class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport
```

####2. Tell sbt to use the new injected routes generator in build.sbt
```
routesGenerator := InjectedRoutesGenerator
```

####3a. Add specs2 to the libraryDependencies in the build.sbt 
```
libraryDependencies ++= Seq(
  specs2 % Test
)
```
####3b. Tell sbt where to find its dependencies in build.sbt
```
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
```

####4. Fix the test in ApplicationSpec
```
route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
```

####5. Put this in conf/logback.xml
```
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%logger{15} - %message%n</pattern>
        </encoder>
    </appender>

    <logger name="application" level="OFF" />
    <logger name="play" level="OFF" />
    <logger name="akka.event.slf4j.Slf4jLogger" level="OFF" />

    <!-- Off these ones as they are annoying, and anyway we manage configuration ourself -->
    <logger name="com.avaje.ebean.config.PropertyMapLoader" level="OFF" />
    <logger name="com.avaje.ebeaninternal.server.core.XmlConfigLoader" level="OFF" />
    <logger name="com.avaje.ebeaninternal.server.lib.BackgroundThread" level="OFF" />
    <logger name="com.gargoylesoftware.htmlunit.javascript" level="OFF" />

    <root level="ERROR">
        <appender-ref ref="STDOUT" />
    </root>

</configuration>
```

## Exercise Part II: Removing Global Settings
####1a. Create the AppModule class in app/app
```
class AppModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[Something])
      .to(classOf[Thing]).asEagerSingleton
  }
}
```

####1b. Tell the application.conf that we are using an AbstractModule
```
play.modules.enabled += "app.AppModule"
```

####2a. Modify the Thing object to be a class that extends the Something Trait and injects the ApplicationLifecycle
```
class Thing @Inject()(playConfig: play.api.Configuration, lifecycle: ApplicationLifecycle) extends Something
```

####2b. Get the configuration from the playConfig
```
val name = playConfig.underlying.getString("thing.name")
```
####3. Call the start method on instantiation
```
  this.start()
```
####4. Add a stop hook that calls the stop method
```
  lifecycle.addStopHook { () =>
    Future.successful(this.stop())
  }
```
