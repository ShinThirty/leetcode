package main

func minNumberOperations(target []int) int {
	prev := 0
	ans := 0
	for _, cur := range target {
		if cur > prev {
			ans += cur - prev
		}
		prev = cur
	}

	return ans
}
