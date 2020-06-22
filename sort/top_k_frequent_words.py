import collections
import heapq


class Key:
    def __init__(self, word, count):
        self.word = word
        self.count = count[word]

    def __lt__(self, other):
        if self.count < other.count:
            return True
        elif self.count > other.count:
            return False
        else:
            return self.word > other.word


class Solution:
    def topKFrequent(self, words, k):
        count = collections.defaultdict(int)
        for w in words:
            count[w] += 1

        h = []
        for word in count:
            if len(h) < k:
                heapq.heappush(h, (Key(word, count), word))
            else:
                p = Key(word, count)
                if p > h[0][0]:
                    heapq.heappop(h)
                    heapq.heappush(h, (p, word))

        res = collections.deque()
        while h:
            res.appendleft(heapq.heappop(h)[1])

        return res


def run():
    words = ["i", "love", "leetcode", "i", "love", "coding"]
    k = 3
    s = Solution()
    print(s.topKFrequent(words, k))


if __name__ == "__main__":
    run()
