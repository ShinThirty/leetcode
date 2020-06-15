import threading
from collections import deque
import sys


class Solution:
    def numberToWords(self, num: int) -> str:
        if num == 0:
            return "Zero"

        ths = [
            None, "Thousand", "Million", "Billion"
        ]

        ds = [
            None, "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        ]

        ts = [
            None, None, "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety"
        ]

        o = num
        t = -1
        words = deque()
        while o > 0:
            print(o)
            t += 1
            c = o % 1000
            o //= 1000
            if c == 0:
                continue
            if t > 0:
                words.appendleft(ths[t])

            s = []
            if c >= 100:
                s.append(ds[c // 100])
                s.append("Hundred")
                c %= 100

            if c >= 20:
                s.append(ts[c // 10])
                c %= 10

            if c > 0:
                s.append(ds[c])

            while s:
                words.appendleft(s.pop())

        return " ".join(words)


def worker():
    num = 1000000000
    s = Solution()
    print(s.numberToWords(num))


def run():
    t = threading.Thread(target=worker, daemon=True)
    t.start()
    t.join(5)
    if t.is_alive():
        print("Time Limit Exception")
    sys.exit()


if __name__ == "__main__":
    run()
