package fruits

abstract class Fruit { val name: String }

abstract class CitrusFruit extends Fruit { }

class Orange extends CitrusFruit { val name = "Orange" }

class OrganicOrange extends Orange { override val name = "Organic orange" }

class Apple extends Fruit { val name = "Apple" }