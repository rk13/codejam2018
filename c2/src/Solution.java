import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int N;

    static int[] WT;


    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            N = Integer.parseInt(s[0]);

            String[] s1 = in.nextLine().split(" ");
            WT = Stream.of(s1).mapToInt(Integer::parseInt).toArray();
//
//            F = new int[Y][];
//            for (int j = 0; j < Y; j++) {
//                String s2 = in.nextLine();
//                F[j] = s2.chars().map(x -> (char) x == '*' ? 1 : 0).toArray();
//            }

            String r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process() {

        int W = WT[N - 1] * 6;

        long[] v = new long[N];

        for (int i = WT.length - 1; i >= 0; i--) {
            v[i] = WT[i] * 6;
        }

        int[][] V = new int[N + 1][W + 1];
        int[][] X = new int[N + 1][W + 1];

        for (int i = 1; i <= W; i++) {
            if (i >= WT[0]) {
                V[1][i] = 1;
                X[1][i] = WT[0];
            } else {
                V[1][i] = 0;
                X[1][i] = 0;
            }
        }

        for (int ant = 2; ant <= N; ant++) {
            for (int weight = 1; weight <= W; weight++) {
                boolean done = false;
                if (WT[ant - 1] <= weight) {
                    int wremaining = weight - WT[ant - 1];

                    if (X[ant - 1][wremaining] <= v[ant - 1]) {
                        int valCurrentAndMaxForRemainingWeight = 1 + V[ant - 1][wremaining];
                        int valWithoutCurrent = V[ant - 1][weight];

                        if (valCurrentAndMaxForRemainingWeight > valWithoutCurrent) {
                            V[ant][weight] = valCurrentAndMaxForRemainingWeight;
                            X[ant][weight] = X[ant - 1][wremaining] + WT[ant - 1];
                            done = true;
                        }
                    }
                }

                if (!done) {
                    V[ant][weight] = V[ant - 1][weight];
                    X[ant][weight] = X[ant - 1][weight];
                }
            }
        }

//////        //Printing the matrix
//        for (int[] rows : V) {
//            for (int col : rows) {
//                System.out.format("%5d", col);
//            }
//            System.out.println();
//        }
//
//
//        System.out.println();
//
//        for (int[] rows : X) {
//            for (int col : rows) {
//                System.out.format("%5d", col);
//            }
//            System.out.println();
//        }

//        int max = Integer.MIN_VALUE;
//        for (int i = 1; i <= W; i++) {
//            max = Math.max(max, V[N][i]);
//        }
        return V[N][W] + "";

    }


    public static void main1(String[] args) {
//        // N = 3;
//        // WT  = new int[]{8, 4, 100};
//        N = 2;
//        WT = new int[]{9, 1};

        N = 9;
        WT = new int[]{10, 10, 10, 10, 10, 5, 5, 5, 100};

        process();
    }

}