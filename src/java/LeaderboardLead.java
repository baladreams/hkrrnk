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

// solution for https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?h_r=next-challenge&h_v=zen

class LeaderboardLead {

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        List<Integer> ranks = new ArrayList<>();
        Collections.reverse(player);
        int r = 0;
        int x = 0;
        int y = 0;
        int lastRanked = 0;
        while (x < player.size()) {
            while (y < ranked.size() && player.get(x) < ranked.get(y)) {
                if (lastRanked != ranked.get(y)) {
                    r++;
                }
                lastRanked = ranked.get(y);
                y++;
            }
            ranks.add(r + 1);
            x++;
        }
        Collections.reverse(ranks);
        return ranks;
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(climbingLeaderboard(arrayToList(new int[] { 100, 100, 50, 40, 40, 20, 10 }),
                arrayToList(new int[] { 5, 25, 50, 120 })));
        System.out.println(climbingLeaderboard(arrayToList(new int[] { 100, 90, 90, 80, 75, 60 }),
                arrayToList(new int[] { 50, 65, 77, 90, 102 })));
    }

}
