package main

func groupThePeople(groupSizes []int) [][]int {
	sizeToPeople := make(map[int][]int)

	for i, size := range groupSizes {
		sizeToPeople[size] = append(sizeToPeople[size], i)
	}

	ans := make([][]int, 0)
	for size, people := range sizeToPeople {
		n := 0
		group := make([]int, 0)
		for _, p := range people {
			group = append(group, p)
			n += 1
			if n == size {
				ans = append(ans, group)
				group = make([]int, 0)
				n = 0
			}
		}
	}

	return ans
}
