
def test(n):


	if n < 10:
		return n 

	return test(n/10)


print test(3814)