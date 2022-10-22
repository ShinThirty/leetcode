package filpcolumnsformaximumnumberofequalrows

object Solution {
  def maxEqualRowsAfterFlips(matrix: Array[Array[Int]]): Int = {
    def canonicalForm: Array[Int] => Boolean => String = row => flip => {
      row.foldLeft("") {
        case (partial, x) =>
          val cx = (if (flip) 1 - x else x) + 48
          partial :+ cx.toChar
      }
    }
    def needFlip: Array[Int] => Boolean = {
      case Array() => false
      case Array(x, _*) => x == 1
    }

    def groupByCanonicalForm: Array[Array[Int]] => Map[String, Int] = matrix => {
      matrix.foldLeft(Map.empty[String, Int]) {
        case (partial, row) =>
          val canonForm = canonicalForm(row)(needFlip(row))
          val number = partial.getOrElse(canonForm, 0)
          partial + (canonForm -> (number + 1))
      }
    }

    val groups = groupByCanonicalForm(matrix)
    groups.foldLeft(0) {
      case (res, (_, count)) => Math.max(res, count)
    }
  }
}