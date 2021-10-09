import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

// solution for https://www.hackerrank.com/challenges/java-lambda-expressions/problem

public class ShepardOfLambda {
    interface PerformOperation {
        boolean check(int a);
    }

    public static class MyMath {
        public static boolean checker(PerformOperation p, int num) {
            return p.check(num);
        }

        // Write your code here

        PerformOperation isOdd() {
            return (int x) -> {
                return (x % 2 == 1) ? true : false;
            };
        }

        PerformOperation isPrime() {
            return (int x) -> {
                for (int i = 2; i <= x / 2; i++) {
                    if (x % i == 0)
                        return false;
                }
                return true;
            };
        }

        PerformOperation isPalindrome() {
            return (int x) -> {
                int temp = x;
                int y = 0;
                while (temp > 0) {
                    int digit = temp % 10;
                    y = 10 * y + digit;
                    temp = temp / 10;
                }
                return x == y;
            };
        }
    }

    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 12; // Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        /*
         * EVEN PRIME PALINDROME ODD COMPOSITE COMPOSITE PRIME PALINDROME NOT PALINDROME
         * PALINDROME COMPOSITE PRIME
         */
        String[] input = { "1 4", "2 5", "3 898", "1 3", "2 12", "2 232", "2 2", "3 333", "3 344", "3 454", "2 68",
                "2 67" };
        while (T-- > 0) {
            String s = input[11 - T];// br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
            }
            System.out.println(ans);
        }
    }
}
