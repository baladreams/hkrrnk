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

// solution for https://www.hackerrank.com/challenges/acm-icpc-team/problem

class ACMICPCTeam {

    /*
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY. The function accepts
     * STRING_ARRAY topic as parameter.
     */

    public static List<Integer> acmTeam(List<String> topic) {
        // Write your code here
        int maxTopics = 0;
        int maxTeams = 0;
        List<Integer> returnValue = new ArrayList<>(2);
        for (int i = 0; i < topic.size(); i++) {
            String subjectI = topic.get(i);
            for (int j = i + 1; j < topic.size(); j++) {
                String subjectJ = topic.get(j);
                int combo = 0;
                for (int x = 0; x < subjectJ.length(); x++) {
                    if (subjectI.charAt(x) == '1' || subjectJ.charAt(x) == '1') {
                        combo++;
                    }
                }
                if (combo > maxTopics) {
                    maxTopics = combo;
                    maxTeams = 1;
                } else if (combo == maxTopics) {
                    maxTeams++;
                }
            }
        }
        returnValue.add(maxTopics);
        returnValue.add(maxTeams);
        return returnValue;
    }

    public static List<String> arrayToList(String[] a) {
        List<String> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void printList(List<Integer> s) {
        s.stream().forEach(x -> System.out.print(x + ","));
        System.out.println();
    }

    public static void main(String[] args) {
        printList(acmTeam(arrayToList(new String[] { "10101", "11110", "00010" })));
        printList(acmTeam(arrayToList(new String[] { "10101", "11100", "11010", "00101" })));
    }

}
