package main

import (
	"sort"
	"strconv"
)

type void struct{}

var member void

func displayTable(orders [][]string) [][]string {
	displayTableInfo := make(map[string]map[int]int)
	tables := make([]int, 0)
	tableSet := make(map[int]void)
	items := make([]string, 0)

	for _, order := range orders {
		tableNumber, _ := strconv.Atoi(order[1])
		item := order[2]
		_, exist := displayTableInfo[item]
		if !exist {
			items = append(items, item)
			displayTableInfo[item] = make(map[int]int)
		}
		_, exist = tableSet[tableNumber]
		if !exist {
			tables = append(tables, tableNumber)
			tableSet[tableNumber] = member
		}
		displayTableInfo[item][tableNumber] += 1
	}

	sort.Strings(items)
	sort.Ints(tables)

	ans := make([][]string, len(tables)+1)
	header := make([]string, 1)
	header[0] = "Table"
	header = append(header, items...)
	ans[0] = header

	for j, table := range tables {
		row := make([]string, len(items)+1)
		row[0] = strconv.Itoa(table)
		for i, item := range items {
			count := displayTableInfo[item][table]
			row[i+1] = strconv.Itoa(count)
		}
		ans[j+1] = row
	}

	return ans
}
