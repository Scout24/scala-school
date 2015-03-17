package traits

/* Simple class defining a point with x and y co-ordinates */


class Point(val x: Int, val y: Int) extends Ordered[Point] {
  /* this is the thin interface */
  override def compare(that: Point) = (this.x - that.y) + (this.y - that.y)

}
