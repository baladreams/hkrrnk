import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JumpingOnClouds {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        int startPos = 0, jumps = 0;
        while (startPos < c.length - 1) {
            if (startPos + 2 > c.length - 1 || c[startPos + 2] == 1)
                startPos += 1;
            else
                startPos += 2;
            jumps++;
        }
        return jumps;
    }

    public static void main(String[] args) throws IOException {

        String[] cItems = "0 0 0 1 0 0".split(" ");
        int[] c = new int[cItems.length];
        for (int i = 0; i < c.length; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        System.out.println(result);
    }
}
