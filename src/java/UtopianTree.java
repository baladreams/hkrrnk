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

// solution for https://www.hackerrank.com/challenges/utopian-tree

class UtopianTree {

    /*
     * Complete the 'utopianTree' function below.
     *
     * The function is expected to return an INTEGER. The function accepts INTEGER n
     * as parameter.
     */

    public static int utopianTree(int n) {
        // Write your code here
        int i = 1;
        for (int c = 0; c < n; c++) {
            if (c % 2 == 0)
                i = i * 2;
            else
                i = i + 1;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(utopianTree(5));
        System.out.println(utopianTree(0));
        System.out.println(utopianTree(1));
        System.out.println(utopianTree(4));
    }

}
