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

// solution for https://www.hackerrank.com/challenges/equality-in-a-array/problem

class ArrayEqualizer {

    /*
     * Complete the 'equalizeArray' function below.
     *
     * The function is expected to return an INTEGER. The function accepts
     * INTEGER_ARRAY arr as parameter.
     */

    public static int equalizeArray(List<Integer> arr) {
        // Write your code here
        int[] counts = new int[101];
        for (int i : arr) {
            counts[i]++;
        }
        int max = 0;
        for (int i : counts) {
            if (max < i)
                max = i;
        }
        return arr.size() - max;
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(equalizeArray(arrayToList(new int[] { 1, 2, 2, 3 })));
        System.out.println(equalizeArray(arrayToList(new int[] { 3, 3, 2, 1, 3 })));
    }

}
