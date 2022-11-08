import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// solution for https://www.hackerrank.com/challenges/pairs/problem

public class PairDifference {

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        int pairs = 0;
        Map<Integer, Integer> remaining = new HashMap<>();
        for (Integer num : arr) {
            remaining.put(num - k, num);
        }
        for (Integer num : arr) {
            if (remaining.containsKey(num))
                pairs++;
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println(pairs(2, Arrays.asList(1, 5, 3, 4, 2)));
    }

}
