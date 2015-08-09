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

object OptionMatch {

  def parseURL(url: String): Option[URL] = Try(new URL(url)).toOption

  def getURLContent(urlarg: String): Option[Iterator[String]] = {
    val maybeUrl = parseURL(urlarg)
    val url = maybeUrl match {
      case Some(u) => u
      case None => println("Please make sure to enter a valid URL"); null
    }

    val maybeConn = Try(url.openConnection()).toOption
    val connection: URLConnection = maybeConn match {
      case Some(c) => c
      case None => println("Something went wrong"); null
    }

    val maybeIS = Try(connection.getInputStream).toOption
    val is: InputStream = maybeIS match {
      case Some(i) => i
      case None => println("Something happened"); null
    }

    val maybeSource = Try(Source.fromInputStream(is)).toOption
    val source: Source = maybeSource match {
      case Some(s) => s
      case None => println("e"); null
    }

    Try(source.getLines()).toOption
  }
}

// ---------------------------------------------------------

object OptionMap {

  def parseURL(url: String): Option[URL] = Try(new URL(url)).toOption

  def getURLContent(urlarg: String): Option[Iterator[String]] = {
    parseURL(urlarg)
      .map(_.openConnection())
      .map(_.getInputStream)
      .map(Source.fromInputStream)
      .map(_.getLines())
  }
}

// ---------------------------------------------------------

object OptionFlatMap {

  def parseURL(url: String): Option[URL] = Try(new URL(url)).toOption

  def getURLContent(urlarg: String): Option[Iterator[String]] = {

    parseURL(urlarg).flatMap( url =>
      Try(url.openConnection()).toOption flatMap ( connection =>
        Try(connection.getInputStream).toOption flatMap (is =>
          Try(Source.fromInputStream(is)).toOption map ( source =>
            source.getLines()))))

  }
}

// ---------------------------------------------------------

// hint: '<-' translates to flatMap
object OptionFor {

  def parseURL(url: String): Option[URL] = Try(new URL(url)).toOption

  def getURLContent(urlarg: String): Option[Iterator[String]] =
    for {
      url <- parseURL(urlarg)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source <- Try(Source.fromInputStream(is))
    } yield source.getLines()
}

