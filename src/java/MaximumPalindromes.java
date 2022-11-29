import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

// solution for https://www.hackerrank.com/challenges/maximum-palindromes/problem

public class MaximumPalindromes {

    private static final int MODULO_BASE = 1000000007;
    static int[][] letterCounts;
    static final int LETTER_COUNT = 26;
    static final Map<Long, Long> factorialStore = new HashMap<>();
    static final Map<Long, Long> modInverseStore = new HashMap<>();

    public static void initialize(String s) {
        // This function is called once before all queries.
        letterCounts = new int[LETTER_COUNT][s.length()];
        int[] currentCount = new int[LETTER_COUNT];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            currentCount[chars[i] - 'a']++;
            for (int x = 0; x < LETTER_COUNT; x++) {
                letterCounts[x][i] = currentCount[x];
            }
        }
    }

    public static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.
        long result = 1;
        l--;
        r--;
        int singles = 0;
        long doubles = 0;
        int[] doubleCharCount = new int[LETTER_COUNT];
        int[] singleCharCount = new int[LETTER_COUNT];
        for (int i = 0; i < LETTER_COUNT; i++) {
            int charCount = letterCounts[i][r] - (l > 0 ? letterCounts[i][l - 1] : 0);
            singleCharCount[i] = charCount % 2;
            singles += charCount % 2;
            doubleCharCount[i] = (charCount / 2);
            doubles += charCount / 2;
        }
        if (doubles > 0) {
            long interim = factorialWithModulo(doubles);
            long temp = interim;
            for (int j = 0; j < LETTER_COUNT; j++) {
                if (doubleCharCount[j] <= 1)
                    continue;
                long x = extendedEulideanModMultiplicativeInverse(factorialWithModulo(doubleCharCount[j]));
                temp = (long) (temp * x) % MODULO_BASE;
            }
            result = temp;
            if (singles > 0) {
                result = (singles * temp) % MODULO_BASE;
            }
        } else {
            result = singles;
        }
        return (int) result;
    }

    private static long factorialWithModulo(long n) {
        if (factorialStore.containsKey(n))
            return factorialStore.get(n);
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result = (result * i) % MODULO_BASE;
        }
        factorialStore.put(n, result);
        return result;
    }

    private static long extendedEulideanModMultiplicativeInverse(long n) {
        if (modInverseStore.containsKey(n))
            return modInverseStore.get(n);
        long t = 0;
        long newT = 1;
        long r = MODULO_BASE;
        long newR = (long) n;
        while (newR != 0) {
            long quotient = r / newR;
            long t1 = t - quotient * newT;
            t = newT;
            newT = t1;
            long r1 = r - quotient * newR;
            r = newR;
            newR = r1;
        }
        if (t < 0)
            t = t + MODULO_BASE;
        modInverseStore.put(n, t);
        return t;
    }

    public static void main(String[] args) {
        initialize("madamimadam");
        System.out.println(answerQuery(4, 7)); // 2
        initialize("week");
        System.out.println(answerQuery(1, 4)); // 2
        System.out.println(answerQuery(2, 3)); // 1
        initialize("abab");
        System.out.println(answerQuery(1, 4)); // 2
        initialize(
                "daadabbadcabacbcccbdcccdbcccbbaadcbabbdaaaabbbdabdbbdcadaaacaadadacddabbbbbdcccbaabbbacacddbbbcbbdbd");
        System.out.println(answerQuery(72, 75));
        initialize("wldsfubcsxrryqpqyqqxrlffumtuwymbybnpemdiwyqz");
        System.out.println(answerQuery(3, 41)); // 740299127
    }
}
