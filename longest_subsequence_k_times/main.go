package main

import (
	"fmt"
	"sort"
	"strings"
)

type Queue []string

func (q *Queue) Put(s string) {
	*q = append(*q, s)
}

func (q *Queue) Get() string {
	old := *q
	s := old[0]
	old[0] = ""
	*q = old[1:]
	return s
}

func (q Queue) Len() int {
	return len(q)
}

func (q Queue) Less(i, j int) bool {
	return q[i] > q[j]
}

func (q Queue) Swap(i, j int) {
	q[i], q[j] = q[j], q[i]
}

func checkRepeatedK(ss string, s string, k int) bool {
	j := 0
	kk := 0
	for i := 0; i < len(s); i++ {
		if ss[j] == s[i] {
			j += 1
		}
		if j == len(ss) {
			j = 0
			kk += 1
		}
	}

	return kk >= k
}

func longestSubsequenceRepeatedK(s string, k int) string {
	count := make(map[string]int)
	processed := make(map[string]struct{})
	candidates := make(Queue, 0)
	for _, c := range s {
		cc := string(c)
		count[cc] += 1
		_, visited := processed[cc]
		if !visited && count[cc] >= k {
			candidates.Put(cc)
			processed[cc] = struct{}{}
		}
	}

	sort.Sort(candidates)
	characters := make([]string, len(candidates))
	copy(characters, candidates)

	ans := ""
	for candidates.Len() > 0 {
		current := candidates.Get()
		if len(current) > len(ans) {
			ans = current
		}

		for _, c := range characters {
			var sb strings.Builder
			sb.WriteString(current)
			sb.WriteString(c)
			ss := sb.String()
			_, visited := processed[ss]
			if !visited && checkRepeatedK(ss, s, k) {
				candidates.Put(ss)
				processed[ss] = struct{}{}
			}
		}
	}

	return ans
}

func main() {
	s := "letsleetcode"
	k := 2
	fmt.Println(longestSubsequenceRepeatedK(s, k))
}
