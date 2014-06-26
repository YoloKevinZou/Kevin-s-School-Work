

def test():

	number_of_input = int(input())
	
	for i in range(0,number_of_input):

		itemlist = map(str, raw_input().split())
		
		length = len(itemlist)-1

		print "Case #" + str(i+1) + ":",

		while length >=0:

			print itemlist[length],
			length -=1

		print '\r'


test()