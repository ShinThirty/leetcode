class Solution:
    def validateStackSequences(self, pushed: list, popped: list) -> bool:
        s = []
        i, j = 0, 0

        while j < len(popped):
            if not s or s[-1] != popped[j]:
                if i < len(pushed):
                    s.append(pushed[i])
                    i += 1
                else:
                    break
            else:
                s.pop()
                j += 1

        return len(s) == 0


def run():
    s = Solution()
    pushed = [1,2,3,4,5]
    popped = [4,3,5,1,2]
    print(s.validateStackSequences(pushed, popped))


if __name__ == "__main__":
    run()
