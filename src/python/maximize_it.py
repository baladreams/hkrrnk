from typing import Callable, List
from itertools import product

# solution for https://www.hackerrank.com/challenges/maximize-it/problem

class Maximizer:

    m: int
    numbers: List[List[int]]
    f: Callable[[int], int]

    def maximize(self) -> int:
        solution = max(map(lambda l: sum(l) % self.m, product(
            *map(lambda l: map(lambda x: self.f(x), l), self.numbers))))
        return solution


def do_input() -> Maximizer:
    m = Maximizer()
    line = "7 867"  # input()
    n,m.m = map(int,line.split())
    numbers: List = []
    input_strings = ["7 6429964 4173738 9941618 2744666 5392018 5813128 9452095",
                     "7 6517823 4135421 6418713 9924958 9370532 7940650 2027017",
                     "7 1506500 3460933 1550284 3679489 4538773 5216621 5645660",
                     "7 7443563 5181142 8804416 8726696 5358847 7155276 4433125",
                     "7 2230555 3920370 7851992 1176871 610460 309961 3921536",
                     "7 8518829 8639441 3373630 5036651 5291213 2308694 7477960",
                     "7 7178097 249343 9504976 8684596 6226627 1055259 4880436"]
    for i in range(0, n):
        input_string = input_strings[i].split()[1:]  # input().split()[1:]
        numbers.append(list(map(lambda n: int(n), input_string)))
    m.numbers = numbers
    return m


m: Maximizer = do_input()
m.f = lambda n: n * n
s = m.maximize()
print(s)
