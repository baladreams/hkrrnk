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

// solution for https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem

class SherlockAnagrams {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER. The function accepts STRING s
     * as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
        // Write your code here
        int anagramSets = 0;
        for (int len = 1; len < s.length(); len++) {
            List<String> substrings = new ArrayList<>();
            for (int i = 0; i <= s.length() - len; i++) {
                substrings.add(s.substring(i, i + len));
            }
            for (int i = 0; i < substrings.size(); i++) {
                Map<Character, Integer> curCharMap = characterize(substrings.get(i));
                for (int j = i + 1; j < substrings.size(); j++) {
                    Map<Character, Integer> charMap = characterize(substrings.get(j));
                    if (assertAnagrams(curCharMap, charMap))
                        anagramSets++;
                }
            }
        }
        return anagramSets;
    }

    public static Map<Character, Integer> characterize(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!charMap.containsKey(c))
                charMap.put(c, 1);
            else
                charMap.put(c, charMap.get(c) + 1);
        }
        return charMap;
    }

    public static boolean assertAnagrams(Map<Character, Integer> cMapOne, Map<Character, Integer> cMapTwo) {
        if (cMapOne.size() != cMapTwo.size())
            return false;
        for (char c : cMapOne.keySet()) {
            if (!cMapTwo.containsKey(c))
                return false;
            if (cMapOne.get(c).intValue() != cMapTwo.get(c).intValue())
                return false;
        }
        return true;
    }

    public static int factorial(int n) {
        int returnVal = 1;
        for (int i = 1; i <= n; i++) {
            returnVal *= i;
        }
        return returnVal;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(sherlockAndAnagrams("mom"));
        // 4
        System.out.println(sherlockAndAnagrams("abba"));
        // 0
        System.out.println(sherlockAndAnagrams("abcd"));
        // 3
        System.out.println(sherlockAndAnagrams("ifailuhkqq"));
        // 10
        System.out.println(sherlockAndAnagrams("kkkk"));
        // 5
        System.out.println(sherlockAndAnagrams("cdcd"));
    }

}
