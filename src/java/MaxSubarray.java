import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// solution for https://www.hackerrank.com/challenges/maxsubarray/problem

public class MaxSubarray {

    public static List<Integer> maxSubarray(List<Integer> arr) {
        List<Integer> sums = new ArrayList<>();
        int maxSubArrSum = Integer.MIN_VALUE;
        int subArrSum = 0;
        int maxSubSeqSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) >= 0) {
                if (maxSubSeqSum < 0)
                    maxSubSeqSum = 0;
                maxSubSeqSum += arr.get(i);
                if (subArrSum < 0)
                    subArrSum = 0;
                subArrSum += arr.get(i);
                maxSubArrSum = Math.max(maxSubArrSum, subArrSum);
            } else {
                subArrSum = Math.max(arr.get(i), subArrSum + arr.get(i));
                maxSubArrSum = Math.max(maxSubArrSum, subArrSum);
                maxSubSeqSum = Math.max(maxSubSeqSum, arr.get(i));
            }
        }
        sums.add(maxSubArrSum);
        sums.add(maxSubSeqSum);
        return sums;
    }

    public static void main(String[] args) {
        List<Integer> result = null;
        result = maxSubarray(Arrays.asList(new Integer[] { 1, 2, 3, 4 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { 2, -1, 2, 3, 4, -5 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { 1 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { -1, -2, -3, -4, -5, -6 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { 1, -2 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { 1, 2, 3 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { -10 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        result = maxSubarray(Arrays.asList(new Integer[] { 1, -1, -1, -1, -1, 5 }));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
