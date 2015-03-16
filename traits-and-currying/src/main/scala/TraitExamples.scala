// Example code based on Programming in Scala, Second Edition by Martin Odersky, Lex Spoon, Bill Venners http://booksites.artima.com/programming_in_scala_2ed

object TraitExamples {

  trait Philosophical {
    def philosophize() {
      println("I consume memory, therefore I am!")
    }
  }

  class Animal

  class Frog extends Animal with Philosophical {
    override def toString = "green"
    override def philosophize() {
      println("It ain't easy being "+ toString +"!")
    }
  }
  
  // -------------- Traits Exercise - 1 ----------------

//    new Point(1,1) with Ordered[Point] {
//    def compare(that: Point) = (this.x - that.y) + (this.y - that.y)
//  }

//  class MyPoint(x: Int, y: Int) extends Point(x,y) with Ordered[Point] {
//    def compare(that: Point) = (this.x - that.y) + (this.y - that.y)
//  }


}
