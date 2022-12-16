import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// solution for https://www.hackerrank.com/challenges/making-candies/problem

public class MakingCandies {
    public static long minimumPasses(long machines, long workers, long price, long target) {
        // Write your code here
        long passes = 0;
        MathContext twoUp = new MathContext(20, RoundingMode.UP);
        BigDecimal bigPrice = BigDecimal.valueOf(price);
        BigDecimal bigTarget = BigDecimal.valueOf(target);
        BigDecimal candiesProduced = BigDecimal.ZERO;
        boolean growthPhase = price < target ? true : false;
        while ((candiesProduced.compareTo(bigTarget) < 0) && growthPhase) {
            // passes++;
            // candies that can be produced in current pass
            BigDecimal production = BigDecimal.valueOf(machines).multiply(BigDecimal.valueOf(workers));
            long passesToNextPossiblePurchase = Math
                    .max((long) Math.ceil(bigPrice.subtract(candiesProduced).divide(production, twoUp).doubleValue()),
                            1);
            passes += passesToNextPossiblePurchase;
            // total candies till current pass
            // candiesProduced += production;
            candiesProduced = candiesProduced
                    .add(production.multiply(BigDecimal.valueOf(passesToNextPossiblePurchase)));
            long newMachines = machines;
            long newWorkers = workers;
            BigDecimal budget = candiesProduced;
            // spend current candies as much as possible to grow
            if (budget.compareTo(bigPrice) >= 0) {
                long imbalance = newMachines - newWorkers;
                long purchases = budget.divide(bigPrice, twoUp).longValue();
                budget = budget.remainder(bigPrice);
                if (imbalance < 0)
                    newMachines += Math.min(-imbalance, purchases);
                else
                    newWorkers += Math.min(imbalance, purchases);
                purchases = Math.max(0, purchases - Math.abs(imbalance));
                newWorkers += Math.ceil(purchases / 2d);
                newMachines += Math.floor(purchases / 2d);
            }
            BigDecimal newProduction = BigDecimal.valueOf(newMachines).multiply(BigDecimal.valueOf(newWorkers));
            // verify if spending candies during current pass
            // will reduce the number of passes in the future
            if (bigTarget.subtract(budget).divide(newProduction, twoUp)
                    .compareTo(bigTarget.subtract(candiesProduced).divide(production, twoUp)) < 0) {
                report("Pass " + passes + " spent " + (candiesProduced.subtract(budget).longValue()) + " acquired "
                        + (newMachines - machines) + " machines and " + (newWorkers - workers) + " workers" + " now "
                        + newMachines + " machines and " + newWorkers + " workers and balance " + budget);
                candiesProduced = budget;
                machines = newMachines;
                workers = newWorkers;
            } else if (budget.compareTo(candiesProduced) != 0) {
                report("Pass " + passes + " hit max acquisition potential at candies " + candiesProduced
                        + " machines " + machines + " and workers " + workers);
                growthPhase = false;
            } else {
                report("Pass " + passes + " candies produced " + candiesProduced + " with " + machines
                        + " machines and " + workers + " workers");
            }
        }
        // add number of passes needed to reach
        // target at current production capacity
        BigDecimal production = BigDecimal.valueOf(machines).multiply(BigDecimal.valueOf(workers));
        if (bigTarget.compareTo(candiesProduced) > 0)
            passes += Math.ceil(bigTarget.subtract(candiesProduced).divide(production, twoUp).doubleValue());
        return passes;
    }

    private static void report(String status) {
        // System.out.println(status);
    }

    public static void main(String[] args) {
        System.out.println(minimumPasses(1, 2, 1, 60)); // 4
        System.out.println(minimumPasses(3, 1, 2, 12)); // 3
        System.out.println(minimumPasses(1, 1, 6, 45)); // 16
        System.out.println(minimumPasses(5184889632l, 5184889632l, 20, 10000)); // 1
        System.out.println(minimumPasses(1, 1, 1000000000000l, 1000000000000l)); // 1000000000000
        System.out.println(minimumPasses(123456789012l, 215987654321l, 50000000000l,
                1000000000000l)); // 1
        System.out.println(minimumPasses(4294967297l, 4294967297l, 1000000000000l, 1000000000000l)); // 1
        System.out.println(minimumPasses(5, 1, 172, 364)); // 72
        System.out.println(minimumPasses(1, 100, 10000000000l, 1000000000000l)); // 617737754
    }
}
