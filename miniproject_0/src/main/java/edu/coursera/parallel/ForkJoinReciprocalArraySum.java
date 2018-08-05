package edu.coursera.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinReciprocalArraySum {
    public static double seqArraySum(double[] x) {
        long startTime = System.nanoTime();
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += 1 / x[i];
        }
        long timeNanos = System.nanoTime() - startTime;
        printResults("seqArraySum", timeNanos, sum);
        return sum;
    }

    public static double parArraySum(double[] x) {
        long startTime = System.nanoTime();
        double sum = 0;
        SumArray t = new SumArray(x, 0, x.length);
        ForkJoinPool.commonPool().invoke(t);
        sum = t.ans;
        long timeNanos = System.nanoTime() - startTime;
        printResults("seqArraySum", timeNanos, sum);
        return sum;
    }

    private static void printResults(String name, long timeInNanos, double sum) {
        System.out.printf("  %s is completed is %8.3f milliseconds, with sum = 8.5%f %n", name, timeInNanos / 1e6, sum);
    }

    private static class SumArray extends RecursiveAction {
        static int SEQUENTIAL_THRESHOLD = 1000;
        int lo, hi;
        double arr[];
        double ans;

        public SumArray(double[] arr, int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.arr = arr;
        }

        @Override
        protected void compute() {
            if (hi - lo < SEQUENTIAL_THRESHOLD) {
                for (int i = lo; i < hi; i++) {
                    ans += 1 / arr[i];
                }
            } else {
                SumArray left = new SumArray(arr, lo, (hi + lo) / 2);
                SumArray right = new SumArray(arr, (hi + lo) / 2, hi);
                left.fork();
                right.compute();
                left.join();
                ans = left.ans + right.ans;
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");

        double arr[] = new double[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random();
        }

        seqArraySum(arr);
        parArraySum(arr);
    }
}
