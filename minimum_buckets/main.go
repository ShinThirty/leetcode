package main

func minimumBuckets(s string) int {
	n := len(s)
	p := false
	ans := 0
	for i := 0; i < n; i++ {
		if s[i] == 'H' {
			if p {
				p = false
			} else if i+1 < n && s[i+1] == '.' {
				ans += 1
				p = true
			} else if i-1 > 0 && s[i-1] == '.' {
				ans += 1
				p = false
			} else {
				return -1
			}
		} else {
			if i-1 > 0 && s[i-1] == '.' {
				p = false
			}
		}
	}

	return ans
}
