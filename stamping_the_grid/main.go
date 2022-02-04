package main

import "fmt"

func createPrefixSum(nums [][]int) [][]int {
	n := len(nums)
	m := len(nums[0])
	pSum := make([][]int, n+1)
	pSum[0] = make([]int, m+1)
	for i := 1; i < n+1; i++ {
		pSum[i] = make([]int, m+1)
		for j := 1; j < m+1; j++ {
			pSum[i][j] = pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1] + nums[i-1][j-1]
		}
	}

	return pSum
}

func validStamp(pSum [][]int, i int, j int, stampHeight int, stampWidth int) bool {
	x1 := i - stampHeight + 1
	y1 := j - stampWidth + 1
	x2 := i + 1
	y2 := j + 1
	if x1 < 0 || y1 < 0 {
		return false
	}

	return pSum[x2][y2]+pSum[x1][y1]-pSum[x1][y2]-pSum[x2][y1] == 0
}

func covered(pSum [][]int, i int, j int, stampHeight int, stampWidth int) bool {
	n := len(pSum)
	m := len(pSum[0])
	x1 := i
	y1 := j
	x2 := i + stampHeight
	y2 := j + stampWidth
	if x2 >= n {
		x2 = n - 1
	}
	if y2 >= m {
		y2 = m - 1
	}

	return pSum[x2][y2]+pSum[x1][y1]-pSum[x1][y2]-pSum[x2][y1] > 0
}

func possibleToStamp(grid [][]int, stampHeight int, stampWidth int) bool {
	n := len(grid)
	m := len(grid[0])

	pSum := createPrefixSum(grid)

	stamps := make([][]int, n)
	for i := 0; i < n; i++ {
		stamps[i] = make([]int, m)
		for j := 0; j < m; j++ {
			if validStamp(pSum, i, j, stampHeight, stampWidth) {
				stamps[i][j] = 1
			}
		}
	}
	fmt.Println(stamps)
	pSum2 := createPrefixSum(stamps)

	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if grid[i][j] == 0 {
				if !covered(pSum2, i, j, stampHeight, stampWidth) {
					return false
				}
			}
		}
	}

	return true
}
