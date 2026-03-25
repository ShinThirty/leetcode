class Solution:
    def minDays(self, bloomDay: list, m: int, k: int) -> int:
        n = len(bloomDay)
        if m * k > n:
            return -1

        def make_bouquets(day, k):
            print(f"day = {day}")
            bouquets = 0
            count = 0
            for bday in bloomDay:
                if count == k:
                    count = 0
                    bouquets += 1
                    print(bouquets)
                    if bouquets == m:
                        return True
                if bday > day:
                    count = 0
                else:
                    count += 1
            if count == k:
                bouquets += 1
                print(bouquets)
            return bouquets == m

        lo = 1
        hi = max(bloomDay)
        while lo < hi:
            guess = lo + (hi - lo) // 2
            if make_bouquets(guess, k):
                hi = guess
            else:
                lo = guess + 1

        return lo


def run():
    bloom_days = [1,10,3,10,2]
    m = 3
    k = 1
    s = Solution()
    print(f"Result: {s.minDays(bloom_days, m, k)}")


if __name__ == "__main__":
    run()
