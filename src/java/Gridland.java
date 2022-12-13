import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// solution for https://www.hackerrank.com/challenges/gridland-metro/problem

public class Gridland {

    public static long gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
        // Write your code here
        long spaces = 0;
        Map<Integer, List<List<Integer>>> trackMap = new HashMap<>();
        for (List<Integer> t : track) {
            trackMap.computeIfAbsent(t.get(0), key -> new ArrayList<List<Integer>>())
                    .add(Arrays.asList(t.get(1), t.get(2)));
        }
        for (List<List<Integer>> t : trackMap.values()) {
            spaces += consolidateTracks(t, m);
        }
        spaces += 1l * (n - trackMap.size()) * m;
        return spaces;
    }

    public static long consolidateTracks(List<List<Integer>> tracks, int m) {
        Collections.sort(tracks, (t1, t2) -> t1.get(0) - t2.get(0));
        List<Integer> currentTrack = tracks.get(0);
        long spaces = 0;
        spaces += currentTrack.get(0) - 1;
        for (int i = 1; i < tracks.size(); i++) {
            if (currentTrack.get(1) >= tracks.get(i).get(0)) {
                currentTrack.set(1, Math.max(currentTrack.get(1), tracks.get(i).get(1)));
            } else if (tracks.get(i).get(0) > currentTrack.get(1)) {
                spaces += tracks.get(i).get(0) - (currentTrack.get(1) + 1);
                currentTrack = tracks.get(i);
            }
        }
        spaces += m - currentTrack.get(1);
        return spaces;
    }

    public static void main(String[] args) {
        System.out.println(gridlandMetro(4, 4, 3, Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(3, 1, 4),
                Arrays.asList(4, 4, 4)))); // 9
        System.out.println(gridlandMetro(2, 9, 3,
                Arrays.asList(Arrays.asList(2, 1, 5), Arrays.asList(2, 2, 4), Arrays.asList(2, 8, 8)))); // 12
    }
}
