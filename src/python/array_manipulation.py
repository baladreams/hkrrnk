from typing import List

# solution for https://www.hackerrank.com/challenges/crush/problem


def arrayManipulation(n, queries):
    # Write your code here
    changes = {}
    for query in queries:
        start = query[0]-1
        end = query[1]
        k = query[2]
        if start in changes:
            changes[start] += k
        else:
            changes[start] = k
        if end in changes:
            changes[end] -= k
        else:
            changes[end] = -k
    max_val = 0
    cur_val = 0
    for i in range(0, n+1):
        if i in changes:
            cur_val += changes[i]
            if max_val < cur_val:
                max_val = cur_val
    return max_val


# 200
print(arrayManipulation(5, [[1, 2, 100], [2, 5, 100], [3, 4, 100]]))
# 10
print(arrayManipulation(10, [[1, 5, 3], [4, 8, 7], [6, 9, 1]]))
# 31
print(arrayManipulation(10, [[2, 6, 8], [3, 5, 7], [1, 8, 1], [5, 9, 15]]))
