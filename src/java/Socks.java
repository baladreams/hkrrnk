import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Socks {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        int sockPairs = 0;
        int[] socks = new int[100];
        for(int i=0;i<socks.length;i++) socks[i]=0;
        for(int i=0; i<ar.length; i++){
            socks[ar[i]-1]++;
        }
        for(int i=0;i<socks.length;i++) sockPairs+=socks[i]/2;
        return sockPairs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] arItems = "1 2 1 2 1 3 2".split(" ");
        int[] ar = new int[arItems.length];

        for (int i = 0; i < arItems.length; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(ar.length, ar);
        System.out.println(result);

    }
}
