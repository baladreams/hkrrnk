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

// solution for https://www.hackerrank.com/challenges/beautiful-days-at-the-movies/

class BeautifulDays {

    /*
     * Complete the 'beautifulDays' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. INTEGER i 2. INTEGER j 3. INTEGER k
     */

    public static int beautifulDays(int i, int j, int k) {
        // Write your code here
        int solution = 0;
        for (int c = i; c <= j; c++) {
            if (Math.abs(c - reverseNum(c)) % k == 0)
                solution++;
        }
        return solution;
    }

    public static int reverseNum(int i) {
        int copyI = i;
        int reverseI = 0;
        while (copyI > 0) {
            reverseI = reverseI * 10 + copyI % 10;
            copyI = copyI / 10;
        }
        return reverseI;
    }

    public static void main(String[] args) {
        System.out.println(beautifulDays(20, 23, 6));
    }

}
