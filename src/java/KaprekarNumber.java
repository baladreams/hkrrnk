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

// solution for https://www.hackerrank.com/challenges/kaprekar-numbers/problem

class KaprekarNumbers {

    /*
     * Complete the 'kaprekarNumbers' function below.
     *
     * The function accepts following parameters: 1. INTEGER p 2. INTEGER q
     */

    public static void kaprekarNumbers(int p, int q) {
        // Write your code here
        List<Integer> kaprekarNumbers = new ArrayList<>();
        for (int num = p; num <= q; num++) {
            int length = (int) (Math.log10(num) + 1);
            double tenLength = Math.pow(10, length);
            double squared = Math.pow(num, 2);
            double r = squared % tenLength;
            double l = (int) (squared / tenLength);
            if (r + l == num) {
                kaprekarNumbers.add(num);
            }
        }
        if (!kaprekarNumbers.isEmpty()) {
            Iterator<Integer> iterator = kaprekarNumbers.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + (iterator.hasNext() ? " " : ""));
            }
        } else
            System.out.print("INVALID RANGE");
    }

    public static void main(String[] args) {
        kaprekarNumbers(1, 100);
    }

}
