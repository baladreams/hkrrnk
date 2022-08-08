import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// partial solution for https://www.hackerrank.com/challenges/heavy-light-white-falcon/problem

public class HeavyLightWhiteFalcon {

    static Integer minNode = 1;

    public static List<Integer> solve(List<List<Integer>> tree, List<List<Integer>> queries) {
        // Write your code here
        List<Integer> results = new ArrayList<>();
        Map<String, Integer> lcpMap = new HashMap<>();
        int[] weights = new int[tree.size() + 1];
        int[] level = new int[tree.size() + 1];
        int[] parent = new int[tree.size() + 1];
        List<List<Integer>> child = new ArrayList<>(tree.size() + 1);
        while (child.size() < tree.size() + 1)
            child.add(null);
        List<Integer> eulerNodes = new ArrayList<>(tree.size() + 1);
        int[] firstEuler = new int[tree.size() + 1];
        updateNeighbours(tree, child);
        populateParentChild(child, parent, level, eulerNodes, firstEuler);
        int[] lcpSegmentTree = new int[4 * eulerNodes.size()];
        buildEulerSegmentTree(lcpSegmentTree, level, eulerNodes, 0, 0, eulerNodes.size() - 1);
        int[] subtreeSize = new int[tree.size() + 1];
        calculateSubtreeSize(tree, parent, child, subtreeSize);
        Map<String, String> edgeClass = new HashMap<>();
        classifyEdges(tree, parent, subtreeSize, edgeClass);
        List<List<Integer>> paths = new ArrayList<>();
        buildPaths(child, edgeClass, paths);
        Map<Integer, Integer> pathMap = new HashMap<>();
        Map<Integer, Integer> pathIndex = new HashMap<>();
        int[] pathMax = new int[1];
        Map<Integer, Integer> nodeMax = new HashMap<>();
        List<int[]> pathSegmentTrees = new ArrayList<>(paths.size());
        fetchPathMembership(paths, pathMap, pathIndex, pathSegmentTrees);
        for (List<Integer> query : queries) {
            Integer n1 = query.get(1);
            Integer n2 = query.get(2);
            if (query.get(0) == 1) {
                Integer node1 = n1;
                Integer node2 = n2;
                Integer pathId = pathMap.get(node1);
                int[] segment = pathSegmentTrees.get(pathId);
                updateMax(segment, paths.get(pathId), pathIndex.get(node1), node2, weights[node1], 0, 0,
                        paths.get(pathId).size() - 1);
                weights[node1] = node2;
            } else {
                Integer node1 = firstEuler[n1] < firstEuler[n2] ? n1 : n2;
                Integer node2 = n1 + n2 - node1;
                Integer parentN = 0;
                String nodeKey = node1 + ":" + node2;
                // if (!pathMap.get(node1).equals(pathMap.get(node2)))
                //     nodeKey = node1 + ":" + pathMap.get(node2);
                // if (lcpMap.containsKey(nodeKey)) {
                //     parentN = lcpMap.get(nodeKey);
                // } else {
                    // parentN = lcp(node1, node2, parent, level, lcpMap, paths, pathMap);
                    parentN = lcp(lcpSegmentTree, level, 0, firstEuler[node1], firstEuler[node2], 0,
                            eulerNodes.size() - 1);
                    // lcpMap.put(nodeKey, parentN);
                    // System.out.println(node1 + ":" + node2 + "::" + parentN);
                // }
                Integer maxOne = getMaxTillLCP(weights, parent, paths, pathMap, pathMax, pathIndex, nodeMax,
                        pathSegmentTrees, node1, parentN);
                Integer maxTwo = getMaxTillLCP(weights, parent, paths, pathMap, pathMax, pathIndex, nodeMax,
                        pathSegmentTrees, node2, parentN);
                results.add(Math.max(maxOne, maxTwo));
            }
        }
        return results;
    }

    public static int mid(int rangeStart, int rangeEnd) {
        return rangeStart + ((rangeEnd - rangeStart) / 2);
    }

