package bagoftokens

object Solution {
  def bagOfTokensScore(tokens: Array[Int], power: Int): Int = {
    def getScores: Array[Int] => Int => Int => Seq[Int] = tokens =>
      power =>
        accu => {
          if (tokens.isEmpty) {
            Seq(accu)
          } else {
            val head = tokens.head
            if (power >= head) {
              getScores(tokens.tail)(power - head)(accu + 1)
            } else {
              val last = tokens.last
              if (accu > 0) {
                accu +: getScores(tokens.init)(power + last)(accu - 1)
              } else {
                Seq(accu)
              }
            }
          }
        }

    getScores(tokens.sorted)(power)(0).max
  }
}
