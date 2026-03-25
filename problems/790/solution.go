package main

func numTilings(n int) int {
	f00 := 0
	f01 := 0
	f10 := 1
	f11 := 1
	f20 := 1
	f21 := 0

	for i := 1; i < n; i++ {
		f00 = f10 + f20 + 2*f21
		f01 = f10 + f11
		f20 = f10
		f21 = f11
		f10 = f00
		f11 = f01
	}

	return f10
}
