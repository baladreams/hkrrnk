import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// solution for https://www.hackerrank.com/challenges/cube-summation/problem

public class CubeSummation {

    static class PrefixTree {
        BigInteger[][][] tree;
        int xDim, yDim, zDim;

        PrefixTree(int xDim, int yDim, int zDim) {
            tree = new BigInteger[xDim + 1][yDim + 1][zDim + 1];
            this.xDim = xDim;
            this.yDim = yDim;
            this.zDim = zDim;
            tree[0][0][0] = BigInteger.ZERO;
        }

        void update(int x, int y, int z, BigInteger num) {
            for (int i = x; i < xDim; i += LSB(i)) {
                for (int j = y; j < yDim; j += LSB(j)) {
                    for (int k = z; k < zDim; k += LSB(k)) {
                        if (tree[i][j][k] == null)
                            tree[i][j][k] = BigInteger.ZERO;
                        tree[i][j][k] = tree[i][j][k].add(num);
                    }
                }
            }
        }

        BigInteger sum(int x, int y, int z) {
            BigInteger sum = tree[0][0][0];
            for (int i = x; i != 0; i -= LSB(i)) {
                for (int j = y; j != 0; j -= LSB(j)) {
                    for (int k = z; k != 0; k -= LSB(k)) {
                        if (tree[i][j][k] != null)
                            sum = sum.add(tree[i][j][k]);
                    }
                }
            }
            return sum;
        }

        private int LSB(int k) {
            return k & -k;
        }

        BigInteger query(int x1, int y1, int z1, int x2, int y2, int z2) {
            BigInteger sum = BigInteger.ZERO;
            sum = sum.add(sum(x2, y2, z2));
            if (x1 - 1 > 0)
                sum = sum.subtract(sum(x1 - 1, y2, z2));
            if (y1 - 1 > 0)
                sum = sum.subtract(sum(x2, y1 - 1, z2));
            if (x1 - 1 > 0 && y1 - 1 > 0)
                sum = sum.add(sum(x1 - 1, y1 - 1, z2));
            if (z1 - 1 > 0) {
                sum = sum.subtract(sum(x2, y2, z1 - 1));
                if (x1 - 1 > 0)
                    sum = sum.add(sum(x1 - 1, y2, z1 - 1));
                if (y1 - 1 > 0)
                    sum = sum.add(sum(x2, y1 - 1, z1 - 1));
                if (x1 - 1 > 0 && y1 - 1 > 0)
                    sum = sum.subtract(sum(x1 - 1, y1 - 1, z1 - 1));
            }
            return sum;
        }
    }

    public static List<BigInteger> cubeSum_fenwick(int n, List<String> operations) {
        List<BigInteger> sums = new ArrayList<>();
        PrefixTree pTree = new PrefixTree(n, n, n);
        for (String op : operations) {
            String[] opVals = op.split(" ");
            if ("UPDATE".equals(opVals[0])) {
                pTree.update(Integer.parseInt(opVals[1]) - 1, Integer.parseInt(opVals[2]) - 1,
                        Integer.parseInt(opVals[3]) - 1,
                        new BigInteger(opVals[4]));
            } else if ("QUERY".equals(opVals[0])) {
                sums.add(pTree.query(Integer.parseInt(opVals[1]) - 1, Integer.parseInt(opVals[2]) - 1,
                        Integer.parseInt(opVals[3]) - 1, Integer.parseInt(opVals[4]) - 1,
                        Integer.parseInt(opVals[5]) - 1,
                        Integer.parseInt(opVals[6]) - 1));
            }
        }
        return sums;
    }

    public static List<BigInteger> cubeSum(int n, List<String> operations) {
        List<BigInteger> sums = new ArrayList<>();
        HashMap<Integer, HashMap<Integer, HashMap<Integer, BigInteger>>> cube = new HashMap<>();
        for (String op : operations) {
            String[] opVals = op.split(" ");
            if ("UPDATE".equals(opVals[0])) {
                cube.computeIfAbsent(Integer.parseInt(opVals[1]), HashMap::new)
                        .computeIfAbsent(Integer.parseInt(opVals[2]), HashMap::new)
                        .put(Integer.parseInt(opVals[3]), new BigInteger(opVals[4]));
            } else {
                BigInteger sum = sum(cube, opVals);
                sums.add(sum);
            }
        }
        return sums;
    }

    private static BigInteger sum(HashMap<Integer, HashMap<Integer, HashMap<Integer, BigInteger>>> cube,
            String[] opVals) {
        BigInteger sum = BigInteger.ZERO;
        int i1 = Integer.parseInt(opVals[1]);
        int i2 = Integer.parseInt(opVals[4]);
        int j1 = Integer.parseInt(opVals[2]);
        int j2 = Integer.parseInt(opVals[5]);
        int k1 = Integer.parseInt(opVals[3]);
        int k2 = Integer.parseInt(opVals[6]);
        for (Integer i : cube.keySet()) {
            if (i >= i1 && i <= i2) {
                for (Integer j : cube.get(i).keySet()) {
                    if (j >= j1 && j <= j2) {
                        for (Integer k : cube.get(i).get(j).keySet()) {
                            if (k >= k1 && k <= k2) {
                                sum = sum.add(cube.get(i).get(j).get(k));
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] input = new String[] { "1",
        "4 5",
        "UPDATE 2 2 2 4",
        "QUERY 1 1 1 3 3 3",
        "UPDATE 1 1 1 23",
        "QUERY 2 2 2 4 4 4",
        "QUERY 1 1 1 3 3 3" };
        int line = 1;
        int t = Integer.parseInt(input[0]);
        for (int i = 0; i < t; i++) {
            String[] def = input[line].split(" ");
            line++;
            List<String> ops = new LinkedList<>();
            for (int x = 0; x < Integer.parseInt(def[1]); x++) {
                ops.add(input[line]);
                line++;
            }
            List<BigInteger> sums = cubeSum(Integer.parseInt(def[0]), ops);
            for (BigInteger sum : sums) {
                System.out.println(sum);
            }
        }
    }
}
