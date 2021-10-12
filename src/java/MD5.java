import java.io.*;

// solution for https://www.hackerrank.com/challenges/java-md5/problem

public class MD5 {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        try {
            java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("MD5");
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            String input = "HelloWorld";// br.readLine();
            md5.update(input.getBytes());
            byte[] digest = md5.digest();
            StringBuffer hexString = new StringBuffer();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println(hexString.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
