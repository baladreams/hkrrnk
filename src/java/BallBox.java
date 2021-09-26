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

// solution for https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem

class BallBox {

    /*
     * Complete the 'organizingContainers' function below.
     *
     * The function is expected to return a STRING. The function accepts
     * 2D_INTEGER_ARRAY container as parameter.
     */

    public static String organizingContainers(List<List<Integer>> container) {
        // Write your code here
        List<Long> containerSizes = new ArrayList<>();
        List<Long> ballCounts = new ArrayList<>(container.get(0).size());
        for (int i = 0; i < container.get(0).size(); i++)
            ballCounts.add(0l);
        for (int i = 0; i < container.size(); i++) {
            List<Integer> containerI = container.get(i);
            long ballSizeI = 0l;
            for (int j = 0; j < containerI.size(); j++) {
                ballCounts.set(j, ballCounts.get(j) + containerI.get(j));
                ballSizeI += containerI.get(j);
            }
            containerSizes.add(ballSizeI);
        }
        if (containerSizes.size() == ballCounts.size()) {
            containerSizes.removeAll(ballCounts);
            if (containerSizes.isEmpty())
                return "Possible";
            else
                return "Impossible";
        } else
            return "Impossible";
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static List<List<Integer>> arraysToList(int[][] a) {
        List<List<Integer>> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(arrayToList(a[c]));
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(organizingContainers(arraysToList(new int[][] { { 1, 4 }, { 2, 3 } })));
        System.out.println(organizingContainers(arraysToList(new int[][] { { 1, 1 }, { 1, 1 } })));
        System.out.println(organizingContainers(arraysToList(new int[][] { { 0, 2 }, { 1, 1 } })));
    }

}
