package main

type Key struct {
	i    int
	prev int
}

var memo map[Key]int

func count(nums []int, i int, prev int, target int) int {
	key := Key{i, prev}
	cached, exist := memo[key]
	n := len(nums)
	if exist {
		return cached
	} else {
		if prev == target {
			return 1 << (n - i)
		} else if n == i {
			return 0
		} else {
			res := count(nums, i+1, prev|nums[i], target) + count(nums, i+1, prev, target)
			memo[key] = res
			return res
		}
	}
}

func countMaxOrSubsets(nums []int) int {
	target := 0
	for _, num := range nums {
		target |= num
	}

	memo = make(map[Key]int)
	return count(nums, 0, 0, target)
}
