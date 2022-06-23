package `2034`

import scala.collection.mutable

class StockPrice() {

  private val priceByTimestamp = mutable.HashMap.empty[Int, Int]
  private val countsByPrice = mutable.TreeMap.empty[Int, Int]
  private var latestTimestamp = 0

  def update(timestamp: Int, price: Int) {
    priceByTimestamp.get(timestamp).foreach((oldPrice) => {
      countsByPrice(oldPrice) -= 1
      if (countsByPrice(oldPrice) == 0) {
        countsByPrice -= oldPrice
      }
    })

    countsByPrice.get(price) match {
      case Some(oldCount) => countsByPrice += (price -> (oldCount + 1))
      case None => countsByPrice += (price -> 1)
    }
    priceByTimestamp += (timestamp -> price)
    if (timestamp >= latestTimestamp) {
      latestTimestamp = timestamp
    }
  }

  def current(): Int = {
    priceByTimestamp(latestTimestamp)
  }

  def maximum(): Int = {
    countsByPrice.lastKey
  }

  def minimum(): Int = {
    countsByPrice.firstKey
  }

}

/**
  * Your StockPrice object will be instantiated and called as such:
  * var obj = new StockPrice()
  * obj.update(timestamp,price)
  * var param_2 = obj.current()
  * var param_3 = obj.maximum()
  * var param_4 = obj.minimum()
  */