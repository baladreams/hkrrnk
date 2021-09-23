
// solution for https://www.hackerrank.com/challenges/taum-and-bday/problem

class TaumDiksha {

    /*
     * Complete the 'taumBday' function below.
     *
     * The function is expected to return a LONG_INTEGER. The function accepts
     * following parameters: 1. INTEGER b 2. INTEGER w 3. INTEGER bc 4. INTEGER wc
     * 5. INTEGER z
     */

    public static long taumBday(int b, int w, int bc, int wc, int z) {
        // Write your code here
        long solution = 0;
        int cheap = bc > wc ? wc : bc;
        int costly = bc > wc ? bc : wc;
        long longB = b;
        long longW = w;
        if ((cheap + z) < costly) {
            solution = (longB + longW) * cheap + ((cheap == bc) ? longW * z : longB * z);
        } else {
            solution = longB * bc + longW * wc;
        }
        return solution;
    }

    public static void main(String[] args) {
        // 29
        System.out.println(taumBday(3, 5, 3, 4, 1));
        // 20
        System.out.println(taumBday(10, 10, 1, 1, 1));
        // 37
        System.out.println(taumBday(5, 9, 2, 3, 4));
        // 12
        System.out.println(taumBday(3, 6, 9, 1, 1));
        // 35
        System.out.println(taumBday(7, 7, 4, 2, 1));
        // 12
        System.out.println(taumBday(3, 3, 1, 9, 2));
        // 7201244
        System.out.println(taumBday(384, 887, 2778, 6916, 7794));
        // 906753
        System.out.println(taumBday(336, 387, 493, 6650, 1422));
        // 2841792
        System.out.println(taumBday(363, 28, 8691, 60, 7764));
        // 8134553
        System.out.println(taumBday(927, 541, 3427, 9173, 5737));
        // 2917086
        System.out.println(taumBday(212, 369, 2568, 6430, 5783));
        // 6231528
        System.out.println(taumBday(531, 863, 5124, 4068, 3136));
        // 6197767
        System.out.println(taumBday(930, 803, 4023, 3059, 3070));
        // 3395504
        System.out.println(taumBday(168, 394, 8457, 5012, 8043));
        // 2857140
        System.out.println(taumBday(230, 374, 4422, 4920, 3785));
        // 3981734
        System.out.println(taumBday(538, 199, 4325, 8316, 4371));
        System.out.println(taumBday(27984, 1402, 619246, 615589, 247954));
    }

}
