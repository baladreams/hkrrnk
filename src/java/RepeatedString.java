import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RepeatedString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {

        long times = n/Long.valueOf(s.length());
        long remaining = n%s.length();
        long totala =0, remnanta=0;
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)=='a'){
                if(i<remaining) remnanta+=1;
                totala+=1;
            }
        }
        return (totala * times) + remnanta;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        long result = repeatedString("a", 1000000000000l);
        System.out.println(result);
    }
}
