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

class Result {

    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. INTEGER steps 2. STRING path
     */

    public static int countingValleys(int steps, String path) {
        int level = 0, valley = 0;
        for(int i=0; i<steps ; i++){
            switch(path.charAt(i)){
                case 'U' : if(level+1==0) valley++; level++; break;
                default: level--;
            }
        }
        return valley;
    }

}

public class Valley {
    public static void main(String[] args) throws IOException {
        int result = Result.countingValleys(8, "UDDDUDUU");
        System.out.println(result);
    }
}
