package tennis


class TennisGame1(val player1Name: String, val player2Name: String) extends TennisGame {
  // TODO:
  var m_score1: Int = 0
  var m_score2: Int = 0

  def wonPoint(player: String) {
    if (player == "player1")
      m_score1 += 1
    else
      m_score2 += 1
  }

  def mapScoreToString(score: Int): String =
    m_score1 match {
      case 0 => "Love-All"
      case 1 => "Fifteen-All"
      case 2 => "Thirty-All"
      case _ => "Deuce"
    }


  def scoreOf(score1: Int): String =
    score1 match {
      case 0 => "Love"
      case 1 => "Fifteen"
      case 2 => "Thirty"
      case 3 => "Forty"
    }

  //what is expected here

  def getScore(): String = s"${scoreOf(m_score1)}-${scoreOf(m_score2)}"

  def calculateScore(): String = {
    // Only when the scores are equal.
    if (m_score1 == m_score2) {
      // map the score to something else
      mapScoreToString(m_score1)
    }
    // if either score is above 4
    else if (m_score1 >= 4 || m_score2 >= 4) {

      val minusResult = m_score1 - m_score2
      minusResult match {
        case 1 => "Advantage player1"
        case -1 => "Advantage player2"
        case score if score >= 2 => "Win for player1"
        case score => "Win for player2"
      }
    }
    else {
      getScore()
    }
  }
}

