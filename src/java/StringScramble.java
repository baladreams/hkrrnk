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

// solution for https://www.hackerrank.com/challenges/encryption/problem

class StringScramble {

    /*
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING. The function accepts STRING s as
     * parameter.
     */

    public static String encryption(String s) {
        // Write your code here
        StringBuilder returnString = new StringBuilder();
        s = s.replace(" ", "");
        double floor = Math.floor(Math.sqrt(s.length()));
        double ceil = Math.ceil(Math.sqrt(s.length()));
        double row = 0;
        double col = 0;
        if (floor * floor >= s.length()) {
            row = col = floor;
        } else if (floor * ceil >= s.length()) {
            row = floor;
            col = ceil;
        } else {
            row = ceil;
            col = ceil;
        }
        for (double i = 0; i < col; i++) {
            for (double j = 0; j < row; j++) {
                double pos = i + (j * col);
                if (pos < s.length())
                    returnString.append((s.charAt((int) pos)));
                else
                    break;
            }
            returnString.append(' ');
        }
        return returnString.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println("imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau"
                .equals(encryption("if man was meant to stay on the ground god would have given us roots")));
        System.out.println("hae and via ecy".equals(encryption("haveaniceday")));
        System.out.println("fto ehg ee dd".equals(encryption("feedthedog")));
        System.out.println("clu hlt io".equals(encryption("chillout")));
    }

}
