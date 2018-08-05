package edu.coursera.parallel;

import java.util.Random;

import static edu.rice.pcdp.PCDP.finish;
import static edu.rice.pcdp.PCDP.async;

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

    public static double parArraySum(double[] x) {
        long startTime = System.nanoTime();
        sum1 = sum2 = 0;

        finish(() -> {


            async(() -> {
                for (int i = 0; i < x.length / 2; i++) {
                    sum1 += x[i];
                }
            });

            for (int i = x.length / 2; i < x.length; i++) {
                sum2 += x[i];
            }

        });

        double sum = sum1 + sum2;
        long timeNanos = System.nanoTime() - startTime;
        printResults("parArraySum", timeNanos, sum);
        return sum;
    }

    private static void printResults(String name, long timeInNanos, double sum) {
        System.out.printf("  %s is completed is %8.3f milliseconds, with sum = 8.5%f %n", name, timeInNanos / 1e6, sum);
    }

    public static void main(String[] args) {
        double arr[] = new double[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random();
        }

        seqArraySum(arr);
        parArraySum(arr);
    }
}
