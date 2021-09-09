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

// solution for https://www.hackerrank.com/challenges/larrys-array/problem

class SpinSort {

    /*
     * Complete the 'larrysArray' function below.
     *
     * The function is expected to return a STRING. The function accepts
     * INTEGER_ARRAY A as parameter.
     */

    public static String larrysArray(List<Integer> A) {
        // Write your code here
        boolean solvable = true;
        int[] seen = new int[A.size() + 1];
        int inversions = 0;
        for (int i = A.size() - 1; i >= 0; i--) {
            for (int n = A.get(i); n >= 1; n--) {
                if (seen[n] == 1)
                    inversions++;
            }
            seen[A.get(i)] = 1;
        }
        if (inversions % 2 != 0)
            solvable = false;
        return solvable ? "YES" : "NO";
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {

        // YES
        System.out.println(larrysArray(arrayToList(new int[] { 3, 1, 2 })));
        // YES
        System.out.println(larrysArray(arrayToList(new int[] { 1, 3, 4, 2 })));
        // NO
        System.out.println(larrysArray(arrayToList(new int[] { 1, 2, 3, 5, 4 })));
        // YES
        System.out.println(larrysArray(arrayToList(new int[] { 1, 6, 5, 2, 4, 3 })));
        // NO
        System.out.println(larrysArray(arrayToList(new int[] { 1, 6, 5, 2, 3, 4 })));
        // YES
        System.out.println(larrysArray(arrayToList(new int[] { 3, 1, 2, 4 })));
        System.out.println(larrysArray(arrayToList(new int[] { 1, 3, 4, 2 })));
        System.out.println(larrysArray(arrayToList(new int[] { 1, 2, 3, 5, 4 })));

        System.out.println(larrysArray(arrayToList(new int[] { 4, 1, 3, 2 })));
    }

}
