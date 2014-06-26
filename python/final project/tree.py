from node import *

class tree:

	data = None
	parent = None
	children = None

	def __init__(self, s):
		self.data = s
		self.parent = None
		self.children = []

	def add_child(self, node):
		self.children.append(node)

	#converts the string into a tree
	@staticmethod
	def from_string(s):
		root = None
		spot = None
		rootFound = False	
		term = ""
		for i in s:
			
			if i == ' ' and term == "":
				continue

			elif i != '(' and i != ')' and i != ' ':
				term+=i

			elif i =='(' and not rootFound:
					
				newNode = tree(term)
				root = newNode
				spot = root
				term = ""
				rootFound = True

			elif i == '(':

				newNode = tree(term)
				newNode.parent = spot
				spot.add_child(newNode)
				spot = newNode
				term = ""

			elif i == ')':
							
				if term == "":
					spot = spot.parent
					continue

				newNode = tree(term)
				newNode.parent = spot
				spot.add_child(newNode)
				spot = newNode

				if len(spot.children) == 0:
					spot = spot.parent.parent
				else: 
					spot = spot.parent

				term=""

			elif i == ' ' and term != "":
				newNode = tree(term)
				newNode.parent = spot
				spot.add_child(newNode)
				spot = newNode
				spot = spot.parent
				term = ""

		return root

	#prints the tree using depth first search
	@staticmethod
	def printTree(root):
		'''
		if len(root.children) == 0:
			print root.data,

		for i in root.children:
			#print i.data
			tree.printTree(i)
		'''
		if len(root.children) == 0:
			print root.data,
			return

		print root.data,
		for i in root.children:
			tree.printTree(i)

	#tree matching and return the right hand side of the matched rules as a sublist
	def patternMatching(self, rule_tuple):

		matched = False
		sublist = []
		rule_root = rule_tuple[0]

		matched, variable_dict = self.treeMatch(rule_root)
		
		if matched:
			
			rule_rhs = rule_tuple[1].split()

			for i in rule_rhs:

				if i in variable_dict:
					sublist.append(variable_dict[i])

				else:
					sublist.append(i)

		if len(sublist) > 0:
			matched = True
		return matched, sublist
	
	#tree matching using depth first search
	def treeMatch(self, rule_pattern, variable_dict = {}):

		rule_data = rule_pattern.data
		is_variable = False

		if rule_data[0]=='x':
			a = rule_data.split(":")
			rule_data = a[1]
			variable_dict[a[0]] = self
			is_variable = True

		if self.data != rule_data:
			return False, variable_dict

		if not is_variable and len(self.children) != len(rule_pattern.children):
			return False, variable_dict

		matched = True

		for i in range(len(rule_pattern.children)):

			matched, variable_dict = self.children[i].treeMatch(rule_pattern.children[i],variable_dict)

			if not matched:

				return False, variable_dict

		return matched, variable_dict
