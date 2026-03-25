from collections import deque


class Solution:
    def smallestSufficientTeam(self, req_skills, people):
        r_masks = {}
        mask = 1
        for skill in req_skills:
            r_masks[skill] = mask
            mask <<= 1

        p_skills = []
        for skills in people:
            ss = 0
            for s in skills:
                ss |= r_masks[s]
            p_skills.append(ss)

        p = {}
        for i, ss in enumerate(p_skills):
            for j, ref in enumerate(p_skills):
                if i != j and ss | ref == ref:
                    p_skills[i] = 0
                    break
            else:
                p[i] = ss

        start = (1 << len(req_skills)) - 1
        Q = deque()
        Q.append(start)
        black = {start}
        prev = {}
        prev[start] = None

        def generate_team(state):
            team = []
            cur = state
            while prev[cur] is not None:
                team.append(prev[cur][1])
                cur = prev[cur][0]
            return team

        while Q:
            cur = Q.popleft()
            if cur == 0:
                return generate_team(cur)
            else:
                for i in p:
                    nn = cur & (~p[i])
                    if nn not in black:
                        black.add(nn)
                        Q.append(nn)
                        prev[nn] = (cur, i)


def run():
    req_skills = ["algorithms","math","java","reactjs","csharp","aws"]
    people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
    sol = Solution()
    print(sol.smallestSufficientTeam(req_skills, people))


if __name__ == "__main__":
    run()
