import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Solution {

    public static final double EPS = 0.0000001;

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int N;
    static int P;

    static double[][] F;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            N = Integer.parseInt(s[0]);
            P = Integer.parseInt(s[1]);

            F = new double[N][];
            for (int j = 0; j < N; j++) {
                String[] s1 = in.nextLine().split(" ");
                F[j] = Stream.of(s1).mapToDouble(Double::parseDouble).toArray();
            }

            double r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }


    static MyPoint[] apoints;
    static boolean[] apointsAvailable;

    public static double process() {

        double sum1 = 0;

        List<MyPoint> points = new ArrayList<>();

        for (int i = 0; i < F.length; i++) {
            sum1 += 2 * (F[i][0] + F[i][1]);
            points.add(new MyPoint(Math.min(F[i][0], F[i][1]) * 2,
                    Math.sqrt(Math.pow(F[i][0], 2) + Math.pow(F[i][1], 2)) * 2));
        }

        Collections.sort(points, Comparator.comparingDouble(o -> o.max));

        apoints = new MyPoint[points.size()];
        apointsAvailable = new boolean[points.size()];

        int idx = 0;
        for (int i = points.size() - 1; i >= 0; i--) {
            apoints[idx] = points.get(i);
            apointsAvailable[idx] = true;
            idx++;
        }

        if (P - sum1 < EPS) {
            return P;
        }


        double[][] dp = knapsack(P - sum1);
        BitSet selectedItems = reconstructKnapsack(dp);

        return 0;
    }


    private static double[][] knapsack(double v) {

        int capacity = (int) v;

        double dp[][] = new double[apoints.length + 1][capacity + 1];

        for (int i = 0; i < apoints.length; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (apoints[i].min <= w) {
                    // i-th item would fit into the knapsack
                    dp[i + 1][w] = Math.max(
                            apoints[i].max + dp[i][w - (int) apoints[i].min], // use i-th item
                            dp[i][w]); // do not use i-th item
                } else {
                    // i-th item would not fit into the knapsack
                    dp[i + 1][w] = dp[i][w];
                }
            }
        }
        return dp;
    }

    /** Returns the set of items selected into a solved knapsack. */
    private static BitSet reconstructKnapsack(double[][] dp) {
        int itemCount = dp.length - 1;
        int maxWeight = dp[itemCount].length - 1;
        BitSet selectedItems = new BitSet(itemCount);
        while (itemCount > 0) {
            if (dp[itemCount][maxWeight] > dp[itemCount - 1][maxWeight]) {
                selectedItems.set(itemCount - 1);
                maxWeight -= apoints[itemCount - 1].min;
            }
            itemCount--;
        }
        return selectedItems;
    }

    private static double next(double sum) {
        double result = sum;

        boolean flag = false;
        for (int i = 0; i < apoints.length; i++) {
            if (apointsAvailable[i]) {
                MyPoint point = apoints[i];
                if (sum - point.max > EPS) {
                    flag = true;

                    apointsAvailable[i] = false;
                    double tmp = next(sum - point.max);
                    apointsAvailable[i] = true;

                    if (tmp < result) {
                        result = tmp;
                    }
                } else {
                    break;
                }
            }
        }

        if (!flag) {
            for (int i = 0; i < apoints.length; i++) {
                if (apointsAvailable[i]) {
                    MyPoint point = apoints[i];
                    if (sum >= point.min && sum <= point.max) {
                        return 0.0;
                    }
                }
            }
        }
        return result;
    }

    static class MyPoint {
        double min, max;

        MyPoint(double x, double y) {
            this.min = x;
            this.max = y;
        }
    }
}