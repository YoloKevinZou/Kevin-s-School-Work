def dwar(a, b):
	
	count = 0
	j = 0
	for i in range(len(a)):

		if a[i] > b[j]:
			count +=1
			j += 1

	return count

def war(a, b):

	c = []
	d = []

	count = 0
	j=0
	for i in range(len(b)):
		if(b[i] > a[j]):
			count += 1
			j += 1 

	return count

def test():

	k = input()

	for q in range(k):

		size = raw_input()

		a = map(float, raw_input().split())
		b = map(float, raw_input().split())

		a.sort()
		b.sort()
		
		dwarCount = dwar(a,b)
		warCount = war(a,b)

		print "Case #" + str(q+1) + ": " + str(dwarCount) + " " + str(len(b)-warCount)

test()