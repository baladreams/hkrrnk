import java.util.Arrays;
import java.util.List;

// solution for https://www.hackerrank.com/challenges/stockmax/problem

public class StockMax {
    public static long stockmax(List<Integer> prices) {
        long profit = 0;
        int[] maxPossible = new int[prices.size()];
        maxPossible[prices.size() - 1] = prices.get(prices.size() - 1);
        for (int i = prices.size() - 2; i >= 0; i--) {
            maxPossible[i] = Math.max(maxPossible[i + 1], prices.get(i));
        }
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i) < maxPossible[i])
                profit += maxPossible[i] - prices.get(i);
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(stockmax(Arrays.asList(new Integer[] { 5, 3, 2 })));
        System.out.println(stockmax(Arrays.asList(new Integer[] { 1, 2, 100 })));
        System.out.println(stockmax(Arrays.asList(new Integer[] { 1, 3, 1, 2 })));
    }
}
