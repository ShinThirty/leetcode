package `950`

object Solution {
  def deckRevealedIncreasing(deck: Array[Int]): Array[Int] = {
    val n = deck.size
    val revealingOrder = new Array[Int](n)
    var order = 0
    val indexes = new Array[Int](n + 1)
    val m = indexes.size
    for (i <- 0 to n) {
      indexes(i) = i
    }
    var head = 0
    var tail = n

    while (head != tail) {
      val nextIndex = indexes(head)
      head = (head + 1) % m
      revealingOrder(order) = nextIndex
      order += 1
      indexes(tail) = indexes(head)
      head = (head + 1) % m
      tail = (tail + 1) % m
    }

    val sortedDeck = deck.sorted
    val results = new Array[Int](n)
    Range(0, n).foreach(i => results(revealingOrder(i)) = sortedDeck(i))
    results
  }
}
