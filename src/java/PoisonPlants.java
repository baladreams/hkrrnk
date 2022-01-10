import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// solution for https://www.hackerrank.com/challenges/poisonous-plants/problem
// with a bit of guidance

class PoisonPlant {
    public static int poisonousPlants(List<Integer> p) {
        int maxInversion = 0;
        Deque<Integer> kept = new LinkedList<>();
        kept.add(0);
        Map<Integer, Integer> removePosition = new HashMap<>();
        removePosition.put(0, 0);
        for (int i = 1; i < p.size(); i++) {
            if (p.get(i) > p.get(kept.peekLast())) {
                kept.add(i);
                removePosition.put(i, 1);
            } else {
                int val = 0;
                if (p.get(i) <= p.get(kept.peekLast())) {
                    while (!kept.isEmpty() && p.get(kept.peekLast()) >= p.get(i)) {
                        int removed = kept.removeLast();
                        val = Math.max(val, removePosition.get(removed));
                    }
                    if (kept.isEmpty())
                        removePosition.put(i, 0);
                    else
                        removePosition.put(i, val + 1);
                    kept.add(i);
                }
            }
        }
        for (Integer i : removePosition.values())
            maxInversion = Math.max(maxInversion, i);
        return maxInversion;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 3, 6, 2, 7, 5 })));
        // 2
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 6, 5, 8, 4, 7, 10, 9 })));
        // 3
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 4, 3, 7, 5, 6, 4, 2 })));
        // 3
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 3, 1, 10, 7, 3, 5, 6, 6 })));
        // 4
        System.out.println(PoisonPlant.poisonousPlants(
                Arrays.asList(new Integer[] { 20, 5, 6, 15, 2, 2, 17, 2, 11, 5, 14, 5, 10, 9, 19, 12, 5 })));
        // 0
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 1, 1, 1, 1, 1 })));
        // 5
        System.out.println(PoisonPlant
                .poisonousPlants(Arrays.asList(new Integer[] { 11, 7, 19, 6, 12, 12, 8, 8, 7, 1, 10, 15, 5, 12 })));
        // 2
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 3, 2, 5, 4 })));
        // 0
        System.out.println(PoisonPlant.poisonousPlants(Arrays.asList(new Integer[] { 5, 4, 3, 2, 1 })));
    }
}
