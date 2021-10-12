import java.io.*;

// solution for https://www.hackerrank.com/challenges/sha-256/problem

public class SHA256 {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        try {
            java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("SHA-256");
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            String input = "Javarmi123";// br.readLine();
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
