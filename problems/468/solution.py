class Solution:
    def validIPAddress(self, IP: str) -> str:
        ipv4 = False
        ipv6 = False
        hexes = "abcdefABCDEF"
        delimiter = ".:"
        section = None
        n_section = 0
        IPV4 = "IPv4"
        IPV6 = "IPv6"
        NEITHER = "Neither"

        def addchar(c):
            nonlocal section
            if section is None:
                section = []
            section.append(c)

        def evaluate():
            nonlocal section
            if section is None:
                return False
            if len(section) > 3:
                return False
            if section[0] == "0" and len(section) > 1:
                return False
            if section[0] == "0" or section[0] == "1":
                return True
            num = int("".join(section))
            if num > 255:
                return False
            return True

        def evaulateV6():
            nonlocal section
            if section is None:
                return False
            if len(section) > 4:
                return False
            return True

        for c in IP:
            if c.isdigit():
                addchar(c)
            elif c in hexes:
                if ipv4:
                    return NEITHER
                ipv6 = True
                addchar(c)
            elif c in delimiter:
                if c == ".":
                    if ipv6:
                        return NEITHER
                    ipv4 = True
                    if not evaluate():
                        return NEITHER
                else:
                    if ipv4:
                        return NEITHER
                    ipv6 = True
                    if not evaulateV6():
                        return NEITHER
                n_section += 1
                if ipv4 and n_section == 4:
                    return NEITHER
                elif ipv6 and n_section == 8:
                    return NEITHER
                section = None
            else:
                return NEITHER

        if section is None:
            return NEITHER

        if ipv4:
            if not evaluate():
                return NEITHER
            n_section += 1
            if n_section != 4:
                return NEITHER
        else:
            if not evaulateV6():
                return NEITHER
            n_section += 1
            if n_section != 8:
                return NEITHER

        return IPV4 if ipv4 else IPV6