    public static int maxRange(int[] segmentTree, int curIndex, int rangeStart, int rangeEnd, int segmentStart,
            int segmentEnd) {
        if (rangeEnd < segmentStart || segmentEnd < rangeStart) {
            return -1;
        } else if (rangeStart <= segmentStart && rangeEnd >= segmentEnd && segmentTree[curIndex] != -1) {
            return segmentTree[curIndex];
        } else {
            int midIndex = mid(segmentStart, segmentEnd);
            return Math.max(maxRange(segmentTree, 2 * curIndex + 1, rangeStart, rangeEnd, segmentStart, midIndex),
                    maxRange(segmentTree, 2 * curIndex + 2, rangeStart, rangeEnd, midIndex + 1, segmentEnd));
        }
    }

    public static boolean updateMax(int[] segmentTree, List<Integer> path, int updateIndex, int maxVal, int oldVal,
            int curIndex, int segmentStart, int segmentEnd) {
        if (updateIndex < segmentStart || segmentEnd < updateIndex) {
            return false;
        } else if (segmentStart == segmentEnd) {
            segmentTree[curIndex] = maxVal;
            return true;
        } else {
            int midIndex = mid(segmentStart, segmentEnd);
            boolean proceed = false;
            if (updateIndex <= midIndex)
                proceed = updateMax(segmentTree, path, updateIndex, maxVal, oldVal, 2 * curIndex + 1, segmentStart,
                        midIndex);
            else
                proceed = updateMax(segmentTree, path, updateIndex, maxVal, oldVal, 2 * curIndex + 2, midIndex + 1,
                        segmentEnd);
            if (proceed) {
                if (segmentTree[curIndex] == oldVal) {
                    segmentTree[curIndex] = -1;
                    int newMax = maxRange(segmentTree, curIndex, segmentStart, segmentEnd, segmentStart,
                            segmentEnd);
                    segmentTree[curIndex] = newMax;
                    return proceed;
                } else {
                    segmentTree[curIndex] = Math.max(segmentTree[curIndex], maxVal);
                    return proceed;
                }
            } else {
                return false;
            }
        }
    }

    private static void updateMax(int[] segmentTree, int maxVal, int oldVal, int curIndex) {
        if (segmentTree[curIndex] == oldVal)
            segmentTree[curIndex] = maxVal;
        else
            segmentTree[curIndex] = Math.max(segmentTree[curIndex], maxVal);
    }

    private static void updateNeighbours(List<List<Integer>> tree, List<List<Integer>> neighbours) {
        for (List<Integer> edge : tree) {
            if (neighbours.get(edge.get(0)) == null)
                neighbours.set(edge.get(0), new ArrayList<>());
            if (neighbours.get(edge.get(1)) == null)
                neighbours.set(edge.get(1), new ArrayList<>());
            neighbours.get(edge.get(0)).add(edge.get(1));
            neighbours.get(edge.get(1)).add(edge.get(0));
        }
    }

    private static Integer getMaxTillLCP(int[] weights, int[] parent,
            List<List<Integer>> paths, Map<Integer, Integer> pathMap, int[] pathMax, Map<Integer, Integer> pathIndex,
            Map<Integer, Integer> nodeMax, List<int[]> pathSegmentTrees, Integer node1, Integer parentN) {
        Integer maxVal = 0;
        List<Integer> currentPath = null;
        Integer pathId = pathMap.get(node1);
        while (!pathId.equals(pathMap.get(parentN))) {
            currentPath = paths.get(pathId);
            int[] segmentTree = pathSegmentTrees.get(pathId);
            maxVal = Math.max(maxVal, maxRange(segmentTree, 0, 0, pathIndex.get(node1), 0, currentPath.size() - 1));
            node1 = parent[currentPath.get(0)];
            pathId = pathMap.get(node1);
        }
        currentPath = paths.get(pathId);
        int[] segmentTree = pathSegmentTrees.get(pathId);
        return Math.max(
                maxRange(segmentTree, 0, pathIndex.get(parentN), pathIndex.get(node1), 0, currentPath.size() - 1),
                maxVal);
    }

