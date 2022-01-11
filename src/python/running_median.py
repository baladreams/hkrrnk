from queue import PriorityQueue
from typing import List

# solution for https://www.hackerrank.com/challenges/find-the-running-median/problem

def runningMedian(a: List):
    median = []
    small = PriorityQueue()
    large = PriorityQueue()
    small_size = 0
    large_size = 0
    for i in range(0, len(a)):
        cur = a[i]
        if small.empty() or small.queue[0][1] >= cur:
            small.put((-cur, cur))
            small_size += 1
        else:
            large.put((cur, cur))
            large_size += 1
        if small_size - large_size > 1:
            removed = small.get()
            large.put((removed[1], removed[1]))
            small_size -= 1
            large_size += 1
        if large_size - small_size > 1:
            removed = large.get()
            small.put((-removed[1], removed[1]))
            large_size -= 1
            small_size += 1
        m = 0
        if (i+1) % 2 == 0:
            m = (small.queue[0][1] + large.queue[0][1]) / 2
        else:
            if small_size > large_size:
                m = small.queue[0][1] / 1
            else:
                m = large.queue[0][1] / 1
        median.append(m)
    return median


# 7.0
print(runningMedian([7]))
# 5.0
print(runningMedian([3, 7]))
# 5.0
print(runningMedian([3, 5, 7]))
# 4.0
print(runningMedian([2, 3, 5, 7]))
print(runningMedian([7, 3, 5, 2]))
