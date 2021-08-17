import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//solution for https://www.hackerrank.com/challenges/magic-square-forming/problem

class MagicSquare {

    /*
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER. The function accepts
     * 2D_INTEGER_ARRAY s as parameter.
     */

    public static int formingMagicSquare(List<List<Integer>> s) {
        // Write your code here
        int cost = 0;
        int[][][] borderSets = new int[][][] { { { 4, 3, 8 }, { 8, 3, 4 } }, { { 6, 1, 8 }, { 8, 1, 6 } },
                { { 2, 9, 4 }, { 4, 9, 2 } }, { { 2, 7, 6 }, { 6, 7, 2 } } };
        int[][][] centerSets = new int[][][] { { { 3, 5, 7 }, { 7, 5, 3 } }, { { 1, 5, 9 }, { 9, 5, 1 } } };
        int[][] targetMatrix = convertToArray(s);
        int[][] transposeTargetMatrix = transposeMatrix(targetMatrix);
        cost = Math.min(
                Math.min(
                        Math.min(
                                diffSet(targetMatrix[0], borderSets[0][0]) + diffSet(targetMatrix[2], borderSets[3][0])
                                        + diffSet(targetMatrix[1], centerSets[1][1]),
                                diffSet(targetMatrix[0], borderSets[0][1]) + diffSet(targetMatrix[2], borderSets[3][1])
                                        + diffSet(targetMatrix[1], centerSets[1][0])),
                        Math.min(
                                diffSet(transposeTargetMatrix[0], borderSets[0][0])
                                        + diffSet(transposeTargetMatrix[2], borderSets[3][0])
                                        + diffSet(transposeTargetMatrix[1], centerSets[1][1]),
                                diffSet(transposeTargetMatrix[0], borderSets[0][1])
                                        + diffSet(transposeTargetMatrix[2], borderSets[3][1])
                                        + diffSet(transposeTargetMatrix[1], centerSets[1][0]))),
                Math.min(
                        Math.min(
                                diffSet(targetMatrix[0], borderSets[3][0]) + diffSet(targetMatrix[2], borderSets[0][0])
                                        + diffSet(targetMatrix[1], centerSets[1][1]),
                                diffSet(targetMatrix[0], borderSets[3][1]) + diffSet(targetMatrix[2], borderSets[0][1])
                                        + diffSet(targetMatrix[1], centerSets[1][0])),
                        Math.min(
                                diffSet(transposeTargetMatrix[0], borderSets[3][0])
                                        + diffSet(transposeTargetMatrix[2], borderSets[0][0])
                                        + diffSet(transposeTargetMatrix[1], centerSets[1][1]),
                                diffSet(transposeTargetMatrix[0], borderSets[3][1])
                                        + diffSet(transposeTargetMatrix[2], borderSets[0][1])
                                        + diffSet(transposeTargetMatrix[1], centerSets[1][0]))));
        return cost;
    }

    public static int diffSet(int[] given, int[] target) {
        return Math.abs(given[0] - target[0]) + Math.abs(given[1] - target[1]) + Math.abs(given[2] - target[2]);
    }

    public static int[][] convertToArray(List<List<Integer>> s) {
        int[][] ret = new int[3][3];
        ret[0][0] = s.get(0).get(0);
        ret[0][1] = s.get(0).get(1);
        ret[0][2] = s.get(0).get(2);
        ret[1][0] = s.get(1).get(0);
        ret[1][1] = s.get(1).get(1);
        ret[1][2] = s.get(1).get(2);
        ret[2][0] = s.get(2).get(0);
        ret[2][1] = s.get(2).get(1);
        ret[2][2] = s.get(2).get(2);
        return ret;
    }

    public static int[][] transposeMatrix(int[][] in) {
        int[][] ret = new int[3][3];
        ret[0][0] = in[0][0];
        ret[0][1] = in[1][0];
        ret[0][2] = in[2][0];
        ret[1][0] = in[0][1];
        ret[1][1] = in[1][1];
        ret[1][2] = in[2][1];
        ret[2][0] = in[0][2];
        ret[2][1] = in[1][2];
        ret[2][2] = in[2][2];
        return ret;
    }

    public static List<List<Integer>> transformInput(int[][] in) {
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            l.add(i, new ArrayList<>());
            for (int j = 0; j < in[i].length; j++) {
                l.get(i).add(in[i][j]);
            }
        }
        return l;
    }

    public static int maxthree(int n1, int n2, int n3) {
        return Math.max(n1, Math.max(n2, n3));
    }

    public static void main(String[] args) throws IOException {
        // 7
        System.out.println(MagicSquare
                .formingMagicSquare(MagicSquare.transformInput(new int[][] { { 5, 3, 4 }, { 1, 5, 8 }, { 6, 4, 2 } })));
        // 21
        System.out.println(MagicSquare
                .formingMagicSquare(MagicSquare.transformInput(new int[][] { { 2, 9, 8 }, { 4, 2, 7 }, { 5, 6, 7 } })));
        // 1
        System.out.println(MagicSquare
                .formingMagicSquare(MagicSquare.transformInput(new int[][] { { 4, 9, 2 }, { 3, 5, 7 }, { 8, 1, 5 } })));
        // 4
        System.out.println(MagicSquare
                .formingMagicSquare(MagicSquare.transformInput(new int[][] { { 4, 8, 2 }, { 4, 5, 7 }, { 6, 1, 6 } })));

        // 21
        System.out.println(MagicSquare
                .formingMagicSquare(MagicSquare.transformInput(new int[][] { { 4, 1, 5 }, { 7, 6, 4 }, { 7, 2, 2 } })));
    }

}
