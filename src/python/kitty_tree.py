import array

# partial solution for https://www.hackerrank.com/challenges/kittys-calculations-on-a-tree/problem

class CustomInput:

    input_number = 0

    def custom_input():
        inputs = ["7 3",
                  "1 2",
                  "1 3",
                  "1 4",
                  "3 5",
                  "3 6",
                  "3 7",
                  "2",
                  "2 4",
                  "1",
                  "5",
                  "3",
                  "2 4 5"]
        ret_val = inputs[CustomInput.input_number]
        CustomInput.input_number += 1
        return ret_val

def lcp(n1, n2, level_map, parent_map):
    while n1 != n2:
        if level_map[n1] > level_map[n2]:
            n1 = parent_map[n1]
        else:
            n2 = parent_map[n2]
    return n1


counts = CustomInput.custom_input().split(" ")
parent_map = [0 for x in range(0, int(counts[0])+1)]
parent_map[1] = 1
for i in range(0, int(counts[0])-1):
    edge = CustomInput.custom_input().split(" ")
    edge[0] = int(edge[0])
    edge[1] = int(edge[1])
    if(edge[0] < edge[1]):
        parent_map[edge[1]] = edge[0]
    else:
        parent_map[edge[0]] = edge[1]
# child_sum = array.array('Q',(0 for x in range(0, int(counts[0])+1)))
# last_child_sum = array.array('i',(0 for x in range(0, int(counts[0])+1)))
# node_set = array.array('i',(0 for x in range(0, int(counts[0])+1)))
cur_query = 0
counts_0 = int(counts[0])
counts_1 = int(counts[1])
i = 0
while i < counts_1:
    i = i + 1
    cur_query += 1
    set_count = CustomInput.custom_input()
    node_query = CustomInput.custom_input().split(" ")
    sum_distance = 0
    # node_set = sorted(node_set, key=lambda n: int(n), reverse=True)
    node_sum = 0
    node_height_product_sum = 0
    child_sum = array.array('Q',(0 for x in range(0, int(counts[0])+1)))
    node_set = array.array('i',(0 for x in range(0, int(counts[0])+1)))
    for n_str in node_query:
        n = int(n_str)
        node_set[n] = cur_query
        node_sum += n
    n = counts_0 + 1
    while n > 1:
        n = n - 1
        cur_sum = 0
        # if last_child_sum[n] != cur_query:
        #     child_sum[n] = 0
        #     last_child_sum[n] = cur_query
        cur_sum = child_sum[n]
        if node_set[n] == cur_query:
            cur_sum += n
        if cur_sum > 0:
            # each  node contributes (sum of child nodes in set) * (other nodes of set not in same subtree) value to total
            sum_distance += (cur_sum * (node_sum - cur_sum))
            # if last_child_sum[parent_map[n]] != cur_query:
            #     child_sum[parent_map[n]] = 0
            #     last_child_sum[parent_map[n]] = cur_query
            child_sum[parent_map[n]] += cur_sum
    print(sum_distance % 1000000007)
    