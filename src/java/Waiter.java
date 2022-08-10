import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// solution for https://www.hackerrank.com/challenges/waiter/problem

public class Waiter {

    public static List<Integer> waiter(List<Integer> number, int q) {
        // Write your code here
        List<Integer> answers = new ArrayList<>();
        List<Long> primes = new ArrayList<>();
        Deque<Integer> a = new LinkedList<>();
        Deque<Integer> b = new LinkedList<>();
        long currentNumber = 2l;
        while (q > 0) {
            q--;
            a.clear();
            boolean isNotPrime = true;
            while (isNotPrime) {
                isNotPrime = false;
                for (long prime : primes) {
                    if (currentNumber % prime == 0) {
                        currentNumber++;
                        isNotPrime = true;
                        break;
                    }
                }
            }
            primes.add(currentNumber);
            for (int plate : number) {
                if (plate % currentNumber == 0)
                    b.add(plate);
                else
                    a.addFirst(plate);
            }
            number = new ArrayList<>();
            number.addAll(a);
            answers.addAll(b);
            b.clear();
        }
        Collections.reverse((List) a);
        answers.addAll(a);
        return answers;
    }

    public static void main(String[] args) {
        // List<Integer> results = waiter(Arrays.asList(2, 3, 4, 5, 6, 7), 3);
        List<Integer> results = waiter(Arrays.asList(3, 3, 4, 4, 9), 2);
        results.forEach(a -> System.out.print(a + ","));
    }

}
