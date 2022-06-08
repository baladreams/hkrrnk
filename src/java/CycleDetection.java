import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// solution for https://www.hackerrank.com/challenges/detect-whether-a-linked-list-contains-a-cycle/problem

public class CycleDetection {
    static boolean hasCycle(SinglyLinkedListNode head) {
        boolean hasCycle = false;
        SinglyLinkedListNode one = head;
        SinglyLinkedListNode two = head.next == null ? null : head.next.next;
        while (one != null && two != null && !hasCycle) {
            two = two.next == null ? two.next : two.next.next;
            one = one.next;
            if (one == two)
                hasCycle = true;
        }
        return hasCycle;
    }

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }

        public void createCycle(int nodePos1) {
            SinglyLinkedListNode temp = this.head;
            while (nodePos1 > 0 && temp != null) {
                temp = temp.next;
                nodePos1--;
            }
            tail.next = temp;
        }
    }

    public static void main(String[] args) throws IOException {

        int tests = 1;

        for (int testsItr = 0; testsItr < tests; testsItr++) {
            int index = 1;
            SinglyLinkedList llist = new SinglyLinkedList();
            int[] numbers = new int[] { 1, 2, 3 };
            int llistCount = numbers.length;
            for (int i = 0; i < llistCount; i++) {
                int llistItem = numbers[i];
                llist.insertNode(llistItem);
            }

            SinglyLinkedListNode extra = new SinglyLinkedListNode(-1);
            SinglyLinkedListNode temp = llist.head;

            for (int i = 0; i < llistCount; i++) {
                if (i == index) {
                    extra = temp;
                }

                if (i != llistCount - 1) {
                    temp = temp.next;
                }
            }

            temp.next = extra;

            boolean result = hasCycle(llist.head);

            System.out.println(String.valueOf(result ? 1 : 0));
        }

    }
}
