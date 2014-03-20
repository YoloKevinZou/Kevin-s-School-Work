import sys

def zero_or_one_knapsack(items, weight, number_of_items, cached={}):

	#print number_of_items

	if weight <= 0:
		return 0

	if number_of_items <= 0:
		return 0

	index = number_of_items-1

	item_weight = items[index][0]
	item_value = items[index][1]

	if (weight,number_of_items) in cached:
		return cached[weight,number_of_items]	

	cached[weight,number_of_items] = max(zero_or_one_knapsack(items, weight-item_weight, number_of_items-1) + item_value, zero_or_one_knapsack(items, weight, number_of_items-1))
	return cached[weight,number_of_items]

def knapsack(items, weight, number_of_items, number_of_copies,cached={}):

	if weight <= 0:
		return 0

	if number_of_items <= 0:
		return 0

	item_weight = items[number_of_items-1][0]
	item_value = items[number_of_items-1][1]

	next_item_copy_value = items[number_of_items-2][2]

	if (weight,number_of_items,number_of_copies) in cached:
		return cached[weight,number_of_items,number_of_copies]	

	if item_weight <= weight and number_of_copies > 0:
		cached[weight,number_of_items, number_of_copies] = max(knapsack(items, weight-item_weight, number_of_items, number_of_copies-1) + item_value, knapsack(items, weight, number_of_items-1,next_item_copy_value))
		return cached[weight,number_of_items,number_of_copies]
	else:
		return 0


tiebreak = {}
def bounded_knapsack(items, weight, number_of_items, cached = {}):

	if weight <=0:
		return 0

	if number_of_items <=0:
		return 0

	if (weight,number_of_items) in cached:
		return cached[weight,number_of_items]

	item_weight = items[number_of_items-1][0]
	item_value = items[number_of_items-1][1]
	number_of_copies = items[number_of_items-1][2]

	index = number_of_items-1
	biggest = 0
	amount_of_copy_used = 0
	current_value = 0

	for j in range(number_of_copies+1):

		if weight >= j*item_weight:
			current_value = bounded_knapsack(items,weight-(j*item_weight),number_of_items-1) + (j*item_value)
			
		if biggest < current_value:
			biggest = current_value

	cached[weight,number_of_items] = biggest

	tiebreak[number_of_items-1] = amount_of_copy_used
	
	return biggest



def test():
	n, w = map(int, raw_input().split())
	
	items = []
	print "items: ", n
	print "weight: ", w

	for i in range(0,n):
		a = map(int, raw_input().split())
		items.append(a)
		#tiebreak.append(0)

	print "items: ",items

	print "zero or one: " ,zero_or_one_knapsack(items,w,n)

	copy = items[n-1][2]
	print "bounded: ",knapsack(items,w,n,copy)

	print "bounded_knapsack: ", bounded_knapsack(items,w,n)

	#print "cached: ", cached
	print "tiebreak: ", tiebreak

test()