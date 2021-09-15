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

// solution for https://www.hackerrank.com/challenges/non-divisible-subset/problem

class NonDivisibleSubset {

    /*
     * ,Complete the 'nonDivisibleSubset' function below.
     *
     * ,The function is expected to return an INTEGER. The function accepts
     * following ,parameters: 1. INTEGER k 2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (Integer x : s)
            graph.computeIfAbsent(x % k, i -> new ArrayList<Integer>()).add(x);
        int count = graph.remove(0) != null ? 1 : 0;
        for (Integer i : graph.keySet()) {
            if (i < (k - i) || (i > (k - i) && !graph.containsKey(k - i))) {
                count += Math.max(graph.get(i).size(), graph.getOrDefault(k - i, new ArrayList<>(0)).size());
            } else if (i == (k - i)) {
                count += 1;
            }
        }
        return count;
    }

    public static int nonDivisibleSubsetGraph(int k, List<Integer> s) {
        // Write your code here
        int maxSize = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < s.size(); i++) {
            int cur = s.get(i);
            for (int j = i + 1; j < s.size(); j++) {
                int other = s.get(j);
                if ((cur + other) % k != 0) {
                    graph.computeIfAbsent(cur, n -> new ArrayList<>()).add(other);
                    graph.computeIfAbsent(other, n -> new ArrayList<>()).add(cur);
                }
            }
        }
        List<List<Integer>> cliques = new ArrayList<>();
        HashMap<Integer, Integer> P = new HashMap<>();
        graph.keySet().forEach(x -> P.put(x, x));
        List<Integer> R = new ArrayList<>();
        HashMap<Integer, Integer> X = new HashMap<>();
        BronKerboschWithPivot(graph, R, P, X, cliques);
        for (List<Integer> clique : cliques) {
            if (clique.size() > maxSize) {
                maxSize = clique.size();
            }
        }
        if (maxSize == 0) {
            for (Integer i : s) {
                if (i % k == 0)
                    maxSize = 1;
            }
        }
        return maxSize;
    }

    public static HashMap<Integer, Integer> intersect(HashMap<Integer, Integer> a, List<Integer> b) {
        HashMap<Integer, Integer> c = new HashMap<>();
        b.stream().forEach(x -> {
            if (a.containsKey(x))
                c.put(x, x);
        });
        return c;
    }

    public static void BronKerboschWithPivot(Map<Integer, List<Integer>> graph, List<Integer> R,
            HashMap<Integer, Integer> P, HashMap<Integer, Integer> X, List<List<Integer>> cliques) {
        if (P.size() == 0 && X.size() == 0)
            cliques.add(new ArrayList<>(R));
        else {
            Integer pivot;
            if (P.size() > 0)
                pivot = P.keySet().iterator().next();
            else
                pivot = X.keySet().iterator().next();
            // List<Integer> pivotNeighbours = graph.get(pivot);
            Map<Integer, Integer> pivotNeigbours = new HashMap<>();
            graph.get(pivot).stream().forEach(x -> pivotNeigbours.put(x, x));
            HashMap<Integer, Integer> PCopy = new HashMap<>(P);
            for (Integer v : P.keySet()) {
                if (pivotNeigbours.containsKey(v))
                    continue;
                R.add(v);
                BronKerboschWithPivot(graph, R, intersect(PCopy, graph.get(v)), intersect(X, graph.get(v)), cliques);
                R.remove(v);
                PCopy.remove(v);
                X.put(v, v);
            }
        }
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {

        // 3
        System.out.println(nonDivisibleSubset(4, arrayToList(new int[] { 19, 10, 12, 10, 24, 25, 22 })));
        // 3
        System.out.println(nonDivisibleSubset(3, arrayToList(new int[] { 1, 7, 2, 4 })));
        // 50
        System.out.println(nonDivisibleSubset(9,
                arrayToList(new int[] { 61197933, 56459859, 319018589, 271720536, 358582070, 849720202, 481165658,
                        675266245, 541667092, 615618805, 129027583, 755570852, 437001718, 86763458, 791564527,
                        163795318, 981341013, 516958303, 592324531, 611671866, 157795445, 718701842, 773810960,
                        72800260, 281252802, 404319361, 757224413, 682600363, 606641861, 986674925, 176725535,
                        256166138, 827035972, 124896145, 37969090, 136814243, 274957936, 980688849, 293456190,
                        141209943, 346065260, 550594766, 132159011, 491368651, 3772767, 131852400, 633124868, 148168785,
                        339205816, 705527969, 551343090, 824338597, 241776176, 286091680, 919941899, 728704934,
                        37548669, 513249437, 888944501, 239457900, 977532594, 140391002, 260004333, 911069927,
                        586821751, 113740158, 370372870, 97014913, 28011421, 489017248, 492953261, 73530695, 27277034,
                        570013262, 81306939, 519086053, 993680429, 599609256, 639477062, 677313848, 950497430,
                        672417749, 266140123, 601572332, 273157042, 777834449, 123586826 })));
        // 1
        System.out.println(nonDivisibleSubset(1, arrayToList(new int[] { 1, 2, 3, 4, 5 })));
        // 11
        System.out.println(nonDivisibleSubset(7,
                arrayToList(new int[] { 278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436 })));
    }

}
