import sys
from operator import itemgetter
#sys.setrecursionlimit(100000)

def solve(itemlist,credit):

	left_index = 0
	right_index = len(itemlist)-1
	
	left_value = itemlist[left_index][0]
	right_value = itemlist[right_index][0]

	total = left_value + right_value
	while total != credit:

		if total < credit:
			left_index += 1

		elif total > credit:
			right_index -=1

		left_value = itemlist[left_index][0]

		right_value = itemlist[right_index][0]

		total = left_value + right_value

	first_index = itemlist[left_index][1]
	second_index = itemlist[right_index][1]

	return first_index, second_index

def test():

	number_of_input = int(input())
	count = 1
	for i in range(0,number_of_input):

		cached = {}
		back = {}		

		credit = int(input())

		number_of_item = int(input())

		itemlist = [(int(s), i+1) for i, s in enumerate(raw_input().split())]
		
		result = sorted(itemlist, key=itemgetter(0))

		left,right = solve(result,credit)

		if left < right:
			print "Case #" + str(count) + ": ", left, right
		else:
			print "Case #" + str(count) + ": ",right, left

		count +=1

test()