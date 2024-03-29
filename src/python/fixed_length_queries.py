from typing import List

# solution for https://www.hackerrank.com/challenges/queries-with-fixed-length/problem

def solve(arr, queries):
    # Write your code here
    min_max_all = []
    for query in queries:
        cur = 0
        start = 0
        min_max = None
        while(start <= len(arr)-query):
            max_pos = start
            cur_max = arr[max_pos]
            cur = start
            while(cur < len(arr)):
                cur_val = arr[cur]
                if cur_max <= cur_val:
                    cur_max = cur_val
                    max_pos = cur
                if cur+1 < start + query:
                    cur += 1
                else:
                    break
            if min_max == None or min_max > cur_max:
                min_max = cur_max
            start = max_pos + 1
        min_max_all.append(min_max)
    return min_max_all


# 3,4
print(solve([2, 3, 4, 5, 6], [2, 3]))
# 11,33,44,44,55
print(solve([33, 11, 44, 11, 55], [1, 2, 3, 4, 5]))
# 1,2,3,4,5
print(solve([1, 2, 3, 4, 5], [1, 2, 3, 4, 5]))
print(solve([176641, 818878, 590130, 846132, 359913, 699520, 974627, 806346, 343832, 619769, 760242, 693331, 832192, 775549, 353117, 23950, 496548, 183204, 971799, 393071, 727476, 351337, 811496, 24595, 417701, 664960, 745806, 538176, 230403, 942316, 21481, 605695, 598531, 651683, 558460, 583357, 530911, 721611, 308228, 724620, 429167, 909353, 330152, 116815, 986067, 713467, 906132, 428600, 927889, 567272, 647109, 992614, 747948, 192884, 879696, 262543, 782487, 829272, 470060, 427956, 751730, 597177, 870616, 754791, 421830, 11676, 425656, 841955, 693419, 462693, 245403, 192649, 750201, 180732,
      17450, 44723, 527618, 174579, 515786, 444844, 210843, 563425, 809540, 752036, 608529, 748313, 667439, 255643, 387412, 320353, 704213, 755272, 267902, 657989, 651762, 325654, 582887, 382501, 715426, 897450], [78, 96, 89, 29, 81, 17, 50, 34, 8, 17, 58, 7, 65, 59, 3, 58, 80, 31, 21, 12, 87, 19, 6, 70, 60, 98, 55, 27, 67, 94, 57, 69, 14, 66, 52, 73, 62, 73, 30, 77, 38, 23, 15, 63, 25, 72, 89, 91, 25, 38, 88, 22, 48, 79, 71, 33, 72, 21, 26, 59, 100, 43, 77, 81, 55, 44, 43, 2, 42, 48, 1, 30, 33, 71, 94, 58, 34, 93, 58, 27, 92, 91, 83, 47, 61, 34, 25, 88, 37, 90, 3, 95, 5, 68, 39, 40, 71, 56, 89, 4]))
