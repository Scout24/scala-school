package variance

import fruits.{CitrusFruit, Fruit, Orange, OrganicOrange}

object Introduction {

  makeOrangeJuice(new GroceryStore, juiceSqueezer)
  makeOrangeJuice(new OrganicStore, juiceExtractor)

  def makeOrangeJuice(store: GroceryStore, squeezer: (CitrusFruit => Juice)) = {
    val orange: Orange = store.getOrange()
    val juice:  Juice  = squeezer(orange)
    juice
  }

  case class Juice(val description: String)

  class GroceryStore {
    def getOrange(): Orange = new Orange
  }

  class OrganicStore extends GroceryStore {
    override def getOrange(): OrganicOrange = new OrganicOrange
  }

  val juiceExtractor = (f: Fruit)       => new Juice(f.name + " juice")
  val juiceSqueezer  = (f: CitrusFruit) => new Juice(f.name + " juice with pulp")
}
