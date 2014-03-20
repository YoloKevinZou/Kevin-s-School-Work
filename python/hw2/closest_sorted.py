from bisect import *
import sys
def closest_sorted(a, x, k):

	boundaryValue = sys.maxint

	#add boundary values into the list for right and left
	a.append(boundaryValue)
	a.insert(0, boundaryValue*-1)

	#insert the query element into the list
	#use build in binary search to get the index that it should be inserted into
	insertPosition=bisect(a, x)
	a.insert(insertPosition, x)

	result = []
	left = insertPosition - 1
	right = insertPosition + 1

	
	while len(result) != k:

		if abs(a[right] - x) <= abs(a[left] - x):
			result.append(a[right])
			right += 1
		else:
			result.append(a[left])
			left -= 1

	return result


def test():
	a = [1,2,3,4,4,7]
	b = [1,2,3,4,4,7]

	print closest_sorted(a, 5.2, 2)
	print closest_sorted(b, 6.5, 3)
test()