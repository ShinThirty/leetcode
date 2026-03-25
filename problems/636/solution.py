from operator import itemgetter


class Solution:
    def exclusiveTime(self, n: int, logs: list) -> list:
        output = [0] * n

        events = []
        for log in logs:
            i, t, tic = log.split(":")
            tic = int(tic) + (t == "end")
            event = (int(i), 0 if t == "start" else 1, tic)
            events.append(event)

        events.sort(key=itemgetter(2))
        print(events)

        s = []
        for i, t, tic in events:
            if t == 0:
                output[i] -= tic
                if s:
                    output[s[-1]] += tic
                s.append(i)
            else:
                output[i] += tic
                s.pop()
                if s:
                    output[s[-1]] -= tic

        return output


def run():
    n = 2
    logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    s = Solution()
    print(s.exclusiveTime(n, logs))


if __name__ == "__main__":
    run()
