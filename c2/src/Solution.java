import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int N;

    static int[] WT;

    static boolean DEBUG;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            N = Integer.parseInt(s[0]);

            String[] s1 = in.nextLine().split(" ");
            WT = Stream.of(s1).mapToInt(Integer::parseInt).toArray();

            String r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process() {

        // We define a function f(x, y) as the maximum number of ants that can form a stack
        // (following the stack requirements given in the problem statement),
        // in which we only consider ants from the first ant to the x-th ant,
        // inclusive, and the sum of the ants' weight is not more than y.

        int W = Arrays.stream(WT).sum();

        int[][] V = new int[N + 1][W + 1];

        for (int weight = 1; weight <= W; weight++) {
            if (WT[0] <= weight) {
                V[1][weight] = 1;
            }
        }

        for (int ant = 2; ant <= N; ant++) {
            for (int weight = 1; weight <= W; weight++) {
                int valWithoutCurrent = V[ant - 1][weight];
                if (WT[ant - 1] <= weight) {
                    int weightRemaining = Math.min(weight - WT[ant - 1], WT[ant - 1] * 6);
                    int valCurrentAndMaxForRemainingWeight = V[ant - 1][weightRemaining] + 1;
                    V[ant][weight] = Math.max(valCurrentAndMaxForRemainingWeight, valWithoutCurrent);
                } else {
                    V[ant][weight] = valWithoutCurrent;
                }
            }
        }

        if (DEBUG) {
            for (int[] rows : V) {
                for (int col : rows) {
                    System.out.format("%5d", col);
                }
                System.out.println();
            }
        }

        return V[N][W] + "";
    }


    public static void main1(String[] args) {
        DEBUG = true;

//         N = 3;
//         WT  = new int[]{8, 4, 100};

//        N = 2;
//        WT = new int[]{9, 1};

        N = 9;
        WT = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 100};

        process();
    }

}