    private static void fetchPathMembership(List<List<Integer>> paths, Map<Integer, Integer> pathMap,
            Map<Integer, Integer> pathIndex, List<int[]> pathSegmentTrees) {
        for (int i = 0; i < paths.size(); i++) {
            List<Integer> path = paths.get(i);
            for (int j = 0; j < path.size(); j++) {
                Integer n = path.get(j);
                pathMap.put(n, i);
                pathIndex.put(n, j);
            }
            int leafNodes = path.size();
            int segmentTreeHeight = (int) Math.ceil(Math.log(leafNodes) / Math.log(2));
            int maxNodes = 2 * (int) Math.pow(2, segmentTreeHeight) - 1;
            pathSegmentTrees.add(i, new int[maxNodes]);
        }
    }

    private static void buildPaths(List<List<Integer>> child, Map<String, String> edgeClass,
            List<List<Integer>> paths) {
        List<Integer> frontier = new LinkedList<>();
        frontier.add(0);
        while (!frontier.isEmpty()) {
            Integer node = frontier.remove(0);
            boolean pathBreak = false;
            List<Integer> path = new ArrayList<>();
            while (!pathBreak) {
                path.add(node);
                pathBreak = true;
                List<Integer> childNodes = child.get(node);
                if (childNodes != null && !childNodes.isEmpty()) {
                    for (Integer n : childNodes) {
                        if ("heavy".equals(edgeClass.get(node + ":" + n))) {
                            node = n;
                            pathBreak = false;
                        } else {
                            frontier.add(n);
                        }
                    }
                }
            }
            paths.add(path);
        }
    }

    private static void classifyEdges(List<List<Integer>> tree, int[] parent,
            int[] subtreeSize, Map<String, String> edgeClass) {
        for (List<Integer> edge : tree) {
            Integer childNode = edge.get(1) == parent[edge.get(0)] ? edge.get(0) : edge.get(1);
            Integer parentNode = (childNode.equals(edge.get(0))) ? edge.get(1) : edge.get(0);
            String edgeId = parentNode + ":" + childNode;
            if (subtreeSize[childNode] >= subtreeSize[parentNode] / 2)
                edgeClass.put(edgeId, "heavy");
            else
                edgeClass.put(edgeId, "light");
        }
    }

    private static void calculateSubtreeSize(List<List<Integer>> tree, int[] parent,
            List<List<Integer>> child, int[] subtreeSize) {
        List<Integer> frontier = new LinkedList<>();
        identifyLeaves(tree, child, frontier);
        Map<Integer, Boolean> parsedNodes = new HashMap<>();
        for (Integer node : frontier)
            subtreeSize[node] = 1;
        while (!frontier.isEmpty()) {
            List<Integer> newFrontier = new LinkedList<>();
            for (Integer node : frontier) {
                parsedNodes.put(node, true);
                subtreeSize[node] += 1;
                Integer parentNode = parent[node];
                subtreeSize[parentNode] += subtreeSize[node];
                if (!parsedNodes.containsKey(parentNode))
                    newFrontier.add(parentNode);
            }
            frontier = newFrontier;
        }
    }

    private static void identifyLeaves(List<List<Integer>> tree, List<List<Integer>> child,
            List<Integer> frontier) {
        for (int i = 0; i < child.size(); i++) {
            if (child.get(i) == null || child.get(i).isEmpty())
                frontier.add(i);
        }
    }

    private static void populateParentChild(List<List<Integer>> neighbours, int[] parent,
            int[] level, List<Integer> eulerVisit, int[] firstEuler) {
        Deque<Integer> frontier = new LinkedList<>();
        frontier.add(0);
        level[0] = 0;
        int[] visited = new int[level.length];
        // eulerVisit.add(0);
        visited[0] = 1;
        while (!frontier.isEmpty()) {
            // List<Integer> newFrontier = new LinkedList<>();
            Integer parentNode = frontier.remove();
            List<Integer> nextNodes = neighbours.get(parentNode).stream().filter(n -> visited[n] == 0)
                    .collect(Collectors.toList());
            // if (eulerVisit.isEmpty() || !eulerVisit.get(eulerVisit.size() -
            // 1).equals(parentNode))
            eulerVisit.add(parentNode);
            if (nextNodes == null || nextNodes.isEmpty()) {
                // if (visited[parentNode] == 1)
                // neighbours.set(parentNode, nextNodes);
                // frontier.remove();
                if (firstEuler[parentNode] == 0)
                    firstEuler[parentNode] = eulerVisit.size() - 1;
                continue;
            }
            visited[parentNode] += 1;
            // neighbours.set(parentNode, nextNodes);
            firstEuler[parentNode] = eulerVisit.size() - 1;
            Collections.reverse(nextNodes);
            nextNodes.forEach(n -> {
                visited[n] += 1;
                parent[n] = parentNode;
                neighbours.get(n).remove(parentNode);
                level[n] = level[parentNode] + 1;
                // newFrontier.add(n);
                frontier.addFirst(parentNode);
                frontier.addFirst(n);
            });
            // frontier.addAll(newFrontier);
        }
    }

