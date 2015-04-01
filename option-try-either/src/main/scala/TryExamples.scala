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

// ---------------------------------------------------------

object TryMatch {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(urlarg: String): Try[Iterator[String]] = {
    val maybeUrl = parseURL(urlarg)
    val url = maybeUrl match {
      case Success(url) => url
      case Failure(ex) => println("Please make sure to enter a valid URL"); null
    }

    val maybeConn = ???
    val connection: URLConnection = ???

    val maybeIS = ???
    val is: InputStream = ???

    val maybeSource = ???
    ???
  }
}

// ---------------------------------------------------------

object TryMap {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(urlarg: String): Try[Iterator[String]] = {
    parseURL(urlarg)
      .map(???)
      .map(???)
      .map(???)
      .map(???)
  }
}

// ---------------------------------------------------------

object TryFlatMap {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(urlarg: String): Try[Iterator[String]] = {

    parseURL(urlarg).flatMap( url =>
      Try(url.openConnection()) flatMap ( connection =>
        Try(connection.getInputStream) flatMap (is =>
          Try(Source.fromInputStream(is)) map ( source =>
            source.getLines()))))

  }
}

// ---------------------------------------------------------

// hint: '<-' translates to flatMap
object TryFor {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- Try(???)
      //...
      //...
      //...
    } yield ???
}

// ---------------------------------------------------------

object TryRecover {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(url: String): Try[Iterator[String]] =
    (for {
      url <- Try(???)
    //...
    //...
    //...
    } yield ???)
      .recover {
      case e: FileNotFoundException => println("Requested page does not exist"); Iterator("")
      case e: MalformedURLException => println("Please make sure to enter a valid URL"); Iterator("")
      case _ => println("An unexpected error has occurred. We are so sorry!"); Iterator("")
    }
}
