# import heapq
from typing import List
import bisect

# solution for https://www.hackerrank.com/challenges/median/problem


class MedianQueue:
    small = []
    large = []
    small_size = 0
    large_size = 0

    def __rebalance__(self):
        if self.small_size - self.large_size > 1:
            # removed = heapq.heappop(self.small)
            # heapq.heappush(self.large, (removed[1], removed[1]))
            removed = self.small.pop(-1)
            self.large.insert(0, removed)
            self.small_size -= 1
            self.large_size += 1
        if self.large_size - self.small_size > 1:
            # removed = heapq.heappop(self.large)
            # heapq.heappush(self.small, (-removed[1], removed[1]))
            removed = self.large.pop(0)
            self.small.append(removed)
            self.large_size -= 1
            self.small_size += 1

    def add_num(self, cur):
        # if self.small_size == 0 or self.small[0][1] >= cur:
        if self.small_size == 0 or self.small[-1] >= cur:
            # heapq.heappush(self.small, (-cur, cur))
            bisect.insort_left(self.small, cur)
            self.small_size += 1
        else:
            # heapq.heappush(self.large, (cur, cur))
            bisect.insort_left(self.large, cur)
            self.large_size += 1
        self.__rebalance__()
        return self.find_median()

    def remove_num(self, cur):
        # if self.small_size > 0 and self.small[0][1] >= cur and (-cur, cur) in self.small:
        if self.small_size > 0 and self.small[-1] >= cur and cur in self.small:
            # self.small.remove((-cur, cur))
            # heapq.heapify(self.small)
            self.small.remove(cur)
            self.small_size -= 1
            self.__rebalance__()
            return self.find_median()
        # elif (cur, cur) in self.large:
        elif cur in self.large:
            # self.large.remove((cur, cur))
            # heapq.heapify(self.large)
            self.large.remove(cur)
            self.large_size -= 1
            self.__rebalance__()
            return self.find_median()
        else:
            return "Wrong!"

    def __format_number__(self, num):
        if num == 'Wrong!':
            return num
        if num % 1 == 0:
            return int(num)
        else:
            return num

    def find_median(self):
        i = len(self.small) + len(self.large)
        m = "Wrong!"
        if i > 0:
            if i % 2 == 0:
                # m = (self.small[0][1] + self.large[0][1]) / 2
                m = (self.small[-1] + self.large[0]) / 2
            else:
                if self.small_size > self.large_size:
                    # m = self.small[0][1] / 1
                    m = self.small[-1] / 1
                else:
                    # m = self.large[0][1] / 1
                    m = self.large[0] / 1
        return self.__format_number__(m)


def median(a: List, x: List):
    mq = MedianQueue()
    for i in range(0, len(a)):
        op = a[i]
        val = x[i]
        median = 0
        if op == 'a':
            median = mq.add_num(val)
        elif op == 'r':
            median = mq.remove_num(val)
        print(median)


def make_input(inputs: List[str]):
    s = []
    x = []
    for a in inputs:
        i, j = a.split(' ')
        s.append(i)
        x.append(int(j))
    return s, x


# s = ['r', 'a', 'a', 'a', 'r', 'r', 'r']
# x = [1, 1, 2, 1, 1, 2, 1]
# median(s, x)
s, x = make_input(['a -2147483648',
                  'a -2147483648',
                   'a -2147483647',
                   'r -2147483648',
                   'a 2147483647',
                   'r -2147483648',
                   'a 10',
                   'a 10',
                   'a 10',
                   'r 10',
                   'r 10',
                   'r 10',
                   'r 100',
                   'r 100',
                   'r 100',
                   'r -2147483648',
                   'r 2147483647',
                   'r 10',
                   'a 1',
                   'a -1',
                   'a 1',
                   'a -1',
                   'r 1',
                   'r -1',
                   'r -1',
                   'r -1',
                   'r -1',
                   'r 1',
                   'r 1',
                   'r 0',
                   'a 0',
                   'a 1',
                   'a 2147483647',
                   'a 2',
                   'r 1',
                   'a 2147483646',
                   'r 2',
                   'a 2147483640',
                   'a 10',
                   'r 2',
                   'r 2',
                   'r 2',
                   'r 1',
                   'r 1',
                   'r 1',
                   'a 2147483640',
                   'a 2147483640',
                   'a -2147483648',
                   'a -2147483640',
                   'r 2147483640'])
median(s, x)
