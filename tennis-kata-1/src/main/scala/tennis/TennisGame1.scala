package tennis


class TennisGame1(val player1Name: String, val player2Name: String) extends TennisGame {

  // This is ugly!
  var player1Score: Int = 0
  var player2Score: Int = 0

  // Mutates the internal state.
  def wonPoint(playerName: String) {
    if (playerName == player1Name)
      player1Score += 1
    else if (playerName == player2Name)
      player2Score += 1
    else
      throw new Exception("He's not playing!!")
  }

  def calculateScore(): String = {

    // if scores are equal?
    if (player1Score == player2Score) {
      player1Score match {
        case 0 => "Love-All"
        case 1 => "Fifteen-All"
        case 2 => "Thirty-All"
        case _ => "Deuce"
      }
    }
    // possible win

    else if (player1Score >= 4 || player2Score >= 4) {
      player1Score - player2Score match {
        case 0 if player1Score < 3 => convertScoreToString(player1Score) + "-All"
        case 0 =>  "Deuce"
        case 1 => "Advantage player1"
        case -1 => "Advantage player2"
        case d if d >= 2 => "Win for player1"
        case _ => "Win for player2"
      }
    }
    else {
      convertScoreToString(player1Score) + "-" + convertScoreToString(player2Score)
    }
  }

  def convertScoreToString(score: Int): String = {
    score match {
      case 0 => "Love"
      case 1 => "Fifteen"
      case 2 => "Thirty"
      case 3 => "Forty"
    }
  }
}