import java.util.Arrays;
import java.util.List;

// solution for https://www.hackerrank.com/challenges/sherlock-and-cost/problem

public class SherlockAndCost {
    public static int cost(List<Integer> B) {
        int maxVal = 0;
        int n = B.size();
        int[][] maxSum = new int[n][2];
        maxSum[n - 1][0] = 0;
        maxSum[n - 1][1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            maxSum[i][1] = Math.max(B.get(i) - 1 + maxSum[i + 1][0],
                    Math.abs(B.get(i) - B.get(i + 1)) - 1 + maxSum[i + 1][1]);
            maxSum[i][0] = Math.max(maxSum[i + 1][0], B.get(i + 1) - 1 + maxSum[i + 1][1]);
        }
        maxVal = Math.max(maxSum[0][0], maxSum[0][1]);
        return maxVal;
    }

    public static void main(String[] args) {
        System.out.println(cost(Arrays.asList(new Integer[] { 1, 2, 3 })));
        System.out.println(cost(Arrays.asList(new Integer[] { 10, 1, 10, 1, 10 })));
        System.out.println(cost(Arrays.asList(new Integer[] { 100, 2, 100, 2, 100 })));
        System.out.println(cost(Arrays.asList(new Integer[] { 3, 15, 4, 12, 10 })));
        System.out.println(cost(Arrays.asList(new Integer[] { 4, 7, 9 })));
        System.out.println(cost(Arrays.asList(new Integer[] { 69, 19, 15, 81, 93, 100, 35, 18, 81, 16, 65, 20, 4, 45,
                81, 83, 90, 14, 82, 85, 43, 7, 64, 76, 33, 47, 95, 12, 78, 93, 14, 22, 97, 36, 65, 66, 36, 26, 59, 81,
                81, 82, 44, 79, 89, 94, 32, 94, 22, 33, 19, 46, 46, 62, 19, 47, 70, 91, 97, 62, 17, 43, 11, 25, 74, 73,
                64, 98, 73, 7, 40, 8, 2, 96, 89, 81, 80, 17, 88, 13, 31, 44, 64
        })));
    }
}
