import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// solution for https://www.hackerrank.com/challenges/unbounded-knapsack/problem

public class Knapsack {
    public static int unboundedKnapsack(int k, List<Integer> arr) {
        int sum = 0;
        Set<Integer> uniq = new HashSet<>();
        arr.forEach(x -> uniq.add(x));
        arr = new ArrayList<>();
        arr.addAll(uniq);
        Collections.sort(arr);
        Collections.reverse(arr);
        List<Integer> valuesUsed = new ArrayList<>();
        int index = 0;
        int curSum = 0;
        int startIndex = 0;
        while (startIndex < arr.size()) {
            if (curSum <= k && index < arr.size()) {
                curSum += arr.get(index);
                valuesUsed.add(arr.get(index));
                if (curSum <= k)
                    sum = Math.max(sum, curSum);
            } else if (index < arr.size()) {
                index++;
            } else if (index == arr.size()) {
                if (!valuesUsed.isEmpty()) {
                    int removed = valuesUsed.remove(valuesUsed.size() - 1);
                    curSum -= removed;
                    index = arr.indexOf(removed) + 1;
                } else {
                    startIndex++;
                    index = startIndex;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(unboundedKnapsack(12, Arrays.asList(new Integer[] { 1, 6, 9 })));
        System.out.println(unboundedKnapsack(9, Arrays.asList(new Integer[] { 3, 4, 4, 4, 8 })));
        System.out.println(unboundedKnapsack(15, Arrays.asList(new Integer[] { 2, 6, 9 })));
        System.out.println(unboundedKnapsack(5, Arrays.asList(new Integer[] { 3, 4, 4, 4, 8 })));
    }
}
