object TrySolutions {

  // Examples inspired by http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html

  import java.io.{InputStream, FileNotFoundException}
  import java.net.{URLConnection, MalformedURLException, URL}
  import scala.util.{Failure, Success, Try}
  import scala.io.{BufferedSource, Source}


  object TryCatch {
    def parseURL(urlarg: String) = {
      try {
        new URL(urlarg)
      } catch {
        case m: MalformedURLException => throw m
      }
    }

    def getURLContent(urlarg: String): Iterator[String] = {
      try {
        val url = parseURL(urlarg)
        val connection = url.openConnection()
        val is = connection.getInputStream
        Source.fromInputStream(is).getLines()
      } catch {
        case e: FileNotFoundException => println("Requested page does not exist"); null
        case e: MalformedURLException => println("Please make sure to enter a valid URL"); null
        case _: Throwable => println("An unexpected error has occurred. We are so sorry!"); null
      }
    }

  }

  object TryMatch {

    def parseURL(url: String): Try[URL] = Try(new URL(url))

    def getURLContent(urlarg: String): Try[Iterator[String]] = {
      val maybeUrl = parseURL(urlarg)
      val url = maybeUrl match {
        case Success(url) => url
        case Failure(ex) => println("Please make sure to enter a valid URL"); null
      }
      val maybeConn = Try(url.openConnection())
      val connection: URLConnection = maybeConn match {
        case Success(connection) => connection
        case Failure(ex) => println("An unexpected error has occurred. We are so sorry!"); null
      }
      val maybeIS = Try(connection.getInputStream)
      val is: InputStream = maybeIS match {
        case Success(is) => is
        case Failure(ex) => println("An unexpected error has occurred. We are so sorry!"); null
      }

      val maybeSource = Try(Source.fromInputStream(is))
      maybeSource match {
        case Success(source) => Try(source.getLines())
        case Failure(ex) => println("An unexpected error has occurred. We are so sorry!"); null
      }
    }
  }

  object TryMap {

    def parseURL(url: String): Try[URL] = Try(new URL(url))

    def getURLContent(urlarg: String): Try[Iterator[String]] = {
      parseURL(urlarg)
        .map(_.openConnection())
        .map(_.getInputStream)
        .map(Source.fromInputStream)
        .map(_.getLines())
    }
  }

  object TryFlatMap {

    def parseURL(url: String): Try[URL] = Try(new URL(url))

    def getURLContent(urlarg: String): Try[Iterator[String]] = {

      val url = parseURL(urlarg)
      val connection = url.flatMap(u => Try(u.openConnection()))
      val is = connection.flatMap(conn => Try(conn.getInputStream))
      val source = is.map(i => Source.fromInputStream(i))
      source.map(_.getLines())
    }

    def getURLContent2(urlarg: String): Try[Iterator[String]] = {

      parseURL(urlarg).flatMap( url =>
        Try(url.openConnection()) flatMap ( connection =>
          Try(connection.getInputStream) flatMap (is =>
            Try(Source.fromInputStream(is)) map ( source =>
              source.getLines()))))

    }
  }

  object TryFor {

    def parseURL(url: String): Try[URL] = Try(new URL(url))

    def getURLContent(url: String): Try[Iterator[String]] =
      for {
        url <- parseURL(url)
        connection <- Try(url.openConnection())
        is <- Try(connection.getInputStream)
        source <- Try(Source.fromInputStream(is))
      } yield source.getLines()

  }



}
