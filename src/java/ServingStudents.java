import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// solution for https://www.hackerrank.com/challenges/java-priority-queue/problem

public class ServingStudents {

    public class Student {

        public Student(int id, String name, double CGPA) {
            this.id = id;
            this.name = name;
            this.CGPA = CGPA;
        }

        private int id;

        private String name;

        private double CGPA;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCGPA() {
            return CGPA;
        }

        public void setCGPA(double cgpa) {
            this.CGPA = cgpa;
        }

    }

    List<Student> getStudents(List<String> events) {
        List<Student> servingOrder = new ArrayList<>();
        PriorityQueue<Student> queue = new PriorityQueue<>(
                (student1, student2) -> student1.getCGPA() == student2.getCGPA()
                        ? ((student1.getName().compareTo(student2.getName())) == 0
                                ? (student1.getId() < student2.getId() ? -1 : 1)
                                : (student1.getName().compareTo(student2.getName())))
                        : (student1.getCGPA() < student2.getCGPA() ? 1 : -1));
        for (String event : events) {
            String[] eventData = event.split(" ");
            if (eventData[0].equals("ENTER")) {
                Student s = new Student(Integer.parseInt(eventData[3]), eventData[1], Double.parseDouble(eventData[2]));
                queue.offer(s);
            } else {
                queue.poll();
            }
        }
        while (queue.size() > 0)
            servingOrder.add(queue.poll());
        return servingOrder;
    }

    private static void serveStudents(int cases, String[] inputArray, BufferedReader br) throws Exception {
        List<String> input = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            input.add(br.readLine());
            // input.add(inputArray[i]);
        }
        ServingStudents p = new ServingStudents();
        List<Student> servList = p.getStudents(input);
        if (servList.size() > 0)
            servList.stream().forEach(student -> System.out.println(student.getName()));
        else
            System.out.println("EMPTY");
    }

    public static void main(String[] args) {
        try {
            /* String[][] inputArray = new String[][] {
                    { "ENTER John 3.75 50", "ENTER Mark 3.8 24", "ENTER Shafaet 3.7 35", "SERVED", "SERVED",
                            "ENTER Samiha 3.85 36", "SERVED", "ENTER Ashley 3.9 42", "ENTER Maria 3.6 46",
                            "ENTER Anik 3.95 49", "ENTER Dan 3.95 50", "SERVED" },
                    { "ENTER Bidhan 3.75 50", "ENTER Rijul 3.8 24", "ENTER Shafaet 3.7 35", "SERVED", "SERVED",
                            "ENTER Samiha 3.85 36", "SERVED", "ENTER Ratul 3.9 42", "ENTER Tanvir 3.6 46",
                            "ENTER Anik 3.95 49", "ENTER Nisha 3.95 50", "SERVED", "SERVED", "SERVED", "SERVED",
                            "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED" },
                    { "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED",
                            "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED",
                            "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED", "SERVED",
                            "SERVED", "SERVED", "SERVED" } };
            for (String[] input : inputArray)
                serveStudents(input.length, input); */
            BufferedReader br = new BufferedReader(new
            java.io.InputStreamReader(System.in));
            int cases = Integer.parseInt(br.readLine());
            serveStudents(cases, null, br);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
