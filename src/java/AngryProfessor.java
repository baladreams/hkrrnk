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

// solution for https://www.hackerrank.com/challenges/angry-professor

class AngryProfessor {

    /*
     * Complete the 'angryProfessor' function below.
     *
     * The function is expected to return a STRING. The function accepts following
     * parameters: 1. INTEGER k 2. INTEGER_ARRAY a
     */

    public static String angryProfessor(int k, List<Integer> a) {
        // Write your code here
        long onTime = a.stream().filter(x -> x <= 0).count();
        return onTime >= k ? "NO" : "YES";
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(angryProfessor(3, arrayToList(new int[] { -1, -3, 4, 2 })));
        System.out.println(angryProfessor(2, arrayToList(new int[] { 0, -1, 2, 1 })));
    }

}
