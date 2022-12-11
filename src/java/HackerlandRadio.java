import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// solution for https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem

public class HackerlandRadio {
    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        // Write your code here
        Collections.sort(x);
        int minRadio = 0;
        List<Integer> radios = new LinkedList<>();
        List<Integer> houseWindow = new ArrayList<>();
        int lastPos = -1;
        for (int i : x) {
            if (lastPos == i)
                continue;
            else
                lastPos = i;
            if (houseWindow.isEmpty() || (2 * k + houseWindow.get(0)) >= i) {
                houseWindow.add(i);
            } else {
                int minHouse = houseWindow.get(0);
                int maxHouse = (minHouse + 2 * k);
                int midHouse = (minHouse + maxHouse) / 2;
                int bestHousePostion = Collections.binarySearch(houseWindow, midHouse);
                minRadio++;
                int radioHouse = -1;
                if (bestHousePostion >= 0) {
                    radioHouse = houseWindow.get(bestHousePostion);
                } else {
                    radioHouse = houseWindow.get(-bestHousePostion - 2);
                }
                radios.add(radioHouse);
                int maxCovered = k + radioHouse;
                int retainHouseIndex = Collections.binarySearch(houseWindow, maxCovered);
                List<Integer> newHouseWindow = new ArrayList<>();
                if (retainHouseIndex < 0)
                    retainHouseIndex = -retainHouseIndex - 2;
                if (retainHouseIndex < houseWindow.size() - 1)
                    newHouseWindow.addAll(houseWindow.subList(retainHouseIndex + 1, houseWindow.size()));
                houseWindow = newHouseWindow;
                houseWindow.add(i);
            }
        }
        while (!houseWindow.isEmpty()) {
            int minHouse = houseWindow.get(0);
            int maxHouse = (minHouse + 2 * k);
            int midHouse = (minHouse + maxHouse) / 2;
            int bestHousePostion = Collections.binarySearch(houseWindow, midHouse);
            minRadio++;
            int radioHouse = -1;
            if (bestHousePostion >= 0) {
                radioHouse = houseWindow.get(bestHousePostion);
            } else {
                radioHouse = houseWindow.get(-bestHousePostion - 2);
            }
            radios.add(radioHouse);
            int maxCovered = k + radioHouse;
            int retainHouseIndex = Collections.binarySearch(houseWindow, maxCovered);
            List<Integer> newHouseWindow = new ArrayList<>();
            if (retainHouseIndex < 0)
                retainHouseIndex = -retainHouseIndex - 2;
            if (retainHouseIndex < houseWindow.size() - 1)
                newHouseWindow.addAll(houseWindow.subList(retainHouseIndex + 1, houseWindow.size()));
            houseWindow = newHouseWindow;
        }
        return minRadio;
    }

    public static void main(String[] args) {
        System.out.println(hackerlandRadioTransmitters(Arrays.asList(new Integer[] { 1, 2, 3, 5, 9 }), 1)); // 3
        System.out.println(hackerlandRadioTransmitters(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }), 1)); // 2
        System.out.println(hackerlandRadioTransmitters(Arrays.asList(new Integer[] { 7, 2, 4, 6, 5, 9, 12, 11 }), 2)); // 3
        System.out.println(hackerlandRadioTransmitters(Arrays.asList(new Integer[] { 1 }), 1)); // 1
        System.out.println(hackerlandRadioTransmitters(Arrays.asList(new Integer[] { 2, 2, 2, 2, 1, 1, 1, 1 }), 1)); // 1
        try (BufferedReader fr = new BufferedReader(new FileReader("resources/HackerlandRadio_in.txt"))) {
            List<Integer> houses = Stream.of(fr.readLine().split(" ")).map(Integer::parseInt)
                    .collect(Collectors.toList());
            System.out.println(hackerlandRadioTransmitters(houses, 80));
        } catch (Exception e) {
        }
        System.out.println(hackerlandRadioTransmitters(Arrays.asList(new Integer[] { 9, 5, 4, 2, 6, 15, 12 }), 2)); // 4
    }
}
