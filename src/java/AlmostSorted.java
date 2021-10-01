import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// solution for https://www.hackerrank.com/challenges/almost-sorted/problem

class AlmostSorted {

    /*
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
        // Write your code here
        Stack<Integer> s = new Stack();
        boolean possible = true;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                if (s.size() < 2) {
                    s.push(i);
                } else {
                    int top = s.peek();
                    if (i - top > 1)
                        possible = false;
                    else
                        s.push(i);
                }
            }
        }
        if (arr.get(arr.size() - 1) < arr.get(arr.size() - 2)) {
            if (s.size() < 2) {
                s.push(arr.size() - 1);
            } else {
                int top = s.peek();
                if (arr.size() - 1 - top > 1)
                    possible = false;
                else
                    s.push(arr.size() - 1);
            }
        }
        if (s.size() == 0)
            System.out.println("yes");
        else if (s.size() == 1) {
            int temp = s.pop();
            if (temp == 0 || (arr.get(temp - 1) > arr.get(temp + 1)))
                System.out.println("no");
            else {
                System.out.println("yes");
                String r = "";
                temp = temp + 1;
                r = "swap " + temp + " " + (temp + 1);
                System.out.println(r);
            }
        } else {
            if (!possible)
                System.out.println("no");
            else {
                if (s.size() == 2) {
                    System.out.println("yes");
                    String r = "";
                    int temp = s.pop();
                    r = (temp + (((temp + 1) < arr.size()) ? 2 : 1)) + r;
                    r = " " + r;
                    r = (s.pop() + 1) + r;
                    r = " " + r;
                    r = "swap" + r;
                    System.out.println(r);
                } else {
                    System.out.println("yes");
                    String r = "";
                    int temp = s.pop();
                    r = (temp + (((temp + 1) < arr.size()) ? 2 : 1)) + r;
                    r = " " + r;
                    while (s.size() > 1)
                        s.pop();
                    r = (s.pop() + 1) + r;
                    r = " " + r;
                    r = "reverse" + r;
                    System.out.println(r);
                }
            }
        }
    }

    public static List<Integer> arrayToList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int c = 0; c < a.length; c++) {
            l.add(a[c]);
        }
        return l;
    }

    public static void main(String[] args) {
        // swap 3 4
        almostSorted(arrayToList(new int[] { 2, 3, 5, 4 }));
        // swap 1 2
        almostSorted(arrayToList(new int[] { 4, 2 }));
        // no
        almostSorted(arrayToList(new int[] { 3, 1, 2 }));
        // reverse 2 5
        almostSorted(arrayToList(new int[] { 1, 5, 4, 3, 2, 6 }));
        // swap 3 4
        almostSorted(arrayToList(new int[] { 1, 2, 4, 3, 5, 6 }));
        // no
        almostSorted(arrayToList(new int[] { 43, 65, 1, 98, 99, 101 }));
    }

}
