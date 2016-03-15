package tennis



class TennisGame2 (val player1Name : String, val player2Name : String) extends TennisGame {

  var p1point = 0
  var p2point = 0

  var p1res = ""
  var p2res = ""

  // Sort this out...
  def calculateScore() : String = {
    // Consider making this a large match
    // where we do not mutate score but return value
    // probably have to create helpers too?
    var score = ""

    if (p1point == p2point && p1point < 4)
    {
      score = (p1point match {
        case 0 => "Love"
        case 1 => "Fifteen"
        case 2 => "Thirty"
        case 3 => "Forty"
      }) + "-All"
    }
    if (p1point==p2point && p1point>=3)
      score = "Deuce"

    if (p1point > 0 && p2point==0)
    {
      p1res = intToString(p1point)

      p2res = intToString(p2point)
      score = p1res + "-" + p2res
    }
    if (p2point > 0 && p1point==0)
    {
      p2res = intToString(p2point)

      p1res = intToString(p1point)
      score = p1res + "-" + p2res
    }

    if (p1point>p2point && p1point < 4)
    {
        p1res=intToString(p1point)
        p2res=intToString(p2point)
      score = p1res + "-" + p2res
    }
    if (p2point>p1point && p2point < 4)
    {
      p1res=intToString(p1point)
      p2res=intToString(p2point)
      score = p1res + "-" + p2res
    }

    if (p1point > p2point && p2point >= 3)
    {
      score = "Advantage player1"
    }

    if (p2point > p1point && p1point >= 3)
    {
      score = "Advantage player2"
    }

    if (p1point>=4 && p2point>=0 && (p1point-p2point)>=2)
    {
      score = "Win for player1"
    }
    if (p2point>=4 && p1point>=0 && (p2point-p1point)>=2)
    {
      score = "Win for player2"
    }
    return score
  }

  def intToString(point:Int): String  = {
    point match {
      case 0 => "Love"
      case 1 => "Fifteen"
      case 2 => "Thirty"
      case 3 => "Forty"
      case _ => ""
    }
  }

  def wonPoint(player : String) {
    if (player == player1Name) {
      p1point += 1
    }
    else {
      p2point += 1
    }
  }
}