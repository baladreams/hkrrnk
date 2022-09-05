import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// solution for https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem

public class FAN {
    public static int activityNotifications(List<Integer> expenditure, int d) {
        int alerts = 0;
        int startIndex = 0;
        List<Integer> window = new ArrayList<>();
        window.add(expenditure.get(0));
        for (int i = 1; i < expenditure.size(); i++) {
            if (i - startIndex > d) {
                int insertionPoint = Collections.binarySearch(window, expenditure.get(startIndex));
                window.remove(insertionPoint);
                startIndex++;
            }
            double median = 0;
            if (window.size() >= d) {
                if (d % 2 == 0)
                    median = (window.get(d / 2 - 1) + window.get(d / 2)) / 2d;
                else
                    median = window.get(d / 2);
                BigDecimal accMedian = BigDecimal.valueOf(median);
                accMedian = accMedian.setScale(1, RoundingMode.HALF_DOWN);
                if (expenditure.get(i) >= 2 * accMedian.doubleValue())
                    alerts++;
            }
            int insertionPoint = Collections.binarySearch(window, expenditure.get(i));
            if (insertionPoint > window.size())
                window.add(expenditure.get(i));
            else if (insertionPoint >= 0)
                window.add(insertionPoint, expenditure.get(i));
            else
                window.add(-insertionPoint - 1, expenditure.get(i));
        }
        return alerts;
    }

    public static void main(String[] args) {
        System.out.println(activityNotifications(Arrays.asList(new Integer[] { 10, 20, 30, 40, 50 }), 3));
        System.out.println(activityNotifications(Arrays.asList(new Integer[] { 2, 3, 4, 2, 3, 6, 8, 4, 5 }), 5));
        System.out.println(activityNotifications(Arrays.asList(new Integer[] { 1, 2, 3, 4, 4 }), 4));
    }
}
