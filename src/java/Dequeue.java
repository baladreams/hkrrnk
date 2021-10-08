import java.io.*;
import java.util.*;

// solution for https://www.hackerrank.com/challenges/java-dequeue/problem

public class Dequeue {

    private static void countUniques(String specString, String numbersString) {
        Deque<Integer> q = new LinkedList<>();
        String[] params = specString.split(" ");
        int totNumbers = Integer.parseInt(params[0]);
        int maxSeq = Integer.parseInt(params[1]);
        long maxUniq = 0l;
        String[] numbers = numbersString.split(" ");
        Set<Integer> s = new HashSet<>(3);
        for (String num : numbers) {
            q.addLast(Integer.parseInt(num));
            s.add(q.getLast());
            if (q.size() == maxSeq) {
                long size = s.size();
                if (maxUniq < size)
                    maxUniq = size;
                Integer first = q.removeFirst();
                if (!q.contains(first))
                    s.remove(first);
            }
        }
        System.out.println(maxUniq);
    }

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        try {
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            countUniques(br.readLine(), br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
