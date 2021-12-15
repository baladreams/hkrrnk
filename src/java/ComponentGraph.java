import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// solution for https://www.hackerrank.com/challenges/components-in-graph/problem

class ComponentGraph {
    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        Map<Integer, Integer> provenance = new HashMap<>(gb.size());
        Map<Integer, Integer> id = new HashMap<>(gb.size());
        for (List<Integer> edge : gb) {
            int child = edge.get(1);
            int newParent = edge.get(0);
            if (provenance.containsKey(child)) {
                while (provenance.containsKey(child) && newParent != provenance.get(child)) {
                    int currentParent = provenance.get(child);
                    provenance.put(child, Math.min(currentParent, newParent));
                    child = Math.max(currentParent, newParent);
                    newParent = Math.min(newParent, currentParent);
                }
            }
            provenance.put(child, newParent);
        }
        for (Map.Entry<Integer, Integer> parent : provenance.entrySet()) {
            int p = parent.getKey();
            while (provenance.containsKey(p) && p != provenance.get(p)) {
                p = provenance.get(p);
            }
            id.put(p, id.getOrDefault(p, 1) + 1);
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Integer componentSize : id.values()) {
            if (min > componentSize) {
                min = componentSize;
            }
            if (max < componentSize) {
                max = componentSize;
            }
        }
        List<Integer> result = new ArrayList<>(2);
        result.add(min);
        result.add(max);
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> gb = new ArrayList<>();
        Integer[][] edges = new Integer[][] { { 1, 6 }, { 2, 7 }, { 3, 8 }, { 4, 9 }, { 2, 6 } };
        for (Integer[] edge : edges) {
            gb.add(Arrays.asList(edge));
        }
        // 2 4
        List<Integer> result1 = ComponentGraph.componentsInGraph(gb);
        System.out.println(result1.stream().map(Object::toString).collect(Collectors.joining(" ")));

        gb = new ArrayList<>();
        edges = new Integer[][] { { 1, 5 }, { 1, 6 }, { 2, 4 } };
        for (Integer[] edge : edges) {
            gb.add(Arrays.asList(edge));
        }
        // 2 3
        List<Integer> result2 = ComponentGraph.componentsInGraph(gb);
        System.out.println(result2.stream().map(Object::toString).collect(Collectors.joining(" ")));

        // 11 25
        gb = new ArrayList<>();
        edges = new Integer[][] { { 5, 56 },
                { 4, 51 },
                { 2, 53 },
                { 8, 52 },
                { 5, 43 },
                { 2, 80 },
                { 5, 47 },
                { 4, 79 },
                { 3, 75 },
                { 1, 67 },
                { 7, 61 },
                { 2, 57 },
                { 5, 47 },
                { 4, 63 },
                { 10, 79 },
                { 1, 57 },
                { 4, 42 },
                { 8, 79 },
                { 6, 53 },
                { 3, 74 },
                { 7, 60 },
                { 10, 79 },
                { 5, 46 },
                { 3, 50 },
                { 6, 57 },
                { 8, 71 },
                { 6, 74 },
                { 10, 44 },
                { 9, 80 },
                { 7, 59 },
                { 7, 74 },
                { 6, 55 },
                { 3, 77 },
                { 3, 53 },
                { 5, 50 },
                { 9, 70 },
                { 4, 72 },
                { 5, 73 },
                { 6, 70 },
                { 7, 46 } };
        for (Integer[] edge : edges) {
            gb.add(Arrays.asList(edge));
        }
        List<Integer> result3 = ComponentGraph.componentsInGraph(gb);
        System.out.println(result3.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
