#!/bin/python3

# solution for https://www.hackerrank.com/challenges/matrix-script/problem

import math
import os
import random
import re
import sys


first_multiple_input = "7 3".rstrip().split()  # input().rstrip().split()

n = int(first_multiple_input[0])

m = int(first_multiple_input[1])

matrix = []
input_strings = ["Tsi",
                 "h%x",
                 "i #",
                 "sM ",
                 "$a ",
                 "#t%",
                 "ir!"]
for _ in range(n):
    matrix_item = input_strings[_]  # input()
    matrix.append(matrix_item)

output = ""

matrixInverse = [['' for x in range(len(matrix))]
                 for y in range(len(matrix[0]))]
for i in range(0, len(matrix)):
    for j in range(0, len(matrix[0])):
        c = matrix[i][j]
        matrixInverse[j][i] = c

for i in range(len(matrixInverse)):
    output += "".join(matrixInverse[i])

output = re.sub("([a-zA-Z]+)([\W|\s]+)(?=\w+)", "\\1 ", output)
print(output)
