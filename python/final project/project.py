#!/usr/bin/python
# -*- coding: utf-8 -*-
import sys
from tree import *
from node import *
cached = {}
rules = []

def translate(input_root):

	if isinstance(input_root, str):
		return 1.0, input_root

	if input_root in cached:
		return cached[input_root]

	best = 0.0
	result = ''

	#check every single rule to see if it matches
	for r in rules:	
		temp = ''

		matched, sublist = input_root.patternMatching(r)

		if matched:

			#rules are in form of tuple
			#extract the probably from the 3rd item of the tuple
			prob = float(r[2])
			for n in sublist:
				prob_i, s_i = translate(n)
				prob = prob * prob_i
				temp += s_i

			if prob > best:
				best = prob
				result = temp

	cached[input_root] = best, result

	return cached[input_root]

def main():

	rules_input = open( "rules3.txt", "r" )
	for line in rules_input:

		a = line.split(' -> ')
		rules_root = tree.from_string(a[0])

		x = a[1].split(' ### prob=')
		x[1] = x[1][:len(x[1])-1]
		rules.append((rules_root,x[0],x[1]))

	rules_input.close()

	
	translate_text = open("input3.txt", "r")

	for line in translate_text:

		input_root = tree.from_string(line)
		prob, text = translate(input_root)

		print prob, text

main()
