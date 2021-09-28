import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// solution for https://www.hackerrank.com/challenges/beautiful-triplets/problem

class BeautifulTriplets {

    /*
     * Complete the 'beautifulTriplets' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. INTEGER d 2. INTEGER_ARRAY arr
     */

    public static int beautifulTriplets(int d, List<Integer> arr) {
        // Write your code here
        int solution = 0;
        Map<Integer, List<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!valueToIndex.containsKey(arr.get(i))) {
                valueToIndex.put(arr.get(i), new ArrayList<>());
                valueToIndex.get(arr.get(i)).add(i);
            } else {
                valueToIndex.get(arr.get(i)).add(i);
            }
        }
        for (int val : valueToIndex.keySet()) {
            if (valueToIndex.containsKey(val + d) && valueToIndex.containsKey(val + 2 * d)) {
                List<Integer> indices = valueToIndex.get(val);
                for (int index : indices) {
                    List<Integer> step1Indices = valueToIndex.get(val + d);
                    List<Integer> step2Indices = valueToIndex.get(val + 2 * d);
                    if (!step1Indices.isEmpty() && !step2Indices.isEmpty()) {
                        for (int step1Index : step1Indices) {
                            if (index < step1Index) {
                                for (int step2Index : step2Indices) {
                                    if (step1Index < step2Index)
                                        solution++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return solution;
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(beautifulTriplets(1, arrayToList(new int[] { 2, 2, 3, 4, 5 })));
        System.out.println(beautifulTriplets(3, arrayToList(new int[] { 1, 2, 4, 5, 7, 8, 10 })));
    }

}
