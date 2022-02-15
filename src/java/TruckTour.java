import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// solution for https://www.hackerrank.com/challenges/truck-tour/problem

/**
 * TruckTour
 */
public class TruckTour {

    public static int truckTour(List<List<Integer>> petrolpumps) {
        // Write your code here
        int i = 0;
        int petrolGauge = petrolpumps.get(0).get(0);
        int startPump = 0;
        int pumpsCovered = 0;
        do {
            int petrolToIndex = petrolpumps.get(i % petrolpumps.size()).get(1);
            int index = ++i % petrolpumps.size();
            int petrolAtIndex = petrolpumps.get(index).get(0);
            petrolGauge -= petrolToIndex;
            pumpsCovered++;
            while (petrolGauge < 0 && pumpsCovered > 0) {
                int lostPetrol = petrolpumps.get(startPump).get(0);
                int unusedPetrol = petrolpumps.get(startPump).get(1);
                petrolGauge -= lostPetrol;
                petrolGauge += unusedPetrol;
                startPump++;
                pumpsCovered--;
            }
            petrolGauge += petrolAtIndex;
        } while (pumpsCovered < petrolpumps.size() - 1 && startPump < petrolpumps.size());
        return startPump < petrolpumps.size() ? startPump : -1;
    }

    public static void main(String[] args) {
        TruckTour truckTour = new TruckTour();
        int[][] pumps = new int[][] { { 1, 5 }, { 10, 3 }, { 3, 4 } };
        System.out.println(truckTour.truckTour(Arrays.stream(pumps)
                .map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList())).collect(Collectors.toList())));
    }

}
