import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// solution for https://www.hackerrank.com/challenges/board-cutting/problem

public class CuttingBoards {
    public static long boardCutting(List<Integer> cost_y, List<Integer> cost_x) {
        // Write your code here
        final int MODULO_BASE = 1000000007;
        long minCost = 0;
        int hCuts = 1;
        int vCuts = 1;
        cost_y.sort(Comparator.reverseOrder());
        cost_x.sort(Comparator.reverseOrder());
        int yIndex = 0;
        int xIndex = 0;
        while (yIndex < cost_y.size() && xIndex < cost_x.size()) {
            if (cost_y.get(yIndex) > cost_x.get(xIndex)
                    || (cost_y.get(yIndex).equals(cost_x.get(xIndex)) && vCuts <= hCuts)) {
                int cost = cost_y.get(yIndex) % MODULO_BASE;
                minCost = (minCost + ((1l * cost * vCuts) % MODULO_BASE)) % MODULO_BASE;
                hCuts++;
                yIndex++;
            } else {
                int cost = cost_x.get(xIndex) % MODULO_BASE;
                minCost = (minCost + ((1l * cost * hCuts) % MODULO_BASE)) % MODULO_BASE;
                vCuts++;
                xIndex++;
            }
        }
        List<Integer> remaining = yIndex == cost_y.size() ? cost_x : cost_y;
        int cuts = yIndex == cost_y.size() ? hCuts : vCuts;
        int index = yIndex == cost_y.size() ? xIndex : yIndex;
        while (index < remaining.size()) {
            int cost = remaining.get(index) % MODULO_BASE;
            minCost = (minCost + ((1l * cuts * cost) % MODULO_BASE)) % MODULO_BASE;
            index++;
        }
        return minCost % MODULO_BASE;
    }

    public static void main(String[] args) {
        System.out.println(boardCutting(Arrays.asList(new Integer[] { 2 }),
                Arrays.asList(new Integer[] { 1 }))); // 4
        System.out.println(
                boardCutting(Arrays.asList(new Integer[] { 2, 1, 3, 1, 4 }),
                        Arrays.asList(new Integer[] { 4, 1, 2 }))); // 42
    }
}
