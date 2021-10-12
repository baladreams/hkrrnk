
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// solution for https://www.hackerrank.com/challenges/java-annotations/problem

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface FamilyBudget {
    String userRole() default "GUEST";// ~~Complete the interface~~

    int budgetLimit() default 0;
}

class FamilyMember {
    // ~~Complete this line~~
    @FamilyBudget(userRole = "SENIOR", budgetLimit = 100)
    public void seniorMember(int budget, int moneySpend) {
        System.out.println("Senior Member");
        System.out.println("Spend: " + moneySpend);
        System.out.println("Budget Left: " + (budget - moneySpend));
    }

    // ~~Complete this line~~
    @FamilyBudget(userRole = "JUNIOR", budgetLimit = 50)
    public void juniorUser(int budget, int moneySpend) {
        System.out.println("Junior Member");
        System.out.println("Spend: " + moneySpend);
        System.out.println("Budget Left: " + (budget - moneySpend));
    }
}

public class Annotations {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = 3; // Integer.parseInt(in.nextLine());
        String[] inputRoles = new String[] { "SENIOR", "JUNIOR", "SENIOR" };
        int[] inputSpends = new int[] { 75, 45, 40 };
        while (testCases > 0) {
            String role = inputRoles[inputRoles.length - testCases];// in.next();
            int spend = inputSpends[inputRoles.length - testCases];// in.nextInt();
            try {
                Class annotatedClass = FamilyMember.class;
                Method[] methods = annotatedClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(FamilyBudget.class)) {
                        FamilyBudget family = method.getAnnotation(FamilyBudget.class);
                        String userRole = family.userRole();
                        int budgetLimit = family.budgetLimit();// ~~Complete this line~~;
                        if (userRole.equals(role)) {
                            if (budgetLimit >= spend) {
                                method.invoke(FamilyMember.class.newInstance(), budgetLimit, spend);
                            } else {
                                System.out.println("Budget Limit Over");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            testCases--;
        }
    }
}
