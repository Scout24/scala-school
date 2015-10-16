package variance

object Introduction {

  makeOrangeJuice(new GroceryStore, new JuiceSqueezer)

  makeOrangeJuice(new OrganicStore, new JuiceExtractor)

  def makeOrangeJuice(store: GroceryStore, squeezer: JuiceSqueezer) = {
    val orange: Orange = store.getOrange()
    val juice:  Juice  = squeezer.process(orange)
    juice
  }

  abstract class Fruit { val name: String }

  abstract class CitrusFruit extends Fruit { }

  class Orange extends CitrusFruit { val name = "Orange" }

  class OrganicOrange extends Orange { override val name = "Organic orange" }

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

  case class Ice(val description: String)

  type IceMaker = Juice => Ice
}
