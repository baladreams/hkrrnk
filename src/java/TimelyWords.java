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

// solution for https://www.hackerrank.com/challenges/the-time-in-words/problem

class TimelyWorlds {

    /*
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING. The function accepts following
     * parameters: 1. INTEGER h 2. INTEGER m
     */

    public static String timeInWords(int h, int m) {
        // Write your code here
        String[] digits = new String[] { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        String[] tens = new String[] { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                "seventeen", "eighteen", "nineteen" };
        String result = "";
        if (m > 30)
            h++;
        if (h > 0 && h < 10) {
            result = digits[h];
        } else {
            result = tens[h - 10];
        }
        if (m > 30 && m < 59 && m != 45) {
            result = " minutes to " + result;
        } else if (m > 1 && m < 30 && m != 15) {
            result = " minutes past " + result;
        } else if (m == 1) {
            result = " minute past " + result;
        } else if (m == 59) {
            result = " minute to " + result;
        }
        if (m > 0 && m < 30 && m != 15) {
            if (m < 10)
                result = digits[m] + result;
            else if (m < 20)
                result = tens[m - 10] + result;
            else {
                result = digits[m - 20] + result;
                result = "twenty " + result;
            }
        } else if (m > 30 && m < 60 && m != 45) {
            int mMod = m - 30;
            mMod = 30 - mMod;
            if (mMod < 10)
                result = digits[mMod] + result;
            else if (mMod < 20)
                result = tens[mMod - 10] + result;
            else {
                result = digits[mMod - 20] + result;
                result = "twenty " + result;
            }
        }
        if (m == 0)
            result += " o' clock";
        else if (m == 30)
            result = "half past " + result;
        else if (m == 15)
            result = "quarter past " + result;
        else if (m == 45)
            result = "quarter to " + result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(timeInWords(5, 0));
        System.out.println(timeInWords(5, 1));
        System.out.println(timeInWords(5, 10));
        System.out.println(timeInWords(5, 15));
        System.out.println(timeInWords(5, 30));
        System.out.println(timeInWords(5, 40));
        System.out.println(timeInWords(5, 45));
        System.out.println(timeInWords(5, 47));
        System.out.println(timeInWords(5, 28));
        System.out.println(timeInWords(5, 47));
        System.out.println(timeInWords(3, 00));
        System.out.println(timeInWords(7, 15));
        System.out.println(timeInWords(10, 57));
    }

}
