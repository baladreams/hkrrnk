import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//solution for https://www.hackerrank.com/challenges/picking-numbers/problem

class PickingNumbers {

    /*
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER. The function accepts
     * INTEGER_ARRAY a as parameter.
     */

    public static int pickingNumbers(List<Integer> a) {
        // Write your code here
        int solution = 0;
        int[] nCount = new int[101];
        for (int i = 0; i < a.size(); i++) {
            nCount[a.get(i)]++;
            if (a.get(i) < 100 && (nCount[a.get(i) + 1] + nCount[a.get(i)] > solution))
                solution = nCount[a.get(i) + 1] + nCount[a.get(i)];
            if (a.get(i) > 0 && (nCount[a.get(i) - 1] + nCount[a.get(i)] > solution))
                solution = nCount[a.get(i) - 1] + nCount[a.get(i)];
        }
        return solution;
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        // 5
        System.out.println(pickingNumbers(arrayToList(new int[] { 1, 1, 2, 2, 4, 4, 5, 5, 5 })));
        // 3
        System.out.println(pickingNumbers(arrayToList(new int[] { 4, 6, 5, 3, 3, 1 })));
        // 5
        System.out.println(pickingNumbers(arrayToList(new int[] { 1, 2, 2, 3, 1, 2 })));
        // 30
        System.out.println(pickingNumbers(arrayToList(new int[] { 7, 12, 13, 19, 17, 7, 3, 18, 9, 18, 13, 12, 3, 13, 7,
                9, 18, 9, 18, 9, 13, 18, 13, 13, 18, 18, 17, 17, 13, 3, 12, 13, 19, 17, 19, 12, 18, 13, 7, 3, 3, 12, 7,
                13, 7, 3, 17, 9, 13, 13, 13, 12, 18, 18, 9, 7, 19, 17, 13, 18, 19, 9, 18, 18, 18, 19, 17, 7, 12, 3, 13,
                19, 12, 3, 9, 17, 13, 19, 12, 18, 13, 18, 18, 18, 17, 13, 3, 18, 19, 7, 12, 9, 18, 3, 13, 13, 9, 7 })));
    }

}
