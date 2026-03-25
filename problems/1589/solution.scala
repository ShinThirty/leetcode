package maximumsumobtainedofanypermutation

object Solution {

  /** Count the frequencies of each queried element and apply the higher values
    * to more frequently access elements. How to count? Think about an event
    * based approach, we place a start event in index start and an end event in
    * index end+1. Then we can get the real frequencies for each element by
    * going left to right and accumulate the event values.
    *
    * @param nums
    * @param requests
    * @return
    */
  def maxSumRangeQuery(nums: Array[Int], requests: Array[Array[Int]]): Int = {
    val n = nums.length
    val fences = Array.fill(n + 1)(0)
    val p = 1000000007
    requests.foreach((request) => {
      val start = request(0)
      val end = request(1)
      fences(start) += 1
      fences(end + 1) -= 1
    })
    var acc = 0L
    var frequencies = fences.map((fence) => {
      acc += fence
      acc
    })
    scala.util.Sorting.quickSort(nums)
    scala.util.Sorting.quickSort(frequencies)
    frequencies = frequencies.drop(1)

    frequencies.view
      .lazyZip(nums)
      .foldLeft(0L) { case (acc, (freq, num)) =>
        (acc + (freq * num) % p) % p
      }
      .toInt
  }
}
