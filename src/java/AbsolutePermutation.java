import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// solution for https://www.hackerrank.com/challenges/absolute-permutation/problem

public class AbsolutePermutation {
    public static List<Integer> absolutePermutation(int n, int k) {
        List<Integer> negativeOne = new ArrayList<>();
        negativeOne.add(-1);
        List<Integer> permutation = firstLexicalPermutation(n, k);
        return permutation != null && !permutation.isEmpty() ? permutation : negativeOne;
    }

    private static List<Integer> firstLexicalPermutation(int n, int k) {
        int[] used = new int[n + 1];
        List<Integer> conflicts = new ArrayList<>();
        // boolean permFound = isPossibleRecursive(1, k, used, conflicts);
        boolean permFound = isPossibleNonRecursive(k, used);
        if (permFound) {
            int[] number = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                number[used[i]] = i;
            }
            List<Integer> numberList = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                numberList.add(number[i]);
            }
            return numberList;
        }
        return new ArrayList<>();
    }

    private static boolean isPossibleNonRecursive(int k, int[] used) {
        boolean possible = true;
        int i = 1;
        int[] options = new int[used.length];
        List<Integer> conflicts = new ArrayList<>();
        while (i < used.length) {
            int option = options[i] == 0 ? i - k : i + k;
            if (option > 0 && option < used.length && used[option] == 0) {
                used[option] = i;
                options[i]++;
                i++;
            } else {
                if (option > 0 && option < used.length && used[option] == i) {
                    used[option] = 0;
                }
                if (!conflicts.isEmpty() && conflicts.stream().allMatch(x -> used[x] != 0)) {
                    options[i] = 0;
                    i--;
                } else {
                    if (options[i] == 2) {
                        possible = false;
                        break;
                    }
                    if (options[i] < 2) {
                        options[i]++;
                    } else {
                        options[i] = 0;
                        if (option > 0 && option < used.length && used[option] != i)
                            conflicts.add(option);
                        else if (option > 0 && option < used.length && used[option] == i) {
                            conflicts.remove(option);
                        }
                        i--;
                    }
                }
            }
        }
        return possible;
    }

    private static boolean isPossibleRecursive(int currentIndex, int k, int[] used, List<Integer> conflicts) {
        boolean possible = false;
        if (currentIndex >= used.length)
            possible = true;
        int optionOne = currentIndex - k;
        int optionTwo = currentIndex + k;
        if (!possible && optionOne > 0 && optionOne < used.length && used[optionOne] == 0) {
            System.out.println("CurrentIndex: " + currentIndex + ", optionOne: " + optionOne);
            used[optionOne] = currentIndex;
            possible |= isPossibleRecursive(currentIndex + 1, k, used, conflicts);
        }
        if (!possible && optionOne > 0 && optionOne < used.length && used[optionOne] == currentIndex)
            used[optionOne] = 0;
        if (!conflicts.isEmpty() && conflicts.stream().allMatch(x -> used[x] != 0))
            return possible;
        else
            conflicts.clear();
        if (!possible && optionTwo > 0 && optionTwo < used.length && used[optionTwo] == 0) {
            System.out.println("CurrentIndex: " + currentIndex + ", optionTwo: " + optionTwo);
            used[optionTwo] = currentIndex;
            possible |= isPossibleRecursive(currentIndex + 1, k, used, conflicts);
        }
        if (!possible && optionTwo > 0 && optionTwo < used.length && used[optionTwo] == currentIndex)
            used[optionTwo] = 0;
        if (!conflicts.isEmpty() && conflicts.stream().allMatch(x -> used[x] != 0))
            return possible;
        else
            conflicts.clear();
        if (!possible && conflicts.isEmpty()) {
            if (optionOne > 0 && optionOne < used.length && used[optionOne] != 0)
                conflicts.add(optionOne);
            if (optionTwo > 0 && optionTwo < used.length && used[optionTwo] != 0)
                conflicts.add(optionTwo);
            // System.out.println("Conflicts " + conflicts);
        }
        return possible;
    }

    private static List<Integer> firstLexicalPermutationWithHeapsAlgo(int n, int k) {
        Integer[] number = new Integer[n];
        int[] permuter = new int[n];
        int i = 1;
        while (i <= n) {
            number[i - 1] = i;
            i++;
        }
        i = 0;
        // initial permutation
        if (validPerm(number, k))
            return Arrays.asList(number);
        // non-recursive heap's algorithm
        while (i < n) {
            if (permuter[i] < i) {
                if (i % 2 == 0)
                    swap(number, 0, i);
                else
                    swap(number, permuter[i], i);
                // System.out.println(Arrays.toString(number).replaceAll("\\[|\\]|,|\\s", ""));
                if (validPerm(number, k))
                    return Arrays.asList(number);
                permuter[i] += 1;
                i = 0;
            } else {
                permuter[i] = 0;
                i += 1;
            }
        }
        return null;
    }

    private static boolean validPerm(Integer[] number, int k) {
        for (int i = 0; i < number.length; i++) {
            if (Math.abs(number[i] - (i + 1)) != k - 1)
                return false;
        }
        return true;
    }

    private static void swap(Integer[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    private static boolean nextPermutation(int[] a, int n) {
        int k = a.length;
        for (int i = k - 1; i >= 0; i--) {
            if (a[i] < n - k + i + 1) {
                a[i]++;
                for (int j = i + 1; j < k; j++)
                    a[j] = a[j - 1] + 1;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            int[][] inputArray = new int[][] { {66,60},
            {95,22},
            {48,0},
            {2,0},
            {94,47},
            {80,0},
            {63,0},
            {2,1},
            {65,0},
            {42,0} };
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter("resources/AbsolutePerm_out.txt"));
            for (int[] inArray : inputArray) {
                List<Integer> permutation = absolutePermutation(inArray[0], inArray[1]);
                bufferedWriter.write(
                        permutation.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(" "))
                                + "\n");
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
