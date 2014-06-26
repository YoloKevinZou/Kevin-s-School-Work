def removeZero(a):

	temp = str(a)

	result =""
	for i in range(len(temp)):
		if temp[i]!='0':
			result += temp[i]

	return result

def solve(a,b):

	originalSum = a+b

	SumRemoveZero = removeZero(originalSum)

	newA = removeZero(a)
	newB = removeZero(b)

	result = int(newA) + int(newB)

	if int(SumRemoveZero) == result:
		return "YES"
	else:
		return "NO"



def test():

	a = input("")
	b = input("")

	print solve(a,b)

test()