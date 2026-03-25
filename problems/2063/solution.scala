package leetcode2063

object Solution {
  def countVowels(word: String): Long = {
    val n = word.length.toLong
    val vowels = "aeiou".toSet
    word.zipWithIndex.foldLeft(0L)((cur, ci) => {
      val c = ci._1
      val i = ci._2
      cur + (if (vowels.contains(c)) (i + 1) * (n - i) else 0)
    })
  }
}
