package main

func minOperations(nums []int) int {
	bitOnes := 0
	maxBitLength := 1
	for _, num := range nums {
		bitLength := 0
		for num > 0 {
			if num%2 == 1 {
				bitOnes += 1
			}
			num /= 2
			bitLength += 1
		}
		if bitLength > maxBitLength {
			maxBitLength = bitLength
		}
	}

	return bitOnes + maxBitLength - 1
}
