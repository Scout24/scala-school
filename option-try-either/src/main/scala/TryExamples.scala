// Examples inspired by http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html

import java.io.{InputStream, FileNotFoundException}
import java.net.{MalformedURLException, URL}
import scala.util.{Failure, Success, Try}
import scala.io.{BufferedSource, Source}


object TryCatch {
  def parseURL(urlarg: String) = {
    var url: URL = null
    try {
      url = new URL(urlarg)
    } catch {
      case m: MalformedURLException => throw m
    }
    url
  }

  def getURLContent(urlarg: String): Iterator[String] = {
    var lines: Iterator[String] = null
    try {
      val url = parseURL(urlarg)
      val connection = url.openConnection()
      val is = connection.getInputStream
      lines = Source.fromInputStream(is).getLines()
    } catch {
      case e: FileNotFoundException => Iterator("Requested page does not exist")
      case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
      case _: Throwable => Iterator("An unexpected error has occurred. We are so sorry!")
    }
    lines
  }

}

object TryMatch {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(urlarg: String): Try[Iterator[String]] = {
    val maybeUrl = parseURL(urlarg)
    val url = maybeUrl match {
      case Success(url) => url
      case Failure(ex) => throw ex
    }
    val maybeConn = Try(url.openConnection())
    val connection = maybeConn match {
      case Success(connection) => connection
      case Failure(ex) => throw ex
    }
    val maybeIS = Try(connection.getInputStream)
    val is = maybeIS match {
      case Success(is) => is
      case Failure(ex) => throw ex
    }

    val maybeSource = Try(Source.fromInputStream(is))
    maybeSource match {
      case Success(source) => Try(source.getLines())
      case Failure(ex) => throw ex
    }
  }
}

object TryMap {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(urlarg: String): Try[Iterator[String]] = {
    parseURL(urlarg)
      .map(_.openConnection())
      .map(_.getInputStream)
      .map(Source.fromInputStream(_))
      .map(_.getLines())
  }

  val content1 = getURLContent("http://example.com") match {
    case Success(lines) => lines.next
    case Failure(ex) => s"Problem rendering URL content: ${ex.getMessage}"
  }
}

object TryFlatMap {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(urlarg: String): Try[Iterator[String]] = {
    parseURL(urlarg)
      .flatMap(url => Try(url.openConnection()))
      .flatMap(connection => Try(connection.getInputStream)
      .map(is => Source.fromInputStream(is)))
      .map(_.getLines())
  }

  val content1 = getURLContent("http://example.com") match {
    case Success(lines) => lines.next
    case Failure(ex) => s"Problem rendering URL content: ${ex.getMessage}"
  }
}

object TryFor {

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()
  
  val content1 = getURLContent("http://example.com") match {
    case Success(lines) => lines.next
    case Failure(ex) => s"Problem rendering URL content: ${ex.getMessage}"
  }

  val content2: Try[Iterator[String]] = getURLContent("garbage") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
    case _ => Iterator("An unexpected error has occurred. We are so sorry!")
  }

  val aTry = Try(println("hello"))
}
