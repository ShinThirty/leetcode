package maximumgoodpeoplebasedonstatements

object Solution {
  def maximumGood(statements: Array[Array[Int]]): Int = {
    val n = statements.length

    var ans = 0
    for (configuration <- 0 until 1 << n) {
      ans = Math.max(ans, verify(configuration))
    }

    def verify(configuration: Int): Int = {
      var nGood = 0
      var c = configuration
      for (i <- 0 until n) {
        if (nGood >= 0) {
          if ((c & 1) == 1) {
            nGood += 1
            if (verifyStatement(statements(i), configuration)) {
              nGood = -1
            }
          }

          c >>= 1
        }
      }
      nGood
    }

    def verifyStatement(statement: Array[Int], configuration: Int): Boolean = {
      var obliged = true
      var c = configuration
      for (i <- 0 until n) {
        if ((c & 1) == 0 && statement(i) == 1) {
          obliged = false
        } else if ((c & 1) == 1 && statement(i) == 0) {
          obliged = false
        }

        c >>= 1
      }

      obliged
    }

    ans
  }
}
