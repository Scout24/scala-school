package fruits

object Introduction {

  makeOrangeJuice(new GroceryStore, new JuiceSqueezer)

  makeOrangeJuice(new OrganicStore, new JuiceExtractor)

  def makeOrangeJuice(store: GroceryStore, squeezer: JuiceSqueezer) = {
    val orange: Orange = store.getOrange()
    val juice:  Juice  = squeezer.process(orange)
    juice
  }

  class GroceryStore {
    def getOrange(): Orange = new Orange
  }

  class OrganicStore extends GroceryStore {
    override def getOrange(): OrganicOrange = new OrganicOrange
  }

  class JuiceSqueezer {
    def process(fruit: CitrusFruit): Juice = new Juice(fruit.name + " juice with pulp")
  }

  class JuiceExtractor extends JuiceSqueezer {

    override def process(fruit: CitrusFruit) = process2(fruit)

    def process2(fruit: Fruit): Juice = new Juice(fruit.name + " juice")
  }

  case class Juice(val description: String)
}
