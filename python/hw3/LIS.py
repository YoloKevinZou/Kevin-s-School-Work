import sys

def LIS(items,index,cached={}):
	
	if index == 0:
		return 0

	if index in cached:
		return cached[index]

	for j in range(len(items)-1):
		if items[j] < items[index]:
			cached[index] = LIS(items,j) + 1

	return cached[index]


def test():

	#n = map(ord, raw_input())
	for line in sys.stdin:

		n = [-1*sys.maxint]
		#n.append(sys.maxint)

		print n

		#print LIS(n,len(n)-1)-1
		'''
test()

