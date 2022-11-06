package partitionlabels

object Solution {
  def partitionLabels(s: String): List[Int] = {
    def firstOccurence: String => Map[Char, Int] = s => {
      s.zipWithIndex.foldLeft(Map.empty[Char, Int]) { case (m, (c, i)) =>
        if (m.contains(c)) m else m + ((c, i))
      }
    }
    def lastOccurence: String => Map[Char, Int] = s => {
      s.zipWithIndex.foldRight(Map.empty[Char, Int]) { case ((c, i), m) =>
        if (m.contains(c)) m else m + ((c, i))
      }
    }
    def intervals: Map[Char, Int] => Map[Char, Int] => List[Tuple2[Int, Int]] =
      f =>
        l => {
          f.keySet.map(c => (f(c), l(c))).toList.sortBy { case (a, b) => a }
        }
    def overlap: Tuple2[Int, Int] => Tuple2[Int, Int] => Boolean = x1 =>
      x2 => x1._2 >= x2._1
    def merge: List[Tuple2[Int, Int]] => List[Tuple2[Int, Int]] = {
      case x1 :: x2 :: tail =>
        if (overlap(x1)(x2))
          merge(Tuple2(x1._1, Math.max(x1._2, x2._2)) +: tail)
        else x1 +: merge(x2 +: tail)
      case x :: _ => List(x)
      case List() => List()
    }

    (intervals(firstOccurence(s)) andThen merge)(lastOccurence(s)).map {
      case (a, b) => b - a + 1
    }
  }
}
