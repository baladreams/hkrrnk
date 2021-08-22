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

// solution for https://www.hackerrank.com/challenges/the-hurdle-race/

class HurdleHeights {

    /*
     * Complete the 'hurdleRace' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. INTEGER k 2. INTEGER_ARRAY height
     */

    public static int hurdleRace(int k, List<Integer> height) {
        // Write your code here
        int maxHurdle = height.stream().reduce(Math::max).get();
        return maxHurdle > k ? maxHurdle - k : 0;
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(hurdleRace(1, arrayToList(new int[] { 1, 2, 3, 3, 2 })));
        System.out.println(hurdleRace(7, arrayToList(new int[] { 2, 5, 5, 5, 2 })));
    }

}
