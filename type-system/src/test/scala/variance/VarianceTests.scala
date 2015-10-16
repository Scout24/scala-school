package variance

import org.scalatest._
import variance.RXVarianced._
// import variance.RXTyped._
import variance.Introduction._

class VarianceTests extends FlatSpec with MustMatchers {

  "RX.Observable" should "be covariant in [E]" in {
    val squeezer: JuiceSqueezer = new JuiceSqueezer
    var juices: List[Juice] = Nil
    val collect = (j: Juice) => juices = j :: juices

    val obs: Observable[Orange] = Observable.just[OrganicOrange](new OrganicOrange, new OrganicOrange)

    obs.map(squeezer.process).subscribe(collect)

    juices.length must be === 2
  }

}
