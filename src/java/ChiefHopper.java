import java.util.Arrays;
import java.util.List;

// solution for https://www.hackerrank.com/challenges/chief-hopper/problem

public class ChiefHopper {
    public static int chiefHopper(List<Integer> arr) {
        // Write your code here
        int minEnergy = (int) Math.ceil(arr.get(arr.size() - 1) / 2d);
        for (int i = arr.size() - 2; i >= 0; i--) {
            minEnergy = (int) Math.ceil((arr.get(i) + minEnergy) / 2d);
        }
        return minEnergy;
    }

    public static void main(String[] args) {
        System.out.println(chiefHopper(Arrays.asList(new Integer[] { 2, 3, 4, 3, 2 }))); // 3
        System.out.println(chiefHopper(Arrays.asList(new Integer[] { 3, 4, 3, 2, 4 }))); // 4
        System.out.println(chiefHopper(Arrays.asList(new Integer[] { 4, 4, 4 }))); // 4
        System.out.println(chiefHopper(Arrays.asList(new Integer[] { 1, 6, 4 }))); // 3
        System.out.println(chiefHopper(Arrays.asList(new Integer[] { 4, 4 }))); // 3
        System.out.println(chiefHopper(Arrays.asList(new Integer[] { 5 }))); // 3
    }
}
