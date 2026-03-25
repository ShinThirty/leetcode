package `1938`

import scala.collection.mutable

class TrieNode(val children: mutable.Map[Boolean, TrieNode]) {
  def getChildOrUpdate(indicator: Boolean): TrieNode = {
    children.getOrElseUpdate(indicator, TrieNode())
  }

  def hasChild(indicator: Boolean): Boolean = {
    children.contains(indicator)
  }

  def isEmpty(): Boolean = {
    children.isEmpty
  }

  def removeChild(indicator: Boolean): Unit = {
    children.remove(indicator)
  }
}

object TrieNode {
  def apply(): TrieNode = {
    new TrieNode(mutable.Map.empty[Boolean, TrieNode])
  }
}

class Trie {
  var _root = TrieNode()
  val _fac = 1 << 17

  def addNum(num: Int): Unit = {
    var fac = _fac
    var cur = _root
    while (fac > 0) {
      val indicator = (num & fac) != 0
      val child = cur.getChildOrUpdate(indicator)
      cur = child
      fac = fac >> 1
    }
  }

  def delNum(num: Int): Unit = {
    def del(fac: Int, root: TrieNode): Unit = {
      if (fac > 0) {
        val indicator = (num & fac) != 0
        val child = root.getChildOrUpdate(indicator)
        del(fac >> 1, child)
        if (child.isEmpty()) {
          root.removeChild(indicator)
        }
      }
    }

    del(_fac, _root)
  }

  def getMaximumXOR(op2: Int): Int = {
    var fac = _fac
    var cur = _root
    var ans = 0
    while (fac > 0) {
      ans = ans << 1
      val indicator = (op2 & fac) == 0
      val hasChild = cur.hasChild(indicator)
      if (hasChild) {
        ans += 1
        cur = cur.getChildOrUpdate(indicator)
      } else {
        ans += 0
        cur = cur.getChildOrUpdate(!indicator)
      }

      fac = fac >> 1
    }

    ans
  }
}

object Solution {
  def maxGeneticDifference(
      parents: Array[Int],
      queries: Array[Array[Int]]
  ): Array[Int] = {
    val children = mutable.Map.empty[Int, mutable.Buffer[Int]]
    parents.zipWithIndex.foreach {
      case (parent, child) => {
        children.getOrElseUpdate(parent, mutable.Buffer.empty[Int]).addOne(child)
      }
    }

    val root = children(-1).head

    val queryToIndex = mutable.Map.empty[Int, mutable.Buffer[(Int, Int)]]
    queries.zipWithIndex.foreach {
      case (query, index) => {
        val node = query(0)
        val value = query(1)
        queryToIndex.getOrElseUpdate(node, mutable.Buffer.empty).addOne((index, value))
      }
    }

    val ans = Array.fill(queries.length)(0)
    val trie = new Trie()

    def traverse(num: Int) {
      trie.addNum(num)
      queryToIndex.get(num).foreach((queryList) => {
        queryList.foreach {
          case (index, value) => ans(index) = trie.getMaximumXOR(value)
        }
      })

      children.get(num).foreach((childList) => childList.foreach(traverse(_)))

      trie.delNum(num)
    }

    traverse(root)

    ans
  }
}
