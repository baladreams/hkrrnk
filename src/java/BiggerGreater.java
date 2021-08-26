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

//solution for https://www.hackerrank.com/challenges/bigger-is-greater/problem

class BiggerGreater {

    /*
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING. The function accepts STRING w as
     * parameter.
     */

    public static String biggerIsGreater(String w) {
        // Write your code here
        char[] chars = w.toCharArray();
        boolean found = false;
        String solution = w;
        int[] minPos = new int[w.length()];
        minPos[w.length() - 1] = w.length() - 1;
        for (int i = w.length() - 2; i >= 0 && !found; i--) {
            char current = chars[i];
            char replace = current;
            int replacePos = i;
            if (current < chars[minPos[i + 1]]) {
                minPos[i] = i;
            } else {
                minPos[i] = minPos[i + 1];
            }
            for (int j = i; j < w.length(); j++) {
                if (current < chars[j] && (!found || replace >= chars[j])) {
                    replace = chars[j];
                    replacePos = j;
                    found = true;
                }
            }
            if (replace != current) {
                chars[i] = replace;
                chars[replacePos] = current;
                Arrays.sort(chars, i + 1, w.length());
                solution = String.valueOf(chars);
            }
        }
        return found ? solution : "no answer";
    }

    public static void main(String[] args) {
        // ba
        System.out.println(biggerIsGreater("ab"));
        // no answer
        System.out.println(biggerIsGreater("bb"));
        // hegf
        System.out.println(biggerIsGreater("hefg"));
        // dhkc
        System.out.println(biggerIsGreater("dhck"));
        // hcdk
        System.out.println(biggerIsGreater("dkhc"));
        // lmon
        System.out.println(biggerIsGreater("lmno"));
        // no answer
        System.out.println(biggerIsGreater("dcba"));
        // no answer
        System.out.println(biggerIsGreater("dcbb"));
        // acbd
        System.out.println(biggerIsGreater("abdc"));
        // abdc
        System.out.println(biggerIsGreater("abcd"));
        // fedcbabdc
        System.out.println(biggerIsGreater("fedcbabcd"));
        System.out.println(biggerIsGreater("nostfeubnqffxpkmnrptryrkpcxcuaqjgbdrmwzikuaozmyyxmuajbhyvsshtcmgea"));
    }

}
