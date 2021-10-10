import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// solution for https://www.hackerrank.com/challenges/duplicate-word/problem

public class DuplicateWords {

    public static void main(String[] args) {

        String regex = "\\b(\\w+)(\\s+\\b\\1\\b)+"; //"(\\s+\\w+)(\\1)+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
        String[] inputs = new String[] { "Goodbye bye bye world world world", "Sam went went to to to his business",
                "Reya is is the the best player in eye eye game", "in inthe", "Hello hello Ab aB" };
        int numSentences = inputs.length; // Integer.parseInt(in.nextLine());
        while (numSentences-- > 0) {
            String input = inputs[inputs.length-1-numSentences];// in.nextLine();

            Matcher m = p.matcher(input);

            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
                input = input.replaceAll(m.group(), m.group(1));
            }

            // Prints the modified sentence.
            System.out.println(input);
        }

        in.close();
    }
}
