package countvowelstringsinranges

object Solution {
  def vowelStrings(
      words: Array[String],
      queries: Array[Array[Int]]
  ): Array[Int] = {
    val vowels = "aeiou"
    def validate: String => Boolean = s =>
      vowels.contains(s.head) && vowels.contains(s.last)
    val prefixCounts = words.view
      .map(validate)
      .scanLeft(0)((z, valid) => if (valid) z + 1 else z)
      .toBuffer
    queries.map(q => {
      val s = q(0)
      val e = q(1)
      prefixCounts(e + 1) - prefixCounts(s)
    })
  }
}
