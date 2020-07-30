class Solution:
    def longestDupSubstring(self, S: str) -> str:
        ords = [ord(c) - ord("a") for c in S]
        BASE = 26
        MOD = 2 ** 32
        seen = set()

        def duplicate(guess):
            if guess == 0:
                return 0

            msb = pow(BASE, guess - 1, MOD)
            seen.clear()

            hs = 0
            for i in range(guess):
                hs = (hs * BASE + ords[i]) % MOD

            seen.add(hs)

            for i in range(guess, len(S)):
                hs += MOD
                hs -= ords[i - guess] * msb % MOD
                hs = (hs * BASE + ords[i]) % MOD

                if hs in seen:
                    return i + 1
                else:
                    seen.add(hs)

        lo = 0
        hi = len(S) - 1

        pos = 0

        while lo < hi:
            guess = lo + (hi - lo) // 2 + 1

            i = duplicate(guess)

            if i is not None:
                pos = i
                lo = guess
            else:
                hi = guess - 1

        return S[pos - lo:pos]


def run():
    with open(f"longest_duplicate_substring.txt") as f:
        S = f.read()

        sol = Solution()
        print(sol.longestDupSubstring(S))


if __name__ == "__main__":
    run()
