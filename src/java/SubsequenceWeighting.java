import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// solution for https://www.hackerrank.com/challenges/subsequence-weighting/problem

class SubsequenceWeighting {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY a
     * 2. INTEGER_ARRAY w
     */

    public static Long solveReverseMap(List<Integer> a, List<Integer> w) {
        // Write your code here
        Long maxWeight = 0l;
        Map<Long, Integer> candidates = new TreeMap<>(Collections.reverseOrder());
        for (Integer v : a) {
            Long currentWeight = Long.valueOf(w.remove(0));
            Long currentMax = 0l;
            for (Map.Entry<Long, Integer> candidate : candidates.entrySet()) {
                if (candidate.getValue() < v) {
                    currentMax = candidate.getKey();
                    break;
                }
            }
            currentMax += currentWeight;
            if (maxWeight < currentMax) {
                maxWeight = currentMax;
            }
            candidates.put(currentMax, Math.min(v, candidates.getOrDefault(currentMax, Integer.MAX_VALUE)));
        }
        return maxWeight;
    }

    public static Long solve(List<Integer> a, List<Integer> w) {
        // Write your code here
        Long maxWeight = 0l;
        List<Integer> candidates = new ArrayList<>(a.size());
        Map<Integer, Long> sums = new HashMap<>();
        Map<Integer, Long> weights = new HashMap<>();
        Map<Integer, Long> maxii = new TreeMap<>(Collections.reverseOrder());
        List<Integer> holding = new LinkedList<>();
        for (Integer v : a) {
            Long currentWeight = Long.valueOf(w.remove(0));
            Long currentMax = 0l;
            if (weights.getOrDefault(v, 0l) > currentWeight)
                continue;
            Integer index = Collections.binarySearch(candidates, v);
            index = index < 0 ? -index - 2 : index - 1;
            while (index >= 0) {
                int candidate = candidates.get(index);
                if (candidate < v) {
                    if (maxii.containsKey(candidate)) {
                        currentMax = Math.max(currentMax, maxii.get(candidate));
                        break;
                    }
                    if (currentMax < sums.get(candidate)) {
                        currentMax = sums.get(candidate);
                    }
                } else {
                    System.out.println("WOTWOTWOT");
                    break;
                }
                index--;
            }
            currentMax += currentWeight;
            if (!weights.containsKey(v)) {
                Integer pos = Collections.binarySearch(candidates, v);
                if (pos < 0) {
                    candidates.add(-pos - 1, v);
                }
            }
            if (currentMax > sums.getOrDefault(v, 0l)) {
                if (maxWeight < currentMax) {
                    maxWeight = currentMax;
                }
                sums.put(v, currentMax);
                // maxii.entrySet().removeIf((e) -> e.getKey() > v);
                for (Map.Entry<Integer, Long> e : maxii.entrySet()) {
                    if (e.getKey() < v)
                        break;
                    if (e.getValue() < currentMax)
                        holding.add(e.getKey());
                }
                for (Integer i : holding) {
                    maxii.remove(i);
                }
                candidates.removeAll(holding);
                holding.clear();
                maxii.put(v, currentMax);
            } else {
                System.out.println("WOTWOTWOT");
            }
            weights.put(v, currentWeight);
        }
        return maxWeight;
    }

    public static Long solveArr(List<Integer> a, List<Integer> w) {
        // Write your code here
        Long maxWeight = 0l;
        long[] weightSum = new long[a.size()];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (weightSum[i] < weightSum[j] && a.get(i) > a.get(j))
                    weightSum[i] = weightSum[j];
            }
            weightSum[i] += w.get(i);
            if (maxWeight < weightSum[i])
                maxWeight = weightSum[i];
        }
        return maxWeight;
    }

    public static void main(String[] args) {
        String a = "1 2 3 4 1 2 3 4";
        String w = "10 20 30 40 15 15 15 50";
        System.out.println(solve(toIntList(a), toIntList(w)));
    }

    private static List<Integer> toIntList(String a) {
        return Arrays.asList(a.split(" ")).stream().map(v -> Integer.parseInt(v)).collect(Collectors.toList());
    }

}
