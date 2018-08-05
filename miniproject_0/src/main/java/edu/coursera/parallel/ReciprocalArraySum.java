package edu.coursera.parallel;

public class ReciprocalArraySum {

    static double sum1, sum2;

    public static double seqArraySum(double[] x) {
        long startTime = System.nanoTime();
        sum1 = sum2 = 0;

        for (int i = 0; i < x.length / 2; i++) {
            sum1 += x[i];
        }
        for (int i = x.length / 2; i < x.length; i++) {
            sum2 += x[i];
        }
        double sum = sum1 + sum2;
        long timeNanos = System.nanoTime() - startTime;
        printResults("seqArraySum", timeNanos, sum);
        return sum;
    }



    private static void printResults(String name, long timeInNanos, double sum){
        System.out.printf("  %s is completed is %8.3f milliseconds, with sum = 8.5%f %n", name, timeInNanos/1e6, sum);
    }
}
