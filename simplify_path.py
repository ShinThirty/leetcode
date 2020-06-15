class Solution:
    def simplifyPath(self, path: str) -> str:
        simplenames = []

        simplename = None
        for c in path + "/":
            if c == "/":
                if isinstance(simplename, list):
                    name = "".join(simplename)
                    if name == "..":
                        if simplenames:
                            simplenames.pop()
                    elif name == ".":
                        None
                    else:
                        simplenames.append(name)
                    simplename = None
            else:
                if simplename is None:
                    simplename = []
                simplename.append(c)

        return "/" + "/".join(simplenames)


def run():
    path = "/a//b////c/d//././/.."
    s = Solution()
    print(s.simplifyPath(path))


if __name__ == "__main__":
    run()
