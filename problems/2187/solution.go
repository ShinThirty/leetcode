package main

import "sort"

func isPossible(s []int, m int, totalTrips int) bool {
	trips := 0
	for _, consumption := range s {
		trips += m / consumption
		if trips >= totalTrips {
			return true
		}
	}

	return false
}

func minimumTime(time []int, totalTrips int) int64 {
	s := time
	sort.Ints(s)
	n := len(s)
	lo := 1
	hi := s[n-1] * totalTrips

	for lo < hi {
		t := lo + (hi-lo)/2

		if !isPossible(s, t, totalTrips) {
			lo = t + 1
		} else {
			hi = t
		}
	}

	return int64(lo)
}
