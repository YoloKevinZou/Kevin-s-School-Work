def mergeSort(a,b):
	
	if a == [] or b == []:
		return a+b

	if a[0] < b[0]:
		return [a[0]] + mergeSort(a[1:],b)
	
	return [b[0]] + mergeSort(a,b[1:])

def split(a):

	if len(a) == 1:
		return a

	left = a[:len(a)/2]
	right = a[len(a)/2:]
	return mergeSort(split(left),split(right))

def test():

	a=[4,1,5,2,6,3,7,0]

	print split(a)

test()