    private static Integer lcp(Integer node1, Integer node2, int[] parent, int[] level,
            Map<String, Integer> lcpMap, List<List<Integer>> paths, Map<Integer, Integer> pathMap) {
        String nodeKey = node1 + ":" + node2;
        if (!pathMap.get(node1).equals(pathMap.get(node2)))
            nodeKey = node1 + ":" + pathMap.get(node2);
        if (lcpMap.containsKey(nodeKey)) {
            node1 = lcpMap.get(nodeKey);
            return node1;
        }
        while (!pathMap.get(node1).equals(pathMap.get(node2))) {
            Integer node1Root = paths.get(pathMap.get(node1)).get(0);
            Integer node2Root = paths.get(pathMap.get(node2)).get(0);
            if (level[node1Root] > level[node2Root])
                node1 = parent[node1Root];
            else
                node2 = parent[node2Root];
        }
        while (!node1.equals(node2)) {
            if (level[node1] > level[node2]) {
                node1 = parent[node1];
            } else {
                node2 = parent[node2];
            }
        }
        return node1;
    }

    public static void buildEulerSegmentTree(int[] eulerSegmentTree, int[] levels, List<Integer> eulerList,
            int currentNode, int startRange, int endRange) {
        if (startRange == endRange)
            eulerSegmentTree[currentNode] = eulerList.get(startRange);
        else {
            int mid = (startRange + endRange) / 2;
            buildEulerSegmentTree(eulerSegmentTree, levels, eulerList, currentNode * 2 + 1, startRange, mid);
            buildEulerSegmentTree(eulerSegmentTree, levels, eulerList, currentNode * 2 + 2, mid + 1, endRange);
            eulerSegmentTree[currentNode] = levels[eulerSegmentTree[2
                    * currentNode + 1]] < levels[eulerSegmentTree[2 * currentNode + 2]]
                            ? eulerSegmentTree[2 * currentNode + 1]
                            : eulerSegmentTree[2 * currentNode + 2];
        }
    }

    public static int lcp(int[] segmentTree, int[] levels, int curIndex, int rangeStart, int rangeEnd, int segmentStart,
            int segmentEnd) {
        if (rangeEnd < segmentStart || segmentEnd < rangeStart) {
            return -1;
        } else if (rangeStart <= segmentStart && rangeEnd >= segmentEnd && segmentTree[curIndex] != -1) {
            return segmentTree[curIndex];
        } else {
            int midIndex = mid(segmentStart, segmentEnd);
            int leftNode = lcp(segmentTree, levels, 2 * curIndex + 1, rangeStart, rangeEnd, segmentStart, midIndex);
            int rightNode = lcp(segmentTree, levels, 2 * curIndex + 2, rangeStart, rangeEnd, midIndex + 1, segmentEnd);
            if (leftNode == -1 && rightNode != -1)
                return rightNode;
            if (rightNode == -1 && leftNode != -1)
                return leftNode;
            if (rightNode == -1 && leftNode == -1)
                return -1;
            if (levels[leftNode] <= levels[rightNode])
                return leftNode;
            else
                return rightNode;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/HeavyLightWhiteFalcon.txt"));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(firstMultipleInput[0]);
        int q = Integer.parseInt(firstMultipleInput[1]);
        List<List<Integer>> tree = new ArrayList<>();
        IntStream.range(0, n - 1).forEach(i -> {
            try {
                tree.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        List<List<Integer>> queries = new ArrayList<>();
        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        List<Integer> result = HeavyLightWhiteFalcon.solve(tree, queries);
        // System.out.println(result.stream().map(Object::toString).collect(Collectors.joining("\n"))
        // + "\n");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/HeavyLightWhiteFalcon_out.txt"));
        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n"))
                        + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }

}
