package main

func findIntegers(n int) int {
	// Define f(k) is the number of ints in range [0, 2^k - 1]
	// that doesn't have consecutive ones, then
	// f(k) = f(k - 1) + f(k - 2)
	// Since the last digits of valid n-digit ints must either be
	// 0 or 01
	// f(0) = 1
	// f(1) = 2
	f := make([]int, 31)
	f[0] = 1
	f[1] = 2
	for k := 2; k < 31; k++ {
		f[k] = f[k-1] + f[k-2]
	}

	// Tail recursion: split the range [0, N] to two ranges
	// [0, 2^(k-1)-1] and [2^(k-1), N]
	// where k is the digits of N
	// We use previously calculated f values to calculate first range
	// For second range we can remove the msb and reduce to a smaller problem [0, N']
	// where N' = N - 2^(k-1)
	input := n
	ans := 0
	msb := 1 << 29
	prev := 0
	for digits := 30; digits > 0; digits-- {
		if input&msb != 0 {
			if prev == 0 {
				ans += f[digits-1]
				input = input &^ msb
				prev = 1
			} else {
				return ans + f[digits-1]
			}
		} else {
			prev = 0
		}
		msb >>= 1
	}

	return ans + 1
}
