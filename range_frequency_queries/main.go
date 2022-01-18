package main

import "sort"

type RangeFreqQuery struct {
	occurences map[int][]int
}

func Constructor(arr []int) RangeFreqQuery {
	rfq := RangeFreqQuery{make(map[int][]int)}
	for i, element := range arr {
		rfq.occurences[element] = append(rfq.occurences[element], i)
	}

	return rfq
}

func (this *RangeFreqQuery) Query(left int, right int, value int) int {
	occurences := this.occurences[value]

	idl := sort.SearchInts(occurences, left)
	idr := sort.SearchInts(occurences, right)
	offset := 0
	if idr < len(occurences) && occurences[idr] == right {
		offset = 1
	}

	return idr - idl + offset
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,value);
 */
