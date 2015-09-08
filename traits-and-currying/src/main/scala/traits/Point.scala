package traits

/* Simple class defining a point with x and y co-ordinates */
class Point(val x: Int, val y: Int)

// TODO Trait Exercise 1 Step 2: make this class mix-in Ordered
class OrderedPoint(x: Int, y: Int) extends Point(x,y) ///...

// TODO Trait Exercise 1 Step 3: then define a "small" and a "big" point
object OrderedPoint {
  lazy val orderedPointSmall: Point with Ordered[Point] = ???
  lazy val orderedPointBig: Point with Ordered[Point] =  ???
}
