import java.util.Arrays;
import java.util.List;

// solution for https://www.hackerrank.com/challenges/coin-change/problem

public class CoinChange {
    public static long getWays(int n, List<Long> c) {
        long ways = 0;
        long[] memStore = new long[n + 1];
        memStore[0] = 1;
        for (Long coin : c) {
            for (int i = 0; i <= n; i++) {
                if (coin.intValue() <= i)
                    memStore[i] += memStore[i - coin.intValue()];
            }
        }
        ways = memStore[n];
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(getWays(3, Arrays.asList(8l, 3l, 1l, 2l)));
        System.out.println(getWays(4, Arrays.asList(1l, 2l, 3l)));
        System.out.println(getWays(10, Arrays.asList(2l, 5l, 3l, 6l)));
        System.out.println(getWays(75, Arrays.asList(25l, 10l, 11l, 29l, 49l, 31l, 33l, 39l, 12l, 36l, 40l, 22l, 21l,
                16l, 37l, 8l, 18l, 4l, 27l, 17l, 26l, 32l, 6l, 38l, 2l, 30l, 34l)));
        System.out.println(getWays(166, Arrays.asList(5l, 37l, 8l, 39l, 33l, 17l, 22l, 32l, 13l, 7l, 10l, 35l, 40l, 2l,
                43l, 49l, 46l, 19l, 41l, 1l, 12l, 11l, 28l)));
        System.out.println(getWays(219, Arrays.asList(36l, 10l, 42l, 7l, 50l, 1l, 49l, 24l, 37l, 12l, 34l, 13l, 39l,
                18l, 8l, 29l, 19l, 43l, 5l, 44l, 28l, 23l, 35l, 26l)));
    }
}
