import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.joining;

// solution for https://www.hackerrank.com/challenges/sparse-arrays/problem

class SparseArray {
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        Map<String, Integer> stringCounts = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < strings.size()) {
            String s = strings.get(i);
            int c = 0;
            if (stringCounts.containsKey(s))
                c = stringCounts.get(s);
            c += 1;
            stringCounts.put(s, c);
            i++;
        }
        i = 0;
        while (i < queries.size()) {
            String s = queries.get(i);
            int c = 0;
            if (stringCounts.containsKey(s))
                c = stringCounts.get(s);
            result.add(c);
            i++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String[] queries = { "ab", "abc", "bc" };
        String[] strings = { "ab", "ab", "abc" };
        List<Integer> res = SparseArray.matchingStrings(Arrays.asList(strings), Arrays.asList(queries));
        System.out.println(res.stream().map(Object::toString).collect(joining(",")) + "\n");
    }
}
