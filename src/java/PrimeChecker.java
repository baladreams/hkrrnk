import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;

// solution for https://www.hackerrank.com/challenges/prime-checker/problem

public class PrimeChecker {

    public static class Prime {
        public void checkPrime(int... input) {
            List<Integer> primes = new ArrayList<>();
            for (int n : input) {
                if (n < 2)
                    continue;
                if (n == 2) {
                    primes.add(n);
                    continue;
                }
                boolean isNotPrime = false;
                for (int i = 2; i <= n / 2 && !isNotPrime; i++) {
                    if (n % i == 0)
                        isNotPrime = true;
                }
                if (!isNotPrime)
                    primes.add(n);
            }
            if (primes.isEmpty())
                System.out.println("");
            else {
                for (int n : primes)
                    System.out.print(n + " ");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n1 = Integer.parseInt(br.readLine());
            int n2 = Integer.parseInt(br.readLine());
            int n3 = Integer.parseInt(br.readLine());
            int n4 = Integer.parseInt(br.readLine());
            int n5 = Integer.parseInt(br.readLine());
            Prime ob = new Prime();
            ob.checkPrime(n1);
            ob.checkPrime(n1, n2);
            ob.checkPrime(n1, n2, n3);
            ob.checkPrime(n1, n2, n3, n4, n5);
            Method[] methods = Prime.class.getDeclaredMethods();
            Set<String> set = new HashSet<>();
            boolean overload = false;
            for (int i = 0; i < methods.length; i++) {
                if (set.contains(methods[i].getName())) {
                    overload = true;
                    break;
                }
                set.add(methods[i].getName());

            }
            if (overload) {
                throw new Exception("Overloading not allowed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
