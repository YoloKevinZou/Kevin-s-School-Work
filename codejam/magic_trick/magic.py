def test():
	k = input("")

	for q in range(k):
		#for i in range(16):
		row_number = int(raw_input())
		#print row_number

		items = {}

		for i in range(4):
			x = map(int, raw_input().split())
			if i == row_number-1:
				for j in x:
					items[j]=True

		row_number = int(raw_input())

		count = 0
		value  = 0
		for i in range(4):
			x = map(int, raw_input().split())
			if i==row_number-1:
				for j in x:
					if j in items:
						count +=1
						value = j
		if count == 1:
			print "Case #" + str(q+1) + ": " + str(value)
		elif count == 0:
			print "Case #" + str(q+1) + ": Volunteer cheated!" 
		else:
			print "Case #" + str(q+1) + ": Bad magician!"
	
test()