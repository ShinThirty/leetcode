import collections
import bisect


class TweetCounts:

    def __init__(self):
        self.stats = collections.defaultdict(list)
        self.delta = {
            "minute": 60,
            "hour": 3600,
            "day": 86400
        }

    def recordTweet(self, tweetName: str, time: int) -> None:
        bisect.insort(self.stats[tweetName], time)

    def getTweetCountsPerFrequency(self, freq: str, tweetName: str, startTime: int, endTime: int):
        tweet_counts = []
        d = self.delta[freq]
        start = startTime
        end = start + d
        i = bisect.bisect_left(self.stats[tweetName], start)
        while end < endTime + 1:
            j = bisect.bisect_left(self.stats[tweetName], end)
            tweet_counts.append(j - i)
            start += d
            end += d
            i = j

        if start < endTime + 1:
            j = bisect.bisect_left(self.stats[tweetName], endTime + 1)
            tweet_counts.append(j - i)

        return tweet_counts


# Your TweetCounts object will be instantiated and called as such:
# obj = TweetCounts()
# obj.recordTweet(tweetName,time)
# param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime)
